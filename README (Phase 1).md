# CS-180-GroupProject

Instructions: <br>
text <br>
Name - Submitted Report <br> 
Name - Submitted Report <br>

Interface: User

| Method Name    | Return Type | Input Parameters | Description                                  |
|----------------|-------------|------------------|----------------------------------------------|
| getUserName    | String      | none             | returns the username                         |
| getProfile     | Profile     | none             | returns the users profile                    |
| getFriends     | List<User>  | none             | returns a list of friends                    |
| addFriends     | void        | User user        | adds a friend to the list of friends         |
| removeFriends  | void        | User user        | removes a friend from the list of friends    |
| blockUser      | void        | User user        | blocks the user                              |
| setPassword    | void        | String password  | sets the password of the given password      |
| verifyPassword | boolean     | String password  | verifies that the given password is correct  |

Interface: Profile

| Method Name    | Return Type | Input Parameters | Description                                  |
|----------------|-------------|------------------|----------------------------------------------|
| getName        | String      | none             | returns the name                             |
| getBio         | Profile     | none             | returns the bio                              |
| setName        | void        | String name      | sets the name to the given value             |
| setBio         | void        | String bio       | sets the bio to the given value              |

Interface: Post

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

Interface: Database

| Method Name        | Return Type   | Input Parameters           | Description                             |
|--------------------|---------------|----------------------------|-----------------------------------------|
| addUser            | void          | User user                  | adds a user to the database             |
| getUserByUsername  | User          | String username            | returns a user based on their username  |
| addPost            | List<Post>    | Post post                  | adds a post to the list of post         |
| getPostByUser      | void          | User user                  | returns a post based on user            |
| addCommendToPost   | void          | Comment comment, Post post | adds a comment to the post              |
| getCommentsForPost | List<Comment> | Post post                  | return the list of comments of the post |

Interface: Comment

| Method Name | Return Type | Input Parameters | Description                       |
|-------------|-------------|------------------|-----------------------------------|
| getAuthor   | User        | none             | returns the author of the comment |
| getContent  | String      | none             | returns the content of comment    |
| getLikes    | int         | none             | returns the amount of likes       |
| getDislikes | int         | none             | returns the amount of dislikes    |
| like        | void        | none             | increases like by one             |
| dislike     | void        | none             | increases dislike by one          |