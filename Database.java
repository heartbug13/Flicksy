import java.util.List;
public interface Database {
    void addUser(User user);
    User getUserByUsername(String username);
    void addPost(Post post);
    List<Post> getPostsByUser(User user);
    Void addCommendToPost(Comment comment, Post post);
    List<Comment> getCommentsForPost(Post post);
}
