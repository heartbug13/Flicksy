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
Excepetion: InvalidUserException
</b>

extends Exception

Constructor:

| Access Modifier | Constructor Name     | Input Parameters | Description                                                                                  |
|-----------------|----------------------|------------------|----------------------------------------------------------------------------------------------|
| public          | InvalidUserException | String message   | Calls the constructor of the exception superclass with the message passed in as a parameter  |

<b>
Excepetion: AlreadyAddedException
</b>

extends Exception

Constructor:

| Access Modifier | Constructor Name      | Input Parameters | Description                                                                                  |
|-----------------|-----------------------|------------------|----------------------------------------------------------------------------------------------|
| public          | AlreadyAddedException | String message   | Calls the constructor of the exception superclass with the message passed in as a parameter  |



<b>
Interface: Users
</b>

| Method Name             | Return Type     | Input Parameters | Description                                                                                                                                                                                                                                                              |
|-------------------------|-----------------|------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| getUserName             | String          | none             | returns the username                                                                                                                                                                                                                                                     |
| getProfile              | Profile         | none             | returns the users profile                                                                                                                                                                                                                                                |
| getFriends              | List<User>      | none             | returns a list of friends                                                                                                                                                                                                                                                |
| addFriends              | void            | User user        | checks that the user is not already in the friend list, if the user is already added throw a new AlreadyAddedException <br> <br> checks if the user is blocked by user, if the user is blocked throw a Blocked User Exception <br> <br> adds user to the list of friends |
| removeFriends           | void            | User user        | removes a friend from the list of friends                                                                                                                                                                                                                                |
| blockUser               | void            | User user        | blocks the user, if the user is already blocked throw a new AlreadyBlockedException                                                                                                                                                                                      |
| unblockUser             | void            | User user        | unblocks the user                                                                                                                                                                                                                                                        |
| getBlocked              | List<User>      | none             | returns the list of blocked users                                                                                                                                                                                                                                        |
| setPassword             | void            | String password  | sets the password of the given password, throws Invalid Password Exception                                                                                                                                                                                               |
| verifyPassword          | boolean         | String password  | verifies that the given password is correct                                                                                                                                                                                                                              |
| equal                   | boolean         | User user        | checks if user is equal to this.user <br> <br> user are considered equal if they have the same username                                                                                                                                                                  |
| searchFriendsByUsername | ArrayList<User> | String search    | searches for any friends that contains the search string in the friends username                                                                                                                                                                                         |
| toString                | none            | none             | returns the string version of the user with the name, bio, username, and password of the user on a separate line                                                                                                                                                         |
| isFriend                | boolean         | User user        | returns true if the user is a friend of the this.user, else return false                                                                                                                                                                                                 |
| isBlocked               | boolean         | User user        | returns true if the user is blocked bt this.user, else returns false                                                                                                                                                                                                     |

<b>
Class: User
</b>

implements Users and Serializable

Fields:

| Access Modifier | Field Name     | Type       | Description                               |
|-----------------|----------------|------------|-------------------------------------------|
| private         | username       | String     | username of user                          |
| private         | password       | String     | password of user                          |
| private         | friends        | List<User> | list of all the friends of the user       |
| private         | profile        | Profile    | profile of user                           |
| private         | blocked        | List<User> | list of all the blocked users of the user |

Constructor:

| Access Modifier | Constructor Name | Input Parameters                                     | Description                                                                                                                                                                                                                                                                                                                                                             |
|-----------------|------------------|------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| public          | User             | String username , String password , Profile profile  | checks to make sure that the users password is 12 characters long <br><br> if the password is less than 12 characters long, throw a InvalidPasswordException <br><br> checks to make sure a username doesn't contain "-" <br><bfr> if so throw a new InvalidUserException <br><br> Constructors a newly User and instantiate the fields with the perspective parameters |

Methods:

