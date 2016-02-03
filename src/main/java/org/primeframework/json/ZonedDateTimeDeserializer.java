/*
 * Copyright (c) 2015, Inversoft Inc., All Rights Reserved
 */
package org.primeframework.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Jackson deserializer for the ZonedDateTime class.
 *
 * @author Brian Pontarelli
 */
public class ZonedDateTimeDeserializer extends StdScalarDeserializer<ZonedDateTime> {
  public ZonedDateTimeDeserializer() {
    super(Long.TYPE);
  }

  @Override
  public ZonedDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    JsonToken t = jp.getCurrentToken();
    long value;
    if (t == JsonToken.VALUE_NUMBER_INT || t == JsonToken.VALUE_NUMBER_FLOAT) {
      value = jp.getLongValue();
    } else if (t == JsonToken.VALUE_STRING) {
      String str = jp.getText().trim();
      if (str.length() == 0) {
        return null;
      }

      try {
        value = Long.parseLong(str);
      } catch (NumberFormatException e) {
        throw ctxt.mappingException(handledType());
      }
    } else {
      throw ctxt.mappingException(handledType());
    }

    return ZonedDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneOffset.UTC);
  }
}
