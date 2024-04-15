# CS 180 GroupProject

Instructions: <br>
This project implements five different classes, five different interfaces, and two custom exceptions for the backend of our social media platform


Samantha Grief - Submitted Code
<br>

<h2> Phase 1</h2>

<b>
Excepetion: BlockedUserException
</b>

extends Exception

Constructor:

| Access Modifier | Constructor Name      | Input Parameters | Description                                                                                  |
|-----------------|-----------------------|------------------|----------------------------------------------------------------------------------------------|
| public          | BlockedUserException  | String message   | Calls the constructor of the exception superclass with the message passed in as a parameter  |

<b>
Excepetion: InvalidPasswordException
</b>

extends Exception

Constructor:

| Access Modifier | Constructor Name           | Input Parameters | Description                                                                                  |
|-----------------|----------------------------|------------------|----------------------------------------------------------------------------------------------|
| public          | InvalidPasswordException   | String message   | Calls the constructor of the exception superclass with the message passed in as a parameter  |

<b>
Interface: Users
</b>

| Method Name             | Return Type     | Input Parameters | Description                                                                                              |
|-------------------------|-----------------|------------------|----------------------------------------------------------------------------------------------------------|
| getUserName             | String          | none             | returns the username                                                                                     |
| getProfile              | Profile         | none             | returns the users profile                                                                                |
| getFriends              | List<User>      | none             | returns a list of friends                                                                                |
| addFriends              | void            | User user        | adds a friend to the list of friends, throws Blocked User Exception                                      |
| removeFriends           | void            | User user        | removes a friend from the list of friends                                                                |
| blockUser               | void            | User user        | blocks the user                                                                                          |
| setPassword             | void            | String password  | sets the password of the given password, throws Invalid Password Exception                               |
| verifyPassword          | boolean         | String password  | verifies that the given password is correct                                                              |
| equals                  | boolean         | User user        | checks if user is equal to this.user <br> <br> user are considered equal if they have the same username  |
| searchFriendsByUsername | ArrayList<User> | String search    | searches for any friends that contains the search string in the friends username                         |

<b>
Class: User
</b>

implements Users

Fields:

| Access Modifier | Field Name     | Type       | Description                               |
|-----------------|----------------|------------|-------------------------------------------|
| private         | username       | String     | username of user                          |
| private         | password       | String     | password of user                          |
| private         | friends        | List<User> | list of all the friends of the user       |
| private         | profile        | Profile    | profile of user                           |
| private         | blocked        | List<User> | list of all the blocked users of the user |

Constructor:

| Access Modifier | Constructor Name | Input Parameters                                     | Description                                                                                                                                                                                                                                                |
|-----------------|------------------|------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| public          | User             | String username , String password , Profile profile  | checks to make sure that the users password is 12 characters long <br><br> if the password is less than 12 characters long, throw a InvalidPasswordException <br><br> Constructors a newly User and instantiate the fields with the perspective parameters |

Methods:

| Access Modifiers | Method Name             | Return Type     | Input Parameters | Description                                                                                                                                                                                              |
|------------------|-------------------------|-----------------|------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| public           | getUserName             | String          | none             | returns the username                                                                                                                                                                                     |
| public           | getProfile              | Profile         | none             | returns the users profile                                                                                                                                                                                |
| public           | getFriends              | List<User>      | none             | returns a list of friends                                                                                                                                                                                |
| public           | addFriends              | void            | User user        | checks that the user is not already in the friend list <br> <br> checks if the user is blocked by user, if the user is blocked throw a Blocked User Exception <br> <br> adds user to the list of friends |
| public           | removeFriends           | void            | User user        | removes a friend from the list of friends                                                                                                                                                                |
| public           | blockUser               | void            | User user        | blocks the user                                                                                                                                                                                          |
| public           | setPassword             | void            | String password  | sets the password of the given password, throws Invalid Password Exception                                                                                                                               |
| public           | verifyPassword          | boolean         | String password  | verifies that the given password is correct                                                                                                                                                              |
| public           | equal                   | boolean         | User user        | checks if user is equal to this.user <br> <br> user are considered equal if they have the same username                                                                                                  |
| public           | searchFriendsByUsername | ArrayList<User> | String search    | searches for any friends that contains the search string in the friends username                                                                                                                         |

