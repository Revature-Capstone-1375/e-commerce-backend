# Revature Swag E-Commerce Web App
Our website application sells Revature brand clothing and accessory items.
# Front-End Components
## Cart
### Usage
 A component that calculates the cost of items. Adding/removing quantities of those items. Removing those items completely from the cart. The functions in this class are unique to each id.
### Attributes
Takes in loginUser object to see if admin or not.

### Functions

void editQuantityUp(id : any) - increments quantity.
```JSX
<KeyboardArrowUpOutlined onClick={() => editQuantityUp(product.id)} />
```

void editQuantityDown(id : any) - decrements quantity. 
```JSX
 <KeyboardArrowDownOutlined onClick={() => editQuantityDown(product.id, product.image)} />
```
void removeById(id: any, image: any) - removing the product from the card
```JSX
<CancelPresentationOutlined onClick={() => removeButton(product.id, product.image)} />
```

## Create Product
The Create Product component allows the user to create their own product using UI components that allows them to create a new product, then post it to the API when they are done, using the upsert API call.
### Parameters
userService: UserService -  A user service to update
### Usage
The Create Product component is declared in the Route Paths, and should be declared in the location in which you want a full page HTML page to be visible.
```JSX
<Route path="/product/create" element={<CreateProduct loginUser={user}/>} />
```
### Attributes
Login User - sends the user to Navbar component, and uses the user in the API call to 

### Functions
`void changeImage(event: any)` - changes the image in the image preview screen

`void handleChangeURL(url:any)` - sets the URL state variable with the target value

`void handleCreate(event: React.FormEvent<HTMLFormElement>)` - gets the data inputted from the form and passes it to the API


## Update Product
The Update Product component allows a user logged in as an Admin to update a product. The admin can update a product’s name, description, price, quantity and image url by clicking on the arrow button visible when hovering over the product that needs to be updated. 


### Usage
The path to access the Update Product can be found in the AppRoutes component.
```JSX
<Route path="/product/:id/update" element={<UpdateProduct loginUser={user}/>} />
```

### Attributes
Login User = Takes in the product id and returns information about that specific product.

### Functions
`void changeImage(event: any)` - changes the image in the image preview screen

`void handleChangeURL(url:any)` - sets the URL state variable with the target value

`void handleCreate(event: React.FormEvent<HTMLFormElement>)` - gets the data inputted from the form and passes it to the API

## Display Products
### Usage
The Display Products component allows anyone to view the product cards. The admin can remove, view, and add product cards. The display of product cards is the result of an API get request and uses mapping to display the product card with each unique result.
```JSX
 <Route path="/" element={<DisplayProducts loginUser={user} />} />
```

### Attributes
Login User - takes in a user object to pass it into Navbar to check if user or admin. 
Return

### Functions
void removeButton(id:any, image:any) - function to remove a product from the products page. Requires admin role to use.
```JSX
 {products.map((item) => (<><ProductCard product={item} key={item.id} loginUser={loginUser}}
```

## Product Card
### Usage
This component lets us increment and decrement how many quantities of an item we want. Allows us to add items to our cart. Allows us to navigate to update product only as admin.
Arguments:Takes in props with productProps type.
Return: Returns our product card container with increment/decrement, navigate, and add to cart button embedded in container.

### Functions
`boolean addItemToCart(product: Product)` - adds item to our cart list and updates it.
```JSX
<ShoppingCartOutlined onClick={() => {addItemToCart({...props.product, quantity: counter})}} />
```

`void incrementCount()` - lets us increment how many numbers of items we want.
```JSX
<KeyboardArrowUpOutlined onClick={incrementCount} />
```

`void decrementCount(void)` - lets us decrement how many numbers of items we want.
```JSX
<KeyboardArrowDownOutlined onClick={decrementCount} />
```

## Login

