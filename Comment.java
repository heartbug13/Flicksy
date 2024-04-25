import java.io.Serializable;

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

public class Comment implements Comments , Serializable {
    private User author;
    private String content;
    private int likes;
    private int dislikes;

    public Comment(User author , String content) {
        this.content = content;
        this.author = author;
        this.likes = 0;
        this.dislikes = 0;
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
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public int getDislikes() {
        return this.dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void like() {
        this.likes++;
    }
    public void dislike() {
        this.dislikes++;
    }

    public String toString() {
        return String.format("Author: %s\nContent: %s\nLikes: %d\nDislikes: %d" ,
                author.getUsername() , content , likes , dislikes);
    }

}