<b>
Interface: Profiles
</b>

| Method Name    | Return Type | Input Parameters | Description                         |
|----------------|-------------|------------------|-------------------------------------|
| getName        | String      | none             | returns the name                    |
| getBio         | Profile     | none             | returns the bio                     |
| setName        | void        | String name      | sets the name to the given value    |
| setBio         | void        | String bio       | sets the bio to the given value     |

<b>
Class: Profile
</b>

implements Profiles

Fields:

| Access Modifier | Field Name | Type       | Description                               |
|-----------------|------------|------------|-------------------------------------------|
| private         | name       | String     | username of user                          |
| private         | bio        | String     | password of user                          |

Constructor:

| Access Modifier | Constructor Name | Input Parameters         | Description                                                                         |
|-----------------|------------------|--------------------------|-------------------------------------------------------------------------------------|
| public          | Profile          | String name , String bio | Constructs a new Profile and instantiate the fields with the perspective parameters |

Methods:

| Access Modifier | Method Name    | Return Type | Input Parameters | Description                                  |
|-----------------|----------------|-------------|------------------|----------------------------------------------|
| public          | getName        | String      | none             | returns the name                             |
| public          | getBio         | Profile     | none             | returns the bio                              |
| public          | setName        | void        | String name      | sets the name to the given value             |
| public          | setBio         | void        | String bio       | sets the bio to the given value              |

<b>
Interface: Posts
</b>

| Method Name   | Return Type   | Input Parameters | Description                                 |
|---------------|---------------|------------------|---------------------------------------------|
| getAuthor     | User          | none             | returns the author                          |
| getContent    | String        | none             | returns the content                         |
| getLikes      | int           | String name      | return the amount likes                     |
| getDislikes   | int           | none             | return the amount of dislikes               |
| getComments   | List<Comment> | none             | return a list of comment                    |
| addComments   | void          | Comment comment  | add a comment to the list of comments       |
| deleteComment | void          | Comment comment  | removes a comment from the list of comments |
| like          | void          | none             | increases like by one                       |
| dislike       | void          | none             | increases dislike by one                    |

<b>
Class: Post
</b>

implements Posts

Fields:

| Access Modifier | Field Name | Type          | Description                                       |
|-----------------|------------|---------------|---------------------------------------------------|
| private         | author     | String        | username of user                                  |
| private         | content    | String        | password of user                                  |
| private         | likes      | int           | number of likes the post has                      |
| private         | dislikes   | int           | number of dislikes the post has                   |
| private         | comments   | List<Comment> | list of all the comments associated with the post |

Constructor:

| Access Modifier | Constructor Name | Input Parameters              | Description                                                                                                                  |
|-----------------|------------------|-------------------------------|------------------------------------------------------------------------------------------------------------------------------|
| public          | Post             | User author , String content  | Constructs a new Post and instantiate the fields with the perspective parameters <br> <br> Also sets likes and dislikes to 0 |

Methods: 

| Access Modifiers | Method Name   | Return Type   | Input Parameters | Description                                                                                                       |
|------------------|---------------|---------------|------------------|-------------------------------------------------------------------------------------------------------------------|
| public           | getAuthor     | User          | none             | returns the author                                                                                                |
| public           | getContent    | String        | none             | returns the content                                                                                               |
| public           | getLikes      | int           | String name      | return the amount likes                                                                                           |
| public           | getDislikes   | int           | none             | return the amount of dislikes                                                                                     |
| public           | getComments   | List<Comment> | none             | return a list of comment                                                                                          |
| public           | addComments   | void          | Comment comment  | add a comment to the list of comments                                                                             |
| public           | deleteComment | void          | Comment comment  | removes a comment from the list of comments                                                                       |
| public           | like          | void          | none             | increases like by one                                                                                             |
| public           | dislike       | void          | none             | increases dislike by one                                                                                          |
| public           | equal         | boolean       | Post post        | checks if post is equal to this.post <br> <br> post are considered equal if they have the same content and author |

<b>
Interface: Databases
</b>

