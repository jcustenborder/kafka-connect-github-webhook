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
import org.apache.kafka.connect.data.Timestamp;

import java.util.Date;

public class Comment implements Structable {
  @JsonProperty("url")
  String url;
  @JsonProperty("html_url")
  String htmlUrl;
  @JsonProperty("id")
  Long id;
  @JsonProperty("position")
  Long position;
  @JsonProperty("line")
  Long line;
  @JsonProperty("path")
  Long path;
  @JsonProperty("commit_id")
  String commitId;
  @JsonProperty("created_at")
  Date createdAt;
  @JsonProperty("updated_at")
  Date updatedAt;
  @JsonProperty("body")
  String body;
  @JsonProperty("user")
  User user;

  public User user() {
    return user;
  }

  public void user(User user) {
    this.user = user;
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

  public void htmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  public Long id() {
    return id;
  }

  public void id(Long id) {
    this.id = id;
  }

  public Long position() {
    return position;
  }

  public void position(Long position) {
    this.position = position;
  }

  public Long line() {
    return line;
  }

  public void line(Long line) {
    this.line = line;
  }

  public Long path() {
    return path;
  }

  public void path(Long path) {
    this.path = path;
  }

  public String commitId() {
    return commitId;
  }

  public void commitId(String commitId) {
    this.commitId = commitId;
  }

  public Date createdAt() {
    return createdAt;
  }

  public void createdAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date updatedAt() {
    return updatedAt;
  }

  public void updatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String body() {
    return body;
  }

  public void body(String body) {
    this.body = body;
  }

  public static final Schema KEY_SCHEMA;
  public static final Schema VALUE_SCHEMA;

  static {
    VALUE_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(Comment.class.getName())
        .field("url", SchemaBuilder.string().optional().build())
        .field("html_url", SchemaBuilder.string().optional().build())
        .field("id", SchemaBuilder.int64().optional().build())
        .field("position", SchemaBuilder.int64().optional().build())
        .field("line", SchemaBuilder.int64().optional().build())
        .field("path", SchemaBuilder.int64().optional().build())
        .field("commit_id", SchemaBuilder.string().optional().build())
        .field("created_at", Timestamp.builder().optional().build())
        .field("updated_at", Timestamp.builder().optional().build())
        .field("body", SchemaBuilder.string().optional().build())
        .field("user", User.VALUE_SCHEMA)
        .build();

    KEY_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(Comment.class.getName() + "Key")
        .field("id", SchemaBuilder.int64().optional().build())
        .build();
  }

  @Override
  public Schema keySchema() {
    return KEY_SCHEMA;
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
        .put("url", this.url)
        .put("html_url", this.htmlUrl)
        .put("id", this.id)
        .put("position", this.position)
        .put("line", this.line)
        .put("path", this.path)
        .put("commit_id", this.commitId)
        .put("created_at", this.createdAt)
        .put("updated_at", this.updatedAt)
        .put("body", this.body)
        .put("user", null != this.user ? this.user.valueStruct() : null);
  }

  @Override
  public Long timestamp() {
    return this.updatedAt.getTime();
  }
}
