package com.usermind.usermindsdk.worker.util;

import com.typesafe.config.ConfigValueType;
import com.usermind.usermindsdk.common.config.Configuration;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 *  Validator for {@code Configuration} objects. Throws {@code ConfigurationValidationException}
 *  if constraint are not met.
 *  Having the following validator created:
 *      {@code ConfigurationValidator validator = ConfigurationValidator.builder()
 *           .isBoolean("isSettable")
 *           .inRange("second", 0, 60)
 *           .required("a.b", config -> config.hasPath("a"))
 *           .build(); }
 *  {@code ConfigurationValidator.validate} method will accept such configurations:
 *  1.{
 *      "isSettable" : false,
 *      "second" : 0,
 *      "a" : {
 *        "b" : 1
 *      }
 *    }
 *  2.{
 *      "isSettable" : true,
 *      "second" : 60,
 *    }
 *  3.{
 *      "isSettable" : true,
 *      "second" : 30,
 *    }
 *  but will throw a {@code ConfigurationValidationException} on the following configuration:
 *    {
 *      "isSettable" : "not a boolean",
 *      "second" : -1,
 *      "a" : { }
 *     }
 */
public class ConfigurationValidator {

  private final List<RuleCondition> rules;

  private ConfigurationValidator(List<RuleCondition> rules) {
    this.rules = rules;
  }

  /**
   * Creates a new {@code ValidatorBuilder}.
   * @return {@code ValidatorBuilder}
   */
  public static ValidatorBuilder builder() {
    return new ValidatorBuilder();
  }

  /**
   * Validates against {@code Configuration}.
   * @param config {@code Configuration}.
   * @throws ConfigurationValidationException if validation fails.
   */
  public void validate(Configuration config) {
    List<String> errors = new LinkedList<>();
    for (RuleCondition ruleCondition : rules) {
      if (!ruleCondition.condition.isPresent()
          || ruleCondition.condition.get().test(config)) {
        ruleCondition.rule.validate(config, errors);
      }
    }
    if (!errors.isEmpty()) {
      throw new ConfigurationValidationException(String.join("; ", errors));
    }
  }

  /**
   * {@code ConfigurationValidator} builder.
   */
  public static class ValidatorBuilder {

    private List<RuleCondition> rules = new LinkedList<>();

    private ValidatorBuilder() { }

