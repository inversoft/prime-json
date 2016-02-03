/*
 * Copyright (c) 2015, Inversoft Inc., All Rights Reserved
 */
package org.primeframework.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Marker interface used on empty constructors required for use by Jackson.
 *
 * @author Brian Pontarelli
 */
@Target(ElementType.CONSTRUCTOR)
public @interface JacksonConstructor {
}
