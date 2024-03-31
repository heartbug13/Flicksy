import java.util.List;
public interface Databases {
    void addUser(User user);
    User getUserByUsername(String username);
    void addPost(Post post);
    List<Post> getPostsByUser(User user);
    void addCommendToPost(Comment comment, Post post);
    List<Comment> getCommentsForPost(Post post);
}
