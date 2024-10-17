package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {

    public StringSchema() {
        addCheck("REQUIRED", (value -> value != null && !value.isEmpty()));
    }

    public StringSchema required() {
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

