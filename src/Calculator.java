import java.util.List;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) throws Exception {

        Number firstNumber = null;
        Number secondNumber = null;
        String operator = "";

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] temp = input.split(" ");
        if (temp.length > 3 || temp.length < 3) {
            System.out.println("ОШИБКА-1: Формат строки не удовлетворяет условию");
            throw new Exception();
        } else {
            operator = temp[1];
            firstNumber = parseInput(temp[0]);
            secondNumber = parseInput(temp[2]);
            if (firstNumber == null || secondNumber == null) {
                System.out.println("ОШИБКА-2: Ввод не удовлетворяет условию задачи");
                throw new Exception();
            }
        }

        System.out.println(calculate(firstNumber, secondNumber, operator).toString());

        }

    // Метод для парсинга введенной строки
    public static Number parseInput(String input) {
        Number resultNumber = null;

        try {
            int inputNumber = Integer.parseInt(input);
            resultNumber = new ArabNumber(inputNumber);
        } catch (Exception e) {}

        try {
            resultNumber = RomanNumber.valueOf(input);
        } catch (Exception e) {}

        return resultNumber;
    }

    // Метод-калькулятор
    public static String calculate(Number number1, Number number2, String operator) throws Exception {

        int result = 0;

        if (!number1.getClass().equals(number2.getClass())) {
            System.out.println("ОШИБКА-3: Одновременно используются 2 алфавита");
            throw new Exception();
        }

        if ((number1.getValue() < 0 || number1.getValue() > 10) || (number2.getValue() < 0 || number2.getValue() > 10)) {
            System.out.println("ОШИБКА-4: Ввод не соответствует условию");
            throw new Exception();
        }

        switch (operator) {
            case "+" :
                result = number1.getValue() + number2.getValue();
                break;
            case "-":
                result = number1.getValue() - number2.getValue();
                break;
            case "*":
                result = number1.getValue() * number2.getValue();
                break;
            case "/":
                try {
                    result = number1.getValue() / number2.getValue();
                } catch (Exception e) {
                    System.out.println("ОШИБКА-5: Деление на 0");
                    throw new Exception();
                }
                break;
        }

        return number1 instanceof RomanNumber ? convertToRoman(result) : new ArabNumber(result).toString();

    }

    // Метод для перевода результата в римский алфавит
    public static String convertToRoman(int number) throws Exception {
        if (number <= 0) {
            System.out.println("ОШИБКА-6: В римском алфавите нет отрицательных значений");
            throw new Exception();
        } else {
            List<RomanNumber> romanNumbers = RomanNumber.getReverseSortedValues();

            int i = 0;
            StringBuilder sb = new StringBuilder();

            while ((number > 0) && (i < romanNumbers.size())) {
                RomanNumber currentSymbol = romanNumbers.get(i);
                if (currentSymbol.getValue() <= number) {
                    sb.append(currentSymbol.name());
                    number -= currentSymbol.getValue();
                } else {
                    i++;
                }
            }
            return sb.toString();
        }

    }

}

