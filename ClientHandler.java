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

    @Override
    public void run() {

        try {
            System.out.println("connection established");

            //BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //**
            //PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true); //**

            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

            String inputLine = "";
            //inputLine = reader.readLine();
            while ((inputLine = (String) inputStream.readObject()) != null) {
                System.out.println("input line: " + inputLine);

                if (inputLine.equals("sign in")) {
                    //System.out.println("you should be going here");
                    //String username = reader.readLine();
                    //String password = reader.readLine();

                    String username = (String) inputStream.readObject();
                    String password = (String) inputStream.readObject();
                    //System.out.println("Username " + username);
                    //System.out.println("Password " + password);

                    User checkUser = database.getUserByUsername(username);
                    if (checkUser == null) {
                        //System.out.println("no user");
                        //writer.println("false");
                        //writer.flush();

                        outputStream.writeObject("false");
                        outputStream.flush();

                    } else if (checkUser.verifyPassword(password)){
                        //writer.println("true");
                        //writer.flush();
                        outputStream.writeObject("true");
                        //System.out.println(checkUser);
                        outputStream.writeObject(checkUser);
                        outputStream.writeObject(database.getPosts().size());
                        outputStream.flush();

                        for (int i = 0; i < database.getPosts().size(); i++) {
                            //System.out.println(database.getPosts().get(i));
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
                        System.out.println("false password");
                        //writer.println("false");
                        //writer.flush();
                        outputStream.writeObject("false");
                        outputStream.flush();
                    }
                } else if (inputLine.equals("sign up")) {
                    String username = (String) inputStream.readObject();
                    String password = (String) inputStream.readObject();
                    String bio = "";
                    String name = "";
                    Profile newUsersProfile = new Profile(name , bio);
                    database.addUser(new User(username , password , newUsersProfile));
                    database.writeDatabase();
                    //System.out.println(database.getUserByUsername(username));

                } else if (inputLine.equals("like comment")) {
                    System.out.println("updating");
                    int postIndex = (Integer) inputStream.readObject();
                    int commentIndex = (Integer) inputStream.readObject();

                    database.getPosts().get(postIndex).getComments().get(commentIndex).like();

                    database.writeDatabase();
                } else if (inputLine.equals("dislike comment")) {
                    int postIndex = (Integer) inputStream.readObject();
                    int commentIndex = (Integer) inputStream.readObject();

                    database.getPosts().get(postIndex).getComments().get(commentIndex).dislike();

                    database.writeDatabase();
                } else if (inputLine.equals("like post")) {
                    int postIndex = (Integer) inputStream.readObject();

                    database.getPosts().get(postIndex).like();

                    database.writeDatabase();
                } else if (inputLine.equals("dislike post")) {
                    int postIndex = (Integer) inputStream.readObject();

                    database.getPosts().get(postIndex).dislike();

                    database.writeDatabase();
                } else if (inputLine.equals("add comment")) {
                    int postIndex = (Integer) inputStream.readObject();
                    Comment comment = (Comment) inputStream.readObject();
                    database.getPosts().get(postIndex).addComment(comment);

                    database.writeDatabase();
                } else if (inputLine.equals("add post")) {
                    Post post = (Post) inputStream.readObject();
                    database.addPost(post);
                    database.writeDatabase();
                } else if (inputLine.equals("search")) {
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
                    String username = (String) inputStream.readObject();
                    User foundUser = database.getUserByUsername(username);
                    outputStream.writeObject(foundUser);
                    outputStream.flush();
                } else if (inputLine.equals("friend")) {
                    String username = (String) inputStream.readObject();
                    User user = (User) inputStream.readObject();
                    //System.out.println("username: " + username);

                    User friend = database.getUserByUsername(username);
                    //System.out.println("add friend " + friend);
                    if (friend != user) {
                        try {
                            database.getUserByUsername(user.getUsername()).addFriend(friend);
                            //System.out.println(user.getFriends());
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
                    String username = (String) inputStream.readObject();
                    User user = (User) inputStream.readObject();
                    //System.out.println("username: " + username);

                    User friend = database.getUserByUsername(username);
                    //System.out.println("add friend " + friend);
                    if (friend != user) {
                        database.getUserByUsername(user.getUsername()).removeFriend(friend);
                        //System.out.println(user.getFriends());
                        outputStream.writeObject("adding a friend");
                        outputStream.writeObject(friend);
                        outputStream.flush();
                        database.writeDatabase();
                    } else {
                        outputStream.writeObject("friend and user are equal");
                        outputStream.flush();
                    }
                } else if (inputLine.equals("find post by user")) {
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
                    String username = (String) inputStream.readObject();
                    User user = (User) inputStream.readObject();

                    User unBlocked = database.getUserByUsername(username);
                    database.getUserByUsername(user.getUsername()).unblockUser(unBlocked);

                    outputStream.writeObject(unBlocked);
                    outputStream.flush();

                    database.writeDatabase();

                } else if (inputLine.equals("make profile")) {
                    System.out.println("hello");
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

                } else if (inputLine.equals("close server")) {
                    outputStream.close();
                    inputStream.close();
                    clientSocket.close();
                    return;
                }
            }


            //System.out.println(inputLine);
            //System.out.println(inputLine);

        } catch (Exception e) {
            e.printStackTrace();

        } finally { //**
            //clientSocket.close();
            database.writeDatabase();
        }
    }
}
