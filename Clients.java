import java.io.IOException;
import java.net.Socket;

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

public interface Clients {
    Socket getSocket();
    User makeUser(String username , String password);
    Post makePost(User author , String content);
    Comment makeComment(User author , String content);
    void sendMessageToServer(Object message) throws IOException;
    Object receiveMessageFromServer() throws IOException, ClassNotFoundException;
    void close() throws IOException;
    User makeUser();
    Profile makeProfile();
    Post makePost();
    Comment makeComment();
}
