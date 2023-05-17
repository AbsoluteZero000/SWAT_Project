package app.Rest;

import java.util.*;
import javax.ejb.*;
import javax.inject.Inject;
import app.Models.*;
import app.Models.Restaurant;
import app.Util.RestaurantReport;
import app.Util.Communication_Classes.UserComm;
import app.Service.*;
import app.Util.OrderDetails;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class ApplicationApi {

    @Inject
    CustomerService customerService = new CustomerService();
    @Inject
    RunnerService runnerService = new RunnerService();
    @Inject
    ResturantOwnerService ownerService = new ResturantOwnerService();
    @Inject
    UserService userService = new UserService();


    @POST
    @Path("createRestaurant")
    public void createMenu(Restaurant restaurant) {
        try {
            ownerService.createRestaurantMenu(restaurant);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @PUT
    @Path("editRestaurantMenu")
    public void editRestaurantMenu(ArrayList<Object> Args) {
        Restaurant tempRestaurant = new Restaurant();
        tempRestaurant.setMeals((Set<Meal>) Args.get(1));
        tempRestaurant.setId((int) Args.get(0));
        try{
        ownerService.editRestaurantMenu(tempRestaurant);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @POST
    @Path("addUser")
    public User addUser(UserComm userComm){
        User user = new User(userComm);
        userService.addUser(user);
        return user;
    }
    @GET
    @Path("getRestaurantDetails")
    public Restaurant getRestaurantDetails(int id) {
        return ownerService.getRestaurantDetails(id);
    }

    @GET
    @Path("getRestaurantReport")
    public RestaurantReport getRestaurantReport(int id) {
        Restaurant restaurant = this.getRestaurantDetails(id);
        return ownerService.getRestaurantReport(restaurant);
    }

    @POST
    @Path("createOrder")
    public OrderDetails createOrder(Order order) {
        return customerService.createOrder(order);
    }


    @PUT
    @Path("editOrder")
    public void editOrder(ArrayList<Object> Args) {
        Order order = new Order();
        order.setId((int) Args.get(0));
        order.setMealList((ArrayList<Meal>) Args.get(1));
    }

    @GET
    @Path("getAllRest")
    public ArrayList<Restaurant> getAllRestaurants() {
        return customerService.getAllRestaurants();
    }

    @POST
    @Path("markOrder")
    public void markOrder(int id) {
        try {
            runnerService.markOrder(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @POST
    @Path("AddRunner")
    public void addRunner(Runner runner){
        System.out.println("I'm HERE");

        runnerService.addRunner(runner);
    }

    @GET
    @Path("Test")
    public String test(){
        return runnerService.test();
    }

    @GET
    @Path("NoOfTrips/{id}")
    public int getNumberOfTrips(int id) {
        return runnerService.getNumberOfTrips(id);
    }
}
