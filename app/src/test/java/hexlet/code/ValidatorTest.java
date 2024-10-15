package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    private Validator validator;
    private StringSchema stringSchema;
    private NumberSchema numberSchema;
    private MapSchema mapSchema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        stringSchema = validator.string();
        numberSchema = validator.number();
        mapSchema = validator.map();
    }

    @Test
    void testStringSchema() {
        assertThat(stringSchema.isValid("")).isTrue();
        assertThat(stringSchema.isValid(null)).isTrue();

        stringSchema.required();
        assertThat(stringSchema.isValid("")).isFalse();
        assertThat(stringSchema.isValid("hexlet")).isTrue();

        stringSchema.minLength(5);
        assertThat(stringSchema.isValid("hex")).isFalse();
        assertThat(stringSchema.isValid("hexlet")).isTrue();

        stringSchema.contains("hex");
        assertThat(stringSchema.isValid("hexlet")).isTrue();
        assertThat(stringSchema.isValid("lethex")).isTrue();
        assertThat(stringSchema.isValid("helet")).isFalse();
    }

    @Test
    void testNumberSchema() {
        assertThat(numberSchema.isValid(null)).isTrue();
        assertThat(numberSchema.isValid(5)).isTrue();

        numberSchema.required();
        assertThat(numberSchema.isValid(null)).isFalse();
        assertThat(numberSchema.isValid(10)).isTrue();

        numberSchema.positive();
        assertThat(numberSchema.isValid(-5)).isFalse();
        assertThat(numberSchema.isValid(5)).isTrue();
        assertThat(numberSchema.isValid(0)).isFalse();

        numberSchema.range(5, 10);
        assertThat(numberSchema.isValid(5)).isTrue();
        assertThat(numberSchema.isValid(10)).isTrue();
        assertThat(numberSchema.isValid(4)).isFalse();
        assertThat(numberSchema.isValid(11)).isFalse();
    }
    @Test
    void testMapSchema() {
        assertThat(mapSchema.isValid(null)).isTrue();
        assertThat(mapSchema.isValid(new HashMap<>())).isTrue();

        mapSchema.required();

        assertThat(mapSchema.isValid(null)).isFalse();
        assertThat(mapSchema.isValid(new HashMap<>())).isTrue();

        mapSchema.sizeOf(2);
        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        assertThat(mapSchema.isValid(data)).isFalse();
        data.put("key2", "value2");
        assertThat(mapSchema.isValid(data)).isTrue();
    }
    @Test
    public void testShape() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());

        mapSchema.shape(schemas);

        Map<String, Object> person1 = new HashMap<>();
        person1.put("name", "John");
        person1.put("age", 25);
        assertThat(mapSchema.isValid(person1)).isTrue();

        Map<String, Object> person2 = new HashMap<>();
        person2.put("name", "John");
        person2.put("age", -10);
        assertThat(mapSchema.isValid(person2)).isFalse();

        Map<String, Object> person3 = new HashMap<>();
        person3.put("name", "Alice");
        person3.put("age", 30);
        assertThat(mapSchema.isValid(person3)).isTrue();
    }
}
