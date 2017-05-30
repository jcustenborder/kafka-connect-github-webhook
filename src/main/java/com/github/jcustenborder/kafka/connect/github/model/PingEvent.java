package com.github.jcustenborder.kafka.connect.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;

public class PingEvent implements Structable {
  @JsonProperty("zen")
  String zen;

  @JsonProperty("hook_id")
  Long hookId;





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
