package hexlet.code;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Validator {

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
