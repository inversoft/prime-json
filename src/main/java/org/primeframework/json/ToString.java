/*
 * Copyright (c) 2015, Inversoft Inc., All Rights Reserved
 */
package org.primeframework.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Brian Pontarelli
 */
public class ToString {
  private final static ObjectMapper objectMapper = new ObjectMapper();

  static {
    objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true)
        .configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true)
        .registerModule(new InversoftJacksonModule());
  }

  /**
   * A not-pretty printed JSON string. Returns a portable JSON string.
   *
   * @param o
   * @return
   */
  public static String toJSONString(Object o) {
    try {
      return objectMapper.writer().without(SerializationFeature.INDENT_OUTPUT).writeValueAsString(o);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * A pretty printed JSON string suitable for use in a {@link Object#toString()} override used for debug.
   *
   * @param o
   * @return
   */
  public static String toString(Object o) {
    try {
      return objectMapper.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
