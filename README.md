## **Introduction**

Al-Akeel Company has developed an online order management platform over the past 15 Years. That platform generally allows users to make online orders to a specific restaurant from a registered restaurant list. It also tracks and delivers such orders from the restaurant's locations to the customer. The platform serves clients from either a dedicated mobile application, web app or assisted from customer service over the phone. The platform allows restaurant staff to manage their restaurants specific orders. The platform integrates with Bank Misr as a payment gateway to full-fill online payments for orders. The platform integrates with Aramex shipment company to manage the delivery of orders. Recently the company started to face a set of issues mainly reliability issues where some times the platform reported to be down. Suffers from slow responses. They also faced a huge challenge when they needed to support mobile payment as well as support Al-Akeel specific shipment fleet to work side by side with the existing Aramex shipment.

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

|         Title          | Method | Link                                                                   |
| :--------------------: | :----: | ---------------------------------------------------------------------- |
| Create Resturant Menu  |  POST  | `http://localhost:8080/SWAT_Project-1.0/api/createRestaurant`          |
| :-------------------:  | :----: | --------------------------------------------                           |
|     Add New Runner     |  POST  | `http://localhost:8080/SWAT_Project-1.0/api/addRunner`                 |
| :-------------------:  | :----: | --------------------------------------------                           |
|  Get Number Of Trips   |  GET   | `http://localhost:8080/SWAT_Project-1.0/api/getNumberOfTrips?id=2`     |
| :-------------------:  | :----: | --------------------------------------------                           |
| Get Restaurant Details |  GET   | `http://localhost:8080/SWAT_Project-1.0/api/getRestaurantDetails?id=1` |
| :-------------------:  | :----: | --------------------------------------------                           |
|  Get All Restaurants   |  GET   | `http://localhost:8080/SWAT_Project-1.0/api/getAllRestaurants`         |
| :-------------------:  | :----: | --------------------------------------------                           |
| Get Restaurant Report  |  GET   | `http://localhost:8080/SWAT_Project-1.0/api/getRestaurantReport?id=1`  |
| :-------------------:  | :----: | --------------------------------------------                           |
|       Edit Menu        |  PUT   | `http://localhost:8080/SWAT_Project-1.0/api/1/editMenu`                |
| :-------------------:  | :----: | --------------------------------------------                           |
|    Create New Order    |  POST  | `http://localhost:8080/SWAT_Project-1.0/api/1/createOrder`             |
| :-------------------:  | :----: | --------------------------------------------                           |
|     Edit An Order      |  PUT   | `http://localhost:8080/SWAT_Project-1.0/api/1/editOrder`               |
| :-------------------:  | :----: | --------------------------------------------                           |
|        get Menu        |  GET   | `http://localhost:8080/SWAT_Project-1.0/api/{id}/getMenu`              |
| :-------------------:  | :----: | --------------------------------------------                           |
|   Mark Order As Done   |  PUT   | `http://localhost:8080/SWAT_Project-1.0/api/markOrder?id=1`            |
| :-------------------:  | :----: | --------------------------------------------                           |
|      Cancel Order      |  PUT   | `http://localhost:8080/SWAT_Project-1.0/api/1/cancelOrder`             |
| :-------------------:  | :----: | --------------------------------------------                           |
|   Get Order Details    |  GET   | `http://localhost:8080/SWAT_Project-1.0/api/1/getOrder`                |
| :-------------------:  | :----: | --------------------------------------------                           |
|    Sign Up New User    |  POST  | `http://localhost:8080/SWAT_Project-1.0/api/signup`                    |
| :-------------------:  | :----: | --------------------------------------------                           |
|      Sign In User      |  POST  | `http://localhost:8080/SWAT_Project-1.0/api/login`                     |