| Access Modifiers | Method Name             | Return Type     | Input Parameters | Description                                                                                                                                                                                                                                                              |
|------------------|-------------------------|-----------------|------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| public           | getUserName             | String          | none             | returns the username                                                                                                                                                                                                                                                     |
| public           | getProfile              | Profile         | none             | returns the users profile                                                                                                                                                                                                                                                |
| public           | getFriends              | List<User>      | none             | returns a list of friends                                                                                                                                                                                                                                                |
| public           | addFriends              | void            | User user        | checks that the user is not already in the friend list, if the user is already added throw a new AlreadyAddedException <br> <br> checks if the user is blocked by user, if the user is blocked throw a Blocked User Exception <br> <br> adds user to the list of friends |
| public           | removeFriends           | void            | User user        | removes a friend from the list of friends                                                                                                                                                                                                                                |
| public           | blockUser               | void            | User user        | blocks the user, if the user is already blocked throw a new AlreadyBlockedException                                                                                                                                                                                      |
| public           | unblockUser             | void            | User user        | unblocks the user                                                                                                                                                                                                                                                        |
| public           | getBlocked              | List<User>      | none             | returns the list of blocked users                                                                                                                                                                                                                                        |
| public           | setPassword             | void            | String password  | sets the password of the given password, throws Invalid Password Exception                                                                                                                                                                                               |
| public           | verifyPassword          | boolean         | String password  | verifies that the given password is correct                                                                                                                                                                                                                              |
| public           | equal                   | boolean         | User user        | checks if user is equal to this.user <br> <br> user are considered equal if they have the same username                                                                                                                                                                  |
| public           | searchFriendsByUsername | ArrayList<User> | String search    | searches for any friends that contains the search string in the friends username                                                                                                                                                                                         |
| public           | toString                | none            | none             | returns the string version of the user with the name, bio, username, and password of the user on a separate line                                                                                                                                                         |
| public           | isFriend                | boolean         | User user        | returns true if the user is a friend of the this.user, else return false                                                                                                                                                                                                 |
| public           | isBlocked               | boolean         | User user        | returns true if the user is blocked bt this.user, else returns false                                                                                                                                                                                                     |

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

implements Profiles and Serializable

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

| Method Name   | Return Type   | Input Parameters | Description                                                                                                       |
|---------------|---------------|------------------|-------------------------------------------------------------------------------------------------------------------|
| getAuthor     | User          | none             | returns the author                                                                                                |
| getContent    | String        | none             | returns the content                                                                                               |
| getLikes      | int           | String name      | return the amount likes                                                                                           |
| setLikes      | void          | int likes        | sets this.likes to likes                                                                                          |
| getDislikes   | int           | none             | return the amount of dislikes                                                                                     |
| setDislikes   | void          | int dislikes     | sets this.dislikes to dislikes                                                                                    |
| getComments   | List<Comment> | none             | return a list of comment                                                                                          |
| addComments   | void          | Comment comment  | add a comment to the list of comments                                                                             |
| deleteComment | void          | Comment comment  | removes a comment from the list of comments                                                                       |
| like          | void          | none             | increases like by one                                                                                             |
| dislike       | void          | none             | increases dislike by one                                                                                          |
| equal         | boolean       | Post post        | checks if post is equal to this.post <br> <br> post are considered equal if they have the same content and author |
| toString      | String        | none             | returns the string version of a post with the author's username, comments, likes, and dislikes on separate lines  |

<b>
Class: Post
</b>

implements Posts and Serializable

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
| public           | setLikes      | void          | int likes        | sets this.likes to likes                                                                                          |
| public           | getDislikes   | int           | none             | return the amount of dislikes                                                                                     |
| public           | setDislikes   | void          | int dislikes     | sets this.dislikes to dislikes                                                                                    |
| public           | getComments   | List<Comment> | none             | return a list of comment                                                                                          |
| public           | addComments   | void          | Comment comment  | add a comment to the list of comments                                                                             |
| public           | deleteComment | void          | Comment comment  | removes a comment from the list of comments                                                                       |
| public           | like          | void          | none             | increases like by one                                                                                             |
| public           | dislike       | void          | none             | increases dislike by one                                                                                          |
| public           | equal         | boolean       | Post post        | checks if post is equal to this.post <br> <br> post are considered equal if they have the same content and author |
| public           | toString      | String        | none             | returns the string version of a post with the author's username, comments, likes, and dislikes on separate lines  |

