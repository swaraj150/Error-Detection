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
    private static String xOR(String str1,String str2){
        StringBuilder ans =new StringBuilder();
        for(int i=0;i<str1.length();i++){
            ans.append(String.valueOf((int)str1.charAt(i)^(int)str2.charAt(i)));
        }
        return ans.toString();
    }
    private static StringBuffer strip(StringBuffer str){
        int i;
        for(i=0;i<str.length();i++){
            if(str.charAt(i)=='1') break;
        }
        StringBuffer s=new StringBuffer(str.substring(i));
        return s;
    }
    public static String binaryDivision(String str1,String str2){
        int n1=str1.length();
        int n2=str2.length();
        int i=0,j=0;
        StringBuffer tempRemainder=new StringBuffer();
        StringBuffer tempDividend;
        String divisor;
        String remainder = null;
        while(i<n1){
            if(i+n2-j<n1){
                tempDividend=tempRemainder.append(str1, i, i+n2-j);
            }
            else{
                tempDividend=tempRemainder.append(str1.substring(i));
            }
            divisor=strip(tempDividend).length()==n2?str2:"0".repeat(n2);
            tempRemainder=new StringBuffer(xOR(tempDividend.toString(), divisor));
            remainder=tempRemainder.toString();
            tempRemainder=strip(tempRemainder);
            i=i+n2-j;
            j=tempRemainder.length();
        }
        assert remainder != null;
        return strip(new StringBuffer(remainder)).equals(new StringBuffer("")) ?"0".repeat(n2):remainder;
    }

    public static void main(String[] args) {
        System.out.println(binaryAddition("1010000000","0000000011"));
    }

}
