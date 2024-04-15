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
        this.socket = new Socket(serverAddress, serverPort);
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public User makeUser(String username, String password, Profile profile) throws InvalidPasswordException {
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
        outputStream.writeObject(message);
    }

    public Object receiveMessageFromServer() throws IOException, ClassNotFoundException {
        return inputStream.readObject();
    }

    public void close() throws IOException {
        socket.close();
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
