package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addCheck("REQUIRED", (value -> value != null));
        setRequired();
        return this;
    }

    public MapSchema sizeof(Integer n) {
        addCheck("SIZE_OF", (map -> map.size() == n));
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addCheck("SHAPE", (map ->
                schemas.entrySet().stream()
                        .allMatch(entry -> {
                            String key = entry.getKey();
                            BaseSchema schema = entry.getValue();
                            return schema.isValid(map.get(key));
                        })
        ));
        return this;
    }
}
