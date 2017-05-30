package com.github.jcustenborder.kafka.connect.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.clients.producer.internals.Sender;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;

public class ForkEvent implements Structable {
  @JsonProperty("forkee")
  Repository forkee;
  @JsonProperty("repository")
  Repository repository;
  @JsonProperty("sender")
  Sender sender;

  public static final Schema KEY_SCHEMA;
  public static final Schema VALUE_SCHEMA;

  static {
    KEY_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(ForkEvent.class.getName() + "Key")
        .field("forkeeId", SchemaBuilder.int64().optional().build())
        .field("repositoryId", SchemaBuilder.int64().optional().build())
        .build();

    VALUE_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(ForkEvent.class.getName())
        .field("forkee", Repository.VALUE_SCHEMA)
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
        .put("forkeeId", this.forkee.id)
        .put("repositoryId", this.repository.id);
  }

  @Override
  public Schema valueSchema() {
    return VALUE_SCHEMA;
  }

  @Override
  public Struct valueStruct() {
    return new Struct(VALUE_SCHEMA)
        .put("forkee", this.forkee)
        .put("repository", this.repository)
        .put("sender", this.sender);
  }

  @Override
  public Long timestamp() {
    return null;
  }
}
