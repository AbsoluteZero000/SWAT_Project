package app.Util;

import java.util.Set;

import app.Models.*;
import app.Util.Enums.OrderStatus;

public class RestaurantReport {

    private int earnedAmount;
    private int noOfCompeletedOrders;
    private int noOfCancelledOrders;

    public RestaurantReport(Restaurant restaurant) {
        earnedAmount = 0;
        noOfCancelledOrders = 0;
        noOfCompeletedOrders = 0;

        Set<Orders> orders = restaurant.getOrders();
        for (Orders order: orders) {
            if (order.getOrderStatus() == OrderStatus.DELIVERED)
                noOfCompeletedOrders++;
            else if (order.getOrderStatus() == OrderStatus.CANCELED)
                noOfCancelledOrders++;

            earnedAmount += order.getTotalPrice();
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
