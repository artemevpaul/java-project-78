package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class Validator {

    public static StringSchema string() {
        return new StringSchema();
    }
    public static NumberSchema number() {
        return new NumberSchema();
    }
    public static MapSchema map() {
        return new MapSchema();
    }
}
