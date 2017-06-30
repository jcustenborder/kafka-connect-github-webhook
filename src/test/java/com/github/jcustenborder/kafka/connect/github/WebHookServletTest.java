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

import com.github.jcustenborder.kafka.connect.utils.data.SourceRecordConcurrentLinkedDeque;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WebHookServletTest {
  private static final Logger log = LoggerFactory.getLogger(WebHookServletTest.class);

  @TestFactory
  public Stream<DynamicTest> doGet() {
    Reflections reflections = new Reflections(WebHookServletTest.class.getPackage().getName(), new ResourcesScanner());
    Set<String> resources = reflections.getResources(new FilterBuilder.Include(".*\\.json$"));

    return resources.stream().map(testPath ->
        DynamicTest.dynamicTest(Files.getNameWithoutExtension(testPath), () -> {
          WebHookServlet servlet = new WebHookServlet();
          servlet.records = new SourceRecordConcurrentLinkedDeque();
          servlet.config = new GitHubWebhookSourceConnectorConfig(ImmutableMap.of(GitHubWebhookSourceConnectorConfig.TOPIC_RAW_ENABLE_CONF, true));
          final String eventType = Files.getNameWithoutExtension(testPath);
          HttpServletRequest request = mock(HttpServletRequest.class);
          HttpServletResponse response = mock(HttpServletResponse.class);
          when(request.getHeader("X-GitHub-Event")).thenReturn(eventType);
          when(request.getHeader("X-Hub-Signature")).thenReturn("adsfasdfastadsfasdfasd");
          when(request.getHeader("X-GitHub-Delivery")).thenReturn("fngofigosnfgroisnsfdgo");
          when(response.getWriter()).thenReturn(new PrintWriter(ByteStreams.nullOutputStream()));

          final String inputResource = "/" + testPath;

          InputStream inputStream = WebHookServletTest.class.getResourceAsStream(inputResource);
          ServletInputStream servletInputStream = mock(ServletInputStream.class);
          when(servletInputStream.read(any(), anyInt(), anyInt())).thenAnswer(invocationOnMock -> {
            byte[] output = invocationOnMock.getArgument(0);
            int offset = invocationOnMock.getArgument(1);
            int length = invocationOnMock.getArgument(2);
            return inputStream.read(output, offset, length);
          });
          when(request.getInputStream()).thenReturn(servletInputStream);
          servlet.doPost(request, response);
          verify(response, atLeastOnce()).setStatus(200);
          assertEquals(2, servlet.records.size());

        })
    );
  }
}
