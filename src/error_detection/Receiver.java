package error_detection;


public class Receiver {
    private final StringBuffer data;
    private final StringBuffer extra;

    public Receiver (String data,String extra) {
        assert data!=null;
        this.data = new StringBuffer(data);
        this.extra=new StringBuffer(extra);
    }

    public boolean detectError (String type) {
        if (type == "Parity") return detectErrorByParity();
        else if(type=="CheckSum") return detectErrorByChecksum();
        else return  detectErrorByCRC();
    }

    private boolean detectErrorByParity () { 
        int count = 0;

        for(int i = 0; i < this.data.length(); i++) {
            if (this.data.charAt(i) == '1') count = count + 1;
        }
        return !(count % 2 == 0);
    }
    private boolean detectErrorByChecksum () {
        StringBuilder result = new StringBuilder(Utilities.binaryAddition(this.data.substring(0, 4), this.data.substring(4, 8)));
        result = new StringBuilder(Utilities.onesComplement(Utilities.binaryAddition(new String(result), this.data.substring(8))));
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '1') return true;
        }
        return false;
    }
    private boolean detectErrorByCRC(){
        return Utilities.binaryDivision(this.data.toString(), this.extra.toString()) != "0".repeat(extra.length());
    }
}
