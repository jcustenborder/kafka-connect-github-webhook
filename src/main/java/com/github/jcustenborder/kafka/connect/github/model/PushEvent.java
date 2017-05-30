package com.github.jcustenborder.kafka.connect.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;

import java.util.List;

public class PushEvent implements Structable {
  public static final Schema VALUE_SCHEMA;
  public static final Schema KEY_SCHEMA;

  static {
    VALUE_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(PushEvent.class.getName())
        .field("ref", SchemaBuilder.string().optional().build())
        .field("before", SchemaBuilder.string().optional().build())
        .field("after", SchemaBuilder.string().optional().build())
        .field("created", SchemaBuilder.bool().optional().build())
        .field("deleted", SchemaBuilder.bool().optional().build())
        .field("forced", SchemaBuilder.bool().optional().build())
        .field("base_ref", SchemaBuilder.string().optional().build())
        .field("compare", SchemaBuilder.string().optional().build())
        .field("commits", SchemaBuilder.array(Commit.VALUE_SCHEMA).optional().build())
        .field("head_commit", Commit.VALUE_SCHEMA)
        .field("repository", Repository.VALUE_SCHEMA)
        .field("pusher", Author.VALUE_SCHEMA)
        .field("sender", User.VALUE_SCHEMA)
        .build();

    KEY_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(PushEvent.class.getName() + "Key")
        .field("headCommitId", SchemaBuilder.string().optional().build())
        .build();
  }


  @JsonProperty("ref")
  String ref;
  @JsonProperty("before")
  String before;
  @JsonProperty("after")
  String after;
  @JsonProperty("created")
  Boolean created;
  @JsonProperty("deleted")
  Boolean deleted;
  @JsonProperty("forced")
  Boolean forced;
  @JsonProperty("base_ref")
  Boolean base_ref;
  @JsonProperty("compare")
  String compare;
  @JsonProperty("commits")
  List<Commit> commits;
  @JsonProperty("head_commit")
  Commit headCommit;
  @JsonProperty("repository")
  Repository repository;
  @JsonProperty("pusher")
  Author pusher;
  @JsonProperty("sender")
  User sender;

  @Override
  public Schema keySchema() {
    return null;
  }

  @Override
  public Struct keyStruct() {
    return null;
  }

  @Override
  public Schema valueSchema() {
    return null;
  }

  @Override
  public Struct valueStruct() {
    return null;
  }

  @Override
  public Long timestamp() {
    return null;
  }
}
