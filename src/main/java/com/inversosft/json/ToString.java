/*
 * Copyright (c) 2015-2016, Inversoft Inc., All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.inversosft.json;

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
        .registerModule(new JacksonModule());
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
