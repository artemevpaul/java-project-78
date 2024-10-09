package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    private Validator validator;
    private StringSchema stringSchema;
    private NumberSchema numberSchema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        stringSchema = validator.string();
        numberSchema = validator.number();
    }

    @Test
    void testStringSchema() {
        // Тесты для строки
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
}
