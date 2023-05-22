package app.Rest;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
import app.Util.Communication_Classes.LoginWrapper;
import app.Util.Communication_Classes.MenuWrapper;
import app.Util.Communication_Classes.RestaurantComm;
import app.Util.Communication_Classes.RunnerComm;
import app.Util.Communication_Classes.idsWrapper;
import app.Util.Exceptions.OrderCancelledException;
import app.Util.Exceptions.OrderDeliveredException;
import app.Util.Exceptions.UserAlreadyExistException;
import app.Util.Exceptions.WrongCredentialsException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class ApplicationApi {
    @Inject
    private UserService userService = new UserService();
    @Inject
    private RunnerService runnerService = new RunnerService();
    @Inject
    private CustomerService customerService = new CustomerService();
    @Inject
    private RestaurantOwnerService ownerService = new RestaurantOwnerService();
    @Inject
    private HttpServletRequest request;

    private User currentUser;

    @PostConstruct
    public void setUser(){
        try{Principal principal = request.getUserPrincipal();
        String login = principal.getName();
        currentUser = userService.findUserByName(login);
        if(currentUser == null){
            currentUser = new User(login);
            userService.addUser(currentUser);
        }}
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @PUT
    @Path("{id}/editMenu")
    public Restaurant editRestaurantMenu(@PathParam("id") int id, MenuWrapper menuWrapper) {
        return ownerService.editMenu(id, menuWrapper);
    }

    @POST
    @Path("addUser")
    public User addUser(LoginWrapper wrapper) throws UserAlreadyExistException {
        return userService.addUser(new User(wrapper));
    }

    @PermitAll
    @POST
    @Path("login")
    public User login(LoginWrapper loginWrapper) throws WrongCredentialsException {
        currentUser = userService.logIn(loginWrapper);
        return currentUser;
    }

    @PermitAll
    @POST
    @Path("signup")
    public User signup(LoginWrapper loginWrapper) throws UserAlreadyExistException, IOException{
        currentUser = userService.signup(loginWrapper);
        return currentUser;
    }

    @POST
    @Path("addRunner")
    public Runner addRunner(RunnerComm runner) {
        return runnerService.addRunner(runner);
    }

    @POST
    @Path("{id}/createOrder")
    public OrderDetails createOrder(@PathParam("id") int id, idsWrapper ids) {
        return customerService.createOrder(id, ids.ids);
    }

    @PUT
    @Path("{id}/editOrder")
    public OrderDetails editOrder(@PathParam("id") int id, idsWrapper ids)
            throws NullPointerException, OrderCancelledException, OrderDeliveredException {
        return customerService.editOrder(id, ids.ids);
    }

    @PUT
    @Path("{id}/cancelOrder")
    public Orders cancelOrder(@PathParam("id") int id) {
        return customerService.cancelOrder(id);
    }

    @GET
    @Path("{id}/getOrder")
    public OrderDetails getOrder(@PathParam("id") int id) {
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
    public RestaurantReport getRestaurantReport(@QueryParam("id") int id) {
        return new RestaurantReport(ownerService.getRestaurantDetails(id));
    }

    @GET
    @Path("getAllRestaurants")
    public ArrayList<Restaurant> getAllRestaurants() {
        return customerService.getAllRestaurants();
    }

    @GET
    @Path("/{id}/getMenu")
    public Set<Meal> getMenu(@PathParam("id") int id) {
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
}
