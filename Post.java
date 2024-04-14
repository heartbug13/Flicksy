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

    /**
     * getter methods for author, content, comments, likes, and dislikes
     */

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
    /**
     * adds a comment to the post
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
    /**
     * deletes the comment from the post
     */
    public void deleteComment(Comment comment) {
        this.comments.remove(comment);
    }
    /**
     * increments the like by one
     */
    public void like() {
        this.likes++;
    }
    /**
     * increments the dislike by one
     */
    public void dislike() {
        this.dislikes++;
    }
    /**
     * checks if the posts are equal
     * posts are considered equal if they have same author and content
     */
    public boolean equal(Post post) {
        return author.equals(post.getAuthor()) && content.equals(post.getContent());
    }
}
