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

public interface Comments {
    User getAuthor();
    String getContent();
    int getLikes();
    int getDislikes();
    void like();
    void dislike();

}
