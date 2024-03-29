import java.util.List;
public interface Post {
    User getAuthor();
    String getContent();
    int getLikes();
    int getDislikes();
    List<Comment> getComments();
    void addComment(Comment comment);
    void deleteComment(Comment comment);
    void like();
    void dislike();
}
