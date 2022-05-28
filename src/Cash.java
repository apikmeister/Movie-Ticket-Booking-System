public class Cash extends Payment {

    public Cash(String payType) {
        super(payType);
    }

    @Override
    public String toString() {
        return "Please pay at counter by showing your booking ID : ";
    }

}
