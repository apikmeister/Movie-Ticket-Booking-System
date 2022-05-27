public class Payment {

    private String payType;

    public Payment(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payType='" + payType + '\'' +
                '}';
    }
}
