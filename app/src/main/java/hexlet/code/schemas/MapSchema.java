package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, Object>> {

    public MapSchema required() {
        addCheck(value -> value != null);
        setRequired();
        return this;
    }

    public MapSchema sizeof(Integer n) {
        addCheck(value -> value.size() == n);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        addCheck(map -> {
            for (Map.Entry<String, BaseSchema<?>> entry : schemas.entrySet()) {
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
