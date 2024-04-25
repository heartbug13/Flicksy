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
public interface Users {
    String getUsername();

    Profile getProfile();
    List<User> getFriends();

    void addFriend(User user) throws BlockedUserException , AlreadyAddedException;
    void removeFriend(User user);
    void blockUser(User user) throws AlreadyAddedException;
    void unblockUser(User user);
    void setPassword(String password) throws InvalidPasswordException;
    boolean verifyPassword(String password);
    ArrayList<User> searchFriendsByUsername(String search);
    String toString();
    boolean isFriend(User user);
    boolean isBlocked(User user);


}
