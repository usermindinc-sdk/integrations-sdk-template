package com.usermind.usermindsdk.worker.normalization.action;

import com.typesafe.config.ConfigValueType;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.worker.normalization.FieldDescription;
import com.usermind.usermindsdk.worker.normalization.FieldValueAndType;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copy action that converts custom date string value to long millis.
 * Multiple formats are supported.
 * <p>Sample configurations:</p>
 * <pre>
 *  {
 *    "dateOfBirth": {
 *      "action": {
 *        "config": {
 *          //single format:
 *          "format": "yyyy-MM-dd HH:mm:ss"
 *          //multiple formats:
 *          "format": ["yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"]
 *        },
 *        "name": "COPY_CUSTOM_DATE"
 *      },
 *      "expected": false,
 *      "metadata": {
 *        "display_name": "Date of Birth",
 *        "is_required": false,
 *        "read_only": false
 *      }
 *    }
 *  }
 * </pre>
 * <p>
 *   Sample entities:
 * </p>
 * <pre>
 *  {
 *    "dateOfBirth": "1976-02-23"
 *  }
 * </pre>
 * and
 * <pre>
 *  {
 *    "dateOfBirth": "1976-02-23 00:00:00"
 *  }
 * </pre>
 * both will be normalized to:
 * <pre>
 *  {
 *    "dateOfBirth": 193881600000
 *  }
 * </pre>
 */
public class CopyCustomDateFieldAction extends CopyFieldAction {

  public CopyCustomDateFieldAction() {
    super("COPY_CUSTOM_DATE");
  }

  @Override
  protected FieldValueAndType getValueAndType(Configuration entity,
      FieldDescription fieldDescription) {
    Long millis = entity
        .getStringAsOptional(fieldDescription.getPath())
        .map(date -> parseMillis(fieldDescription, date))
        .orElse(null);
    return new FieldValueAndType(millis, "DATE");
  }

  private static Long parseMillis(FieldDescription fieldDescription, String date) {
    List<String> formats;
    Configuration actionConfig = fieldDescription.getActionConfig();
    if (actionConfig.getFieldType("format") == ConfigValueType.LIST) {
      formats = actionConfig.getStringList("format");
    } else {
      formats = Collections.singletonList(actionConfig.getString("format"));
    }

    DateTimeParser[] parsers = formats.stream()
        .map(format -> DateTimeFormat.forPattern(format).getParser())
        .collect(Collectors.toList()).toArray(new DateTimeParser[]{});

    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
        .append(null, parsers)
        .toFormatter();

    DateTimeFormatter utcParser = formatter.withZone(DateTimeZone.UTC);
    return utcParser.parseMillis(date);
  }
}
