package Day4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Passport {
    private static final String OPTIONAL_PARAM = "cid";
    private Map<String, String> passportFields = new HashMap<>();

    public Passport() {
    }

    public Passport(Map<String, String> passportFields) {
        this.passportFields = passportFields;
    }

    public Map<String, String> getPassportFields() {
        return passportFields;
    }

    public void setPassportFields(Map<String, String> passportFields) {
        this.passportFields = passportFields;
    }

    public boolean isValidByNumberOfFields(int minNumberOfFields){
        return this.passportFields.entrySet().size() == minNumberOfFields;
    }

    public boolean isValidWithOptional(){
        if(isValidByNumberOfFields(8)){
            return true;
        }
        return isValidByNumberOfFields(7) && !this.passportFields.containsKey(OPTIONAL_PARAM);
    }

    private boolean isByrValid(){
        String value = this.passportFields.get("byr");
        if(value != null){
            if(value.length() != 4){
                return false;
            }
            int intValue = Integer.parseInt(value);
            return intValue >= 1920 && intValue <= 2002;
        }
        return false;
    }

    private boolean isIyrValid(){
        String value = this.passportFields.get("iyr");
        if(value != null){
            if(value.length() != 4){
                return false;
            }
            int intValue = Integer.parseInt(value);
            return intValue >= 2010 && intValue <= 2020;
        }
        return false;
    }

    private boolean isEyrValid(){
        String value = this.passportFields.get("eyr");
        if(value != null){
            if(value.length() != 4){
                return false;
            }
            int intValue = Integer.parseInt(value);
            return intValue >= 2020 && intValue <= 2030;
        }
        return false;
    }

    private boolean isHclValid(){
        String value = this.passportFields.get("hcl");
        int validCounter = 0;
        if(value != null){
            if(value.length() == 7){
                if(value.charAt(0)=='#'){
                    for(int i = 1; i<value.length(); i++) {
                        if((value.charAt(i) >= '0' && value.charAt(i) <= '9') || (value.charAt(i) >= 'a' && value.charAt(i) <= 'z')){
                            validCounter++;
                        }
                    }
                    return validCounter == 6;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean isEclValid(){
        String value = this.passportFields.get("ecl");
        List<String> eyeTable = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        if(value != null){
            return eyeTable.contains(value);
        }
        return false;
    }

    private boolean isHgtValid(){
        String value = this.passportFields.get("hgt");
        if(value != null){
            String[] values = value.split("(?<=\\d)(?=\\D)");
            if(values.length == 2){
                if(values[1].equals("in")){
                    int intValue = Integer.parseInt(values[0]);
                    return intValue >= 59 && intValue <= 76;
                } else if(values[1].equals("cm")){
                    int intValue = Integer.parseInt(values[0]);
                    return intValue >= 150 && intValue <= 193;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean isPidValid(){
        String value = this.passportFields.get("pid");
        int validCounter = 0;
        if(value != null){
            for(int i = 0; i<value.length(); i++) {
                if(value.charAt(i) >= '0' && value.charAt(i) <= '9'){
                    validCounter++;
                }
            }
            return validCounter == 9;
        }
        return false;
    }

    public boolean areAllFieldsValid(){
        return isByrValid() && isEclValid() && isHclValid() && isIyrValid() && isPidValid() && isEyrValid() && isHgtValid();
    }
}
