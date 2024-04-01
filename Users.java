import java.util.List;
/**
 * Group Project - Social Media
 *
 * recreates a social media platform
 *
 * @author Samantha Grief, Connor Mccarthy, Ehssan Kanamkandy, Jahiem Cruickshank, L10
 *
 * @version April 1, 2024
 *
 */
public interface Users {
    String getUsername();

    Profile getProfile();
    List<User> getFriends();

    void addFriend(User user) throws BlockedUserException;
    void removeFriend(User user);
    void blockUser(User user);
    void setPassword(String password) throws InvalidPasswordException;
    boolean verifyPassword(String password);

}
