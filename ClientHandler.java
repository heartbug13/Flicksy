import java.io.*;
import java.net.*;
import java.net.Socket;

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

                    } else {
                        //System.out.println("false password");
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
