import java.util.List;
public interface Users {
    String getUsername();

    Profile getProfile();
    List<User> getFriends();

    void addFriend(User user);
    void removeFriend(User user);
    void blockUser(User user);
    void setPassword(String password);
    boolean verifyPassword(String password);

}
