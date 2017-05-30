package com.github.jcustenborder.kafka.connect.github.model;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;

public interface Structable {
  Schema keySchema();

  Struct keyStruct();

  Schema valueSchema();

  Struct valueStruct();

  Long timestamp();
}
