package com.github.jcustenborder.kafka.connect.github;

import org.apache.kafka.connect.data.Struct;

public interface KeyExtractor {
  void process(Struct key, Struct value);
}
