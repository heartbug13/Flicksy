import java.io.*;
import java.net.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Group Project - Social Media
 *
 * recreates a social media platform
 *
 * @author Samantha Grief, Connor Mccarthy, Ehssan Kanamkandy, Jahiem Cruickshank, L10
 *
 * @version April 14, 2024
 *
 */

public class ClientHandler implements Runnable , Serializable{

    private final Socket clientSocket;
    private Database database;

    public ClientHandler(Socket clientSocket , Database database) {
        this.clientSocket = clientSocket;
        this.database = database;

    }

    /**
     * run method that communicates with server
     * when the database is changed, writes the information the files
     */

    @Override
    public void run() {

        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            //creates output and input stream with the client socket
            System.out.println("connection established");

            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());

            String inputLine = "";
            while ((inputLine = (String) inputStream.readObject()) != null) {

                if (inputLine.equals("sign in")) {
                    //checks if the input line is sign in
                    //if so reads the username and password
                    //checks that the password is correct
                    //if so sends the client true, if not sends the client false
                    //also sends the client all the posts by the user signing in and all the posts created

                    String username = (String) inputStream.readObject();
                    String password = (String) inputStream.readObject();

                    User checkUser = database.getUserByUsername(username);
                    if (checkUser == null) {

                        outputStream.writeObject("false");
                        outputStream.flush();

                    } else if (checkUser.verifyPassword(password)){

                        outputStream.writeObject("true");
                        outputStream.writeObject(checkUser);
                        outputStream.writeObject(database.getPosts().size());
                        outputStream.flush();

                        for (int i = 0; i < database.getPosts().size(); i++) {
                            outputStream.writeObject(database.getPosts().get(i));
                            outputStream.flush();
                        }

                        List<Post> postByUser = database.getPostsByUser(checkUser);
                        outputStream.writeObject(postByUser.size());
                        outputStream.flush();

                        for (int i = 0; i < postByUser.size(); i++) {
                            outputStream.writeObject(postByUser.get(i));
                            outputStream.flush();
                        }

                    } else {
                        outputStream.writeObject("false");
                        outputStream.flush();
                    }
                } else if (inputLine.equals("sign up")) {
                    //checks if the input line is sign up
                    //gets the username and password and creates a new profile with empty strings and a new user
                    //the new user is added to the database
                    //if there user is already added, writes the message of the exception to the client
                    String username = (String) inputStream.readObject();
                    String password = (String) inputStream.readObject();
                    String bio = "";
                    String name = "";
                    Profile newUsersProfile = new Profile(name , bio);
                    try {
                        database.addUser(new User(username, password, newUsersProfile));
                        outputStream.writeObject("creating user");
                        outputStream.flush();
                    } catch (AlreadyAddedException e) {
                        outputStream.writeObject(e.getMessage());
                        outputStream.flush();
                    }



                    database.writeDatabase();

                } else if (inputLine.equals("like comment")) {
                    //checks if the input line is like comment
                    //gather the index of the post and comment
                    //likes the comment
                    int postIndex = (Integer) inputStream.readObject();
                    int commentIndex = (Integer) inputStream.readObject();

                    database.getPosts().get(postIndex).getComments().get(commentIndex).like();

                    database.writeDatabase();
                } else if (inputLine.equals("dislike comment")) {
                    //checks if the input line is dislike comment
                    //gathers the index of the post and comment
                    //dislikes the comment
                    int postIndex = (Integer) inputStream.readObject();
                    int commentIndex = (Integer) inputStream.readObject();

                    database.getPosts().get(postIndex).getComments().get(commentIndex).dislike();

                    database.writeDatabase();
                } else if (inputLine.equals("like post")) {
                    //checks if the input line equals like post
                    //gathers the index of the post
                    //likes the post
                    int postIndex = (Integer) inputStream.readObject();

                    database.getPosts().get(postIndex).like();

                    database.writeDatabase();
                } else if (inputLine.equals("dislike post")) {
                    //checks if the input line equals like post
                    //gathers the index of the post
                    //dislikes the post
                    int postIndex = (Integer) inputStream.readObject();

                    database.getPosts().get(postIndex).dislike();

                    database.writeDatabase();
                } else if (inputLine.equals("add comment")) {
                    //checks if the input line is add comment
                    //gathers the post index and the comment the user wishes to add
                    //adds the comment to the post
                    int postIndex = (Integer) inputStream.readObject();
                    Comment comment = (Comment) inputStream.readObject();
                    database.getPosts().get(postIndex).addComment(comment);

                    database.writeDatabase();
                } else if (inputLine.equals("add post")) {
                    //checks if the input equals add post
                    //gathers the post and adds it to the database
                    Post post = (Post) inputStream.readObject();
                    database.addPost(post);
                    database.writeDatabase();
                } else if (inputLine.equals("search")) {
                    //checks if the input equals search
                    //gathers the search string
                    //database searches for the string and returns the array of users
                    //send the array to the client
                    String search = (String) inputStream.readObject();
                    ArrayList<User> searchResult = database.searchByString(search);
                    if (searchResult == null) {
                        outputStream.writeObject(0);
                    } else {
                        outputStream.writeObject(searchResult.size());
                        outputStream.flush();
                        for (int i = 0; i < searchResult.size(); i++) {
                            outputStream.writeObject(searchResult.get(i).getUsername());
                            outputStream.flush();
                        }
                    }
                } else if (inputLine.equals("find user based on username")) {
                    //checks if the input line is find user based on username
                    //gathers the username
                    //uses the database to find the user based on the username
                    //sends the client the found user
                    String username = (String) inputStream.readObject();
                    User foundUser = database.getUserByUsername(username);
                    outputStream.writeObject(foundUser);
                    outputStream.flush();
                } else if (inputLine.equals("friend")) {
                    //checks if the input line is friend
                    //gathers the username of the friend and user that wants to the add friend
                    //finds the user of the friend based on the username and adds the friend
                    //if there is an exception thrown, sends the message to the client
                    //if no exception is thrown, sends the friend and a message to confirm the friend was added
                    String username = (String) inputStream.readObject();
                    User user = (User) inputStream.readObject();

                    User friend = database.getUserByUsername(username);

                    if (friend != user) {
                        try {
                            database.getUserByUsername(user.getUsername()).addFriend(friend);
                            outputStream.writeObject("adding a friend");
                            outputStream.writeObject(friend);
                            outputStream.flush();
                            database.writeDatabase();
                        } catch (BlockedUserException | AlreadyAddedException e) {
                            outputStream.writeObject(e.getMessage());
                            outputStream.flush();
                            e.printStackTrace();
                        }
                    } else {
                        outputStream.writeObject("friend and user are equal");
                        outputStream.flush();
                    }
                } else if (inputLine.equals("unfriend")) {
                    //checks if the input line is unfriend
                    //gathers the username and the user that they want unfriend
                    //finds the friend based on the username
                    //sends a message to the user based on if they were able remove the friend
                    String username = (String) inputStream.readObject();
                    User user = (User) inputStream.readObject();

                    User friend = database.getUserByUsername(username);
                    if (friend != user) {
                        database.getUserByUsername(user.getUsername()).removeFriend(friend);
                        outputStream.writeObject("adding a friend");
                        outputStream.writeObject(friend);
                        outputStream.flush();
                        database.writeDatabase();
                    } else {
                        outputStream.writeObject("friend and user are equal");
                        outputStream.flush();
                    }
                } else if (inputLine.equals("find post by user")) {
                    //checks if the input line is find post by user
                    //gathers the username and finds the user
                    //sends the client an array of all the posts by the user
                    String username = (String) inputStream.readObject();
                    User user = database.getUserByUsername(username);
                    List<Post> usersPost = database.getPostsByUser(user);

                    outputStream.writeObject(usersPost.size());
                    outputStream.flush();

                    for (int i = 0; i < usersPost.size(); i++) {
                        outputStream.writeObject(usersPost.get(i));
                        outputStream.flush();
                    }

                } else if (inputLine.equals("block")) {
                    //checks if the input line is block
                    //gathers the username and the user that wants to block the user
                    //blocks the user and sends a verifying message
                    //if the user is already added sends the message to the client
                    String username = (String) inputStream.readObject();
                    User user = (User) inputStream.readObject();

                    User blocked = database.getUserByUsername(username);

                    try {
                        database.getUserByUsername(user.getUsername()).blockUser(user);

                        outputStream.writeObject("blocking user");
                        outputStream.writeObject(blocked);
                        outputStream.flush();

                    } catch (AlreadyAddedException e) {
                        outputStream.writeObject(e.getMessage());
                        outputStream.flush();
                    }

                    database.writeDatabase();

                } else if (inputLine.equals("unblock")) {
                    //checks if the input line is block
                    //gathers the username and the user that wants to block the user
                    //unblocks the user and sends a verifying message
                    String username = (String) inputStream.readObject();
                    User user = (User) inputStream.readObject();

                    User unBlocked = database.getUserByUsername(username);
                    database.getUserByUsername(user.getUsername()).unblockUser(unBlocked);

                    outputStream.writeObject(unBlocked);
                    outputStream.flush();

                    database.writeDatabase();

                } else if (inputLine.equals("make profile")) {
                    //checks if the input line is make profile
                    //gathers the name and bio of the user
                    //sets the user's bio and name to the information gathered
                    //sends a list of all the posts
                    String bio = (String) inputStream.readObject();
                    String name = (String) inputStream.readObject();
                    String username = (String) inputStream.readObject();

                    database.getUserByUsername(username).getProfile().setName(name);
                    database.getUserByUsername(username).getProfile().setBio(bio);
                    database.writeDatabase();

                    List<Post> posts = database.getPosts();
                    outputStream.writeObject(posts.size());
                    outputStream.flush();

                    for (int i = 0; i < posts.size(); i++) {
                        outputStream.writeObject(posts.get(i));
                        outputStream.flush();
                    }

                }
            }

            outputStream.close();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally { //**
            database.writeDatabase();
        }
    }
}