<b>
Interface: Databases
</b>

| Method Name        | Return Type     | Input Parameters           | Description                                                                                                                                                                                                                                                                                                                                                                                                                                           |
|--------------------|-----------------|----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| addUser            | void            | User user                  | checks that user is not already added <br> <br> adds a user to the database                                                                                                                                                                                                                                                                                                                                                                           |
| getUserByUsername  | User            | String username            | returns the user based on the username                                                                                                                                                                                                                                                                                                                                                                                                                |
| addPost            | List<Post>      | Post post                  | adds post to the array list posts                                                                                                                                                                                                                                                                                                                                                                                                                     |
| getPostByUser      | void            | User user                  | returns all the posts made by user                                                                                                                                                                                                                                                                                                                                                                                                                    |
| addCommentToPost   | void            | Comment comment, Post post | adds a comment to the post                                                                                                                                                                                                                                                                                                                                                                                                                            |
| getCommentsForPost | List<Comment>   | Post post                  | returns the list of comments of the post                                                                                                                                                                                                                                                                                                                                                                                                              |
| searchByString     | ArrayList<User> | String search              | returns a list of all the users that have a username that contains the search string, if there is no user, returns null                                                                                                                                                                                                                                                                                                                               |
| writeDatabase      | void            | none                       | writes all the information to the database in three files <br><br> the first file contains all the users including their name, bio, username, and password <br><br> the second file includes all the posts including the authors username, post content, post likes, post dislikes, and post comments and their info <br><br> the third file contains more information on the friend including who the user has blocked and who they are friends with |
| readDatabase       | void            | none                       | reads all the information from three different files and adds the respective information to their place in the database                                                                                                                                                                                                                                                                                                                               |
| getPosts           | ArrayList<Post> | none                       | returns an array of all the posts                                                                                                                                                                                                                                                                                                                                                                                                                     |

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

| Access Modifier | Method Name        | Return Type     | Input Parameters           | Description                                                                                                                                                                                                                                                                                                                                                                                                                                           |
|-----------------|--------------------|-----------------|----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| public          | addUser            | void            | User user                  | checks that user is not already added <br> <br> adds a user to the database                                                                                                                                                                                                                                                                                                                                                                           |
| public          | getUserByUsername  | User            | String username            | returns the user based on the username                                                                                                                                                                                                                                                                                                                                                                                                                |
| public          | addPost            | List<Post>      | Post post                  | adds post to the array list posts                                                                                                                                                                                                                                                                                                                                                                                                                     |
| public          | getPostByUser      | void            | User user                  | returns all the posts made by user                                                                                                                                                                                                                                                                                                                                                                                                                    |
| public          | addCommendToPost   | void            | Comment comment, Post post | adds a comment to the post                                                                                                                                                                                                                                                                                                                                                                                                                            |
| public          | getCommentsForPost | List<Comment>   | Post post                  | returns the list of comments of the post                                                                                                                                                                                                                                                                                                                                                                                                              |
| public          | searchByString     | ArrayList<User> | String search              | returns a list of all the users that have a username that contains the search string, if there is no user, returns null                                                                                                                                                                                                                                                                                                                               |
| public          | writeDatabase      | void            | none                       | writes all the information to the database in three files <br><br> the first file contains all the users including their name, bio, username, and password <br><br> the second file includes all the posts including the authors username, post content, post likes, post dislikes, and post comments and their info <br><br> the third file contains more information on the friend including who the user has blocked and who they are friends with |
| public          | readDatabase       | void            | none                       | reads all the information from three different files and adds the respective information to their place in the database                                                                                                                                                                                                                                                                                                                               |
| public          | getPosts           | ArrayList<Post> | none                       | returns an array of all the posts                                                                                                                                                                                                                                                                                                                                                                                                                     |

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

implements Comment and Serializable

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

<b>
Interface: Clients
</b>

