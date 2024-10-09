package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private final List<Predicate<T>> checks = new ArrayList<>();
    private boolean isRequired;

    public void addCheck(Predicate<T> check) {
        checks.add(check);
    }
    public boolean isValid(T value) {
        for (Predicate<T> check : checks) {
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
