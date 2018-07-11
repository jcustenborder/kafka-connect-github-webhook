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

import com.github.jcustenborder.kafka.connect.servlet.BaseWebHookConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

class GitHubWebhookSourceConnectorConfig extends BaseWebHookConnectorConfig {

  public static final String TOPIC_PREFIX_CONF = "topic.prefix";
  static final String TOPIC_PREFIX_DOC = "Prefix to append to the value from the [X-GitHub-Event](https://developer.github.com/webhooks/#delivery-headers) " +
      "header. For example if this setting is configured to `github.`, a `push` event would be written to the `github.push` topic.";
  static final String TOPIC_PREFIX_DEFAULT = "github.";

  public static final String TOPIC_RAW_PREFIX_CONF = "topic.raw.prefix";
  static final String TOPIC_RAW_PREFIX_DOC = "Prefix to append to the value from the [X-GitHub-Event](https://developer.github.com/webhooks/#delivery-headers) " +
      "header. For example if this setting is configured to `github.raw.`, a `push` event would be written to the `github.raw.push` topic.";
  static final String TOPIC_RAW_PREFIX_DEFAULT = "github.raw.";


  public static final String TOPIC_RAW_ENABLE_CONF = "topic.raw.enable";
  static final String TOPIC_RAW_ENABLE_DOC = "Flag to determine if an unprocessed copy of the data should be written to Kafka.";


  public final String topicPrefix;
  public final String topicRawPrefix;
  public final boolean topicRawEnable;


  public GitHubWebhookSourceConnectorConfig(Map<?, ?> originals) {
    super(config(), originals);
    this.topicPrefix = this.getString(TOPIC_PREFIX_CONF);
    this.topicRawPrefix = this.getString(TOPIC_RAW_PREFIX_CONF);
    this.topicRawEnable = this.getBoolean(TOPIC_RAW_ENABLE_CONF);
  }

  public static ConfigDef config() {
    return BaseWebHookConnectorConfig.config()
        .define(TOPIC_PREFIX_CONF, ConfigDef.Type.STRING, TOPIC_PREFIX_DEFAULT, ConfigDef.Importance.MEDIUM, TOPIC_PREFIX_DOC)
        .define(TOPIC_RAW_PREFIX_CONF, ConfigDef.Type.STRING, TOPIC_RAW_PREFIX_DEFAULT, ConfigDef.Importance.MEDIUM, TOPIC_RAW_PREFIX_DEFAULT)
        .define(TOPIC_RAW_ENABLE_CONF, ConfigDef.Type.BOOLEAN, true, ConfigDef.Importance.MEDIUM, TOPIC_RAW_ENABLE_DOC);
  }
}
