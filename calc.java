package kata;

import java.util.Scanner;

public class calc {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите выражение [2+2] или два римских [I*X] числа ( от 1 до 10 включительно) + Enter ");
        String virazhenie = scan.nextLine();
        System.out.println(razbor(virazhenie));
    }

    public static String razbor(String vvod) throws Exception {
        int a;
        int b;
        String znak;
        String itog;
        boolean rimskie;
        String[] operands = vvod.split("[+\\-*/]");
        if (operands.length != 2)
            throw new Exception("Ошибка! По условию задачи должно быть два операнда и один оператор (+, -, /, *");
        znak = poiskZnaka(vvod);
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            a = Roman.convertToArabian(operands[0]);
            b = Roman.convertToArabian(operands[1]);
            rimskie = true;
        } else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            a = Integer.parseInt(operands[0]);
            b = Integer.parseInt(operands[1]);
            rimskie = false;
        } else {
            throw new Exception("Ошибка! Можно использовать только два римских или два арабских числа.");
        }
        if (a > 10 || b > 10) {
            throw new Exception("Ошибка! По условию задачи числа должны быть от 1 до 10 включительно");
        }
        int arabskie = calculate(a, b, znak);
        if (rimskie) {
            if (arabskie <= 0) {
                throw new Exception("Ошибка! Римское число не может быть отрицательным или равным нулю");
            }
            itog = Roman.convertToRoman(arabskie);
        } else {
            itog = String.valueOf(arabskie);
        }
        return itog;
    }

    static String poiskZnaka(String vvod) {
        if (vvod.contains("+")) return "+";
        else if (vvod.contains("-")) return "-";
        else if (vvod.contains("*")) return "*";
        else if (vvod.contains("/")) return "/";
        else return null;
    }

    static int calculate(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

    class Roman {
        static String[] rimskieChisla = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                "XCVIII", "XCIX", "C"};

        public static boolean isRoman(String val) {
            for (int i = 0; i < rimskieChisla.length; i++) {
                if (val.equals(rimskieChisla[i])) {
                    return true;
                }
            }
            return false;
        }

        public static int convertToArabian(String roman) {
            for (int i = 0; i < rimskieChisla.length; i++) {
                if (roman.equals(rimskieChisla[i])) {
                    return i;
                }
            }
            return -1;
        }

        public static String convertToRoman(int arabskie) {
            return rimskieChisla[arabskie];
        }
    }
}