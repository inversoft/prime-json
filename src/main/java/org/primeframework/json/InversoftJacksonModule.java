/*
 * Copyright (c) 2015, Inversoft Inc., All Rights Reserved
 */
package org.primeframework.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;

/**
 * Prime's Jackson module. Binds the Locale serializer/deserializer.
 *
 * @author Brian Pontarelli
 */
public class InversoftJacksonModule extends SimpleModule {
  private static final Version VERSION = VersionUtil.parseVersion("0.22", "org.primeframework.mvc", "prime-jackson-module");

  public InversoftJacksonModule() {
    super(VERSION);

    // Deserializers
    addDeserializer(Locale.class, new LocaleDeserializer());
    addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
    addDeserializer(LocalDate.class, new LocalDateDeserializer());
    addDeserializer(ZoneId.class, new ZoneIdDeserializer());

    // Serializers
    addSerializer(Locale.class, new LocaleSerializer());
    addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
    addSerializer(LocalDate.class, new LocalDateSerializer());
    addSerializer(ZoneId.class, new ZoneIdSerializer());
  }
}
