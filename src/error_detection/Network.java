package error_detection;

import java.util.Random;

public class Network {
    public static Receiver transmit (Sender s, String type) {
        String data;

        if(type == "SingleBit") {
            s.extra=s.calculateParity();
            data = induceSingleBitError(new String(s.data) + new String(s.extra));
        }
        else {
            s.extra=s.calculateChecksum();
            data = induceMultiBitError(new String(s.data) + new String(s.extra));
        }

        return new Receiver(data);
    }

    private static String induceSingleBitError (String data) {
        Random rand=new Random();
        int induceError = rand.nextInt(2);
        if (induceError == 0) {
            System.out.println("No error induced");
            return data;
        }

        StringBuffer newData = new StringBuffer(data);
        System.out.println("Original data "+newData);
        int pos=rand.nextInt(data.length());
        if (newData.charAt(pos) == '1') newData.replace(pos, pos + 1, "0");
        else newData.replace(pos, pos + 1, "1");
        System.out.println("With error "+newData);
        return new String(newData);
    }

    private static String induceMultiBitError (String data) {
        Random rand=new Random();
        int induceError = rand.nextInt(2);
        if (induceError == 0) {
            System.out.println("no error");
            return data;
        }

        StringBuffer newData = new StringBuffer(data);
        System.out.println("Original data "+newData);
        int numberOfBits = rand.nextInt(1,data.length());
        System.out.println("number of flips: "+numberOfBits);

        for (int i = 0; i < numberOfBits; i++) {
            int pos=rand.nextInt(data.length());
            if (newData.charAt(pos) == '1') newData.replace(pos, pos + 1, "0");
            else newData.replace(pos, pos + 1, "1");
        }
        System.out.println("With error "+newData);

        return new String(newData);
    }
}
