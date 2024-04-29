import javax.swing.*;
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
public interface GUIs {
    void initialPage();
    void createProfilePage();
    void createUserProfile(User user , List<Post> usersPost);
    void sneakPeakOfUser(User user , List<Post> usersPost);
    void  createNewsFeed();
    JPanel createPostPanel(Post post , String num , String page);
    void createComment(Post post , int postIndex);

}
