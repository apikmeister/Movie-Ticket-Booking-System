public class FPX extends Online {

    private int OTP;

    public FPX(String payType, String type, int OTP) {
        super(payType, type);
        this.OTP = OTP;
    }

    public int getOTP() {
        return OTP;
    }

    public void setOTP(int OTP) {
        this.OTP = OTP;
    }

    @Override
    public String toString() {
        return "FPX " +
                "OTP Number = " + OTP;
    }
}
