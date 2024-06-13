import java.io.Serializable;

public class BirthdayGift implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private String gift;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }
}
