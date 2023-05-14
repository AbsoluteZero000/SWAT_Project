<h1 align="center">Al-Akeel Food Ordering Platform</h1>

## **Introduction**

Al-Akeel Company has developed an online order management platform over the past 15 Years. That platform generally allows users to make online orders to a specific restaurant from a registered restaurant list. It also tracks and delivers such orders from the restaurant's locations to the customer. The platform serves clients from either a dedicated mobile application, web app or assisted from customer service over the phone. The platform allows restaurant staff to manage their restaurants specific orders. The platform integrates with Bank Misr as a payment gateway to full-fill online payments for orders. The platform integrates with Aramex shipment company to manage the delivery of orders. Recently the company started to face a set of issues mainly reliability issues where some times the platform reported to be down. Suffers from slow responses. They also faced a huge challenge when they needed to support mobile payment as well as support Al-Akeel specific shipment fleet to work side by side with the existing Aramex shipment.

### **Entities**

|   Entity   | Attributes                                                             |
| :--------: | ---------------------------------------------------------------------- |
|    User    | Id ,name, role                                                         |
|    Meal    | id, name , price, fk_restaurantId                                      |
|   Order    | Id, Item array, total_price, fk_runnerId, fk_restaurantId order_status |
|   Runner   | Id, name, status(available, busy),delivery_fees                        |
| Restaurant | Id, name, ownerId, list of meals                                       |

_Note: add any additional fields if needed_

### **System Functionalities:**

User can have 3 different roles Customer, RestaurantOwner, Runner roles Restaurant owner, customer, runner.

- **Sign up and Login for each role**
- **Restaurant owner**
  - Create restaurant menu
  - Edit restaurant: change menu meals for each restaurant
  - Get restaurant details by id
  - Create restaurant report: given a restaurant id print how much the restaurant earns (summation of total amount of all completed orders) , Number of completed orders, Number of canceled orders
- **Customer:**
  - Create order by customer
  - When creating an order select a random available runner from db and assign it to an order and convert his status to busy
  - Edit order [change an order’s items] make sure an order is not canceled and it is in the preparing state to be edited
  - List all restaurants
- **Runner**
  - Runner can mark an order is delivered and his status to available
  - Get number of trips completed by a runner make sure orders are not canceled
    and marked as completed

## **EndPoints**

TBD

|         Title         | Method | Link                                         |
| :-------------------: | :----: | -------------------------------------------- |
| Create Resturant Menu |  POST  | `http://localhost:8080/api/resturnat/create` |


