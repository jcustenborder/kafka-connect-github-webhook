package com.github.jcustenborder.kafka.connect.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;

public class WatchEvent implements Structable {
  @JsonProperty("action")
  String action;
  @JsonProperty("repository")
  Repository repository;
  @JsonProperty("sender")
  User sender;

  public static final Schema KEY_SCHEMA;
  public static final Schema VALUE_SCHEMA;

  static {
    KEY_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(WatchEvent.class.getName() + "Key")
        .field("repositoryId", SchemaBuilder.int64().optional().build())
        .field("userId", SchemaBuilder.int64().optional().build())
        .build();
    VALUE_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(WatchEvent.class.getName())
        .field("action", SchemaBuilder.string().optional().build())
        .field("repository", Repository.VALUE_SCHEMA)
        .field("sender", User.VALUE_SCHEMA)
        .build();
  }

  @Override
  public Schema keySchema() {
    return KEY_SCHEMA;
  }

  @Override
  public Struct keyStruct() {
    return new Struct(KEY_SCHEMA)
        .put("repositoryId", this.repository.id)
        .put("userId", this.sender.id);
  }

  @Override
  public Schema valueSchema() {
    return VALUE_SCHEMA;
  }

  @Override
  public Struct valueStruct() {
    return new Struct(VALUE_SCHEMA)
        .put("action", this.action)
        .put("repository", null != this.repository ? this.repository.valueStruct() : null)
        .put("sender", null != this.sender ? this.sender.valueStruct() : null);
  }

  @Override
  public Long timestamp() {
    return null;
  }

  public String action() {
    return action;
  }

  public void action(String action) {
    this.action = action;
  }

  public Repository repository() {
    return repository;
  }

  public void repository(Repository repository) {
    this.repository = repository;
  }

  public User sender() {
    return sender;
  }

  public void sender(User sender) {
    this.sender = sender;
  }
}
