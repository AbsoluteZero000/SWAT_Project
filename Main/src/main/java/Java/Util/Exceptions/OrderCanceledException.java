package Java.Util.Exceptions;

public class OrderCanceledException extends Exception{
    public OrderCanceledException(){
        super("Order is already Cancelled, You can't change a Cancelled Order");
    }
}
