package hexlet.code;


public class StringSchema {
    private boolean isRequired = false;
    private int minLength = -1; // Отрицательное значение означает, что ограничение не установлено
    private String mustContain = null; // Если null, то проверка на подстроку не требуется

    // Метод для обязательного заполнения
    public StringSchema required() {
        isRequired = true;
        return this;
    }

    // Метод для проверки минимальной длины строки
    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    // Метод для проверки наличия подстроки
    public StringSchema contains(String substring) {
        this.mustContain = substring;
        return this;
    }

    // Метод проверки данных на соответствие установленным правилам
    public boolean isValid(String value) {
        // Проверка на обязательность заполнения
        if (isRequired && (value == null || value.isEmpty())) {
            return false;
        }

        // Если значение не обязательно, то null или пустая строка считаются валидными
        if (!isRequired && (value == null || value.isEmpty())) {
            return true;
        }

        // Проверка на минимальную длину строки
        if (minLength >= 0 && value.length() < minLength) {
            return false;
        }

        // Проверка на наличие подстроки
        if (mustContain != null && !value.contains(mustContain)) {
            return false;
        }

        // Если все условия соблюдены, возвращаем true
        return true;
    }
}

