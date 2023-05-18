package app.Util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import app.Models.Meal;
import app.Models.Orders;

public class OrderDetails {
    private LocalDateTime date;
    private ArrayList<Meal> itemsList;
    private double deliveryFees;
    private String runnerName;
    private Orders order;
    private double totalReceipt;

    public OrderDetails(Orders order) {
        this.order = order;
        date = LocalDateTime.now();
        itemsList = order.getItemsArray();
        // deliveryFees = order.getRunner().getDeliveryFees();
        // runnerName = order.getRunner().getName();
        totalReceipt = deliveryFees + order.getTotalPrice();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ArrayList<Meal> getItemsList() {
        return itemsList;
    }

    public void setItemsList(ArrayList<Meal> itemsList) {
        this.itemsList = itemsList;
    }

    public double getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(double deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    public double getTotalReceipt() {
        return totalReceipt;
    }

    public ArrayList<Object> getReceipt() {
        ArrayList<Object> receipt = new ArrayList<>();
        receipt.add(date);
        // receipt.add(order.getRestaurant().GetName());
        receipt.add(itemsList);
        receipt.add(deliveryFees);
        receipt.add(runnerName);
        receipt.add(totalReceipt);
        return receipt;
    }
}