| Method Name        | Return Type   | Input Parameters           | Description                             |
|--------------------|---------------|----------------------------|-----------------------------------------|
| addUser            | void          | User user                  | adds a user to the database             |
| getUserByUsername  | User          | String username            | returns a user based on their username  |
| addPost            | List<Post>    | Post post                  | adds a post to the list of post         |
| getPostByUser      | void          | User user                  | returns a post based on user            |
| addCommendToPost   | void          | Comment comment, Post post | adds a comment to the post              |
| getCommentsForPost | List<Comment> | Post post                  | return the list of comments of the post |

<b>
Class: Database
</b>

implements Databases

Fields:

| Access Modifier | Field Name | Type             | Description           |
|-----------------|------------|------------------|-----------------------|
| private         | users      | ArrayList<Users> | list of all the users |
| private         | posts      | ArrayList<Post>  | list of all the posts |

Constructor:

| Access Modifier | Constructor Name | Input Parameters | Description                                                                  |
|-----------------|------------------|------------------|------------------------------------------------------------------------------|
| public          | Database         | none             | Constructs a new Database and instantiate new array list for users and posts |

Methods: 

| Access Modifier | Method Name        | Return Type   | Input Parameters           | Description                                                                 |
|-----------------|--------------------|---------------|----------------------------|-----------------------------------------------------------------------------|
| public          | addUser            | void          | User user                  | checks that user is not already added <br> <br> adds a user to the database |
| public          | getUserByUsername  | User          | String username            | returns the user based on the username                                      |
| public          | addPost            | List<Post>    | Post post                  | adds post to the array list posts                                           |
| public          | getPostByUser      | void          | User user                  | returns all the posts made by user                                          |
| public          | addCommendToPost   | void          | Comment comment, Post post | adds a comment to the post                                                  |
| public          | getCommentsForPost | List<Comment> | Post post                  | returns the list of comments of the post                                    |

<b>
Interface: Comments
</b>

| Method Name | Return Type | Input Parameters | Description                       |
|-------------|-------------|------------------|-----------------------------------|
| getAuthor   | User        | none             | returns the author of the comment |
| getContent  | String      | none             | returns the content of comment    |
| getLikes    | int         | none             | returns the amount of likes       |
| getDislikes | int         | none             | returns the amount of dislikes    |
| like        | void        | none             | increases like by one             |
| dislike     | void        | none             | increases dislike by one          |

<b>
Class: Comment
</b>

implements Comment

Fields:

| Access Modifier | Field Name | Type             | Description           |
|-----------------|------------|------------------|-----------------------|
| private         | author     | ArrayList<Users> | list of all the users |
| private         | content    | ArrayList<Post>  | list of all the posts |
| private         | likes      | int              | amount of likes       |
| private         | dislikes   | int              | amount of dislikes    |

Constructor:

| Access Modifier | Constructor Name | Input Parameters            | Description                                                                                                                    |
|-----------------|------------------|-----------------------------|--------------------------------------------------------------------------------------------------------------------------------|
| public          | Comment          | User author, String content | Constructs a new Comment and initiates the fields with the respective parameters <br> <br> Also sets likes and dislikes to 0   |

Methods:

| Access Modifier | Method Name | Return Type | Input Parameters | Description                                                                                                                 |
|-----------------|-------------|-------------|------------------|-----------------------------------------------------------------------------------------------------------------------------|
| public          | getAuthor   | User        | none             | returns the author of the comment                                                                                           |
| public          | getContent  | String      | none             | returns the content of comment                                                                                              |
| public          | getLikes    | int         | none             | returns the amount of likes                                                                                                 |
| public          | getDislikes | int         | none             | returns the amount of dislikes                                                                                              |
| public          | like        | void        | none             | increases like by one                                                                                                       |
| public          | dislike     | void        | none             | increases dislike by one                                                                                                    |
| public          | equal       | boolean     | Comment comment  | checks if comment is equal to this.comment <br> <br> comments are considered equal if they have the same content and author |

<b>
Interface: Clients
</b>

| Method Name | Return Type | Input Parameters | Description                                   |
|-------------|-------------|------------------|-----------------------------------------------|
| makeUser    | User        | none             | Creates a new user and returns the user       |
| makeProfile | Profile     | none             | Creates a new profile and returns the profile |
| makePost    | Post        | none             | Creates a new post and returns the post       |
| makeComment | Comment     | none             | Creates a new comment and returns the comment |

