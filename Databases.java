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
public interface Databases {
    void addUser(User user);
    User getUserByUsername(String username);
    void addPost(Post post);
    List<Post> getPostsByUser(User user);
    void addCommendToPost(Comment comment, Post post);
    List<Comment> getCommentsForPost(Post post);
}
