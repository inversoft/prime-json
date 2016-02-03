/*
 * Copyright (c) 2015, Inversoft Inc., All Rights Reserved
 */
package org.primeframework.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZoneId;

/**
 * @author Seth Musselman
 */
public class ZoneIdDeserializer extends JsonDeserializer<ZoneId> {
  @Override
  public ZoneId deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    return ZoneId.of(jp.getText());
  }
}
