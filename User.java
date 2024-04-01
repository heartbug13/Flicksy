import java.util.ArrayList;
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
public class User implements Users {

    private String username;
    private String password;
    private List<User> friends;
    private Profile profile;
    private List<User> blocked;

    public User(String username , String password , Profile profile) throws InvalidPasswordException {
        if (password.length() < 12) {
            throw new InvalidPasswordException("Password must by 12 characters long");
        }
        this.username = username;
        this.password = password;
        this.profile = profile;
        this.friends = new ArrayList<>();
        this.blocked = new ArrayList<>();
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

    public void addFriend(User user) throws BlockedUserException {
        int verify = 0;
        //checks if there is user is already a friend
        for (User friend : friends) {
            if (friend.equal(user)) {
                verify++;
            }
        }
        for (User block : blocked) {
            if (block.equal(user)) {
                throw new BlockedUserException("you have been blocked by this user");
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
        int verify = 0;
        for (User value : blocked) {
            if (user.equal(value)) {
                verify++;
            }
        }

        if (verify == 0) {
            blocked.add(user);
        }

    }

    public void unblockUser(User user) {
        blocked.remove(user);
    }

    public void setPassword(String password) throws InvalidPasswordException {
        if (password.length() < 12) {
            throw new InvalidPasswordException("Password must by 12 characters long");
        }
        this.password = password;
    }
    public boolean verifyPassword(String pass) {
        return this.password.equals(pass);
    }

    public boolean equal(User user) {
        return username.equals(user.getUsername());
    }

}
