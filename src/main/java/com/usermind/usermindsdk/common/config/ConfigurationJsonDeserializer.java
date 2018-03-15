package com.usermind.usermindsdk.common.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Jackson JSON mapper {@code Configuration} deserializer.
 */
public class ConfigurationJsonDeserializer extends JsonDeserializer<Configuration> {

  @Override
  public Configuration deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {

    String jsonConfig = parser.getCodec().readTree(parser).toString();
    return ConfigurationBuilder.createConfiguration(jsonConfig);
  }
}
