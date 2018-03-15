package com.usermind.usermindsdk.worker.normalization.action;

import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.worker.normalization.FieldDescription;
import com.usermind.usermindsdk.worker.normalization.FieldValueAndType;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Copy action that converts ISO date string value to long millis.
 */
public class CopyIsoDateFieldAction extends CopyFieldAction {
  private static final DateTimeFormatter UTC_ISO_PARSER =
      ISODateTimeFormat.dateTimeParser().withZoneUTC();

  public CopyIsoDateFieldAction() {
    super("COPY_ISO_DATE");
  }

  @Override
  protected FieldValueAndType getValueAndType(Configuration entity,
      FieldDescription fieldDescription) {
    Long millis = entity
        .getStringAsOptional(fieldDescription.getPath())
        .map(UTC_ISO_PARSER::parseMillis)
        .orElse(null);
    return new FieldValueAndType(millis, "DATE");
  }
}
