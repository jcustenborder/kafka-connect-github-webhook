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
package com.github.jcustenborder.kafka.connect.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;

public class User implements Structable {
  public static final Schema VALUE_SCHEMA;
  public static final Schema KEY_SCHEMA;

  static {
    VALUE_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(User.class.getName())
        .field("login", SchemaBuilder.string().optional().build())
        .field("id", SchemaBuilder.int64().optional().build())
        .field("avatar_url", SchemaBuilder.string().optional().build())
        .field("gravatar_id", SchemaBuilder.string().optional().build())
        .field("url", SchemaBuilder.string().optional().build())
        .field("html_url", SchemaBuilder.string().optional().build())
        .field("followers_url", SchemaBuilder.string().optional().build())
        .field("following_url", SchemaBuilder.string().optional().build())
        .field("gists_url", SchemaBuilder.string().optional().build())
        .field("starred_url", SchemaBuilder.string().optional().build())
        .field("subscriptions_url", SchemaBuilder.string().optional().build())
        .field("organizations_url", SchemaBuilder.string().optional().build())
        .field("repos_url", SchemaBuilder.string().optional().build())
        .field("events_url", SchemaBuilder.string().optional().build())
        .field("received_events_url", SchemaBuilder.string().optional().build())
        .field("type", SchemaBuilder.string().optional().build())
        .field("site_admin", SchemaBuilder.bool().optional().build())
        .build();

    KEY_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(User.class.getName() + "Key")
        .field("id", SchemaBuilder.int64().optional().build())
        .build();
  }

  @JsonProperty("login")
  String login;
  @JsonProperty("id")
  Long id;
  @JsonProperty("avatar_url")
  String avatarUrl;
  @JsonProperty("gravatar_id")
  String gravatarId;
  @JsonProperty("url")
  String url;
  @JsonProperty("html_url")
  String htmlUrl;
  @JsonProperty("followers_url")
  String followersUrl;
  @JsonProperty("following_url")
  String followingUrl;
  @JsonProperty("gists_url")
  String gistsUrl;
  @JsonProperty("starred_url")
  String starredUrl;
  @JsonProperty("subscriptions_url")
  String subscriptionsUrl;
  @JsonProperty("organizations_url")
  String organizationsUrl;
  @JsonProperty("repos_url")
  String reposUrl;
  @JsonProperty("events_url")
  String eventsUrl;
  @JsonProperty("received_events_url")
  String receivedEventsUrl;
  @JsonProperty("type")
  String type;
  @JsonProperty("site_admin")
  Boolean siteAdmin;

  @JsonProperty("name")
  String name;
  @JsonProperty("email")
  String email;

  public String login() {
    return login;
  }

  public void login(String login) {
    this.login = login;
  }

  public Long id() {
    return id;
  }

  public void id(Long id) {
    this.id = id;
  }

  public String avatarUrl() {
    return avatarUrl;
  }

  public void avatarUrl(String value) {
    this.avatarUrl = value;
  }

  public String gravatarId() {
    return gravatarId;
  }

  public void gravatarId(String value) {
    this.gravatarId = value;
  }

  public String url() {
    return url;
  }

  public void url(String url) {
    this.url = url;
  }

  public String htmlUrl() {
    return htmlUrl;
  }

  public void htmlUrl(String value) {
    this.htmlUrl = value;
  }

  public String followersUrl() {
    return followersUrl;
  }

  public void followersUrl(String value) {
    this.followersUrl = value;
  }

  public String followingUrl() {
    return followingUrl;
  }

  public void followingUrl(String value) {
    this.followingUrl = value;
  }

  public String gistsUrl() {
    return gistsUrl;
  }

  public void gistsUrl(String value) {
    this.gistsUrl = value;
  }

  public String starredUrl() {
    return starredUrl;
  }

  public void starredUrl(String value) {
    this.starredUrl = value;
  }

  public String subscriptionsUrl() {
    return subscriptionsUrl;
  }

  public void subscriptionsUrl(String value) {
    this.subscriptionsUrl = value;
  }

  public String organizationsUrl() {
    return organizationsUrl;
  }

  public void organizationsUrl(String value) {
    this.organizationsUrl = value;
  }

  public String reposUrl() {
    return reposUrl;
  }

  public void reposUrl(String value) {
    this.reposUrl = value;
  }

  public String eventsUrl() {
    return eventsUrl;
  }

  public void eventsUrl(String value) {
    this.eventsUrl = value;
  }

  public String receivedEventsUrl() {
    return receivedEventsUrl;
  }

  public void receivedEventsUrl(String value) {
    this.receivedEventsUrl = value;
  }

  public String type() {
    return type;
  }

  public void type(String type) {
    this.type = type;
  }

  public Boolean siteAdmin() {
    return siteAdmin;
  }

  public void siteAdmin(Boolean value) {
    this.siteAdmin = value;
  }

  @Override
  public Schema keySchema() {
    return VALUE_SCHEMA;
  }

  @Override
  public Struct keyStruct() {
    return new Struct(KEY_SCHEMA)
        .put("id", this.id);
  }

  @Override
  public Schema valueSchema() {
    return VALUE_SCHEMA;
  }

  @Override
  public Struct valueStruct() {
    return new Struct(VALUE_SCHEMA)
        .put("login", this.login)
        .put("id", this.id)
        .put("name", this.name)
        .put("avatar_url", this.avatarUrl)
        .put("gravatar_id", this.gravatarId)
        .put("url", this.url)
        .put("html_url", this.htmlUrl)
        .put("followers_url", this.followersUrl)
        .put("following_url", this.followingUrl)
        .put("gists_url", this.gistsUrl)
        .put("starred_url", this.starredUrl)
        .put("subscriptions_url", this.subscriptionsUrl)
        .put("organizations_url", this.organizationsUrl)
        .put("repos_url", this.reposUrl)
        .put("events_url", this.eventsUrl)
        .put("received_events_url", this.receivedEventsUrl)
        .put("type", this.type)
        .put("site_admin", this.siteAdmin);
  }

  @Override
  public Long timestamp() {
    return null;
  }
}
