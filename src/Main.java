import error_detection.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

            Sender sx = new Sender();

            Scanner sc = new Scanner(System.in);

            int choice;

            while(true) {
                System.out.print("1.Parity Check\n2.Checksum\nEnter choice: ");
                choice = sc.nextInt();
                if (choice >=0 || choice < 3) break;
                else System.out.println();
            }


            String type = switch (choice) {
                case 1 -> "SingleBit";
                case 2 -> "MultiBit";
                default -> "";
            };


            Receiver rx = Network.transmit(sx, type);

            boolean isError = rx.detectError(type);

            if (isError) System.out.println("Data transmitted to the receiver had an error!");
            else System.out.println("Data was successfully transmitted to receiver!");
    }
}