| Method Name              | Return Type | Input Parameters                                  | Description                                                                                                                                                                              |
|--------------------------|-------------|---------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| getSocket                | Socket      | none                                              | returns the client socket                                                                                                                                                                |
| makeUser                 | User        | String username, String password, Profile profile | Creates a new user and returns the user                                                                                                                                                  |
| makeProfile              | Profile     | String name, String bio                           | Creates a new profile and returns the profile                                                                                                                                            |
| makePost                 | Post        | User author, String content                       | Creates a new post and returns the post                                                                                                                                                  |
| makeComment              | Comment     | User author, String content                       | Creates a new comment and returns the comment                                                                                                                                            |
| sendMessageToServer      | void        | Object message                                    | writes the message to the server using a output stream and flushes the output stream, if there is an IO exception thrown prints out an error message                                     |
| receiveMessageFromServer | Object      | none                                              | returns the object that the server sent, if there is an IO or ClassNotFoundException print out a error message                                                                           |
| close                    | void        | none                                              | checks if the socket, output stream, and input stream are null , if so closes the socket, output stream and input stream <br><br> if there is an IO exception print out an error message |
| makeUser                 | User        | none                                              | returns null                                                                                                                                                                             |
| makeProfile              | Profile     | none                                              | returns null                                                                                                                                                                             |
| makePost                 | Post        | none                                              | returns null                                                                                                                                                                             |
| makeComment              | Comment     | none                                              | returns null                                                                                                                                                                             |

<b>
Interface: Servers:
</b>

| Method Name    | Return Type | Input Parameters | Description                                                                                                                                                                                                                                                                                                                                                  |
|----------------|-------------|------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| findFreeThread | int         | none             | goes through the entire array of client threads and returns the index of a free thread, if there is no thread available the method returns -1                                                                                                                                                                                                                |
| shutDown       | void        | none             | goes through the entire list of threads and stops the thread when the server socket closes, the server socket is then closed                                                                                                                                                                                                                                 |
| main           | void        | String[] args    | creates a new database and calls the readDatabase() method <br><br> creates a new server and unless the thread is interrupted the client socket gets accepted by the server socket <br><br> checks to see if there is a free thread (max thread number is 100), if there is a free thread creates a new client handler, otherwise prints out a error message |

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

| Access Modifier | Method Name              | Return Type | Input Parameters                                  | Description                                                                                                                                                                              |
|-----------------|--------------------------|-------------|---------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| public          | getSocket                | Socket      | none                                              | returns the client socket                                                                                                                                                                |
| public          | makeUser                 | User        | String username, String password, Profile profile | Creates a new user and returns the user                                                                                                                                                  |
| public          | makeProfile              | Profile     | String name, String bio                           | Creates a new profile and returns the profile                                                                                                                                            |
| public          | makePost                 | Post        | User author, String content                       | Creates a new post and returns the post                                                                                                                                                  |
| public          | makeComment              | Comment     | User author, String content                       | Creates a new comment and returns the comment                                                                                                                                            |
| public          | sendMessageToServer      | void        | Object message                                    | writes the message to the server using a output stream and flushes the output stream, if there is an IO exception thrown prints out an error message                                     |
| public          | receiveMessageFromServer | Object      | none                                              | returns the object that the server sent, if there is an IO or ClassNotFoundException print out a error message                                                                           |
| public          | close                    | void        | none                                              | checks if the socket, output stream, and input stream are null , if so closes the socket, output stream and input stream <br><br> if there is an IO exception print out an error message |
| public          | makeUser                 | User        | none                                              | returns null                                                                                                                                                                             |
| public          | makeProfile              | Profile     | none                                              | returns null                                                                                                                                                                             |
| public          | makePost                 | Post        | none                                              | returns null                                                                                                                                                                             |
| public          | makeComment              | Comment     | none                                              | returns null                                                                                                                                                                             |

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
| private static  | database     | Database     | creates a new database for the server                          |

Constructor:

| Access Modifier | Constructor Name | Input Parameters | Description                                                                                                           |
|-----------------|------------------|------------------|-----------------------------------------------------------------------------------------------------------------------|
| public          | Server           | none             | Constructs a new Server and inmates the threads array and the server socket, database calls the readDatabase() method |


