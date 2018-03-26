package calculator;

public class Logic {
    private static String resultString;

    public Logic() {
        resultString = "0";
    }

    public static String getResult() {
        return resultString;
    }

    public static void addDigit(String digit) {
        String zero = "0";
        if (!resultString.contains(",") && digit == ",") {
            resultString = resultString + digit;
        }
        if (!resultString.equals(zero) && resultString.length() < 17 && digit != ",") {
            resultString = resultString + digit;
        }
        if (resultString.equals(zero) && digit != ","){
            resultString = digit;
        }

    }

    public static void pressChar(String symbol) {
        resultString = resultString + symbol;
        switch (symbol) {
            case "C":
                zeroResult(); break;
        }
    }

    private static void zeroResult() {
        resultString = "0";
    }

}
