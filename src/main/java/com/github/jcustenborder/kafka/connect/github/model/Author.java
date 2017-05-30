package com.github.jcustenborder.kafka.connect.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;

public class Author implements Structable {

  public static final Schema VALUE_SCHEMA;
  public static final Schema KEY_SCHEMA;

  @JsonProperty("name")
  String name;
  @JsonProperty("email")
  String email;
  @JsonProperty("username")
  String username;

  public String name() {
    return name;
  }

  public void name(String name) {
    this.name = name;
  }

  public String email() {
    return email;
  }

  public void email(String email) {
    this.email = email;
  }

  public String username() {
    return username;
  }

  public void username(String username) {
    this.username = username;
  }

  static {
    VALUE_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(CommitCommentEvent.class.getName())
        .field("name", SchemaBuilder.string().optional().build())
        .field("email", SchemaBuilder.string().optional().build())
        .field("username", SchemaBuilder.string().optional().build())
        .build();

    KEY_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(CommitCommentEvent.class.getName() + "Key")
        .field("username", SchemaBuilder.string().optional().build())
        .build();
  }


  @Override
  public Schema keySchema() {
    return KEY_SCHEMA;
  }

  @Override
  public Struct keyStruct() {
    return new Struct(KEY_SCHEMA)
        .put("username", this.username);
  }

  @Override
  public Schema valueSchema() {
    return VALUE_SCHEMA;
  }

  @Override
  public Struct valueStruct() {
    return new Struct(VALUE_SCHEMA)
        .put("name", this.name)
        .put("email", this.email)
        .put("username", this.username);
  }

  @Override
  public Long timestamp() {
    return null;
  }
}
