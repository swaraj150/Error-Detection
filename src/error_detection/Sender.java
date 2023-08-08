package error_detection;
import java.util.Scanner;

public class Sender {
    public StringBuffer data;
    public StringBuffer extra;

    public StringBuffer crcGenerater;

    public Sender (String type) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the data: ");
        this.data = new StringBuffer(sc.nextLine());
        if(type=="CRC"){
            System.out.print("Enter the generator sequence: ");
            this.crcGenerater=new StringBuffer(sc.next());
        }
        else{
            this.crcGenerater=new StringBuffer();
        }

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

    public StringBuffer calculateCRC(){
        return new StringBuffer(Utilities.binaryDivision(this.data.append("0".repeat(this.crcGenerater.length()-1)).toString(),crcGenerater.toString()));
    }
}