package Day14;

import java.util.ArrayList;
import java.util.List;

public class StaticUtils {

    public static String changeDecimalToBinary(int decValue) {
        StringBuilder result = new StringBuilder();
        int currentDecValue = decValue;
        while (currentDecValue > 0) {
            result.insert(0, currentDecValue % 2);
            currentDecValue = currentDecValue / 2;
        }
        while (result.length() < 36) {
            result.insert(0, "0");
        }
        return result.toString();
    }

    public static String applyMaskToBinary(String mask, String binary) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            if ('X' != mask.charAt(i)) {
                result.append(mask.charAt(i));
            } else {
                result.append(binary.charAt(i));
            }
        }
        return result.toString();
    }

    public static String applyMaskToBinaryAdress(String mask, String binary) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            if ('X' == mask.charAt(i)) {
                result.append("X");
            } else if ('0' == mask.charAt(i)) {
                result.append(binary.charAt(i));
            } else {
                result.append("1");
            }
        }
        return result.toString();
    }

    public static List<Long> getKeyList(String binaryWithMask) {
        List<Long> result = new ArrayList<>();
        List<Integer> positionOfXs = getPositionOfXs(binaryWithMask);
        Long baseValue = getBaseValueWithXs(binaryWithMask);
        long numberOfPermutation = (long) Math.pow(2, positionOfXs.size());
        for (int i = 0; i < numberOfPermutation; i++) {
            String binary = changeDecimalToBinary(i);
            List<Integer> listOfPositions = getListOfOnesPositions(binary);
            if (listOfPositions.isEmpty()) {
                result.add(baseValue);
            } else {
                long resultForASingleCheck = 0L;
                for (Integer temp : listOfPositions) {
                    int tempValue = positionOfXs.get(temp);
                    long poweredValue = (long) Math.pow(2, tempValue);
                    resultForASingleCheck += poweredValue;
                }
                result.add(resultForASingleCheck + baseValue);
            }
        }
        return result;
    }

    private static List<Integer> getListOfOnesPositions(String binary) {
        List<Integer> result = new ArrayList<>();
        for (int i = binary.length() - 1; i >= 0; i--) {
            if ('1' == binary.charAt(i)) {
                result.add(binary.length() - 1 - i);
            }
        }
        return result;
    }

    public static Long getValueFromBinary(String binary) {
        long result = 0L;
        for (int i = binary.length() - 1; i >= 0; i--) {
            result += (long) (Character.getNumericValue(binary.charAt(i)) * Math.pow(2, (binary.length() - i - 1)));
        }
        return result;
    }

    public static Long getBaseValueWithXs(String binary) {
        long result = 0L;
        for (int i = binary.length() - 1; i >= 0; i--) {
            if ('X' != binary.charAt(i)) {
                result += (long) (Character.getNumericValue(binary.charAt(i)) * Math.pow(2, (binary.length() - i - 1)));
            }
        }
        return result;
    }

    private static List<Integer> getPositionOfXs(String inputString) {
        List<Integer> result = new ArrayList<>();
        int stringLength = inputString.length();
        for (int i = stringLength - 1; i >= 0; i--) {
            if ('X' == inputString.charAt(i)) {
                result.add(stringLength - i - 1);
            }
        }
        return result;
    }
}
