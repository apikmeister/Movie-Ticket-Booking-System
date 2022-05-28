public class Card extends Online {

    private int cardNo;

    public Card(String payType, String type) {
        super(payType, type);
        System.out.println("Please enter your card no :");
    }

    public Card(String payType, String type, int cardNo) {
        super(payType, type);
        this.cardNo = cardNo;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }
}
