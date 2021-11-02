public class ArabNumber implements Number {

    private int value;

    ArabNumber(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
