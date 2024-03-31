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
        List<Post> post = new ArrayList<>();
        for (Post value : posts) {
            if (value.getAuthor().equals(user)) {
                post.add(value);
            }
        }
        return post;
    }
    public void addCommendToPost(Comment comment, Post post) {
        for (Post value : posts) {
            if (value.equal(post)) {
                value.addComment(comment);
            }
        }
    }
    public List<Comment> getCommentsForPost(Post post) {
        List<Comment> comments = new ArrayList<>();
        for (Post value : posts) {
            if (value.equal(post)) {
                comments = post.getComments();
            }
        }
        return comments;
    }


}
