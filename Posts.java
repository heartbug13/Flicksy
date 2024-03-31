import java.util.List;
public interface Posts {
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
