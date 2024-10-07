package hexlet.code;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Validator {

    public static StringSchema string() {
        return new StringSchema();
    }
}
