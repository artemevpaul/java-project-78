package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addCheck("REQUIRED", (value -> value != null && !value.isEmpty()));
        setRequired();
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("MIN_LENGTH", (value -> value.length() >= length));
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("CONTAINS", (value -> value.contains(substring)));
        return this;
    }
}