    /**
     * Property is required constraint.
     * @param key property path.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder required(String key) {
      rules.add(new RuleCondition(new Required(key)));
      return this;
    }

    /**
     * Property is required constraint.
     * @param key property path.
     * @param condition validate only if this {@code Predicate} returns true.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder required(String key, Predicate<Configuration> condition) {
      rules.add(new RuleCondition(new Required(key), condition));
      return this;
    }

    /**
     * Only one property from the specified set is present constraint.
     * @param keys property names set.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder requiredOneOf(String... keys) {
      rules.add(new RuleCondition(new RequiredOneOf(keys)));
      return this;
    }

    /**
     * Only one property from the specified set is present constraint.
     * @param keys property names set.
     * @param condition validate only if this {@code Predicate} returns true.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder requiredOneOf(String[] keys,
        Predicate<Configuration> condition) {
      rules.add(new RuleCondition(new RequiredOneOf(keys), condition));
      return this;
    }

    /**
     * Only one or no property from the specified set is present constraint.
     * @param keys property names set.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder optionalOneOf(String... keys) {
      rules.add(new RuleCondition(new OptionalOneOf(keys)));
      return this;
    }

    /**
     * Only one or no property from the specified set is present constraint.
     * @param keys property names set.
     * @param condition validate only if this {@code Predicate} returns true.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder optionalOneOf(String[] keys,
        Predicate<Configuration> condition) {
      rules.add(new RuleCondition(new OptionalOneOf(keys), condition));
      return this;
    }

    /**
     * Property is of type {@code boolean} constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder isBoolean(String key) {
      rules.add(new RuleCondition(new HasType(key, ConfigValueType.BOOLEAN)));
      return this;
    }

    /**
     * Property is of type {@code boolean} constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @param condition validate only if this {@code Predicate} returns true.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder isBoolean(String key, Predicate<Configuration> condition) {
      rules.add(new RuleCondition(new HasType(key, ConfigValueType.BOOLEAN), condition));
      return this;
    }

    /**
     * Property is of type {@code List} constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder isList(String key) {
      rules.add(new RuleCondition(new HasType(key, ConfigValueType.LIST)));
      return this;
    }

    /**
     * Property is of type {@code List} constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @param condition validate only if this {@code Predicate} returns true.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder isList(String key, Predicate<Configuration> condition) {
      rules.add(new RuleCondition(new HasType(key, ConfigValueType.LIST), condition));
      return this;
    }

    /**
     * Property is of type {@code List} and the list is not empty constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder isNotEmptyList(String key) {
      rules.add(new RuleCondition(new ListNotEmpty(key)));
      return this;
    }

    /**
     * Property is of type {@code List} and the list is not empty constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @param condition validate only if this {@code Predicate} returns true.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder isNotEmptyList(String key, Predicate<Configuration> condition) {
      rules.add(new RuleCondition(new ListNotEmpty(key), condition));
      return this;
    }

    /**
     * Property is of type {@code Number} constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder isNumber(String key) {
      rules.add(new RuleCondition(new HasType(key, ConfigValueType.NUMBER)));
      return this;
    }

    /**
     * Property is of type {@code Number} constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @param condition validate only if this {@code Predicate} returns true.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder isNumber(String key, Predicate<Configuration> condition) {
      rules.add(new RuleCondition(new HasType(key, ConfigValueType.NUMBER), condition));
      return this;
    }

    /**
     * Property is of type {@code Object} constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder isObject(String key) {
      rules.add(new RuleCondition(new HasType(key, ConfigValueType.OBJECT)));
      return this;
    }

    /**
     * Property is of type {@code Object} constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @param condition validate only if this {@code Predicate} returns true.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder isObject(String key, Predicate<Configuration> condition) {
      rules.add(new RuleCondition(new HasType(key, ConfigValueType.OBJECT), condition));
      return this;
    }

    /**
     * Property is not empty constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder notEmpty(String key) {
      rules.add(new RuleCondition(new StringNotEmpty(key)));
      return this;
    }

    /**
     * Property is not empty constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @param condition validate only if this {@code Predicate} returns true.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder notEmpty(String key, Predicate<Configuration> condition) {
      rules.add(new RuleCondition(new StringNotEmpty(key), condition));
      return this;
    }

    /**
     * Property is set to one of the {@code values} constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @param values {@code Object.toString()} is used as a value to compare against.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder valuesOneOf(String key, Object... values) {
      rules.add(new RuleCondition(new ValuesOneOf(key, values)));
      return this;
    }

    /**
     * Property is set to one of the {@code values} constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @param condition validate only if this {@code Predicate} returns true.
     * @param values {@code Object.toString()} is used as a value to compare against.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder valuesOneOf(String key, Predicate<Configuration> condition,
        Object... values) {
      rules.add(new RuleCondition(new ValuesOneOf(key, values), condition));
      return this;
    }

    /**
     * Property is of type {@code Number} and is not smaller that some value constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @param min the minimum value.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder min(String key, int min) {
      rules.add(new RuleCondition(new Min(key, min)));
      return this;
    }

    /**
     * Property is of type {@code Number} and is not smaller that some value constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @param min the minimum value.
     * @param condition validate only if this {@code Predicate} returns true.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder min(String key, int min, Predicate<Configuration> condition) {
      rules.add(new RuleCondition(new Min(key, min), condition));
      return this;
    }

    /**
     * Property is of type {@code Number} and is not greater that some value constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @param max the maximum value.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder max(String key, int max) {
      rules.add(new RuleCondition(new Max(key, max)));
      return this;
    }

    /**
     * Property is of type {@code Number} and is not greater that some value constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @param max the maximum value.
     * @param condition validate only if this {@code Predicate} returns true.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder max(String key, int max, Predicate<Configuration> condition) {
      rules.add(new RuleCondition(new Max(key, max), condition));
      return this;
    }

    /**
     * Property is of type {@code Number} and is in some range constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @param min the minimum value.
     * @param max the maximum value.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder inRange(String key, int min, int max) {
      rules.add(new RuleCondition(new InRange(key, min, max)));
      return this;
    }

    /**
     * Property is of type {@code Number} and is in some range constraint.
     * Validation of this constraint is skipped if a validated {@code Configuration}
     * has no mapping for this {@code key}.
     * @param key property path.
     * @param min the minimum value.
     * @param max the maximum value.
     * @param condition validate only if this {@code Predicate} returns true.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder inRange(String key, int min, int max,
        Predicate<Configuration> condition) {
      rules.add(new RuleCondition(new InRange(key, min, max), condition));
      return this;
    }

    /**
     * General constraint allowing to implement any check.
     * @param condition must return true if constraint is met.
     * @param message {@code ConfigurationValidationException} message if {@code condition}
     *      return false.
     * @return {@code ValidatorBuilder}
     */
    public ValidatorBuilder check(Predicate<Configuration> condition,
        String message) {
      rules.add(new RuleCondition(new CheckedCondition(condition, message)));
      return this;
    }

