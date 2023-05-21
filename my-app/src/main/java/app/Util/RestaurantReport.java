package app.Util;


import app.Models.*;

public class RestaurantReport {

    private int earnedAmount;
    private int noOfCompeletedOrders;
    private int noOfCancelledOrders;

    public RestaurantReport(Restaurant restaurant) {
        earnedAmount = 0;
        noOfCancelledOrders = 0;
        noOfCompeletedOrders = 0;

        // Order[] orders = (Order[])restaurant.getOrders().toArray();
        // for (int i = 0; i < orders.length; i++) {
        //     if (orders[i].getOrderStatus() == OrderStatus.DELIVERED)
        //         noOfCompeletedOrders++;
        //     else if (orders[i].getOrderStatus() == OrderStatus.CANCELED)
        //         noOfCancelledOrders++;

        //     earnedAmount += orders[i].getTotalPrice();
        // }
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
