package com.github.jcustenborder.kafka.connect.github;

import com.google.inject.servlet.ServletModule;

class GitHubWebHookModule extends ServletModule {
  final GitHubWebhookSourceConnectorConfig config;

  GitHubWebHookModule(GitHubWebhookSourceConnectorConfig config) {
    this.config = config;
  }

  @Override
  protected void configureServlets() {
    bind(GitHubWebhookSourceConnectorConfig.class).toInstance(this.config);
    serve("/webhook").with(WebHookServlet.class);
  }
}
