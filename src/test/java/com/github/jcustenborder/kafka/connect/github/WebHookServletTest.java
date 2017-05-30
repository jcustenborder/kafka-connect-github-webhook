package com.github.jcustenborder.kafka.connect.github;

import com.github.jcustenborder.kafka.connect.github.model.Structable;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WebHookServletTest {
  private static final Logger log = LoggerFactory.getLogger(WebHookServletTest.class);
  WebHookServlet servlet;


  @BeforeEach
  public void before() {
    this.servlet = new WebHookServlet();
    this.servlet.records = new SourceRecordConcurrentLinkedDeque();
    this.servlet.config = new GitHubWebhookSourceConnectorConfig(ImmutableMap.of());
  }


  @TestFactory
  public Stream<DynamicTest> doGet() {
    Reflections reflections = new Reflections(Structable.class.getPackage().getName(), new ResourcesScanner());
    Set<String> resources = reflections.getResources(new FilterBuilder.Include(".*\\.json$"));

    return resources.stream().map(testPath ->
        DynamicTest.dynamicTest(Files.getNameWithoutExtension(testPath), () -> {
          final String eventType = Files.getNameWithoutExtension(testPath);
          HttpServletRequest request = mock(HttpServletRequest.class);
          HttpServletResponse response = mock(HttpServletResponse.class);
          when(request.getHeader("X-GitHub-Event")).thenReturn(eventType);
          when(request.getHeader("X-Hub-Signature")).thenReturn("adsfasdfastadsfasdfasd");
          when(request.getHeader("X-GitHub-Delivery")).thenReturn("fngofigosnfgroisnsfdgo");
          when(response.getWriter()).thenReturn(new PrintWriter(ByteStreams.nullOutputStream()));

          final String inputResource = "/" + testPath;

          InputStream inputStream = Structable.class.getResourceAsStream(inputResource);
          ServletInputStream servletInputStream = mock(ServletInputStream.class);
          when(servletInputStream.read(any(), anyInt(), anyInt())).thenAnswer(invocationOnMock -> {
            byte[] output = invocationOnMock.getArgument(0);
            int offset = invocationOnMock.getArgument(1);
            int length = invocationOnMock.getArgument(2);
            return inputStream.read(output, offset, length);
          });
          when(request.getInputStream()).thenReturn(servletInputStream);
          this.servlet.doPost(request, response);
          verify(response, atLeastOnce()).setStatus(200);
        })
    );
  }


//  @Test
//  public void foo() throws IOException, ServletException {
//    when(this.request.getHeader("X-GitHub-Event")).thenReturn("commit_comment");
//    when(this.request.getHeader("X-Hub-Signature")).thenReturn("adsfasdfastadsfasdfasd");
//    when(this.request.getHeader("X-GitHub-Delivery")).thenReturn("fngofigosnfgroisnsfdgo");
//
//    InputStream inputStream = Structable.class.getResourceAsStream("commit_comment.json");
//    ServletInputStream servletInputStream = mock(ServletInputStream.class);
//    when(servletInputStream.read(any(), anyInt(), anyInt())).thenAnswer(invocationOnMock -> {
//      byte[] output = invocationOnMock.getArgument(0);
//      int offset = invocationOnMock.getArgument(1);
//      int length = invocationOnMock.getArgument(2);
//      return inputStream.read(output, offset, length);
//    });
//    when(request.getInputStream()).thenReturn(servletInputStream);
//    this.servlet.doPost(request, response);
//    verify(response).setStatus(200);
//  }
}
