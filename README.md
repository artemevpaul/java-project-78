# Data Validator Library

## Description

The `Data Validator` library is designed to provide easy-to-use tools for validating the correctness of various data types in Java applications. This is essential when dealing with external data sources, such as user inputs or API responses, to ensure data integrity and prevent errors.

With this library, you can validate strings, numbers, and even complex data structures such as maps or objects. Each schema type has its own set of rules that you can easily configure, making the library versatile for various use cases. Inspired by the popular `yup` library, `Data Validator` brings similar functionalities to Java, with a focus on simplicity and flexibility.

## Features
- **String Validation**: Check for required fields, set minimum lengths, and more.
- **Number Validation**: Validate for positive values, required fields, or custom constraints.
- **Map/Object Validation**: Define and enforce rules for nested structures, supporting complex use cases.

## Example Usage
Here's a quick example to showcase how to use the library for different types of validation:
```
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

// String Validation
StringSchema stringSchema = v.string().required();

System.out.println(stringSchema.isValid("what does the fox say")); // true
System.out.println(stringSchema.isValid("")); // false

// Number Validation
NumberSchema numberSchema = v.number().required().positive();

System.out.println(numberSchema.isValid(-10)); // false
System.out.println(numberSchema.isValid(10)); // true

// Map/Object Validation with Structure Checking
Map<String, BaseSchema<String>> schemas = new HashMap<>();
schemas.put("firstName", v.string().required());
schemas.put("lastName", v.string().required().minLength(2));

MapSchema mapSchema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("firstName", "John");
human1.put("lastName", "Smith");
System.out.println(mapSchema.isValid(human1)); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("firstName", "Anna");
human2.put("lastName", "B");
System.out.println(mapSchema.isValid(human2)); // false
```


### Hexlet tests and linter status:
[![Actions Status](https://github.com/artemevpaul/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/artemevpaul/java-project-78/actions)

[![Maintainability](https://api.codeclimate.com/v1/badges/1e24e36615acf8113914/maintainability)](https://codeclimate.com/github/artemevpaul/java-project-78/maintainability)

[![Test Coverage](https://api.codeclimate.com/v1/badges/1e24e36615acf8113914/test_coverage)](https://codeclimate.com/github/artemevpaul/java-project-78/test_coverage)

![example workflow](https://github.com/artemevpaul/java-project-71/actions/workflows/main.yml/badge.svg)
