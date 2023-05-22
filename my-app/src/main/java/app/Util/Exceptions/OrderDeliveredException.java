package app.Util.Exceptions;

public class OrderDeliveredException extends Exception {
    public OrderDeliveredException() {
        super("order already delivered");
    }
}
