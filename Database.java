import java.util.ArrayList;
import java.util.List;

public class Database {
    ArrayList<User> users;
    ArrayList<Post> posts;

    public void addUser(User user) {
        int verify = 0;
        //checks if there is user is already a friend
        for (User value : users) {
            if (value.getUsername().equals(user.getUsername())) {
                verify++;
            }
        }
        if (verify == 0) {
           users.add(user);
        }

    }
    public User getUserByUsername(String username) {
        User foundUser = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                foundUser = user;
            }
        }
        return foundUser;
    }

    public void addPost(Post post) {
        posts.add(post);
    }
    public List<Post> getPostsByUser(User user) {
        return null;
    }
    public void addCommendToPost(Comment comment, Post post) {

    }
    public List<Comment> getCommentsForPost(Post post) {
        return null;
    }


}
