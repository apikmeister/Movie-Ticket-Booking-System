public class eWallet extends Online {

    private String code;

    public eWallet(String payType, String type, String code) {
        super(payType, type);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "eWallet" +
                "code = " + code;
    }
}
