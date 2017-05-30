package com.github.jcustenborder.kafka.connect.github;

import com.github.jcustenborder.kafka.connect.utils.VersionUtil;
import com.github.jcustenborder.kafka.connect.webhook.BaseWebHookTask;
import com.google.inject.servlet.ServletModule;

import java.util.Map;

public class GitHubWebHookSourceTask extends BaseWebHookTask<GitHubWebhookSourceConnectorConfig> {
  @Override
  protected GitHubWebhookSourceConnectorConfig config(Map<String, String> map) {
    return new GitHubWebhookSourceConnectorConfig(map);
  }

  @Override
  protected ServletModule servletModule() {
    return new GitHubWebHookModule(this.config);
  }

  @Override
  public String version() {
    return VersionUtil.version(this.getClass());
  }
}
