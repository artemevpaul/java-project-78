package hexlet.code.schemas;


public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addCheck(value -> value != null);
        setRequired();
        return this;
    }
    public NumberSchema positive() {
        addCheck(value -> value == null || value > 0);
        return this;
    }
    public NumberSchema range(Integer a, Integer b) {
        addCheck(value -> value == null || (value >= a && value <= b));
        return this;
    }
}
