package app.Models;

import java.util.ArrayList;

import javax.ejb.Stateless;
import app.Util.Enums.OrderStatus;

@Stateless
public class RestaurantReport {

    private int earnedAmount;
    private int noOfCompeletedOrders;
    private int noOfCancelledOrders;

    public RestaurantReport(Restaurant restaurant) {
        earnedAmount = 0;
        noOfCancelledOrders = 0;
        noOfCompeletedOrders = 0;

        ArrayList<Order> orders = restaurant.getOrders();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderStatus() == OrderStatus.DELIVERED)
                noOfCompeletedOrders++;
            else if (orders.get(i).getOrderStatus() == OrderStatus.CANCELED)
                noOfCancelledOrders++;

            earnedAmount += orders.get(i).getTotalPrice();
        }
    }

    public int getEarnedAmount() {
        return earnedAmount;
    }

    public int getNumberOfCompletedOrders() {
        return noOfCompeletedOrders;
    }

    public int getNumberOfCancelledOrders() {
        return noOfCancelledOrders;
    }
}
