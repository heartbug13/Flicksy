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
 interface Posts {
    User getAuthor();
    String getContent();
    int getLikes();
    void setLikes(int likes);
    int getDislikes();
    void setDislikes(int dislikes);
    List<Comment> getComments();
    void addComment(Comment comment);
    void deleteComment(Comment comment);
    void like();
    void dislike();
    boolean equal(Post post);
    String toString();
}
