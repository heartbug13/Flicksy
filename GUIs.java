import javax.swing.*;
import java.util.List;

public interface GUIs {
    void initialPage();
    void createProfilePage();
    void createUserProfile(User user , List<Post> usersPost);
    void sneakPeakOfUser(User user , List<Post> usersPost);
    void  createNewsFeed();
    JPanel createPostPanel(Post post , String num , String page);
    void createComment(Post post , int postIndex);

}
