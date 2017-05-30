package com.github.jcustenborder.kafka.connect.github;

import com.github.jcustenborder.kafka.connect.github.model.CommitCommentEvent;
import com.github.jcustenborder.kafka.connect.github.model.CreateEvent;
import com.github.jcustenborder.kafka.connect.github.model.DeleteEvent;
import com.github.jcustenborder.kafka.connect.github.model.ForkEvent;
import com.github.jcustenborder.kafka.connect.github.model.PingEvent;
import com.github.jcustenborder.kafka.connect.github.model.PushEvent;
import com.github.jcustenborder.kafka.connect.github.model.Structable;
import com.github.jcustenborder.kafka.connect.github.model.WatchEvent;
import com.github.jcustenborder.kafka.connect.utils.data.SourceRecordConcurrentLinkedDeque;
import com.github.jcustenborder.kafka.connect.utils.jackson.ObjectMapperFactory;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.kafka.connect.source.SourceRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Singleton
class WebHookServlet extends HttpServlet {
  private static final Logger log = LoggerFactory.getLogger(WebHookServlet.class);
  static final Map<String, Class<? extends Structable>> TYPEMAP;

  @Inject
  GitHubWebhookSourceConnectorConfig config;

  @Inject
  SourceRecordConcurrentLinkedDeque records;

  static {
    Map<String, Class<? extends Structable>> types = new HashMap<>();
    types.put("commit_comment", CommitCommentEvent.class);
    types.put("create", CreateEvent.class);
    types.put("delete", DeleteEvent.class);
//    types.put("deployment", Deployment.class);
//    types.put("deployment_status", deployment_status.class);
    types.put("fork", ForkEvent.class);
//    types.put("gollum", gollum.class);
//    types.put("installation", installation.class);
//    types.put("installation_repositories", installation_repositories.class);
//    types.put("issue_comment", issue_comment.class);
//    types.put("issues", issues.class);
//    types.put("label", label.class);
//    types.put("marketplace_purchase", marketplace_purchase.class);
//    types.put("member", member.class);
//    types.put("membership", membership.class);
//    types.put("milestone", milestone.class);
//    types.put("org_block", org_block.class);
//    types.put("organization", organization.class);
//    types.put("page_build", page_build.class);
    types.put("ping", PingEvent.class);
//    types.put("project", project.class);
//    types.put("project_card", project_card.class);
//    types.put("project_column", project_column.class);
//    types.put("public", public.class);
//    types.put("pull_request", pull_request.class);
//    types.put("pull_request_review", pull_request_review.class);
//    types.put("pull_request_review_comment", pull_request_review_comment.class);
    types.put("push", PushEvent.class);
//    types.put("release", release.class);
//    types.put("repository", repository.class);
//    types.put("status", status.class);
//    types.put("team", team.class);
//    types.put("team_add", team_add.class);
    types.put("watch", WatchEvent.class);

    TYPEMAP = ImmutableMap.copyOf(types);
  }

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

    final Class<? extends Structable> structableClass = TYPEMAP.get(headerGitHubEvent);

    if (null == structableClass) {
      log.warn("Event type '%s' is not supported", headerGitHubEvent);
      response.setStatus(500);
      try (PrintWriter writer = response.getWriter()) {
        writer.print(
            String.format("Event type '%s' is not supported", headerGitHubEvent)
        );
      }
      return;
    }

    final Structable structable;

    try (InputStream inputStream = request.getInputStream()) {
      structable = ObjectMapperFactory.INSTANCE.readValue(inputStream, structableClass);
    }

    final String topic = String.format("%s%s", this.config.topicPrefix, headerGitHubEvent);

    Map<String, ?> SOURCE_PARTITION = ImmutableMap.of();
    Map<String, ?> SOURCE_OFFSET = ImmutableMap.of();

    SourceRecord record = new SourceRecord(
        SOURCE_PARTITION,
        SOURCE_OFFSET,
        topic,
        null,
        structable.keySchema(),
        structable.keyStruct(),
        structable.valueSchema(),
        structable.valueStruct(),
        structable.timestamp()
    );
    log.trace("doPost() - Writing record to {}", topic);
    this.records.add(record);

    response.setStatus(200);
  }
}
