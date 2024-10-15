package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {

    public MapSchema required() {
        addCheck(value -> value != null);
        setRequired();
        return this;
    }

    public MapSchema sizeof(Integer n) {
        addCheck(map -> ((Map<?, ?>) map).size() == n);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addCheck(map -> {
            for (Map.Entry<String, BaseSchema<T>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema schema = entry.getValue();

                if (!map.containsKey(key) || !schema.isValid(map.get(key))) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
