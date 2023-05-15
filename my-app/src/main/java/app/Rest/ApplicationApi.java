package app.Rest;

import java.util.*;

import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.inject.Inject;
import javax.validation.constraints.Past;

import app.Models.*;
import app.Models.Restaurant;
import app.Models.RestaurantReport;
import app.Service.*;
import app.Util.OrderDetails;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class ApplicationApi {
    @Inject
    CustomerService customerService;
    @Inject
    RunnerService runnerService;
    @Inject
    ResturantOwnerService ownerService;



    @POST
    @Path("createRestaurant")
    @RolesAllowed("RestaurantOwner")
    public void createMenu(Restaurant restaurant) {
        try {
            ownerService.createRestaurantMenu(restaurant);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @PUT
    @RolesAllowed("RestaurantOwner")
    @Path("editRestaurantMenu")
    public void editRestaurantMenu(ArrayList<Object> Args) {
        Restaurant tempRestaurant = new Restaurant();
        tempRestaurant.setMeals((ArrayList<Meal>) Args.get(1));
        tempRestaurant.setId((int) Args.get(0));
        try{
        ownerService.editRestaurantMenu(tempRestaurant);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @GET
    @RolesAllowed("RestaurantOwner")
    @Path("getRestaurantDetails")
    public Restaurant getRestaurantDetails(int id) {
        return ownerService.getRestaurantDetails(id);
    }

    @GET
    @RolesAllowed("RestaurantOwner")
    @Path("getRestaurantReport")
    public RestaurantReport getRestaurantReport(int id) {
        Restaurant restaurant = this.getRestaurantDetails(id);
        return ownerService.getRestaurantReport(restaurant);
    }

    @Past
    @RolesAllowed("Customer")
    @Path("createOrder")
    public OrderDetails createOrder(Order order) {
        return customerService.createOrder(order);
    }

    @PUT
    @RolesAllowed("Customer")
    @Path("editOrder")
    public void editOrder(ArrayList<Object> Args) {
        Order order = new Order();
        order.setId((int) Args.get(0));
        order.setMealList((ArrayList<Meal>) Args.get(1));
    }

    @GET
    @Path("getAllRest")
    @RolesAllowed("Customer")
    public ArrayList<Restaurant> getAllRestaurants() {
        return customerService.getAllRestaurants();
    }

    @POST
    @Path("markOrder")
    @RolesAllowed("Runner")
    public void markOrder(int id) {
        try {
            runnerService.markOrder(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GET
    @Path("NoOfTrips")
    @RolesAllowed("Runner")
    public int getNumberOfTrips(int id) {
        return runnerService.getNumberOfTrips(id);
    }
}
