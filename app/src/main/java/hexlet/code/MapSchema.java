package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {

    public MapSchema required() {
        addCheck(value -> value != null);
        setRequired();
        return this;
    }
    public MapSchema sizeOf(Integer n) {
        addCheck(value -> value.size() == n);
        return this;
    }
}
