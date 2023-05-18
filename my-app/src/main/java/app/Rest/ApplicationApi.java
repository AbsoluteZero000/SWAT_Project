package app.Rest;

import java.util.*;
import javax.ejb.*;
import javax.inject.Inject;
import app.Models.*;
import app.Util.RestaurantReport;
import app.Util.Communication_Classes.RunnerComm;
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
    public void editRestaurantMenu(int id, Set<Meal> meals) {
        Restaurant restaurant = new Restaurant();
        restaurant.setMeals(meals);
        restaurant.setId(id);
        try{
        ownerService.editRestaurantMenu(restaurant);
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
        return new Restaurant("name of the restaurant", new User("email", "name", "role"));
        //return ownerService.getRestaurantDetails(id);
    }

    @GET
    @Path("getRestaurantReport")
    public RestaurantReport getRestaurantReport(int id) {
        Restaurant restaurant = this.getRestaurantDetails(id);
        return ownerService.getRestaurantReport(restaurant);
    }

    @POST
    @Path("createOrder")
    public OrderDetails createOrder(ArrayList<Meal> meals, int resturantId ) {

        Order order = new Order();
        order.setMealList(meals);
        return customerService.createOrder(order, resturantId);
    }


    @PUT
    @Path("editOrder")
    public Order editOrder(int id, ArrayList<Meal> meals){
        try{
        customerService.editOrder(id, meals);
        return customerService.getOrder(id);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new Order();
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
    public void addRunner(RunnerComm runner){
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
