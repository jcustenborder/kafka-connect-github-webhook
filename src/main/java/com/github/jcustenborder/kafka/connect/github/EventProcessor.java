package com.github.jcustenborder.kafka.connect.github;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.jcustenborder.kafka.connect.utils.data.Parser;
import com.github.jcustenborder.kafka.connect.utils.data.type.TimestampTypeParser;
import com.github.jcustenborder.kafka.connect.utils.jackson.ObjectMapperFactory;
import com.google.common.base.Preconditions;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.data.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

class EventProcessor {
  private static final Logger log = LoggerFactory.getLogger(EventProcessor.class);
  final String event;
  final Schema keySchema;
  final Schema valueSchema;
  final KeyExtractor keyExtractor;
  final TimestampExtractor timestampExtractor;
  final Parser parser;
  Struct keyStruct;
  Struct valueStruct;
  Long timestamp;

  public EventProcessor(String event, Schema keySchema, Schema valueSchema, KeyExtractor keyExtractor, TimestampExtractor timestampExtractor) {
    this.event = event;
    this.keySchema = keySchema;
    this.valueSchema = valueSchema;
    this.keyExtractor = keyExtractor;
    this.timestampExtractor = timestampExtractor;

    Preconditions.checkNotNull(
        valueSchema.parameters(),
        "valueSchema.parameters() should not be null. Schema '%s'", valueSchema.name()
    );
    Preconditions.checkState(
        event.equals(valueSchema.parameters().get(Schemas.EVENT_TYPE_PARAMETER)),
        "event and schema should have the same event"
    );

    Preconditions.checkState(
        keySchema.name().equals(valueSchema.name() + "Key"),
        "keySchema should be '%sKey'. was '%s'", keySchema.name(), keySchema.name()
    );

    this.parser = new Parser();


    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

    this.parser.registerTypeParser(
        Timestamp.SCHEMA, new TimestampTypeParser(TimeZone.getTimeZone("UTC"), dateFormat)
    );
  }

  public Long timestamp() {
    return timestamp;
  }

  public Schema keySchema() {
    return keySchema;
  }

  public Struct keyStruct() {
    return keyStruct;
  }

  public Schema valueSchema() {
    return valueSchema;
  }

  public Struct valueStruct() {
    return valueStruct;
  }

  public String event() {
    return event;
  }

  public void build(String body) throws IOException {
    log.trace("process() - \n{}", body);
    final JsonNode input = ObjectMapperFactory.INSTANCE.readTree(body);
    this.valueStruct = (Struct) this.parser.parseJsonNode(this.valueSchema, input);

    if (null != this.timestampExtractor) {
      this.timestamp = this.timestampExtractor.process(this.valueStruct);
    }

    this.keyStruct = new Struct(this.keySchema);
    this.keyExtractor.process(this.keyStruct, this.valueStruct);
  }

}
