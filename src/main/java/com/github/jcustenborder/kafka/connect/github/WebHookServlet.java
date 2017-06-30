/**
 * Copyright © 2017 Jeremy Custenborder (jcustenborder@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jcustenborder.kafka.connect.github;

import com.github.jcustenborder.kafka.connect.utils.data.Parser;
import com.github.jcustenborder.kafka.connect.utils.data.SourceRecordConcurrentLinkedDeque;
import com.github.jcustenborder.kafka.connect.utils.data.type.TimestampTypeParser;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.CharStreams;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.data.Timestamp;
import org.apache.kafka.connect.source.SourceRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Singleton
class WebHookServlet extends HttpServlet {
  private static final Logger log = LoggerFactory.getLogger(WebHookServlet.class);

  @Inject
  GitHubWebhookSourceConnectorConfig config;

  @Inject
  SourceRecordConcurrentLinkedDeque records;

  Parser parser;

  public WebHookServlet() {
    this.parser = new Parser();


    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

    this.parser.registerTypeParser(
        Timestamp.SCHEMA, new TimestampTypeParser(TimeZone.getTimeZone("UTC"), dateFormat)
    );
  }

  static final Map<String, Schema> SCHEMA_LOOKUP;

  static {
    Map<String, Schema> schemaMap = new HashMap<>();
    for (Schema schema : Schemas.SCHEMAS) {
      if (null == schema.parameters() || schema.parameters().isEmpty()) {
        continue;
      }
      String eventType = schema.parameters().get(Schemas.EVENT_TYPE_PARAMETER);
      if (!Strings.isNullOrEmpty(eventType)) {
        schemaMap.put(eventType, schema);
      }
    }
    SCHEMA_LOOKUP = ImmutableMap.copyOf(schemaMap);
  }

  static final String HEADER_EVENT = "X-GitHub-Event";
  static final Map<String, ?> SOURCE_PARTITION = ImmutableMap.of();
  static final Map<String, ?> SOURCE_OFFSET = ImmutableMap.of();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    final String headerGitHubEvent = request.getHeader("X-GitHub-Event");
    final String headerHubSignature = request.getHeader("X-Hub-Signature");
    final String headerGitHubDelivery = request.getHeader("X-GitHub-Delivery");

    log.trace("doPost() - X-GitHub-Event = '{}' X-Hub-Signature = '{}' X-GitHub-Delivery = '{}'",
        headerGitHubEvent,
        headerHubSignature,
        headerGitHubDelivery
    );

    if (Strings.isNullOrEmpty(headerGitHubEvent)) {
      response.setStatus(400);

      try (PrintWriter writer = response.getWriter()) {
        writer.print(
            String.format("Header '%s' must be specified.", HEADER_EVENT)
        );
      }
      return;
    }

    final String body;
    log.trace("doPost() - Reading post body to string.");
    try (InputStream inputStream = request.getInputStream()) {
      try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
        body = CharStreams.toString(inputStreamReader);
      }
    }

    if (this.config.topicRawEnable) {
      final String topic = String.format("%s.%s", this.config.topicRawPrefix, headerGitHubEvent.toLowerCase());

      log.trace("doPost() - Adding raw record to topic '{}'");
      Schema RAW_SCHEMA = SchemaBuilder.struct()
          .name("com.github.jcustenborder.kafka.connect.github.RawEvent")
          .field("event", Schema.OPTIONAL_STRING_SCHEMA)
          .field("signature", Schema.OPTIONAL_STRING_SCHEMA)
          .field("delivery", Schema.OPTIONAL_STRING_SCHEMA)
          .field("body", Schema.OPTIONAL_STRING_SCHEMA)
          .build();

      Struct valueStruct = new Struct(RAW_SCHEMA)
          .put("event", headerGitHubEvent)
          .put("signature", headerHubSignature)
          .put("delivery", headerGitHubDelivery)
          .put("body", body);

      SourceRecord record = new SourceRecord(
          SOURCE_PARTITION,
          SOURCE_OFFSET,
          topic,
          null,
          null,
          null,
          valueStruct.schema(),
          valueStruct,
          null
      );
      this.records.add(record);
    }

    EventProcessor processor = Schemas.EVENT_PROCESSOR_LOOKUP.get(headerGitHubEvent);

    if (null == processor) {
      log.warn("Event type '{}' is not supported", headerGitHubEvent);
      response.setStatus(400);
      try (PrintWriter writer = response.getWriter()) {
        writer.print(
            String.format("Event type '%s' is not supported", headerGitHubEvent)
        );
      }
      return;
    }

    String topic = String.format("%s.%s", this.config.topicRawPrefix, headerGitHubEvent.toLowerCase());

    processor.build(body);

    SourceRecord record = new SourceRecord(
        SOURCE_PARTITION,
        SOURCE_OFFSET,
        topic,
        null,
        processor.keySchema(),
        processor.keyStruct(),
        processor.valueSchema(),
        processor.valueStruct(),
        processor.timestamp()
    );
    this.records.add(record);
    response.setStatus(200);
  }
}
