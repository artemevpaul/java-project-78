package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addCheck("REQUIRED", (value -> value != null));
        setRequired();
        return this;
    }

    public NumberSchema positive() {
        addCheck("POSITIVE", (value -> value > 0));
        return this;
    }

    public NumberSchema range(Integer a, Integer b) {
        addCheck("RANGE", (value -> (value >= a && value <= b)));
        return this;
    }
}
