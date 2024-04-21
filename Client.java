import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Group Project - Social Media
 *
 * recreates a social media platform
 *
 * @author Samantha Grief, Connor Mccarthy, Ehssan Kanamkandy, Jaheim Cruickshank, L10
 *
 * @version April 1, 2024
 *
 */

public class Client implements Clients {

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public Client(String serverAddress, int serverPort) throws IOException {
        try {
            this.socket = new Socket(serverAddress, serverPort);
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }


    }

    public User makeUser(String username, String password, Profile profile) throws InvalidPasswordException {
        if (password == null) {
            throw new InvalidPasswordException("Please input a password!");
        }
        return new User(username, password, profile);
    }

    public Profile makeProfile(String name, String bio) {
        return new Profile(name, bio);
    }

    public Post makePost(User author, String content) {
        return new Post(author, content);
    }

    public Comment makeComment(User author, String content) {
        return new Comment(author, content);
    }

    public void sendMessageToServer(Object message) throws IOException {
        try {
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e ) {
            System.out.println("Error Sending message to server: " + e.getMessage());
            throw e;
        }

    }

    public Object receiveMessageFromServer() throws IOException, ClassNotFoundException {
        try {
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error recieving message from server: " + e.getMessage());
        }

    }

    public void close() throws IOException {
        try {
            if (socket != null) {
                socket.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }


        } catch (IOException e) {
            System.out.println("Error closing the client system resources");
        }

    }

    @Override
    public User makeUser() {
        return null;
    }

    @Override
    public Profile makeProfile() {
        return null;
    }

    @Override
    public Post makePost() {
        return null;
    }

    @Override
    public Comment makeComment() {
        return null;
    }

    //Can also implement additional methods to interact with server as needed here

}
