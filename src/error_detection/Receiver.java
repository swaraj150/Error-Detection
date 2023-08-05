package error_detection;


public class Receiver {
    private final StringBuffer data;

    public Receiver (String data) {
        this.data = new StringBuffer(data);
    }

    public boolean detectError (String type) {
        if (type == "SingleBit") return detectErrorByParity();
        else return detectErrorByChecksum();
    }

    private boolean detectErrorByParity () { 
        int count = 0;

        for(int i = 0; i < this.data.length(); i++) {
            if (this.data.charAt(i) == '1') count = count + 1;
        }
        return !(count % 2 == 0);
    }
    private boolean detectErrorByChecksum () {
        StringBuffer result = new StringBuffer(Utilities.binaryAddition(this.data.substring(0, 4), this.data.substring(4, 8)));
        result = new StringBuffer(Utilities.onesComplement(Utilities.binaryAddition(new String(result), this.data.substring(8))));
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '1') return true;
        }
        return false;
    }
}