Methods:

| Access Modifier | Method Name    | Return Type | Input Parameters | Description                                                                                                                                                                                                                                                                                                                                                  |
|-----------------|----------------|-------------|------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| private         | findFreeThread | int         | none             | goes through the entire array of client threads and returns the index of a free thread, if there is no thread available the method returns -1                                                                                                                                                                                                                |
| private         | shutDown       | void        | none             | goes through the entire list of threads and stops the thread when the server socket closes, the server socket is then closed                                                                                                                                                                                                                                 |
| public          | main           | void        | String[] args    | creates a new database and calls the readDatabase() method <br><br> creates a new server and unless the thread is interrupted the client socket gets accepted by the server socket <br><br> checks to see if there is a free thread (max thread number is 100), if there is a free thread creates a new client handler, otherwise prints out a error message |

<b>
Class: ClientHandler
</b>

Fields:

| Access Modifier | Field Name   | Type     | Description                                             |
|-----------------|--------------|----------|---------------------------------------------------------|
| private final   | clientSocket | Socket   | socket for the client that gets connected to the server |
| private         | database     | Database | database for user                                       |

Constructor:

| Access Modifier | Constructor Name | Input Parameters                       | Description                                                                             |
|-----------------|------------------|----------------------------------------|-----------------------------------------------------------------------------------------|
| public          | ClientHandler    | Socket clientSocket, Database database | Constructs a new ClientHandler and initiates the fields to their respective parameters  |


Methods:

| Access Modifier | Method Name    | Return Type | Input Parameters | Description                                                                                                              |
|-----------------|----------------|-------------|------------------|--------------------------------------------------------------------------------------------------------------------------|
| public          | run            | void        | none             | run methods for client handler <br> uses a buffered reader and print writer to read and send information from the server |

<h2> Phase 3</h2>


<b> Interface </b>

Methods:

| Method Name       | Return Type | Input Parameters                   | Description                                                                                                                                                                                                                                                       |
|-------------------|-------------|------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| main              | void        | String[] args                      | main method of the user, starts off by calling the initialPage() method to have the user sign in or sign up                                                                                                                                                       |
| initialPage       | void        | none                               | creates the sign in / sign up page for the user                                                                                                                                                                                                                   |
| createProfilePage | void        | none                               | creates the page to get the users name and bio                                                                                                                                                                                                                    |
| createUserProfile | void        | User user, List<Post> usersPost    | creates a profile based on the user, includes a user panel that displays the name, username, bio, number of friends and post of the user <br><br> allows the user to friend/unfriend and block/unblock a user if the user is not the signed in user               |
| sneakPeakOfUser   | void        | User user, List<Post> usersPost    | creates a sneak peak of a user once a user is searched <br><br> includes a user panel that displays the users info as well as the first post of a user, if there is no post by users it displays a message stating so                                             |
| createNewsFeed    | void        | none                               | creates a news feed of all the posts, checks to make sure that the post is not by a blocked user                                                                                                                                                                  |
| createPostPanel   | JPanel      | Post post, String num, String page | creates a panel to display a post by using the post's content, the index of the post and which page the post will be displayed on <br><br> each post allow the user to view the comments/add a comment, like, dislike, and friend/unfriend the author of the post |
| createComment     | void        | Post post, int postIndex           | creates a comment board to display all the comments of a post, allows the user to add a comment, like the comment, and dislike the comment                                                                                                                        |
| actionPerformed   | void        | ActionEvent e                      | handles all the action events of the user including when a button is pressed                                                                                                                                                                                      |


<b>
Class: GUI
</b>

implements: ActionListener and GUIs

Fields:

| Access Modifier | Field Name         | Type           | Description                                                            |
|-----------------|--------------------|----------------|------------------------------------------------------------------------|
| private static  | signIn             | JButton        | button for signing in or signing up the user                           |
| private static  | signUp             | JButton        | button to allow the user to choose to sign in or sign up               |
| private static  | addComment         | JButton        | button to add a comment                                                |
| private static  | getPassword        | JPasswordField | text field to get the password of the user                             |
| private static  | getUsername        | JTextField     | text field to get the username of the user                             |
| private static  | addCommentContent  | JTextArea      | text area to get the content of the comment the user would like to add |
| private static  | bio                | JTextArea      | text area to get the bio of the user                                   |
| private static  | name               | JTextField     | text field to get the name of the user                                 |
| private static  | addPost            | JTextArea      | text area to get the content of the post the would like to add         |
| private static  | search             | JTextField     | text field to search for a user based on their username                |
| private static  | welcomeMessage     | JLabel         | label of the welcome message when signing/singing up                   |
| private static  | makeProfile        | JButton        | button to make a profile                                               |
| private static  | addPostButton      | JButton        | button to add a post                                                   |
| private static  | searchButton       | JButton        | button to search for a user                                            |
| private static  | seeFullProfile     | JButton        | button to see the full profile of a username                           |
| private static  | friendButton       | JButton        | button to add a friend/unfriend                                        |
| private static  | block              | JButton        | button to block/unblock a user                                         |
| private static  | menuBar            | JMenuBar       | menu bar for all the frames                                            |
| private static  | newsFeedButton     | JMenuItme      | menu item to bring the user back to the newsfeed                       |
| private static  | usersProfileButton | JMenuItem      | menu item to bring the user back to their profile                      |
| private static  | client             | Client         | client that connects to the server                                     |
| private static  | userProfile        | JFrame         | frame of the a users profile                                           |
| private static  | newsFeed           | JFrame         | frame of the newsfeed                                                  |
| private static  | commentBoard       | JFrame         | frame of a comment board of a post                                     |
| private static  | sneakPeakProfile   | JFrame         | frame of a sneak peak of a profile after they search for a user        |
| private static  | signInFrame        | JFrame         | frame for the user to sign in or sign up                               |
| private static  | createProfile      | JFrame         | frame to create a profile when the user signs up                       |
| private static  | signedInUser       | User           | user that is currently signed in                                       |
| private static  | posts              | List<Post>     | list off all the posts                                                 |
| private static  | postsByUser        | List<Post>     | list off all the posts by the signed in user                           |

Methods:

| Access Modifier | Method Name       | Return Type | Input Parameters                   | Description                                                                                                                                                                                                                                                       |
|-----------------|-------------------|-------------|------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| public          | main              | void        | String[] args                      | main method of the user, starts off by calling the initialPage() method to have the user sign in or sign up                                                                                                                                                       |
| public          | initialPage       | void        | none                               | creates the sign in / sign up page for the user                                                                                                                                                                                                                   |
| public          | createProfilePage | void        | none                               | creates the page to get the users name and bio                                                                                                                                                                                                                    |
| public          | createUserProfile | void        | User user, List<Post> usersPost    | creates a profile based on the user, includes a user panel that displays the name, username, bio, number of friends and post of the user <br><br> allows the user to friend/unfriend and block/unblock a user if the user is not the signed in user               |
| public          | sneakPeakOfUser   | void        | User user, List<Post> usersPost    | creates a sneak peak of a user once a user is searched <br><br> includes a user panel that displays the users info as well as the first post of a user, if there is no post by users it displays a message stating so                                             |
| public          | createNewsFeed    | void        | none                               | creates a news feed of all the posts, checks to make sure that the post is not by a blocked user                                                                                                                                                                  |
| public          | createPostPanel   | JPanel      | Post post, String num, String page | creates a panel to display a post by using the post's content, the index of the post and which page the post will be displayed on <br><br> each post allow the user to view the comments/add a comment, like, dislike, and friend/unfriend the author of the post |
| public          | createComment     | void        | Post post, int postIndex           | creates a comment board to display all the comments of a post, allows the user to add a comment, like the comment, and dislike the comment                                                                                                                        |
| public          | actionPerformed   | void        | ActionEvent e                      | handles all the action events of the user including when a button is pressed                                                                                                                                                                                      |
