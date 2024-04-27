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

    /**
     * getter methods for the comments author, content, and likes, dislikes
     * setter methods for the comments likes and dislikes
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
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public int getDislikes() {
        return this.dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    /**
     * method that increments the comments like by one
     */

    public void like() {
        this.likes++;
    }

    /**
     * method that decrements the comments like by one
     */

    public void dislike() {
        this.dislikes++;
    }

    /**
     * returns the string version of a comment
     * Author: prankSinatra
     * Content: Hey, you, Cee, what up fam? It's your girl Winston aka Winnie the Bish...
     * Likes: 6
     * Dislikes: 2
     */

    public String toString() {
        return String.format("Author: %s\nContent: %s\nLikes: %d\nDislikes: %d" ,
                author.getUsername() , content , likes , dislikes);
    }

}
