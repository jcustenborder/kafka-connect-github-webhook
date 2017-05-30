package com.github.jcustenborder.kafka.connect.github;

import com.github.jcustenborder.kafka.connect.webhook.BaseWebHookConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

class GitHubWebhookSourceConnectorConfig extends BaseWebHookConnectorConfig {

  public static String TOPIC_PREFIX_CONF="topic.prefix";
  static String TOPIC_PREFIX_DOC="Prefix to append to the value from the [X-GitHub-Event](https://developer.github.com/webhooks/#delivery-headers) " +
      "header. For example if this setting is configured to `github.`, a `push` event would be written to the `github.push` topic.";
  static String TOPIC_PREFIX_DEFAULT="github.";

  public final String topicPrefix;

  public GitHubWebhookSourceConnectorConfig(Map<?, ?> originals) {
    super(config(), originals);
    this.topicPrefix = this.getString(TOPIC_PREFIX_CONF);
  }

  public static ConfigDef config() {
    return BaseWebHookConnectorConfig.config()
        .define(TOPIC_PREFIX_CONF, ConfigDef.Type.STRING, TOPIC_PREFIX_DEFAULT, ConfigDef.Importance.MEDIUM, TOPIC_PREFIX_DOC);
  }
}
