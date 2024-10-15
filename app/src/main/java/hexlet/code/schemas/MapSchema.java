package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    public MapSchema required() {
        addCheck(value -> value != null);
        setRequired();
        return this;
    }

    public MapSchema sizeof(Integer n) {
        addCheck(map -> ((Map<?, ?>) map).size() == n);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck(map -> {
            if (!(map instanceof Map)) {
                return false;
            }
            Map<?, ?> mapValue = (Map<?, ?>) map;

            for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema schema = entry.getValue();

                if (!mapValue.containsKey(key) || !schema.isValid(mapValue.get(key))) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
