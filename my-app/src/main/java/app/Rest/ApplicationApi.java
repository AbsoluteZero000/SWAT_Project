package app.Rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.*;
import javax.inject.Inject;
import app.Models.Restaurant;
import app.Models.Runner;
import app.Models.User;
import app.Models.Meal;
import app.Models.Orders;
import app.Service.CustomerService;
import app.Service.RestaurantOwnerService;
import app.Service.RunnerService;
import app.Service.UserService;
import app.Util.OrderDetails;
import app.Util.RestaurantReport;
import app.Util.Communication_Classes.EditRestComm;
import app.Util.Communication_Classes.OrderComm;
import app.Util.Communication_Classes.RestaurantComm;
import app.Util.Communication_Classes.RunnerComm;
import app.Util.Communication_Classes.UserComm;
import app.Util.Exceptions.OrderCancelledException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;

@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class ApplicationApi {
    @Inject
    UserService userService = new UserService();
    @Inject
    RunnerService runnerService = new RunnerService();
    @Inject
    CustomerService customerService = new CustomerService();
    @Inject
    RestaurantOwnerService ownerService = new RestaurantOwnerService();

    @POST
    @Path("init")
    public void init() {
        userService.init();
    }

    @PUT
    @Path("editMenu")
    public Restaurant editRestaurantMenu(EditRestComm editRestComm) {
        return ownerService.editRestaurantMenu(editRestComm);
    }

    @POST
    @Path("addUser")
    public User addUser(UserComm userComm) {
        return userService.addUser(new User(userComm));
    }

    @POST
    @Path("addRunner")
    public Runner addRunner(RunnerComm runner) {
        return runnerService.addRunner(runner);

    }

    @POST
    @Path("createOrder")
    public OrderDetails createOrder(OrderComm orderComm) {
        return customerService.createOrder(orderComm);
    }

    @PUT
    @Path("editOrder")
    public OrderDetails editOrder(Orders order) throws NullPointerException, OrderCancelledException {
        return customerService.editOrder(order);
    }

    @GET
    @Path("getOrder")
    public OrderDetails getOrder(@QueryParam("id") int id) {
        return new OrderDetails(customerService.getOrder(id));
    }

    @POST
    @Path("createRestaurant")
    public Restaurant createRestaurant(RestaurantComm restComm) {
        return ownerService.addRestaurant(restComm);
    }

    @GET
    @Path("getRestaurantDetails")
    public Restaurant getRestaurantDetails(@QueryParam("id") int id) {
        return ownerService.getRestaurantDetails(id);
    }

    @GET
    @Path("getRestaurantReport")
    public RestaurantReport getRestaurantReport(int id) {
        return ownerService.getRestaurantReport(id);
    }

    @GET
    @Path("getAllRestaurants")
    public ArrayList<Restaurant> getAllRestaurants() {
        return customerService.getAllRestaurants();
    }

    @GET
    @Path("getMenu")
    public Set<Meal> getMenu(int id){
        return customerService.getMenu(id);
    }

    @GET
    @Path("getNumberOfTrips")
    public int getNumberOfTrips(@QueryParam("id") int id) {
        return runnerService.getNumberOfTrips(id);
    }

    @PUT
    @Path("markOrder")
    public OrderDetails markOrder(@QueryParam("id") int id) {
        return runnerService.markOrder(id);
    }

    @POST
    @Path("addMenu")
    public Restaurant createMenu(OrderComm orderComm) {
        return ownerService.createMenu(orderComm);
    }

    @GET
    @Path("getOrders")
    public List<Orders> getOrders(int id) {
        return customerService.getListOfOrders(id);
    }
}
