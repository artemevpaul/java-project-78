package hexlet.code.schemas;


public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addCheck("REQUIRED", (value -> value != null));
        setRequired();
        return this;
    }

    public NumberSchema positive() {
        addCheck("POSITIVE", (value -> value == null || value > 0));
        return this;
    }

    public NumberSchema range(Integer a, Integer b) {
        addCheck("RANGE", (value -> value == null || (value >= a && value <= b)));
        return this;
    }
}
