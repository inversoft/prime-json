/*
 * Copyright (c) 2015, Inversoft Inc., All Rights Reserved
 */
package org.primeframework.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * Jackson serializer for the ZonedDateTime class.
 *
 * @author Brian Pontarelli
 */
public class ZonedDateTimeSerializer extends StdScalarSerializer<ZonedDateTime> {
  public ZonedDateTimeSerializer() {
    super(ZonedDateTime.class);
  }

  @Override
  public void serialize(ZonedDateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
    if (value == null) {
      jgen.writeNull();
    } else {
      jgen.writeNumber(value.toInstant().toEpochMilli());
    }
  }
}
