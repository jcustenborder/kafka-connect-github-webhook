package com.github.jcustenborder.kafka.connect.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;

public class DeleteEvent implements Structable {
  @JsonProperty("ref")
  String ref;
  @JsonProperty("ref_type")
  String refType;
  @JsonProperty("pusher_type")
  String pusherType;
  @JsonProperty("repository")
  Repository repository;
  @JsonProperty("sender")
  User sender;

  public String ref() {
    return ref;
  }

  public void ref(String ref) {
    this.ref = ref;
  }

  public String refType() {
    return refType;
  }

  public void refType(String refType) {
    this.refType = refType;
  }

  public String pusherType() {
    return pusherType;
  }

  public void pusherType(String pusherType) {
    this.pusherType = pusherType;
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
