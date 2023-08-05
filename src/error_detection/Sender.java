package error_detection;

import java.util.Scanner;

public class Sender {
    public StringBuffer data;
    public StringBuffer extra;

    public Sender () {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the data: ");
        this.data = new StringBuffer(sc.nextLine());
//        sc.close();
    }

    public StringBuffer calculateParity () {
        int count = 0;

        for(int i = 0; i < this.data.length(); i++) {
            if (this.data.charAt(i) == '1') count = count + 1;
        }

        return (count % 2 == 1) ? new StringBuffer("1") : new StringBuffer("0");
    }


    public StringBuffer calculateChecksum () {
       return new StringBuffer(Utilities.onesComplement(Utilities.binaryAddition(this.data.substring(0, 4), this.data.substring(4))));
    }
}