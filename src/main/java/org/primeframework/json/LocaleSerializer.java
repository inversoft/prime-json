/*
 * Copyright (c) 2015, Inversoft Inc., All Rights Reserved
 */
package org.primeframework.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;
import java.util.Locale;

/**
 * Locale Jackson's serializer.
 *
 * @author Brian Pontarelli
 */
public class LocaleSerializer extends StdScalarSerializer<Locale> {
  public LocaleSerializer() {
    super(Locale.class);
  }

  @Override
  public void serialize(Locale value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonGenerationException {
    if (value == null) {
      jgen.writeNull();
    } else {
      jgen.writeString(value.toString());
    }
  }
}
