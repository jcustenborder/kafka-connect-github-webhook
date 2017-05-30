package com.github.jcustenborder.kafka.connect.github.model;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;

public class RepositoryEvent implements Structable {



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
