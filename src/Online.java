public class Online extends Payment {

    private String type;

    public Online(String payType, String type) {
        super(payType);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Online " +
                "type = " + getType() ;
    }
}
