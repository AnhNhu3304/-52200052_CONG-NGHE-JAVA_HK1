public class Program {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Invalid expression");
            return;
        }

        try {
            double num1 = Double.parseDouble(args[0]);
            double num2 = Double.parseDouble(args[2]);

            String operator = args[1];

            //Khoi tao bien luu ket qua
            double result;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    System.out.println(result);
                    break;
                case "-":
                    result = num1 - num2;
                    System.out.println(result);
                    break;
                case "x":
                    result = num1 * num2;
                    System.out.println(result);
                    break;
                case "/":
                    if (num2 == 0) {
                        System.out.println("Division by zero is undefined");
                    } else {
                        result = num1 / num2;
                        System.out.println(result);
                    }
                    break;
                case "^":
                    result = Math.pow(num1, num2);
                    System.out.println(result);
                    break;
                default:
                    System.out.println("Unsupported operator");
                    break;
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid expression");
        }
    }
}

