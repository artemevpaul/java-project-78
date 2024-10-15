package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private final Map<String, Predicate<T>> checks = new HashMap<>();
    private boolean isRequired;

    public void addCheck(String name, Predicate<T> check) {
        checks.put(name, check);
    }

    public boolean isValid(T value) {
        for (var check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }

    public void setRequired() {
        isRequired = true;
    }

}
