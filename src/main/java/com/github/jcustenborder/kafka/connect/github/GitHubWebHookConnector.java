package com.github.jcustenborder.kafka.connect.github;

import com.github.jcustenborder.kafka.connect.utils.VersionUtil;
import com.github.jcustenborder.kafka.connect.utils.config.Description;
import com.github.jcustenborder.kafka.connect.utils.config.DocumentationImportant;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;

import java.util.List;
import java.util.Map;

@DocumentationImportant("This connector listens on a network port. Running more than one task or running in distributed " +
    "mode can cause some undesired effects if another task already has the port open. It is recommended that you run this " +
    "connector in :term:`Standalone Mode`.")
@Description("The GitHub WebHook connector is a listening connector that receeives messages from " +
    "`GitHub Webhooks <https://developer.github.com/v3/repos/hooks/>`_ .")
public class GitHubWebHookConnector extends SourceConnector {
  @Override
  public String version() {
    return VersionUtil.version(this.getClass());
  }

  @Override
  public void start(Map<String, String> map) {

  }

  @Override
  public Class<? extends Task> taskClass() {
    return GitHubWebHookSourceTask.class;
  }

  @Override
  public List<Map<String, String>> taskConfigs(int i) {
    return null;
  }

  @Override
  public void stop() {

  }

  @Override
  public ConfigDef config() {
    return GitHubWebhookSourceConnectorConfig.config();
  }
}
