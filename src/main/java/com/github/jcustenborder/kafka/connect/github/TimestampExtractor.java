package com.github.jcustenborder.kafka.connect.github;

import org.apache.kafka.connect.data.Struct;

public interface TimestampExtractor {
  Long process(Struct value);
}
