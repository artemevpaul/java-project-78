package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private final Map<String, Predicate<T>> checks = new HashMap<>();
    private boolean isRequired;

    public final void addCheck(String name, Predicate<T> check) {
        checks.put(name, check);
    }

    public final boolean isValid(T value) {
        Predicate<T> checkRequired = checks.get("REQUIRED");
        if (!isRequired && !checkRequired.test(value)) {
            return true;
        }
        if (isRequired && !checkRequired.test(value)) {
            return false;
        }
        return checks.values().stream()
                .allMatch(check -> check.test(value));
    }


    public final void setRequired() {
        isRequired = true;
    }

}
