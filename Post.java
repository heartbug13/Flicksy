import java.util.ArrayList;
import java.util.List;
/**
 * Group Project - Social Media
 *
 * recreates a social media platform
 *
 * @author Samantha Grief, Connor Mccarthy, Ehssan Kanamkandy, Jahiem Cruickshank, L10
 *
 * @version April 1, 2024
 *
 */
public class Post implements Posts {
    private User author;
    private String content;
    private int likes;
    private int dislikes;
    private List<Comment> comments;

    public Post(User author , String content) {
        this.author = author;
        this.content = content;
        likes = 0;
        dislikes = 0;
        comments = new ArrayList<>();
    }

    public User getAuthor() {
        return this.author;
    }
    public String getContent() {
        return this.content;
    }
    public int getLikes() {
        return this.likes;
    }
    public int getDislikes() {
        return this.dislikes;
    }
    public List<Comment> getComments() {
        return this.comments;
    }
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
    public void deleteComment(Comment comment) {
        this.comments.remove(comment);
    }
    public void like() {
        this.likes++;
    }
    public void dislike() {
        this.dislikes++;
    }

    public boolean equal(Post post) {
        return author.equals(post.getAuthor()) && content.equals(post.getContent());
    }
}
