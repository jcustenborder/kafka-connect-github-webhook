package com.github.jcustenborder.kafka.connect.github.model;

import com.github.jcustenborder.kafka.connect.utils.jackson.ObjectMapperFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class JsonSerialization {

  <T> T assertLoad(String file, Class<T> cls) throws IOException {
    final File input = new File(file);
    final Object output = ObjectMapperFactory.INSTANCE.readValue(input, cls);
    return cls.cast(output);
  }

  @Test
  public void commitComment() throws IOException {
    assertLoad("src/test/resources/com/github/jcustenborder/kafka/connect/github/model/commit_comment.json", CommitCommentEvent.class);
  }

  @Test
  public void create() throws IOException {
    assertLoad("src/test/resources/com/github/jcustenborder/kafka/connect/github/model/create.json", CreateEvent.class);
  }

  @Test
  public void delete() throws IOException {
    assertLoad("src/test/resources/com/github/jcustenborder/kafka/connect/github/model/delete.json", DeleteEvent.class);
  }
}
