package com.github.jcustenborder.kafka.connect.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;

public class Label implements Structable {

  @JsonProperty("url")
  String url;
  @JsonProperty("name")
  String name;
  @JsonProperty("color")
  String color;

  public static final Schema KEY_SCHEMA;
  public static final Schema VALUE_SCHEMA;

  static {
    KEY_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(Label.class.getName() + "Key")
        .field("url", SchemaBuilder.string().optional().build())
        .build();

    VALUE_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(Label.class.getName())
        .field("url", SchemaBuilder.string().optional().build())
        .field("name", SchemaBuilder.string().optional().build())
        .field("color", SchemaBuilder.string().optional().build())
        .build();
  }

  @Override
  public Schema keySchema() {
    return KEY_SCHEMA;
  }

  @Override
  public Struct keyStruct() {
    return new Struct(KEY_SCHEMA)
        .put("url", this.url);
  }

  @Override
  public Schema valueSchema() {
    return VALUE_SCHEMA;
  }

  @Override
  public Struct valueStruct() {
    return new Struct(VALUE_SCHEMA)
        .put("url", this.url)
        .put("name", this.name)
        .put("color", this.color);
  }

  @Override
  public Long timestamp() {
    return null;
  }
}
