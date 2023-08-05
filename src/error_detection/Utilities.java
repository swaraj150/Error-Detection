package error_detection;

public class Utilities {
    public static String binaryAddition (String str1, String str2) {
        StringBuffer result = new StringBuffer();
        int carry = 0;

        for (int i = str1.length() - 1; i >= 0; i--) {
            if (str1.charAt(i) == '0' && str2.charAt(i) == '0') {
                if (carry == 0) result.append("0");
                else {
                    result.append("1");
                    carry=0;
                }
            }
            else if (str1.charAt(i) == '0' && str2.charAt(i) == '1') {
                if (carry == 0){
                    result.append("1");

                }
                else {
                    result.append("0");
                    carry = 1;
                }
            }
            else if (str1.charAt(i) == '1' && str2.charAt(i) == '0') {
                if (carry == 0) result.append("1");
                else {
                    result.append("0");
                    carry = 1;
                }
            }
            else if (str1.charAt(i) == '1' && str2.charAt(i) == '1') {
                if (carry == 0) {
                    result.append("0");
                    carry = 1;
                }
                else {
                    result.append("1");
                    carry = 1;
                }
            }
        }

        if (carry == 1) {
            result = new StringBuffer(binaryAddition(new String(result.reverse()), "0001"));
        }
        else{
            result.reverse();
        }
        return result.toString();
    }
    public static String onesComplement(String s) {
        StringBuffer str1 = new StringBuffer(s);
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == '1') {
                str1.replace(i, i + 1, "0");
            } else {
                str1.replace(i, i + 1, "1");
            }
        }
        return str1.toString();
    }
}
