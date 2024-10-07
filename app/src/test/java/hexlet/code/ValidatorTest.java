package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    private Validator validator;
    private StringSchema stringSchema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        stringSchema = validator.string();
    }

    @Test
    void testStringSchemaWithoutRequired() {
        // Проверка без обязательного условия required()
        assertThat(stringSchema.isValid("")).isTrue();  // Пустая строка допустима
        assertThat(stringSchema.isValid(null)).isTrue();  // null допустим
        assertThat(stringSchema.isValid("hexlet")).isTrue();  // Произвольная строка допустима
    }

    @Test
    void testStringSchemaWithRequired() {
        stringSchema.required();

        // Проверка с обязательным условием
        assertThat(stringSchema.isValid("")).isFalse();  // Пустая строка недопустима
        assertThat(stringSchema.isValid(null)).isFalse();  // null недопустим
        assertThat(stringSchema.isValid("hexlet")).isTrue();  // Произвольная непустая строка допустима
    }

    @Test
    void testStringSchemaWithMinLength() {
        stringSchema.required().minLength(5);

        // Проверка с минимальной длиной
        assertThat(stringSchema.isValid("hex")).isFalse();  // Строка короче 5 символов недопустима
        assertThat(stringSchema.isValid("hello")).isTrue();  // Ровно 5 символов допустимо
        assertThat(stringSchema.isValid("longer string")).isTrue();  // Больше 5 символов допустимо
    }

    @Test
    void testStringSchemaWithContains() {
        stringSchema.required().contains("fox");

        // Проверка на наличие подстроки
        assertThat(stringSchema.isValid("what does the fox say")).isTrue();  // Содержит подстроку "fox"
        assertThat(stringSchema.isValid("what does the dog say")).isFalse();  // Не содержит подстроку "fox"
    }

    @Test
    void testStringSchemaCombinedChecks() {
        stringSchema.required().minLength(5).contains("hex");

        // Комбинированные проверки
        assertThat(stringSchema.isValid("hexlet")).isTrue();  // Удовлетворяет всем условиям
        assertThat(stringSchema.isValid("he")).isFalse();  // Короче 5 символов
        assertThat(stringSchema.isValid("lethex")).isTrue();  // Содержит "hex", длиннее 5 символов
        assertThat(stringSchema.isValid("exams")).isFalse();  // Длиннее 5 символов, но не содержит "hex"
    }

    @Test
    void testResettingSchema() {
        stringSchema.required();
        assertThat(stringSchema.isValid("hexlet")).isTrue();  // Строка валидна

        // Меняем условия
        stringSchema.minLength(10);
        assertThat(stringSchema.isValid("hexlet")).isFalse();  // Теперь не валидна из-за длины
    }
}