<b>
Interface: Servers:
</b>

| Method Name    | Return Type | Input Parameters | Description                                                                                                                                   |
|----------------|-------------|------------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| findFreeThread | int         | none             | goes through the entire array of client threads and returns the index of a free thread, if there is no thread available the method returns -1 |
| shutDown       | void        | none             | goes through the entire list of threads and stops the thread when the server socket closes, the server socket is then closed                  |

<h2> Phase 2</h2>

<b>
Class: Clients
</b>

Fields:

| Access Modifier | Field Name   | Type               | Description           |
|-----------------|--------------|--------------------|-----------------------|
| private         | socket       | Socket             | list of all the users |
| private         | outputStream | ObjectOutputStream | list of all the posts |
| private         | inputStream  | ObjectInputStream  | amount of likes       |

Constructor:

| Access Modifier | Constructor Name | Input Parameters                     | Description                                                                                                                |
|-----------------|------------------|--------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| public          | Client           | String serverAddress, int serverPort | Constructs a new Client and initiates the socket with the given parameters, and creates an object output and input stream  |


Methods:

| Access Modifier | Method Name | Return Type | Input Parameters | Description                                   |
|-----------------|-------------|-------------|------------------|-----------------------------------------------|
| public          | makeUser    | User        | none             | Creates a new user and returns the user       |
| public          | makeProfile | Profile     | none             | Creates a new profile and returns the profile |
| public          | makePost    | Post        | none             | Creates a new post and returns the post       |
| public          | makeComment | Comment     | none             | Creates a new comment and returns the comment |

<b>
Class: Server
</b>

Fields:

| Access Modifier | Field Name   | Type         | Description                                                    |
|-----------------|--------------|--------------|----------------------------------------------------------------|
| private final   | port         | int          | port number of the server <br> initiated to 8080               |
| private final   | MAX_THREADS  | int          | max number of threads our program allows <br> initiated to 100 |
| private static  | threads      | Thread[]     | array of all the clients threads                               |
| private static  | serverSocket | ServerSocket | creates a socket for the server                                |

Constructor:

| Access Modifier | Constructor Name | Input Parameters | Description                                                                 |
|-----------------|------------------|------------------|-----------------------------------------------------------------------------|
| public          | Server           | none             | Constructs a new Server and inmates the threads array and the server socket |


Methods:

| Access Modifier | Method Name    | Return Type | Input Parameters | Description                                                                                                                                                                                                                                                                                                                                          |
|-----------------|----------------|-------------|------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| public          | run            | void        | none             | run methods for server <br> server socket accepts client sockets unless the thread is interrupted <br> first checks if there is an available thread for the client <br> if there is a thread available, inisiates a new thread and adds it the array of threads <br> if there is no thread available the terminal prints "No free threads available" |
| public          | findFreeThread | int         | none             | goes through the entire array of client threads and returns the index of a free thread, if there is no thread available the method returns -1                                                                                                                                                                                                        |
| public          | shutdown       | void        | none             | goes through the entire list of threads and stops the thread when the server socket closes, the server socket is then closed                                                                                                                                                                                                                         |
| public          | main           | void        | String[] args    | creates a server object and a thread for the server, the server thread is then started                                                                                                                                                                                                                                                               |

<b>
Class: ClientHandler
</b>

Fields:

| Access Modifier | Field Name   | Type   | Description                                             |
|-----------------|--------------|--------|---------------------------------------------------------|
| private final   | clientSocket | Socket | socket for the client that gets connected to the server |

Constructor:

| Access Modifier | Constructor Name | Input Parameters    | Description                                                                             |
|-----------------|------------------|---------------------|-----------------------------------------------------------------------------------------|
| public          | ClientHandler    | Socket clientSocket | Constructs a new ClientHandler and initiates the fields to their respective parameters  |


Methods:

| Access Modifier | Method Name    | Return Type | Input Parameters | Description                                                                                                              |
|-----------------|----------------|-------------|------------------|--------------------------------------------------------------------------------------------------------------------------|
| public          | run            | void        | none             | run methods for client handler <br> uses a buffered reader and print writer to read and send information from the server |
