# Step1- Create a Project with following dependencies
Maven
Devtool
Spring web
Spring Data Mongodb
Lombak
Used MongoDb Compass for server conection, and MongoDB Atlas for PROD deployment
# Step2- Create a model Package *.model
Add all required annotation
@Setter/@Getter/@AllArgsConstructor/@NoArgsConstructor
@Document(collection="todos") -- #this is for mongoDB
@Id
ad add all private fields required

# Step3- Create a repository package *.repository
@Repository
extends MongoRepository<TodoDto(Model.java), String>
All queries added here

# Step4- Create a controller package *.controller
@RestController
@Autowired
private TodoRepository todoRepos;  // autowire your repos
Add all endpoints
@GetMapping("/todos")
@PostMapping("/todos")-- in postman make sure to add raw data as Json and add the fields as in model
@GetMapping("/todos/{id}")--update
@DeleteMapping("/todos/{id}")

# NOTE-Make sure to install MOngoDB and run the server before running colletion on postman
https://www.prisma.io/dataguide/mongodb/setting-up-a-local-mongodb-database#:~:text=Open%20up%20MongoDB%20Compass%20to%20begin.&text=If%20you%20click%20Connect%20without,MongoDB%20server%20you%20are%20running.

# Step5--Now test the code till now in postman(create a new collection)
# Step6- Need to add Validation dependency in pom.xml and also create a configuration file.
Create a new package *config
@Configuration - this will tell prin that its a bean for configuration
Register it with @bean
Also add @NotNull in model fields

# Step7- Create a package *.service and and create interface *service.java and its methods implementation java class *serviceImpl.java
@Service
So flow will be
-create a method in interface *service.java
-get the method overide(implementation) in java class *serviceImpl.java
-Refactor it in Controller with respective endpoints


