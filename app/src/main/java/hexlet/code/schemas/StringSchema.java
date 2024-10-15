package hexlet.code.schemas;


public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addCheck(value -> value != null && !value.isEmpty());
        setRequired();
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck(value -> value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck(value -> value.contains(substring));
        return this;
    }
}

