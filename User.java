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
public class User implements Users {

    private String username;
    private String password;
    private List<User> friends;
    private Profile profile;
    private List<User> blocked;

    public User(String username , String password , Profile profile) throws InvalidPasswordException {
        if (password.length() < 12) {
            throw new InvalidPasswordException("Password must be 12 characters long");
        }
        this.username = username;
        this.password = password;
        this.profile = profile;
        this.friends = new ArrayList<>();
        this.blocked = new ArrayList<>();
    }

    /**
     * getter methods for username, profile and friends
     *
     */

    public String getUsername() {
        return username;
    }

    public Profile getProfile() {
        return profile;
    }
    public List<User> getFriends() {
        return friends;
    }

    /**
     * adds a friend to the list of friends
     * checks that the friend is not already a friend
     * checks that the user is not on the users blocked list
     */

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

    /**
     * removes the friend from the list of friends
     */

    public void removeFriend(User user) {
        friends.remove(user);
    }

    /**
     * blocks the user
     * checks that the user is not already blocked
     */

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

    /**
     * removes the user from the blocked user list
     */

    public void unblockUser(User user) {
        blocked.remove(user);
    }

    /**
     * sets the password with the inputted string
     * checks that the password is at least 12 characters long
     * if the password is less than 12 characters long then the method throws a invalid password exception
     */

    public void setPassword(String password) throws InvalidPasswordException {
        if (password.length() < 12) {
            throw new InvalidPasswordException("Password must by 12 characters long");
        }
        this.password = password;
    }

    /**
     * verifies that the password inputted into the method is the same as the users password
     */

    public boolean verifyPassword(String pass) {
        return this.password.equals(pass);
    }

    /**
     * checks if the user is equal to user
     * users are considered equal if they have the same username
     */

    public boolean equal(User user) {
        return username.equals(user.getUsername());
    }

    /**
     * finds any username that contains the search then
     * returns the array of all users that match the search
     */
    public ArrayList<User> searchFriendsByUsername(String search) {
        ArrayList<User> foundUsers = new ArrayList<>();
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).getUsername().toLowerCase().contains(search.toLowerCase())) {
                foundUsers.add(friends.get(i));
            }
        }
        return foundUsers;
    }

}
