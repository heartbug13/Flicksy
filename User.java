import java.util.ArrayList;
import java.util.List;

public class User implements Users {

    private String username;
    private String password;
    private List<User> friends;
    private Profile profile;

    public User (String username , String password , Profile profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
        this.friends = new ArrayList<>();
    }


    public String getUsername() {
        return username;
    }

    public Profile getProfile() {
        return profile;
    }
    public List<User> getFriends() {
        return friends;
    }

    public void addFriend(User user) {
        int verify = 0;
        //checks if there is user is already a friend
        for (User friend : friends) {
            if (friend.getUsername().equals(username)) {
                verify++;
            }
        }
        if (verify == 0) {
            friends.add(user);
        }
    }
    public void removeFriend(User user) {
        friends.remove(user);
    }
    public void blockUser(User user) {

    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
}
