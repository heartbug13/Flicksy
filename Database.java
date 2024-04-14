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

public class Database {
    private ArrayList<User> users;
    private ArrayList<Post> posts;

    public Database() {
        users = new ArrayList<>();
        posts = new ArrayList<>();
    }

    /**
     * adds a user to the list of users
     * first checks if the user is already added to the database
     */

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
        int verify = 0;
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).equal(post)) {
                verify++;
            }
        }
        if (verify == 0) {
            posts.add(post);
        }
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
    public void addCommentToPost(Comment comment, Post post) {
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
