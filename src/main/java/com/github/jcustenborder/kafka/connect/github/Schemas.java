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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Schemas {
  static final Logger log = LoggerFactory.getLogger(Schemas.class);
  static final String EVENT_TYPE_PARAMETER = "github.event.type";

  static final Schema LINK_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Link", Schema.OPTIONAL_INT64_SCHEMA)
      .field("href", Schema.OPTIONAL_STRING_SCHEMA)
      .build();
  static final Schema LINKS_MAP_SCHEMA = SchemaBuilder.map(Schema.STRING_SCHEMA, LINK_SCHEMA).optional().build();

  static SchemaBuilder eventBuilder(String name, String eventType) {
    return SchemaBuilder.struct()
        .optional()
        .name(name)
        .parameter(EVENT_TYPE_PARAMETER, eventType)
        .field("sender", SENDER_SCHEMA);
  }

  static SchemaBuilder modelBuilder(String name, Schema idSchema) {
    return SchemaBuilder.struct()
        .optional()
        .name(name)
        .field("id", idSchema)
        .field("url", Schema.OPTIONAL_STRING_SCHEMA)
        .field("_links", LINKS_MAP_SCHEMA);
  }

  static SchemaBuilder keyBuilder(Schema schema) {
    return SchemaBuilder.struct()
        .name(schema.name() + "Key");
  }

  static final Schema USER_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.User", Schema.OPTIONAL_INT64_SCHEMA)
      .field("login", Schema.OPTIONAL_STRING_SCHEMA)
      .field("name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("email", Schema.OPTIONAL_STRING_SCHEMA)
      .field("date", Timestamp.builder().optional().build())
      .field("avatar_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("gravatar_id", Schema.OPTIONAL_STRING_SCHEMA)
      .field("html_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("followers_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("following_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("gists_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("starred_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("subscriptions_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("organizations_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("repos_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("events_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("received_events_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("type", Schema.OPTIONAL_STRING_SCHEMA)
      .field("site_admin", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .build();

  static final Schema COMMENT_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Comment", Schema.OPTIONAL_INT64_SCHEMA)
      .field("html_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("user", USER_SCHEMA)
      .field("position", Schema.OPTIONAL_INT64_SCHEMA)
      .field("line", Schema.OPTIONAL_INT64_SCHEMA)
      .field("path", Schema.OPTIONAL_STRING_SCHEMA)
      .field("commit_id", Schema.OPTIONAL_STRING_SCHEMA)
      .field("created_at", Timestamp.builder().optional().build())
      .field("updated_at", Timestamp.builder().optional().build())
      .field("body", Schema.OPTIONAL_STRING_SCHEMA)
      .field("diff_hunk", Schema.OPTIONAL_STRING_SCHEMA)
      .field("original_position", Schema.OPTIONAL_INT64_SCHEMA)
      .field("original_commit_id", Schema.OPTIONAL_STRING_SCHEMA)
      .field("pull_request_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("email", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final Schema REPOSITORY_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Repository", Schema.OPTIONAL_INT64_SCHEMA)
      .field("name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("full_name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("owner", USER_SCHEMA)
      .field("private", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("html_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("description", Schema.OPTIONAL_STRING_SCHEMA)
      .field("fork", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("forks_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("keys_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("collaborators_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("teams_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("hooks_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("issue_events_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("events_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("assignees_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("branches_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("tags_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("blobs_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("git_tags_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("git_refs_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("trees_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("statuses_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("languages_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("stargazers_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("contributors_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("subscribers_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("subscription_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("commits_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("git_commits_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("comments_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("issue_comment_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("contents_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("compare_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("merges_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("archive_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("downloads_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("deployments_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("issues_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("pulls_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("milestones_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("notifications_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("labels_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("releases_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("created_at", Timestamp.builder().optional().build())
      .field("updated_at", Timestamp.builder().optional().build())
      .field("pushed_at", Timestamp.builder().optional().build())
      .field("git_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("ssh_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("clone_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("svn_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("homepage", Schema.OPTIONAL_STRING_SCHEMA)
      .field("size", Schema.OPTIONAL_INT64_SCHEMA)
      .field("stargazers_count", Schema.OPTIONAL_INT64_SCHEMA)
      .field("watchers_count", Schema.OPTIONAL_INT64_SCHEMA)
      .field("language", Schema.OPTIONAL_STRING_SCHEMA)
      .field("has_issues", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("has_downloads", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("has_wiki", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("has_pages", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("has_projects", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("forks_count", Schema.OPTIONAL_INT64_SCHEMA)
      .field("mirror_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("open_issues_count", Schema.OPTIONAL_INT64_SCHEMA)
      .field("forks", Schema.OPTIONAL_INT64_SCHEMA)
      .field("open_issues", Schema.OPTIONAL_INT64_SCHEMA)
      .field("watchers", Schema.OPTIONAL_INT64_SCHEMA)
      .field("default_branch", Schema.OPTIONAL_STRING_SCHEMA)
      .field("public", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .build();

  static final Schema SENDER_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Sender", Schema.OPTIONAL_INT64_SCHEMA)
      .field("login", Schema.OPTIONAL_STRING_SCHEMA)
      .field("email", Schema.OPTIONAL_STRING_SCHEMA)
      .field("avatar_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("gravatar_id", Schema.OPTIONAL_STRING_SCHEMA)
      .field("html_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("followers_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("following_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("gists_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("starred_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("subscriptions_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("organizations_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("repos_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("events_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("received_events_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("type", Schema.OPTIONAL_STRING_SCHEMA)
      .field("site_admin", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .build();

  static final Schema COMMIT_COMMENT_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.CommitCommentEvent", "commit_comment")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("comment", COMMENT_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .field("sender", SENDER_SCHEMA)
      .build();

  static final Schema COMMIT_COMMENT_EVENT_KEY_SCHEMA = keyBuilder(COMMIT_COMMENT_EVENT_SCHEMA)
      .field("commit_id", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final EventProcessor COMMIT_COMMENT_PROCESSOR = new EventProcessor(
      "commit_comment",
      COMMIT_COMMENT_EVENT_KEY_SCHEMA,
      COMMIT_COMMENT_EVENT_SCHEMA,
      (key, value) -> key.put("commit_id", value.getStruct("comment").get("commit_id")),
      value -> ((Date) value.getStruct("comment").get("updated_at")).getTime()
  );


  static final Schema CREATE_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.CreateEvent", "create")
      .field("ref", Schema.OPTIONAL_STRING_SCHEMA)
      .field("ref_type", Schema.OPTIONAL_STRING_SCHEMA)
      .field("master_branch", Schema.OPTIONAL_STRING_SCHEMA)
      .field("description", Schema.OPTIONAL_STRING_SCHEMA)
      .field("pusher_type", Schema.OPTIONAL_STRING_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema CREATE_EVENT_KEY_SCHEMA = keyBuilder(CREATE_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final EventProcessor CREATE_EVENT_PROCESSOR = new EventProcessor(
      "create",
      CREATE_EVENT_KEY_SCHEMA,
      CREATE_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name")),
      value -> null
  );

  static final Schema DELETE_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.DeleteEvent", "delete")
      .field("ref", Schema.OPTIONAL_STRING_SCHEMA)
      .field("ref_type", Schema.OPTIONAL_STRING_SCHEMA)
      .field("master_branch", Schema.OPTIONAL_STRING_SCHEMA)
      .field("description", Schema.OPTIONAL_STRING_SCHEMA)
      .field("pusher_type", Schema.OPTIONAL_STRING_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema DELETE_EVENT_KEY_SCHEMA = keyBuilder(DELETE_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final EventProcessor DELETE_EVENT_PROCESSOR = new EventProcessor(
      "delete",
      DELETE_EVENT_KEY_SCHEMA,
      DELETE_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name")),
      value -> null
  );

  static final Schema DEPLOYMENT_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Deployment", Schema.OPTIONAL_INT64_SCHEMA)
      .field("sha", Schema.OPTIONAL_STRING_SCHEMA)
      .field("ref", Schema.OPTIONAL_STRING_SCHEMA)
      .field("task", Schema.OPTIONAL_STRING_SCHEMA)
      .field("payload", SchemaBuilder.map(Schema.STRING_SCHEMA, Schema.STRING_SCHEMA).build())
      .field("environment", Schema.OPTIONAL_STRING_SCHEMA)
      .field("description", Schema.OPTIONAL_STRING_SCHEMA)
      .field("creator", SENDER_SCHEMA)
      .field("created_at", Timestamp.builder().optional().build())
      .field("updated_at", Timestamp.builder().optional().build())
      .field("statuses_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("repository_url", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final Schema DEPLOYMENT_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.DeploymentEvent", "deployment")
      .field("deployment", DEPLOYMENT_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema DEPLOYMENT_EVENT_KEY_SCHEMA = keyBuilder(DEPLOYMENT_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final EventProcessor DEPLOYMENT_EVENT_PROCESSOR = new EventProcessor(
      "deployment",
      DEPLOYMENT_EVENT_KEY_SCHEMA,
      DEPLOYMENT_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name")),
      value -> null
  );

  static final Schema DEPLOYMENT_STATUS_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.DeploymentStatus", Schema.OPTIONAL_INT64_SCHEMA)
      .field("state", Schema.OPTIONAL_STRING_SCHEMA)
      .field("creator", SENDER_SCHEMA)
      .field("description", Schema.OPTIONAL_STRING_SCHEMA)
      .field("target_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("created_at", Timestamp.builder().optional().build())
      .field("updated_at", Timestamp.builder().optional().build())
      .field("deployment_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("repository_url", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final Schema DEPLOYMENT_STATUS_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.DeploymentStatusEvent", "deployment_status")
      .field("deployment_status", DEPLOYMENT_STATUS_SCHEMA)
      .field("deployment", DEPLOYMENT_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema DEPLOYMENT_STATUS_EVENT_KEY_SCHEMA = keyBuilder(DEPLOYMENT_STATUS_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final EventProcessor DEPLOYMENT_STATUS_EVENT_PROCESSOR = new EventProcessor(
      "deployment_status",
      DEPLOYMENT_STATUS_EVENT_KEY_SCHEMA,
      DEPLOYMENT_STATUS_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name")),
      value -> ((Date) value.getStruct("deployment_status").get("updated_at")).getTime()
  );

  static final Schema FORK_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.ForkEvent", "fork")
      .field("forkee", REPOSITORY_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema FORK_EVENT_KEY_SCHEMA = keyBuilder(FORK_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final EventProcessor FORK_EVENT_PROCESSOR = new EventProcessor(
      "fork",
      FORK_EVENT_KEY_SCHEMA,
      FORK_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name")),
      null
  );

  static final Schema PAGE_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Page", Schema.OPTIONAL_INT64_SCHEMA)
      .field("page_name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("title", Schema.OPTIONAL_STRING_SCHEMA)
      .field("summary", Schema.OPTIONAL_STRING_SCHEMA)
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("sha", Schema.OPTIONAL_STRING_SCHEMA)
      .field("html_url", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final Schema GOLLUM_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.GollumEvent", "gollum")
      .field("pages", SchemaBuilder.array(PAGE_SCHEMA).build())
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema GOLLUM_EVENT_KEY_SCHEMA = keyBuilder(GOLLUM_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final EventProcessor GOLLUM_EVENT_PROCESSOR = new EventProcessor(
      "gollum",
      GOLLUM_EVENT_KEY_SCHEMA,
      GOLLUM_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name")),
      null
  );

  static final Schema INSTALLATION_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Installation", Schema.OPTIONAL_INT64_SCHEMA)
      .field("account", SENDER_SCHEMA)
      .field("access_tokens_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("repositories_url", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final Schema INSTALLATION_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.InstallationEvent", "installation")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("installation", INSTALLATION_SCHEMA)
      .build();

  static final Schema INSTALLATION_EVENT_KEY_SCHEMA = keyBuilder(INSTALLATION_EVENT_SCHEMA)
      .field("account", Schema.OPTIONAL_INT64_SCHEMA)
      .build();

  static final EventProcessor INSTALLATION_EVENT_PROCESSOR = new EventProcessor(
      "installation",
      INSTALLATION_EVENT_KEY_SCHEMA,
      INSTALLATION_EVENT_SCHEMA,
      (key, value) -> key.put("account", value.getStruct("installation").getStruct("account").get("id")),
      null
  );

  static final Schema INSTALLATION_REPOSITORIES_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.InstallationRepositoriesEvent", "installation_repositories")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("installation", INSTALLATION_SCHEMA)
      .field("repository_selection", Schema.OPTIONAL_STRING_SCHEMA)
      .field("repositories_added", SchemaBuilder.array(REPOSITORY_SCHEMA).build())
      .field("repositories_removed", SchemaBuilder.array(REPOSITORY_SCHEMA).build())
      .build();

  static final Schema INSTALLATION_REPOSITORIES_EVENT_KEY_SCHEMA = keyBuilder(INSTALLATION_REPOSITORIES_EVENT_SCHEMA)
      .field("account", Schema.OPTIONAL_INT64_SCHEMA)
      .build();

  static final EventProcessor INSTALLATION_REPOSITORIES_EVENT_PROCESSOR = new EventProcessor(
      "installation_repositories",
      INSTALLATION_REPOSITORIES_EVENT_KEY_SCHEMA,
      INSTALLATION_REPOSITORIES_EVENT_SCHEMA,
      (key, value) -> key.put("account", value.getStruct("installation").getStruct("account").get("id")),
      null
  );

  static final Schema LABEL_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Label", Schema.OPTIONAL_INT64_SCHEMA)
      .field("name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("color", Schema.OPTIONAL_STRING_SCHEMA)
      .field("default", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .build();

  static final Schema ISSUE_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Issue", Schema.OPTIONAL_INT64_SCHEMA)
      .field("labels_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("comments_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("events_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("html_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("number", Schema.OPTIONAL_INT64_SCHEMA)
      .field("title", Schema.OPTIONAL_STRING_SCHEMA)
      .field("user", SENDER_SCHEMA)
      .field("labels", SchemaBuilder.array(LABEL_SCHEMA).build())
      .field("state", Schema.OPTIONAL_STRING_SCHEMA)
      .field("locked", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      //TODO: Check this field.
      .field("assignee", Schema.OPTIONAL_STRING_SCHEMA)
      .field("milestone", Schema.OPTIONAL_STRING_SCHEMA)
      .field("comments", Schema.OPTIONAL_INT64_SCHEMA)
      .field("created_at", Timestamp.builder().optional().build())
      .field("updated_at", Timestamp.builder().optional().build())
      .field("closed_at", Timestamp.builder().optional().build())
      .field("body", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final Schema ISSUE_COMMENT_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.IssueComment", Schema.OPTIONAL_INT64_SCHEMA)
      .field("html_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("issue_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("user", SENDER_SCHEMA)
      .field("created_at", Timestamp.builder().optional().build())
      .field("updated_at", Timestamp.builder().optional().build())
      .field("body", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final Schema ISSUE_COMMENT_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.IssueCommentEvent", "issue_comment")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("issue", ISSUE_SCHEMA)
      .field("comment", ISSUE_COMMENT_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema ISSUE_COMMENT_EVENT_KEY_SCHEMA = keyBuilder(ISSUE_COMMENT_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .field("issue_number", Schema.OPTIONAL_INT64_SCHEMA)
      .build();

  static final EventProcessor ISSUE_COMMENT_EVENT_PROCESSOR = new EventProcessor(
      "issue_comment",
      ISSUE_COMMENT_EVENT_KEY_SCHEMA,
      ISSUE_COMMENT_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name"))
          .put("issue_number", value.getStruct("issue").get("number")),
      (value) -> ((Date)value.getStruct("comment").get("updated_at")).getTime()
  );

  static final Schema ISSUE_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.IssuesEvent", "issues")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("issue", ISSUE_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema ISSUE_EVENT_KEY_SCHEMA = keyBuilder(ISSUE_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .field("issue_number", Schema.OPTIONAL_INT64_SCHEMA)
      .build();

  static final EventProcessor ISSUE_EVENT_PROCESSOR = new EventProcessor(
      "issues",
      ISSUE_EVENT_KEY_SCHEMA,
      ISSUE_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name"))
          .put("issue_number", value.getStruct("issue").get("number")),
      (value) -> ((Date)value.getStruct("issue").get("updated_at")).getTime()
  );

  static final Schema ORGANIZATION_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Organization", Schema.OPTIONAL_INT64_SCHEMA)
      .field("login", Schema.OPTIONAL_STRING_SCHEMA)
      .field("repos_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("events_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("hooks_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("issues_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("members_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("public_members_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("avatar_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("description", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final Schema LABEL_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.LabelEvent", "label")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("label", LABEL_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .field("organization", ORGANIZATION_SCHEMA)
      .build();

  static final Schema LABEL_EVENT_KEY_SCHEMA = keyBuilder(LABEL_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .field("label_name", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final EventProcessor LABEL_EVENT_PROCESSOR = new EventProcessor(
      "label",
      LABEL_EVENT_KEY_SCHEMA,
      LABEL_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name"))
          .put("label_name", value.getStruct("label").get("name")),
      null
  );

  static final Schema ACCOUNT_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.MarketplacePurchase", Schema.OPTIONAL_INT64_SCHEMA)
      .field("type", Schema.OPTIONAL_STRING_SCHEMA)
      .field("login", Schema.OPTIONAL_STRING_SCHEMA)
      .field("organization_billing_email", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final Schema PLAN_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Plan", Schema.OPTIONAL_INT64_SCHEMA)
      .field("name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("description", Schema.OPTIONAL_STRING_SCHEMA)
      .field("monthly_price_in_cents", Schema.OPTIONAL_INT64_SCHEMA)
      .field("yearly_price_in_cents", Schema.OPTIONAL_INT64_SCHEMA)
      .field("price_model", Schema.OPTIONAL_STRING_SCHEMA)
      .field("unit_name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("bullets", SchemaBuilder.array(Schema.STRING_SCHEMA).optional().build())
      .build();

  static final Schema MARKETPLACE_PURCHASE_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.MarketplacePurchase", Schema.OPTIONAL_INT64_SCHEMA)
      .field("account", ACCOUNT_SCHEMA)
      .field("billing_cycle", Schema.OPTIONAL_STRING_SCHEMA)
      .field("next_billing_date", Timestamp.builder().optional().build())
      .field("unit_count", Schema.OPTIONAL_INT64_SCHEMA)
      .field("plan", PLAN_SCHEMA)
      .build();

  static final Schema MARKETPLACE_PURCHASE_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.MarketplacePurchaseEvent", "marketplace_purchase")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("effective_date", Timestamp.builder().optional().build())
      .field("marketplace_purchase", MARKETPLACE_PURCHASE_SCHEMA)
      .field("previous_marketplace_purchase", MARKETPLACE_PURCHASE_SCHEMA)
      .build();

  static final Schema MARKETPLACE_PURCHASE_KEY_SCHEMA = keyBuilder(MARKETPLACE_PURCHASE_EVENT_SCHEMA)
      .field("account_id", Schema.OPTIONAL_INT64_SCHEMA)
      .build();

  static final EventProcessor MARKETPLACE_PURCHASE_PROCESSOR = new EventProcessor(
      "marketplace_purchase",
      MARKETPLACE_PURCHASE_KEY_SCHEMA,
      MARKETPLACE_PURCHASE_EVENT_SCHEMA,
      (key, value) -> key.put("account_id", value.getStruct("marketplace_purchase").getStruct("account").get("id")),
      value -> ((Date)value.get("effective_date")).getTime()
  );

  static final Schema MEMBER_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.MemberEvent", "member")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("member", USER_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema MEMBER_EVENT_KEY_SCHEMA = keyBuilder(MEMBER_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final EventProcessor MEMBER_EVENT_PURCHASE_PROCESSOR = new EventProcessor(
      "member",
      MEMBER_EVENT_KEY_SCHEMA,
      MEMBER_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name")),
      null
  );

  static final Schema TEAM_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Team", Schema.OPTIONAL_INT64_SCHEMA)
      .field("name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("slug", Schema.OPTIONAL_STRING_SCHEMA)
      .field("description", Schema.OPTIONAL_STRING_SCHEMA)
      .field("privacy", Schema.OPTIONAL_STRING_SCHEMA)
      .field("permission", Schema.OPTIONAL_STRING_SCHEMA)
      .field("members_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("repositories_url", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final Schema MEMBERSHIP_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.MembershipEvent", "membership")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("scope", Schema.OPTIONAL_STRING_SCHEMA)
      .field("member", USER_SCHEMA)
      .field("team", TEAM_SCHEMA)
      .field("organization", ORGANIZATION_SCHEMA)
      .build();

  static final Schema MEMBERSHIP_EVENT_KEY_SCHEMA = keyBuilder(MEMBERSHIP_EVENT_SCHEMA)
      .field("team_id", Schema.OPTIONAL_INT64_SCHEMA)
      .build();

  static final EventProcessor MEMBERSHIP_EVENT_PURCHASE_PROCESSOR = new EventProcessor(
      "membership",
      MEMBERSHIP_EVENT_KEY_SCHEMA,
      MEMBERSHIP_EVENT_SCHEMA,
      (key, value) -> key.put("team_id", value.getStruct("team").get("id")),
      null
  );

  static final Schema MILESTONE_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Milestone", Schema.OPTIONAL_INT64_SCHEMA)
      .field("html_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("labels_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("number", Schema.OPTIONAL_INT64_SCHEMA)
      .field("title", Schema.OPTIONAL_STRING_SCHEMA)
      .field("description", Schema.OPTIONAL_STRING_SCHEMA)
      .field("creator", USER_SCHEMA)
      .field("open_issues", Schema.OPTIONAL_INT64_SCHEMA)
      .field("closed_issues", Schema.OPTIONAL_INT64_SCHEMA)
      .field("state", Schema.OPTIONAL_STRING_SCHEMA)
      .field("created_at", Timestamp.builder().optional().build())
      .field("updated_at", Timestamp.builder().optional().build())
      .field("due_on", Timestamp.builder().optional().build())
      .field("closed_at", Timestamp.builder().optional().build())
      .build();

  static final Schema MILESTONE_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.MilestoneEvent", "milestone")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("milestone", MILESTONE_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .field("organization", ORGANIZATION_SCHEMA)
      .build();

  static final Schema ORG_BLOCK_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.OrganizationBlockEvent", "org_block")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("blocked_user", USER_SCHEMA)
      .field("organization", ORGANIZATION_SCHEMA)
      .build();

  static final Schema INVITATION_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Invitation", Schema.OPTIONAL_INT64_SCHEMA)
      .field("login", Schema.OPTIONAL_STRING_SCHEMA)
      .field("email", Schema.OPTIONAL_STRING_SCHEMA)
      .field("role", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final Schema MEMBERSHIP_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Membership", Schema.OPTIONAL_INT64_SCHEMA)
      .field("state", Schema.OPTIONAL_STRING_SCHEMA)
      .field("role", Schema.OPTIONAL_STRING_SCHEMA)
      .field("organization_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("user", USER_SCHEMA)
      .build();

  static final Schema ORGANIZATION_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.OrganizationEvent", "organization")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("invitation", INVITATION_SCHEMA)
      .field("membership", MEMBERSHIP_SCHEMA)
      .field("organization", ORGANIZATION_SCHEMA)
      .build();

  static final Schema ORGANIZATION_EVENT_KEY_SCHEMA = keyBuilder(ORGANIZATION_EVENT_SCHEMA)
      .field("organization_id", Schema.OPTIONAL_INT64_SCHEMA)
      .build();

  static final EventProcessor ORGANIZATION_EVENT_PROCESSOR = new EventProcessor(
      "organization",
      ORGANIZATION_EVENT_KEY_SCHEMA,
      ORGANIZATION_EVENT_SCHEMA,
      (key, value) -> key.put("organization_id", value.getStruct("organization").get("id")),
      null
  );

  static final Schema PAGE_BUILD_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.PageBuild", Schema.OPTIONAL_INT64_SCHEMA)
      .field("status", Schema.OPTIONAL_STRING_SCHEMA)
      .field("error", SchemaBuilder.map(Schema.STRING_SCHEMA, Schema.OPTIONAL_STRING_SCHEMA).optional().build())
      .field("pusher", USER_SCHEMA)
      .field("commit", Schema.OPTIONAL_STRING_SCHEMA)
      .field("duration", Schema.OPTIONAL_INT64_SCHEMA)
      .field("created_at", Timestamp.builder().optional().build())
      .field("updated_at", Timestamp.builder().optional().build())
      .build();

  static final Schema PAGE_BUILD_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.PageBuildEvent", "page_build")
      .field("id", Schema.OPTIONAL_INT64_SCHEMA)
      .field("process", PAGE_BUILD_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema PAGE_BUILD_EVENT_KEY_SCHEMA = keyBuilder(PAGE_BUILD_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final EventProcessor PAGE_BUILD_EVENT_PROCESSOR = new EventProcessor(
      "page_build",
      PAGE_BUILD_EVENT_KEY_SCHEMA,
      PAGE_BUILD_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name")),
      null
  );

  static final Schema WEBHOOK_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.WebHook", Schema.OPTIONAL_INT64_SCHEMA)
      .field("type", Schema.OPTIONAL_STRING_SCHEMA)
      .field("name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("active", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("events", SchemaBuilder.array(Schema.STRING_SCHEMA).optional().build())
      .field("config", SchemaBuilder.map(Schema.STRING_SCHEMA, Schema.STRING_SCHEMA).optional().build())
      .field("created_at", Timestamp.builder().optional().build())
      .field("updated_at", Timestamp.builder().optional().build())
      .field("test_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("ping_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("last_response", SchemaBuilder.map(Schema.STRING_SCHEMA, Schema.OPTIONAL_STRING_SCHEMA).optional().build())
      .build();

  static final Schema PING_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.PingEvent", "ping")
      .field("zen", Schema.OPTIONAL_STRING_SCHEMA)
      .field("hook_id", Schema.OPTIONAL_INT64_SCHEMA)
      .field("hook", WEBHOOK_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema PING_EVENT_KEY_SCHEMA = keyBuilder(PING_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final EventProcessor PING_EVENT_PROCESSOR = new EventProcessor(
      "ping",
      PING_EVENT_KEY_SCHEMA,
      PING_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name")),
      null
  );

  static final Schema PROJECT_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Project", Schema.OPTIONAL_INT64_SCHEMA)
      .field("owner_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("columns_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("body", Schema.OPTIONAL_STRING_SCHEMA)
      .field("number", Schema.OPTIONAL_INT64_SCHEMA)
      .field("state", Schema.OPTIONAL_STRING_SCHEMA)
      .field("creator", USER_SCHEMA)
      .field("created_at", Timestamp.builder().optional().build())
      .field("updated_at", Timestamp.builder().optional().build())
      .field("has_projects", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .build();

  static final Schema PROJECT_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.ProjectEvent", "project")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("project", PROJECT_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .field("organization", ORGANIZATION_SCHEMA)
      .build();

  static final Schema PROJECT_EVENT_KEY_SCHEMA = keyBuilder(PROJECT_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final EventProcessor PROJECT_EVENT_PROCESSOR = new EventProcessor(
      "project",
      PROJECT_EVENT_KEY_SCHEMA,
      PROJECT_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name")),
      null
  );
  
  static final Schema PUBLIC_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.PublicEvent", "public")
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema PUBLIC_EVENT_KEY_SCHEMA = keyBuilder(PUBLIC_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final EventProcessor PUBLIC_EVENT_PROCESSOR = new EventProcessor(
      "public",
      PUBLIC_EVENT_KEY_SCHEMA,
      PUBLIC_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name")),
      null
  );

  static final Schema WATCH_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.WatchEvent", "watch")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema TEAM_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.TeamEvent", "team")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("team", TEAM_SCHEMA)
      .field("organization", ORGANIZATION_SCHEMA)
      .build();

  static final Schema TEAM_ADD_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.TeamAddEvent", "team_add")
      .field("team", TEAM_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .field("organization", ORGANIZATION_SCHEMA)
      .build();

  static final Schema AUTHOR_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Author", Schema.OPTIONAL_INT64_SCHEMA)
      .field("name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("email", Schema.OPTIONAL_STRING_SCHEMA)
      .field("username", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final Schema TREE_SCHEMA = SchemaBuilder.struct()
      .name("com.github.jcustenborder.kafka.connect.github.Tree")
      .optional()
      .field("sha", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final Schema COMMIT_DETAILS_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.CommitDetails", Schema.OPTIONAL_INT64_SCHEMA)
      .field("author", AUTHOR_SCHEMA)
      .field("committer", AUTHOR_SCHEMA)
      .field("message", Schema.OPTIONAL_STRING_SCHEMA)
      .field("tree", TREE_SCHEMA)
      .field("comment_count", Schema.OPTIONAL_INT64_SCHEMA)
      .build();

  static final Schema COMMIT_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Commit", Schema.OPTIONAL_STRING_SCHEMA)
      .field("sha", Schema.OPTIONAL_STRING_SCHEMA)
      .field("html_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("comments_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("author", USER_SCHEMA)
      .field("committer", USER_SCHEMA)
      //TODO: Figure out parents
      .build();

  static final Schema BRANCH_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Branch", Schema.OPTIONAL_INT64_SCHEMA)
      .field("master", Schema.OPTIONAL_STRING_SCHEMA)
      .field("name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("commit", COMMIT_SCHEMA)
      .build();

  static final Schema STATUS_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.StatusEvent", "status")
      .field("id", Schema.OPTIONAL_INT64_SCHEMA)
      .field("sha", Schema.OPTIONAL_STRING_SCHEMA)
      .field("name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("target_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("context", Schema.OPTIONAL_STRING_SCHEMA)
      .field("description", Schema.OPTIONAL_STRING_SCHEMA)
      .field("state", Schema.OPTIONAL_STRING_SCHEMA)
      .field("commit", COMMIT_SCHEMA)
      .field("branches", SchemaBuilder.array(BRANCH_SCHEMA).optional().build())
      .field("created_at", Timestamp.builder().optional().build())
      .field("updated_at", Timestamp.builder().optional().build())
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema REPOSITORY_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.RepositoryEvent", "repository")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .field("organization", ORGANIZATION_SCHEMA)
      .build();

  static final Schema RELEASE_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.Release", Schema.OPTIONAL_INT64_SCHEMA)
      .field("assets_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("upload_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("html_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("tag_name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("target_commitish", Schema.OPTIONAL_STRING_SCHEMA)
      .field("name", Schema.OPTIONAL_STRING_SCHEMA)
      .field("draft", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("author", USER_SCHEMA)
      .field("prerelease", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("created_at", Timestamp.builder().optional().build())
      .field("updated_at", Timestamp.builder().optional().build())
      .field("published_at", Timestamp.builder().optional().build())
      .field("assets", SchemaBuilder.array(Schema.STRING_SCHEMA).optional().build())
      .field("tarball_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("zipball_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("body", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final Schema RELEASE_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.ReleaseEvent", "release")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("release", RELEASE_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema COMMIT_REF_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.PullRequest", Schema.OPTIONAL_STRING_SCHEMA)
      .field("label", Schema.OPTIONAL_STRING_SCHEMA)
      .field("ref", Schema.OPTIONAL_STRING_SCHEMA)
      .field("sha", Schema.OPTIONAL_STRING_SCHEMA)
      .field("user", USER_SCHEMA)
      .field("repo", REPOSITORY_SCHEMA)
      .build();

  static final Schema PULL_REQUEST_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.PullRequest", Schema.OPTIONAL_INT64_SCHEMA)
      .field("html_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("href", Schema.OPTIONAL_STRING_SCHEMA)
      .field("diff_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("patch_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("issue_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("number", Schema.OPTIONAL_INT64_SCHEMA)
      .field("state", Schema.OPTIONAL_STRING_SCHEMA)
      .field("locked", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("title", Schema.OPTIONAL_STRING_SCHEMA)
      .field("user", USER_SCHEMA)
      .field("body", Schema.OPTIONAL_STRING_SCHEMA)
      .field("created_at", Timestamp.builder().optional().build())
      .field("updated_at", Timestamp.builder().optional().build())
      .field("closed_at", Timestamp.builder().optional().build())
      .field("merged_at", Timestamp.builder().optional().build())
      .field("merge_commit_sha", Schema.OPTIONAL_STRING_SCHEMA)
      .field("assignee", USER_SCHEMA)
      .field("milestone", MILESTONE_SCHEMA)
      .field("commits_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("review_comments_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("review_comment_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("comments_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("statuses_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("head", COMMIT_REF_SCHEMA)
      .field("base", COMMIT_REF_SCHEMA)
      .field("merged", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("mergeable", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("mergeable_state", Schema.OPTIONAL_STRING_SCHEMA)
      .field("merged_by", USER_SCHEMA)
      .field("comments", Schema.OPTIONAL_INT64_SCHEMA)
      .field("review_comments", Schema.OPTIONAL_INT64_SCHEMA)
      .field("commits", Schema.OPTIONAL_INT64_SCHEMA)
      .field("additions", Schema.OPTIONAL_INT64_SCHEMA)
      .field("deletions", Schema.OPTIONAL_INT64_SCHEMA)
      .field("changed_files", Schema.OPTIONAL_INT64_SCHEMA)
      .build();

  static final Schema PULL_REQUEST_EVENT_SCHEMA = eventBuilder("com.github.jcustenborder.kafka.connect.github.PullRequestEvent", "pull_request")
      .field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("number", Schema.OPTIONAL_INT64_SCHEMA)
      .field("pull_request", PULL_REQUEST_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .field("installation", INSTALLATION_SCHEMA)
      .build();

  static final Schema PULL_REQUEST_EVENT_KEY_SCHEMA = keyBuilder(PULL_REQUEST_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final EventProcessor PULL_REQUEST_EVENT_PROCESSOR = new EventProcessor(
      "pull_request",
      PULL_REQUEST_EVENT_KEY_SCHEMA,
      PULL_REQUEST_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("pull_request").getStruct("base").getStruct("repo").get("full_name")),
      value -> ((Date)value.get("updated_at")).getTime()
  );
  

  static final Schema PULL_REQUEST_REVIEW_SCHEMA = modelBuilder("com.github.jcustenborder.kafka.connect.github.PullRequestReview", Schema.OPTIONAL_INT64_SCHEMA)
      .field("user", USER_SCHEMA)
      .field("body", Schema.OPTIONAL_STRING_SCHEMA)
      .field("submitted_at", Timestamp.builder().optional().build())
      .field("state", Schema.OPTIONAL_STRING_SCHEMA)
      .field("html_url", Schema.OPTIONAL_STRING_SCHEMA)
      .field("pull_request_url", Schema.OPTIONAL_STRING_SCHEMA)
      .build();




  static final Schema PULL_REQUEST_REVIEW_EVENT_SCHEMA = eventBuilder(
      "com.github.jcustenborder.kafka.connect.github.PullRequestReviewEvent",
      "pull_request_review"
  ).field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("review", PULL_REQUEST_REVIEW_SCHEMA)
      .field("pull_request", PULL_REQUEST_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema PULL_REQUEST_REVIEW_EVENT_KEY_SCHEMA = keyBuilder(PULL_REQUEST_REVIEW_EVENT_SCHEMA)
      .field("repository", Schema.OPTIONAL_STRING_SCHEMA)
      .field("pull_request", Schema.OPTIONAL_INT64_SCHEMA)
      .build();

  static final EventProcessor PULL_REQUEST_REVIEW_EVENT_PROCESSOR = new EventProcessor(
      "pull_request_review",
      PULL_REQUEST_REVIEW_EVENT_KEY_SCHEMA,
      PULL_REQUEST_REVIEW_EVENT_SCHEMA,
      (key, value) -> key.put("repository", value.getStruct("repository").get("full_name")).put("pull_request", value.getStruct("pull_request").get("number")),
      value -> ((Date)value.getStruct("review").get("submitted_at")).getTime()
  );

  static final Schema PULL_REQUEST_REVIEW_COMMENT_EVENT_SCHEMA = eventBuilder(
      "com.github.jcustenborder.kafka.connect.github.PullRequestReviewCommentEvent",
      "pull_request_review_comment"
  ).field("action", Schema.OPTIONAL_STRING_SCHEMA)
      .field("comment", COMMENT_SCHEMA)
      .field("pull_request", PULL_REQUEST_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .build();

  static final Schema PUSH_EVENT_SCHEMA = eventBuilder(
      "com.github.jcustenborder.kafka.connect.github.PushEvent",
      "push"
  )
      .field("ref", Schema.OPTIONAL_STRING_SCHEMA)
      .field("before", Schema.OPTIONAL_STRING_SCHEMA)
      .field("after", Schema.OPTIONAL_STRING_SCHEMA)
      .field("created", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("deleted", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      .field("forced", Schema.OPTIONAL_BOOLEAN_SCHEMA)
      //TODO: base_ref
      .field("compare", Schema.OPTIONAL_STRING_SCHEMA)
      .field("commits", SchemaBuilder.array(COMMIT_SCHEMA).optional().build())
      .field("head_commit", COMMIT_SCHEMA)
      .field("repository", REPOSITORY_SCHEMA)
      .field("pusher", AUTHOR_SCHEMA)
      .build();

  static final Schema GENERIC_PAYLOAD_SCHEMA = SchemaBuilder.struct()
      .optional()
      .name("com.github.jcustenborder.kafka.connect.github.GenericPayload")
      .field("body", Schema.OPTIONAL_STRING_SCHEMA)
      .build();

  static final List<Schema> SCHEMAS;

  static {
    List<Schema> schemas = new ArrayList<>();
    for (Field field : Schemas.class.getDeclaredFields()) {
      if (Schema.class.equals(field.getType())) {
        try {
          Schema schema = (Schema) field.get(null);
          log.trace("Adding schema {}", schema.name());
          schemas.add(schema);
        } catch (IllegalAccessException e) {
          log.error("Exception thrown", e);
        }
      }
    }
    log.trace("Adding {} schema(s)", schemas.size());
    SCHEMAS = ImmutableList.copyOf(schemas);
  }

  final static Map<String, EventProcessor> EVENT_PROCESSOR_LOOKUP;

  static {
    Map<String, EventProcessor> eventProcessors = new HashMap<>();
    for (Field field : Schemas.class.getDeclaredFields()) {
      if (EventProcessor.class.equals(field.getType())) {
        try {
          EventProcessor processor = (EventProcessor) field.get(null);
          log.info("Adding event processor for '{}'.", processor.event());
          eventProcessors.put(processor.event(), processor);
        } catch (IllegalAccessException e) {
          throw new IllegalStateException(e);
        }
      }
    }
    EVENT_PROCESSOR_LOOKUP = ImmutableMap.copyOf(eventProcessors);
  }

}