### Usage
Login component allows users to login and have their information passed through the function to be used on the entire frontend in order to auth for various features.  Everytime a user logs in it is passed to AppRoutes where it is saved in a state variable.
```JSX
<Route path="/login" element={<Login updateLoginUser={setUser} />} />
```

### Attributes
It takes one argument to pass in the logged in user to set the user for the state variable.
```JSX
<Login updateLoginUser={setUser} />
```

### Functions
`void handleSubmit(event: React.FormEvent<HTMLFormElement>)` - Uses API login with the email and password.

`void handleData(data: User)` - takes in the responce of the request to update login user.

## Address Form
### Usage
Allow user to put in their address and the website to keep track of it.

### Attributes
Takes in props of type addressFormProps.


### Functions
`void handlesubmit(event: React.FormEvent<HTMLFormElement>))`  - takes in the input of the form and stores those input into a variable then passes the values of that variable to the updateAddress function. Then calls the handleNext function that will trigger our next case statement.


## Navbar
### Usage
The Navbar has two states, user is logged out, and user is logged in. When the user is logged out the navbar will display ‘Revature Swag Shop’, ‘Register’, ‘Sign In’, and Cart. When the user is logged out the Navbar will display ‘Revature Swag Shop’, ‘Profile’, ‘Logout’, and Cart.

### Attributes
The Navbar takes in a User Object and is passed to each component that displays the Navbar.
```JSX
<Navbar updateLoginUser={loginUser}/>
```
### Functions
`void searchBar()` - Returns a search bar component to be displayed to the user.

## Edit Profile
### Usage
Edit Profile allows the user to make changes to any part of their profile including first name, last name, email, and password. To change profile information, the user will type the new information in the appropriate field and click edit profile. The changes will then be persisted to the database, so the user can log in with their new credentials after they have been changed. After logging in again and returning to the edit profile page, the user will see the updated information displayed.
After the user clicks edit profile, that registers as a FormEvent the event handler, handleSubmit is invoked.Data.get updates the values in the user object, which is persisted to the api, then logs out of the api.The value variable "navigate" user is the useNavigate() hook, which navigates the user back to the home page.

### Functions
`void handleDelete()` - Makes a DELETE request to the /user/loginUser.id with the user as the payload
`void handleSubmit(event: React.FormEvent<HTMLFormElement>)` - Sends a PUT request to the users endpoint/loginUser.id to subtmit the request, and updates the login user with the responce payload. 
 
## Register
Allows a new user to create an account by providing the following: First name, Last name, Email and Password. 

### Usage
Once the new user provides the required inputs, the user would then click on the Signup button to register the account and use the credentials for the login page.
Functions:

### Functions
`void handleSubmit(event: React.FormEvent<HTMLFormElement>))` - Takes in the inpjut values from the form then sends a GET request to the API to register a new user.  Then if navigates the user to the login page.

## Search Products
### Usage
The path to access the Search Products can be found in the AppRoutes component.
```JSX
<Route path="/search" element={<SearchProducts loginUser={user}/>} />
```
The Search Products component allows any user to search for products. The user can search for a product by inputting the desired product name inside the search bar and validating search by clicking on the magnifier icon.  

### Attributes
Any User = Takes in the item name and returns information about all products that product name includes the string inputted in the search bar.

## Product Detail
The Product Detail component allows the user to view the product detail on its name, quantity, price and description.  You can also adjust the number of items you want to add in a cart by clicking either the upward or downward arrow button.

### Usage
The path to access the ProductDetail can be found in the AppRoutes component.
You can also access the page from the product list page by hovering the mouse cursor over the product image and clicking on the magnifying glass icon.
```JSX
<Route path="/product/:id" element={<ProductDetail loginUser={user}/>} />
```

### Attributes
Any: LoginUser -

### Funcitons
`void incrementsCount()` - increments the count of quantity to add to the cart
`void decrementCount()` - Decrements the count of the quantity to add to the cart
`void getProduct(id: number)` - gets the product using an API call to the backend and sets the product to the current product that it just recieved
`boolean addItemTocart(props: {})` - Adds an item to the cart based on the quantity, returns if the product sucessfully added or not.


