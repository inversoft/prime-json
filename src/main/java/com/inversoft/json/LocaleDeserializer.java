/*
 * Copyright (c) 2015, Inversoft Inc., All Rights Reserved
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
package com.inversoft.json;

import java.io.IOException;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

/**
 * Jackson deserializer for Locales.
 *
 * @author Brian Pontarelli
 */
public class LocaleDeserializer extends StdScalarDeserializer<Locale> {
  protected LocaleDeserializer() {
    super(Locale.class);
  }

  @Override
  public Locale deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonToken t = jp.getCurrentToken();
    if (t == JsonToken.VALUE_STRING) {
      String str = jp.getText().trim();
      if (str.length() == 0) {
        return null;
      }

      return toLocale(str);
    }

    throw ctxt.mappingException(handledType());
  }

  private Locale toLocale(String str) {
    if (str == null) {
      return null;
    }
    int len = str.length();
    if (len != 2 && len != 5 && len < 7) {
      throw new IllegalArgumentException("Invalid locale format: " + str);
    }
    char ch0 = str.charAt(0);
    char ch1 = str.charAt(1);
    if (ch0 < 'a' || ch0 > 'z' || ch1 < 'a' || ch1 > 'z') {
      throw new IllegalArgumentException("Invalid locale format: " + str);
    }
    if (len == 2) {
      return new Locale(str, "");
    } else {
      if (str.charAt(2) != '_') {
        throw new IllegalArgumentException("Invalid locale format: " + str);
      }
      char ch3 = str.charAt(3);
      if (ch3 == '_') {
        return new Locale(str.substring(0, 2), "", str.substring(4));
      }
      char ch4 = str.charAt(4);
      if (ch3 < 'A' || ch3 > 'Z' || ch4 < 'A' || ch4 > 'Z') {
        throw new IllegalArgumentException("Invalid locale format: " + str);
      }
      if (len == 5) {
        return new Locale(str.substring(0, 2), str.substring(3, 5));
      } else {
        if (str.charAt(5) != '_') {
          throw new IllegalArgumentException("Invalid locale format: " + str);
        }
        return new Locale(str.substring(0, 2), str.substring(3, 5), str.substring(6));
      }
    }
  }
}
