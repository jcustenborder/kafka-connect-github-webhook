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

public class CommitCommentEvent implements Structable {
  @JsonProperty("action")
  String action;
  @JsonProperty("comment")
  Comment comment;
  @JsonProperty("repository")
  Repository repository;
  @JsonProperty("sender")
  User sender;

  public static final Schema VALUE_SCHEMA;
  public static final Schema KEY_SCHEMA;

  static {
    VALUE_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(CommitCommentEvent.class.getName())
        .field("action", SchemaBuilder.string().optional().build())
        .field("comment", Comment.VALUE_SCHEMA)
        .field("repository", Repository.VALUE_SCHEMA)
        .field("sender", User.VALUE_SCHEMA)
        .build();

    KEY_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(CommitCommentEvent.class.getName() + "Key")
        .field("commit_id", SchemaBuilder.string().optional().build())
        .build();
  }

  @Override
  public Schema keySchema() {
    return KEY_SCHEMA;
  }

  @Override
  public Struct keyStruct() {
    return new Struct(KEY_SCHEMA)
        .put("commit_id", this.comment.commitId);
  }

  @Override
  public Schema valueSchema() {
    return VALUE_SCHEMA;
  }

  @Override
  public Struct valueStruct() {
    return new Struct(VALUE_SCHEMA)
        .put("action", this.action)
        .put("comment", null != this.comment ? this.comment.valueStruct() : null)
        .put("repository", null != this.repository ? this.repository.valueStruct() : null)
        .put("sender", null != this.sender ? this.sender.valueStruct() : null);
  }

  @Override
  public Long timestamp() {
    return this.comment.updatedAt.getTime();
  }
}