## App Routes
Provides different URL routes to send the user to different components of the application. 

### Usage
The user can type in a specific URL in the browser and if the URL path matches any of the routes in the router file then the user will be sent to that specific route inside the application.  This is the component of the application that matches with the URL path. If there is not a URL path specified then the user is directed to the home page.

## Dark Mode
Provides the end user with the ability to change the webpage from a “light” to “dark” theme to suit their needs. This button is located at the top left of the webpage. To enable “dark mode” the button needs to only be clicked once. From then every page visited thereafter will be updated to reflect the users theme choice.

### Functions
`void setDarkFunction()` - Sets the theme to dark
`void setLight()` - sets the theme to light
`void togglesTheme()` - Toggles the theme from dark to light, and vice versa

# Front-end API Libraries
## Auth Service 
A small library of functions that the user can utilize that allow the user to make API calls that come preconfigured
### Usage
It is used to login and logout of the application.  It is primarily used in the Login component, and in the Navbar for logging out of the API.
```JSX
const response = await apiLogin(`${data.get('email')}`, `${data.get('password')}`);
```

### Functions
`{status: any, payload: any} apiLogin(email:string, password: string)` - Logs the user into the application at the given URL with the given email and password as arguments. Returns the status response along with the user.

`{status: any, payload: any} apiLogout(promice: Promise<eCommerceApiResponse>)` - Logs the user out of the application at the given URL, does not require argument.  Returns the status response and the logged out user.

`{status: any, payload: any} apiRegister(firstName: string, lastName:string)` - Registers a new user for the application using your first name, last name, email, and password as arguments for the function.  Returns the status response along with the newly registered user.

## E-Commerce Client
Configures the API to send HTTP requests with Axios.  Implements allowing origin, and hard codes in the backend URL.  It is primarily used in the two API libraries(products services and auth services).

## Product Service
This is a small library that provides to the user a list of API calls that allow the user to edit the list of products.  All functions require the user to be logged into the application to use.

### Usage
Most of these functions are utilized in areas in which products are either benign received by the user or set by the user.
```JSX
const result = await apiGetProductById(parseInt(intId))
```

### Functions
`{status: any, payload: any} apiGetAllProducts()` - Takes no arguments and returns a list of products in the payload along with a response code.

`{status: any, payload: any} apiGetProductById(id: number)` - Argument is an id number for what product the user wants.  Returns a payload containing the product, or undefined if there is no product with that id, and returns a status.

`{status: any, payload: any} apiUpsertProduct(noIdProduct: noIdProduct)` - If the user passes a product as an argument which already has a taken id then it updates the product, but if the user passes a product that does not have a set id then it creates a new product with the given product information.  Returns a status code and the updated or created product.

`{status: any, payload: any} apiPurchase(products: {id: number, quantity: number}[])` - The argument is a list of products that are defined as an id number and a quantity to purchase.  The return is the status code and a list of products that have been purchased.

`{status: any, payload: any} apiDeleteProduct(id: number)` - The argument is an id number for what product the user wants to delete.  The return is the status code and what product has been deleted.
 
# Back-End
## EcommerceApplication

### Usage:
```Java
@SpringBootApplication
public class ECommerceApplication {
```
@SpringBootApplication combines @EnableAutoConfiguration and @ComponentScan functionalities into one to create a run-ready application.
Methods:
The main method is the entry point.
Arguments:


## Database
First demo test to test the hibernate:

Creates the products that could be added and removed from the cart
```SQL
INSERT INTO product (id, quantity, price, description, image, name) VALUES (...);
```
Creates the template for the user profiles
```SQL
INSERT INTO users (id, email, password, first_name, last_name) VALUES (...);
```

