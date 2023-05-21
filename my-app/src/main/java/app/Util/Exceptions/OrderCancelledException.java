package app.Util.Exceptions;

public class OrderCancelledException extends Exception {
    public OrderCancelledException() {
        super("Order is already Cancelled, You can't change a Cancelled Order");
    }
}
