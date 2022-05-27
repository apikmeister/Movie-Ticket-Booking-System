public class Card extends Online {

    private String cardNo;

    public Card(String payType, String type) {
        super(payType, type);
        System.out.println("Please enter your card no :");
    }

    public Card(String payType, String type, String cardNo) {
        super(payType, type);
        this.cardNo = cardNo;
    }
}