## Authcontroller
Responsiable for autherntication, and where the user sends the login, logout and register.
### Arguments
`authService: AuthService` - The authentication service that managaes the service layer for authentification
### Methods
```Java
@PostMapping("/login")
public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest, HttpSession session)
```
- Logs the given user into the API based on the login request given from the responce body

```Java
@PostMapping("/logout")
public ResponseEntity<Void> logout(HttpSession session)
```
- Logs the user out of the session

```Java
@PostMapping("/register")
public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest)
```
- Regiseters the user for the application

## UserController
### Arguments
`userService: UserService` - The user service that manages the servic layer for the user
### Methods
```Java
@PostMapping
public User createUser(@RequestBody User user)
```
- Creates a new user

```Java
@DeleteMapping("/{userId}")
public void deleteUserById(@PathVariable Integer userId)
```
- Deletes a user based on the id given in the path variables

```Java
PutMapping("/{userId}")
public User updateUser(@RequestBody User user, @PathVariable Integer userId)
```
- Updates the given user based on the path varaible, with the given user in the request body.

## ProductController
### Arguments
`productService: ProductService` - The product service that manages the service layer for the user
### Methods
```Java
@GetMapping("/{id}")
public ResponseEntity<Product> getProductById(@PathVariable("id") int id)
```
-Returns the product by the id given, returns null if the product was not found.

```Java
@GetMapping
public ResponseEntity<List<Product>> getInventory()
```
- Returns all of the products in inventory.

```Java
@PutMapping
`public ResponseEntity<Product> upsert(@RequestBody Product product)
```
- Updates the product with the given product if the id is already taken, if the product is not taken then it creates a new product.  Returns the new product.

```Java
@PatchMapping
public ResponseEntity<List<Product>> purchase(@RequestBody List<ProductInfo> metadata)
```
- Purchases the products if the user is able to and all of the products are in stock.

```Java
@DeleteMapping("/{id}")
public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id)
```
- Deletes the product with the given it.  If no product is found, then it returns null.

## Auth Service
### Arguments
`userService: UserService` - User service to take and and check if the user is valid.

### Methods 
`public Optional<User> findByCredentials(String email, String password)` - Finds and returns a user based on teh given email and password, if none is found, then it returns null.

`public User register(User user)` - registers a user with the given user credentials.

## Product service
### Arguments
productRepository: ProductRepository - The product repository that the service layer is supposed to be referencing for its products data.

### Methods
`public List<Product> findAll()` - Finds all of the products and then returns it.

`public Optional<Product> findById(int id)` - Finds a product based on the id given, returns null if the id has not been found.

`public Product save(Product product)` - Saves the given product to the product repository.

`public List<Product> saveAll(List<Product> productList, List<ProductInfo> metadata)` - saves a list of products to the product repository

`public void delete(int id)` - Deletes a product from the product repostiroy based on the id passed to it.

## User Service
### Arguments
userRepository: UserRepository - The given user repository is the repositry that keeps track of object persistancy.

### Methods
`public Optional<User> findByCredentials(String email, String password)` - Finds a user based on if the username and the password match the entry in the database.

`public User save(User user)` - Saves a new user in the database or updates an existing one and returns the entry.

`public User createUser(User user)` - Saves a new user in the database or updates an existing one and returns the entry.

`public void deleteUserById(Integer userId)` - Deletes a user with the given user id, or throws an exception if the user cannot be found.

`public User updateUser(User user, Integer userId)` - Updates the user with the given user id with the new user that was also given.  Returns the updated user or throws an exception if the user cannot be found.

## Product Repositoy
Extends the JPARepository.  Keeps track of the persistant databases and allows managing of the products table.

## User Repository
Extends the JPARepository.  Keeps track of the persistant databases and allows managing of the users table.

### Methods
`Optional<User> findByEmailAndPassword(String email, String password)` - Utilizes the Spring JPA to signify that the JPA should find a user in the users table that has a matching email and password.
