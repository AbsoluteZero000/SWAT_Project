package app.Rest;

import javax.ejb.*;
import javax.inject.Inject;
import app.Models.Restaurant;
import app.Models.Runner;
import app.Models.User;
import app.Service.CustomerService;
import app.Service.RestaurantOwnerService;
import app.Service.RunnerService;
import app.Service.UserService;
import app.Util.OrderDetails;
import app.Util.Communication_Classes.OrderComm;
import app.Util.Communication_Classes.RestaurantComm;
import app.Util.Communication_Classes.RunnerComm;
import app.Util.Communication_Classes.UserComm;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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

    //needs to be parsed correctly
    @POST
    @Path("addUser")
    public User addUser(UserComm userComm){
        return userService.addUser(new User(userComm));
    }

    @POST
    @Path("addRunner")
    public Runner addRunner(RunnerComm runner){
        return runnerService.addRunner(runner);

    }

    @POST
    @Path("createOrder")
    public OrderDetails createOrder(OrderComm orderComm ) {
        return customerService.createOrder(orderComm);
    }

    //needs some work
    @POST
    @Path("createRestaurant")
    public Restaurant createRestaurant(RestaurantComm restComm){
        return ownerService.addRestaurant(restComm);
    }

}
