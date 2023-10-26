
package com.calculator;
import java.util.*;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение (например, 3 + 5):");
        String input = scanner.nextLine().trim();

        try {
            String[] parts = input.split(" ");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Неверный формат ввода");
            }

            String operand1 = parts[0];
            String operator = parts[1];
            String operand2 = parts[2];

            if (!isValidInput(operand1) || !isValidInput(operand2)) {
                throw new IllegalArgumentException("Неверные числа");
            }

            int num1 = convertToNumber(operand1);
            int num2 = convertToNumber(operand2);
            int result;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        throw new ArithmeticException("Деление на ноль");
                    }
                    result = num1 / num2;
                    break;
                default:
                    throw new IllegalArgumentException("Неверный оператор");
            }

            if (isValidInput(operand1) && isValidInput(operand2)) {
               if (operand1.matches("^(10|[1-9])$")){
                   System.out.println("Результат: " + result);
                } 
                else {
                 if (result < 1) {
                    throw new IllegalArgumentException("Результат не может быть меньше 1 для римских чисел");
                }
                System.out.println("Результат : " + convertToRoman(result));
            }
            }
       
            
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static boolean isValidInput(String input) {
        return input.matches("^(10|[1-9])$") || input.matches("^[IVXLCDM]+$");
    }

    private static int convertToNumber(String input) {
        if (input.matches("^(10|[1-9])$")) {
            return Integer.parseInt(input);
        } else {
            return romanToArabic(input);
        }
    }

    private static String convertToRoman(int number) {
        if (number < 1 || number > 10) {
            throw new IllegalArgumentException("Число должно быть от I до X для римских чисел");
        }

        String[] romanNumerals = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        return romanNumerals[number - 1];
    }

    private static int romanToArabic(String roman) {
        Map<Character, Integer> romanToArabicMap = new HashMap<>();
        romanToArabicMap.put('I', 1);
        romanToArabicMap.put('V', 5);
        romanToArabicMap.put('X', 10);

        int arabic = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int currentValue = romanToArabicMap.get(roman.charAt(i));

            if (currentValue < prevValue) {
                arabic -= currentValue;
            } else {
                arabic += currentValue;
            }

            prevValue = currentValue;
        }

        return arabic;
    }
}