    /**
     * Builds {@code ConfigurationValidator}.
     * @return {@code ConfigurationValidator}
     */
    public ConfigurationValidator build() {
      return new ConfigurationValidator(rules);
    }

  }

  private interface ValidationRule {

    boolean validate(Configuration config, List<String> errors);

  }

  private static class RuleCondition {
    final ValidationRule rule;
    final Optional<Predicate<Configuration>> condition;

    RuleCondition(ValidationRule rule, Predicate<Configuration> condition) {
      this.rule = rule;
      this.condition = Optional.of(condition);
    }

    RuleCondition(ValidationRule rule) {
      this.rule = rule;
      this.condition = Optional.empty();
    }

  }

  private static class Required implements ValidationRule {

    private String path;

    Required(String path) {
      this.path = path;
    }

    @Override
    public boolean validate(Configuration config, List<String> errors) {
      if (!config.hasPath(path)) {
        errors.add(String.format("Property '%s' is required", path));
        return false;
      }
      return true;
    }

  }

  private static class RequiredOneOf implements ValidationRule {

    private List<String> keys;

    RequiredOneOf(String... keys) {
      this.keys = Arrays.asList(keys);
    }

    @Override
    public boolean validate(Configuration config, List<String> errors) {
      boolean keyFound = false;
      for (String key : keys) {
        if (config.hasPath(key)) {
          if (keyFound) {
            errors.add(String.format("More than one settings found for keys %s", keys));
            return false;
          }
          keyFound = true;
        }
      }
      if (!keyFound) {
        errors.add(String.format("No settings found for keys %s", keys));
      }
      return keyFound;
    }

  }

  private static class OptionalOneOf implements ValidationRule {

    private List<String> keys;

    OptionalOneOf(String... keys) {
      this.keys = Arrays.asList(keys);
    }

    @Override
    public boolean validate(Configuration config, List<String> errors) {
      boolean keyFound = false;
      for (String key : keys) {
        if (config.hasPath(key)) {
          if (keyFound) {
            errors.add(String.format("More than one settings found for keys %s", keys));
            return false;
          }
          keyFound = true;
        }
      }
      return true;
    }

  }

  private static class StringNotEmpty implements ValidationRule {

    private String path;

    StringNotEmpty(String path) {
      this.path = path;
    }

    @Override
    public boolean validate(Configuration config, List<String> errors) {
      if (config.hasPath(path) && StringUtils.isBlank(config.getString(path))) {
        errors.add(String.format("Property '%s' is empty", path));
        return false;
      }
      return true;
    }

  }

  private static class ListNotEmpty implements ValidationRule {

