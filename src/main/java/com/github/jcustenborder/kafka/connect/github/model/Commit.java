package com.github.jcustenborder.kafka.connect.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.data.Timestamp;

import java.util.Date;
import java.util.List;

public class Commit implements Structable {

  @JsonProperty("id")
  String id;
  @JsonProperty("tree_id")
  String tree_id;
  @JsonProperty("distinct")
  Boolean distinct;
  @JsonProperty("message")
  String message;
  @JsonProperty("timestamp")
  Date timestamp;
  @JsonProperty("url")
  String url;
  @JsonProperty("author")
  Author author;
  @JsonProperty("committer")
  Author committer;
  @JsonProperty("added")
  List<String> added;
  @JsonProperty("removed")
  List<String> removed;
  @JsonProperty("modified")
  List<String> modified;

  public static final Schema VALUE_SCHEMA;
  public static final Schema KEY_SCHEMA;

  static {
    VALUE_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(Commit.class.getName())
        .field("id", SchemaBuilder.string().optional().build())
        .field("tree_id", SchemaBuilder.string().optional().build())
        .field("distinct", SchemaBuilder.bool().optional().build())
        .field("timestamp", Timestamp.builder().optional().build())
        .field("url", SchemaBuilder.string().optional().build())
        .field("author", Author.VALUE_SCHEMA)
        .field("committer", Author.VALUE_SCHEMA)
        .field("added", SchemaBuilder.array(Schema.STRING_SCHEMA).optional().build())
        .field("removed", SchemaBuilder.array(Schema.STRING_SCHEMA).optional().build())
        .field("modified", SchemaBuilder.array(Schema.STRING_SCHEMA).optional().build())
        .build();

    KEY_SCHEMA = SchemaBuilder.struct()
        .optional()
        .name(Commit.class.getName() + "Key")
        .field("id", SchemaBuilder.string().optional().build())
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
    return new Struct(KEY_SCHEMA)
        .put("id", this.id)
        .put("tree_id", this.tree_id)
        .put("distinct", this.distinct)
        .put("timestamp", this.timestamp)
        .put("url", this.url)
        .put("author", null != this.author ? this.author.valueStruct() : null)
        .put("committer", null != this.committer ? this.committer.valueStruct() : null)
        .put("added", this.added)
        .put("removed", this.removed)
        .put("modified", this.modified);
  }

  @Override
  public Long timestamp() {
    return this.timestamp.getTime();
  }
}
