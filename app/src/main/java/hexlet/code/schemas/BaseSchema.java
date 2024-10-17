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
        if (!isRequired && value == null) {
            return true;
        }

        if (isRequired) {
            Predicate<T> requiredCheck = checks.get("REQUIRED");
            if (requiredCheck != null && !requiredCheck.test(value)) {
                return false;
            }
        }

        if (value == null) {
            return true;
        }

        for (var entry : checks.entrySet()) {
            String checkName = entry.getKey();
            Predicate<T> check = entry.getValue();

            if ("REQUIRED".equals(checkName)) {
                continue;
            }

            if (!check.test(value)) {
                return false;
            }
        }

        return true;
    }


    public final void setRequired() {
        isRequired = true;
    }

}