    private String path;
    private ValidationRule precondition;

    ListNotEmpty(String path) {
      this.path = path;
      this.precondition = new HasType(path, ConfigValueType.LIST);
    }

    @Override
    public boolean validate(Configuration config, List<String> errors) {
      if (config.hasPath(path)) {
        if (precondition.validate(config, errors)) {
          if (config.getStringList(path).isEmpty()) {
            errors.add(String.format("List '%s' is empty", path));
            return false;
          }
        } else {
          return false;
        }
      }
      return true;
    }

  }

  private static class HasType implements ValidationRule {

    private String path;
    private ConfigValueType type;

    HasType(String path, ConfigValueType type) {
      this.path = path;
      this.type = type;
    }

    @Override
    public boolean validate(Configuration config, List<String> errors) {
      if (config.hasPath(path) && config.getFieldType(path) != type) {
        errors.add(String.format("Property '%s' is not of type %s", path, type));
        return false;
      }
      return true;
    }

  }

  private static class ValuesOneOf implements ValidationRule {

    private String path;
    private List<Object> values;

    ValuesOneOf(String path, Object... values) {
      this.path = path;
      this.values = Arrays.asList(values);
    }

    @Override
    public boolean validate(Configuration config, List<String> errors) {
      if (config.hasPath(path)) {
        String val = config.getString(path);
        for (Object value : values) {
          if (value.toString().equals(val)) {
            return true;
          }
        }
        errors.add(String.format("Property %s = %s is not equal to any of %s",
            path, val, values));
        return false;
      }
      return true;
    }

  }

  private static class Min implements ValidationRule {

    private String path;
    private int min;
    private ValidationRule precondition;

    Min(String path, int min) {
      this.path = path;
      this.min = min;
      this.precondition = new HasType(path, ConfigValueType.NUMBER);
    }

    @Override
    public boolean validate(Configuration config, List<String> errors) {
      if (config.hasPath(path)) {
        if (precondition.validate(config, errors)) {
          int val = config.getInt(path);
          if (val < min) {
            errors.add(String.format("Property %s = %d is smaller than %d", path, val, min));
            return false;
          }
        } else {
          return false;
        }
      }
      return true;
    }

  }

  private static class Max implements ValidationRule {

    private String path;
    private int max;
    private ValidationRule precondition;

    Max(String path, int max) {
      this.path = path;
      this.max = max;
      this.precondition = new HasType(path, ConfigValueType.NUMBER);
    }

    @Override
    public boolean validate(Configuration config, List<String> errors) {
      if (config.hasPath(path)) {
        if (precondition.validate(config, errors)) {
          int val = config.getInt(path);
          if (val > max) {
            errors.add(String.format("Property %s = %d is greater than %d", path, val, max));
            return false;
          }
        } else {
          return false;
        }
      }
      return true;
    }

  }

  private static class InRange implements ValidationRule {

    private int min;
    private int max;
    private String path;
    private ValidationRule precondition;

    InRange(String path, int min, int max) {
      this.min = min;
      this.max = max;
      this.path = path;
      this.precondition = new HasType(path, ConfigValueType.NUMBER);
    }

    @Override
    public boolean validate(Configuration config, List<String> errors) {
      if (config.hasPath(path)) {
        if (precondition.validate(config, errors)) {
          int val = config.getInt(path);
          if (val < min || val > max) {
            errors.add(String.format("Property %s = %d is not in range [%d, %d]",
                path, val, min, max));
            return false;
          }
        } else {
          return false;
        }
      }
      return true;
    }

  }

  private static class CheckedCondition implements ValidationRule {

    private Predicate<Configuration> condition;
    private String message;

    CheckedCondition(Predicate<Configuration> condition,
                     String message) {
      this.condition = condition;
      this.message = message;
    }

    @Override
    public boolean validate(Configuration config, List<String> errors) {
      if (condition.test(config)) {
        return true;
      } else {
        errors.add(message);
        return false;
      }
    }

  }

}
