package validation;

import serialization.JsonMap;

public interface iTxValidationRule {
	public JsonMap validate(JsonMap tx, JsonMap signatures, JsonMap extra);
}
