/**
 * Copyright © 2017 Jeremy Custenborder (jcustenborder@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jcustenborder.kafka.connect.github;

import com.github.jcustenborder.kafka.connect.utils.VersionUtil;
import com.github.jcustenborder.kafka.connect.servlet.BaseWebHookTask;
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
