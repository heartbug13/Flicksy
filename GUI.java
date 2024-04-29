import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
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

public class GUI implements ActionListener {
    private static JButton signIn;
    private static JButton signUp;
    private static JButton addComment;
    private static JPasswordField getPassword;
    private static JTextField getUserName;
    private static JTextArea addCommentContent;
    private static JTextArea bio;
    private static JTextField name;
    private static JTextArea addPost;
    private static JTextField search;
    private static JLabel welcomeMessage;
    private static JButton makeProfile;
    private static JButton addPostButton;
    private static JButton searchButton;
    private static JButton seeFullProfile;
    private static JButton friendButton;
    private static JButton block;
    private static JMenuBar menuBar;
    private static JMenuItem newsFeedButton = new JMenuItem("News Feed");
    private static JMenuItem usersProfileButton = new JMenuItem("Profile");
    private static Client client;
    private static JFrame userProfile = new JFrame();
    private static JFrame newsFeed = new JFrame();
    private static JFrame commentBoard = new JFrame();
    private static JFrame sneakPeakProfile = new JFrame();
    private static JFrame signInFrame = new JFrame();
    private static JFrame createProfile = new JFrame();
    private static User signedInUser;
    private static List<Post> posts = new ArrayList<>();
    private static List<Post> postByUser = new ArrayList<>();

    /**
     * main method of the class
     * creates a new socket and client that connects to the socket
     * creates a menu bar that will be added to most frames
     * menu bar contains a button to go to the newsfeed and to the signed in users profile
     * calls the method initialPage() to create the sign in/sign up page
     *
     */

    public static void main(String[] args) {
        try  {
            Socket socket = new Socket("localhost" , 8080);
            client = new Client(socket);

            menuBar = new JMenuBar();
            JMenu menu = new JMenu("Menu Bar");

            newsFeedButton.addActionListener(new GUI());
            usersProfileButton.addActionListener(new GUI());
            menu.add(newsFeedButton);
            menu.add(usersProfileButton);

            menuBar.add(menu);

            GUI gui = new GUI();

            gui.initialPage();

            /*
            while (true) {

                if (!signInFrame.isActive() && !userProfile.isActive() && !newsFeed.isActive() && !commentBoard.isActive() &&
                        !sneakPeakProfile.isActive() && !createProfile.isActive()) {
                    System.out.println("closing server");
                    client.sendMessageToServer("close server");
                    client.close();
                    return;
                }

            }

             */



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * creates the initial page that the user sees once they start Flicksy
     * includes a welcome message that changes depending on either or not the user is signing in or signing up
     * sign in and sign up panel asks for the users username and password, the password is displayed as dots to protect the users privacy
     * has a button to submit the information to the server to either verify the users information or create another user
     * has a button to switch between the option to signin or signup
     * if the user is signed in they are taken to their newsfeed, if the user is signing up the user is taken to a frame to ask for their name and biography
     */

    public void initialPage() {
        signInFrame = new JFrame();
        signInFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        signInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signInFrame.setSize(470, 470);
        signInFrame.setLayout(new BorderLayout());

        welcomeMessage = new JLabel("Welcome to your Flicksy Community");
        welcomeMessage.setFont(new Font("SansSerif", Font.PLAIN, 20));

        JPanel topPanel = new JPanel();
        topPanel.add(welcomeMessage);
        signInFrame.add(new JPanel(), BorderLayout.WEST);
        signInFrame.add(new JPanel(), BorderLayout.EAST);
        signInFrame.add(new JPanel(), BorderLayout.SOUTH);
        signInFrame.add(topPanel, BorderLayout.NORTH);
        JPanel signInPanel = new JPanel();
        signInPanel.setBackground(Color.white);
        JLabel userName = new JLabel("Username:");
        getUserName = new JTextField(20);
        getUserName.setColumns(35);
        Color grey = new Color(216, 216, 216);
        getUserName.setBorder(new LineBorder(grey, 1, true));

        JLabel password = new JLabel("Password:");
        getPassword = new JPasswordField(20);
        getPassword.setColumns(35);
        getPassword.setBorder(new LineBorder(grey, 1, true));
        signIn = new JButton("Sign In");
        signIn.addActionListener(new GUI());
        signIn.setForeground(new Color(94 , 134 , 236));
        signUp = new JButton("New to Flicksy? Join now");
        signUp.addActionListener(new GUI());

        signInPanel.add(userName);
        signInPanel.add(getUserName);
        signInPanel.add(password);
        signInPanel.add(getPassword);
        signInPanel.add(signIn);
        signInPanel.add(signUp);

        signInFrame.add(signInPanel, BorderLayout.CENTER);
        signInFrame.setMinimumSize(new Dimension(470, 200));
        signInFrame.setMaximumSize(new Dimension(500, 500));
        //frame.pack();
        signInFrame.setVisible(true);
    }

    /**
     * creates a frame to ask for the users name and bio
     * uses a jtextfield to ask for their name, but a jtextarea to ask for their because their bio will likely be longer
     * has a button to summit the information to the server
     * once the user created their profile they are taken to their newsfeed
     */

    public void createProfilePage() {
        createProfile = new JFrame();
        createProfile.setSize(370, 300);
        createProfile.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        main.setBackground(Color.white);

        JPanel getBio = new JPanel();
        JPanel getName = new JPanel();

        getName.setLayout(new BorderLayout());
        getName.add(new JLabel("Name:"), BorderLayout.NORTH);
        name = new JTextField();
        getName.add(name , BorderLayout.SOUTH);

        getBio.setLayout(new BorderLayout());
        bio = new JTextArea();
        bio.setLineWrap(true);
        bio.setText("Bio...");
        //bio.setPreferredSize(new Dimension(166 , 19));
        getBio.add(new JLabel("Bio:") , BorderLayout.NORTH);
        getBio.add(new JScrollPane(bio) , BorderLayout.CENTER);

        makeProfile = new JButton("Make Profile");
        makeProfile.addActionListener(new GUI());

        main.add(getName , BorderLayout.NORTH);
        main.add(getBio , BorderLayout.CENTER);
        main.add(makeProfile , BorderLayout.SOUTH);
        getName.setBackground(Color.white);
        getBio.setBackground(Color.white);
        main.setBorder(new EmptyBorder(10 , 10, 10 ,10));

        createProfile.add(main , BorderLayout.CENTER);
        createProfile.add(new JPanel() , BorderLayout.NORTH);
        createProfile.add(new JPanel() , BorderLayout.SOUTH);
        createProfile.add(new JPanel() , BorderLayout.WEST);
        createProfile.add(new JPanel() , BorderLayout.EAST);
        createProfile.setVisible(true);
        createProfile.setResizable(false);

    }

    /**
     * creates a profile for the user
     * on the left side of the frame holds the user's information including their actual name and their username
     * left side of the panel also has two buttons, one button to friend/unfriend the user and one button two block/unblock the user
     * it also shows the number of friends and posts the user has and their bio
     * on the right side of the frame displays all the users post, if there is no post it
     * displays the message "No posts yet by username"
     */

    public void createUserProfile(User user , List<Post> usersPost) {
        userProfile = new JFrame();
        userProfile.setSize(675 , 663);
        userProfile.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        userProfile.setJMenuBar(menuBar);

        JPanel postsPanel = new JPanel();

        if (usersPost.isEmpty()) {
            JPanel emptyPost = new JPanel();
            emptyPost.setBorder(new EmptyBorder(40, 10, 40, 10));
            emptyPost.setBackground(Color.white);
            emptyPost.setPreferredSize(new Dimension(435, 154));

            emptyPost.add(new JLabel("No posts yet by " + user.getUsername()));
            main.add(emptyPost, BorderLayout.EAST);

        } else {

            if (usersPost.size() > 1) {
                postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
            }
            List<User> blocked = user.getBlocked();

            //JPanel test = new JPanel();
            //System.out.println(posts);
            for (int i = 0; i < posts.size(); i++) {
                if (posts.get(i).getAuthor().equal(user)) {
                    int verify = 0;
                    User author = posts.get(i).getAuthor();
                    for (int j = 0; j < blocked.size(); i++) {
                        if (blocked.get(i).equal(author)) {
                            verify++;
                        }
                    }
                    if (verify == 0 && !user.equal(signedInUser)) {
                        JPanel post = createPostPanel(posts.get(i), String.valueOf(i), "NewsFeed");
                        postsPanel.add(post);
                    } else if (verify == 0) {
                        JPanel post = createPostPanel(posts.get(i), String.valueOf(i), "UsersProfile");
                        postsPanel.add(post);
                    }
                }
            }

            JScrollPane scrollPane = new JScrollPane(postsPanel);
            scrollPane.setBorder(new LineBorder(main.getBackground()));

            //scrollPane.setVerticalScrollBar();
            main.add(scrollPane, BorderLayout.EAST);
        }
        //posts.add(postPanel);
        //posts.add(postPanel);

        JPanel userPanel = new JPanel();
        userPanel.setBorder(new EmptyBorder(10 , 10, 10,10));
        userPanel.setBackground(Color.white);
        userPanel.setLayout(new BorderLayout());

        JPanel friendPanel = new JPanel();
        friendPanel.setLayout(new BorderLayout());
        friendPanel.setBackground(Color.white);
        friendPanel.add(new JLabel(" ") , BorderLayout.NORTH);
        friendPanel.add(new JLabel("Friends: ") , BorderLayout.WEST);
        JLabel friendNum = new JLabel(String.valueOf(user.getFriends().size()));
        friendPanel.add(friendNum , BorderLayout.EAST);

        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BorderLayout());
        postPanel.setBackground(Color.white);
        postPanel.add(new JLabel("Post: ") , BorderLayout.WEST);
        postPanel.add(new JLabel(String.valueOf(usersPost.size())) , BorderLayout.EAST);

        JTextPane bio = new JTextPane();
        bio.setText(user.getProfile().getBio());
        bio.setPreferredSize(new Dimension(144 , 50));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BorderLayout());
        JLabel username = new JLabel(user.getUsername() , JLabel.CENTER);
        Font bold = new Font(username.getFont().getName() , Font.BOLD , 12);
        username.setFont(bold);
        JLabel name = new JLabel(user.getProfile().getName() , JLabel.CENTER);
        namePanel.add(username , BorderLayout.NORTH);
        namePanel.add(name , BorderLayout.SOUTH);
        namePanel.setBackground(Color.white);

        JPanel userInfo = new JPanel();
        userInfo.setLayout(new BoxLayout(userInfo , BoxLayout.Y_AXIS));
        userInfo.add(friendPanel);
        userInfo.add(postPanel);
        userInfo.setBackground(Color.white);

        userPanel.add(namePanel , BorderLayout.NORTH);
        userPanel.add(userInfo , BorderLayout.CENTER);

        JPanel bioPanel = new JPanel();
        bioPanel.setLayout(new BorderLayout());
        bioPanel.add(new JLabel("Bio" , JLabel.CENTER) , BorderLayout.NORTH);
        bioPanel.add(new JLabel(" " ) , BorderLayout.CENTER);
        //bioPanel.add(new JLabel(" "));
        JScrollPane test = new JScrollPane(bio);
        test.setPreferredSize(new Dimension(171 , 120));
        bioPanel.add(test , BorderLayout.SOUTH);
        bioPanel.setBackground(Color.white);

        userPanel.add(bioPanel , BorderLayout.SOUTH);
        userPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.add(userPanel , BorderLayout.NORTH);
        if (!signedInUser.equal(user)) {
            friendButton = new JButton("Friend");
            block = new JButton("Block");

            friendButton.setPreferredSize(new Dimension(171, 18));
            friendButton.addActionListener(new GUI());
            friendButton.setActionCommand(user.getUsername());

            if (signedInUser.isFriend(user)) {
                friendButton.setText("Unfriend");
            }
            if (signedInUser.isBlocked(user)) {
                block.setText("Unblock");
            }
            block.setPreferredSize(new Dimension(171, 18));
            block.addActionListener(new GUI());
            block.setActionCommand(user.getUsername());

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BorderLayout());
            buttonPanel.add(friendButton, BorderLayout.NORTH);
            buttonPanel.add(new JPanel(), BorderLayout.CENTER);
            buttonPanel.add(block, BorderLayout.SOUTH);
            buttonPanel.setBorder(new EmptyBorder(10 ,10 , 10 ,10));

            southPanel.add(buttonPanel, BorderLayout.CENTER);
        }

        JPanel panel = new JPanel();
        panel.add(southPanel);

        main.add(panel , BorderLayout.WEST);

        userProfile.add(main , BorderLayout.CENTER);
        userProfile.add(new JPanel() , BorderLayout.NORTH);
        userProfile.add(new JPanel() , BorderLayout.SOUTH);
        userProfile.add(new JPanel() , BorderLayout.EAST);
        userProfile.add(new JPanel() , BorderLayout.WEST);
        userProfile.setVisible(true);
        userProfile.setResizable(false);

    }

    /**
     * creates the sneak peak frame of a user
     * is shown when a user searchers for another user
     * at the top of the page, it shows the users name, username, number of posts and friends, and their bio
     * at the bottom of the page it shows the first of the users post and a button to take them to their full profile
     */

    public void sneakPeakOfUser(User user , List<Post> usersPost) {
        sneakPeakProfile = new JFrame();
        sneakPeakProfile.setSize(493, 620);
        sneakPeakProfile.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        sneakPeakProfile.setJMenuBar(menuBar);


        JPanel userPanel = new JPanel();
        userPanel.setBackground(Color.white);
        userPanel.setLayout(new BorderLayout());

        JPanel friendPanel = new JPanel();
        friendPanel.setLayout(new BorderLayout());
        friendPanel.setBackground(Color.white);
        friendPanel.add(new JLabel(" "), BorderLayout.NORTH);
        friendPanel.add(new JLabel("Friends: "), BorderLayout.WEST);
        JLabel friendNum = new JLabel(String.valueOf(user.getFriends().size()));
        friendPanel.add(friendNum, BorderLayout.EAST);

        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BorderLayout());
        postPanel.setBackground(Color.white);
        postPanel.add(new JLabel("Post: "), BorderLayout.WEST);
        postPanel.add(new JLabel(String.valueOf(usersPost.size())), BorderLayout.EAST);

        JTextPane bio = new JTextPane();
        bio.setText(user.getProfile().getBio());
        bio.setPreferredSize(new Dimension(450, 90));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BorderLayout());
        JLabel username = new JLabel(user.getUsername(), JLabel.CENTER);
        Font bold = new Font(username.getFont().getName(), Font.BOLD, 12);
        username.setFont(bold);
        JLabel name = new JLabel(user.getProfile().getName(), JLabel.CENTER);
        namePanel.add(username, BorderLayout.NORTH);
        namePanel.add(name, BorderLayout.SOUTH);
        namePanel.setBackground(Color.white);

        JPanel userInfo = new JPanel();
        userInfo.setLayout(new BoxLayout(userInfo, BoxLayout.Y_AXIS));
        //userInfo.add(friendPanel);
        //userInfo.add(postPanel);
        userInfo.setBackground(Color.white);

        userInfo.add(friendPanel);
        userInfo.add(postPanel);

        userPanel.add(namePanel, BorderLayout.NORTH);
        userPanel.add(userInfo, BorderLayout.CENTER);

        JPanel bioPanel = new JPanel();
        bioPanel.setLayout(new BorderLayout());
        bioPanel.add(new JLabel("Bio" , JLabel.CENTER) , BorderLayout.NORTH);
        bioPanel.add(new JLabel(" " ) , BorderLayout.CENTER);
        //bioPanel.add(new JLabel(" "));
        bioPanel.add(new JScrollPane(bio) , BorderLayout.SOUTH);
        bioPanel.setBackground(Color.white);

        userPanel.add(bioPanel , BorderLayout.SOUTH);
        userPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panel = new JPanel();
        panel.add(new JPanel());
        panel.add(userPanel);
        mainPanel.add(userPanel, BorderLayout.NORTH);

        if (usersPost.isEmpty()) {
            JPanel emptyPost = new JPanel();
            emptyPost.setLayout(new BorderLayout());
            emptyPost.add(new JPanel());
            emptyPost.setBorder(new EmptyBorder(10, 10, 10, 10));
            emptyPost.setBackground(Color.white);
            emptyPost.setPreferredSize(new Dimension(435, 154));

            emptyPost.add(new JLabel("No posts yet by " + user.getUsername()));
            mainPanel.add(emptyPost, BorderLayout.CENTER);
        } else {


            int num = 0;
            for (int i = 0; i < posts.size(); i++) {
                if (usersPost.get(0).equal(posts.get(i))) {
                    num = i;
                    i = posts.size();
                }
            }

            JPanel firstPostPanel = createPostPanel(usersPost.get(0) , String.valueOf(num) , "SneakPeakPage");
            JPanel testPanel = new JPanel();
            testPanel.add(new JPanel());
            testPanel.add(firstPostPanel);
            //firstPostPanel.add(new JPanel());
            mainPanel.add(testPanel , BorderLayout.CENTER);

        }

        //sneakPeakProfile.add(new JButton("See Full Profile") , BorderLayout.SOUTH);
        seeFullProfile = new JButton("See Full Profile");
        seeFullProfile.addActionListener(new GUI());
        seeFullProfile.setActionCommand(user.getUsername());
        mainPanel.add(seeFullProfile , BorderLayout.SOUTH);

        sneakPeakProfile.add(mainPanel , BorderLayout.CENTER);
        sneakPeakProfile.add(new JPanel() , BorderLayout.SOUTH);
        sneakPeakProfile.add(new JPanel() , BorderLayout.NORTH);
        sneakPeakProfile.add(new JPanel(), BorderLayout.WEST);
        sneakPeakProfile.add(new JPanel(), BorderLayout.EAST);



        sneakPeakProfile.setVisible(true);
        sneakPeakProfile.setResizable(false);

    }

    /**
     * creates the news feed of a user
     * displays all the posts created by users, hides any posts by blocked users
     * at the left side of the news feed page it shows the signed in users information
     * this included the users name, username, number of posts and friends, and their bio
     * the right side are all the posts
     * each post has the option to display the comments of the posts, like or dislike the post and friend/unfriend the author of the post
     * each post also shows the username of the post's author
     */

    public void  createNewsFeed(List<Post> allPost) {
        System.out.println("creating news feed");
        newsFeed = new JFrame();
        newsFeed.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ImageIcon icon = new ImageIcon("Flicksy.PNG");
        newsFeed.setIconImage(icon.getImage());
        newsFeed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newsFeed.setSize(675, 663);
        //frame.setLayout(new BoxLayout(frame , BoxLayout.X_AXIS));
        newsFeed.setLayout(new BorderLayout());

        newsFeed.setJMenuBar(menuBar);

        JPanel newPanel = new JPanel();
        //newPanel.setBackground(Color.white);
        newPanel.setLayout(new BorderLayout());
        newPanel.setBorder(new EmptyBorder(10 , 10 , 10 ,10));

        Color lightGrey = new Color(216 , 216 , 216);
        search = new JTextField();
        search.setText("Search...");
        search.setBorder(new LineBorder(lightGrey));
        addPost = new JTextArea();

        addPost.setBorder(new LineBorder(lightGrey));
        addPost.setText("Start a Post...");
        addPost.setLineWrap(true);

        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(new EmptyBorder(10 , 10 , 10 ,10));
        searchPanel.setBackground(Color.white);
        searchButton = new JButton("Search");
        searchButton.addActionListener(new GUI());
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(search , BorderLayout.NORTH);
        searchPanel.add(searchButton , BorderLayout.SOUTH);

        JPanel addPostPanel = new JPanel();
        addPostPanel.setBorder(new EmptyBorder(10 , 10 , 10 ,10));
        addPostPanel.setBackground(Color.white);
        addPostButton = new JButton("Add Post");
        addPostButton.addActionListener(new GUI());
        addPostPanel.setLayout(new BorderLayout());
        addPostPanel.add(addPost , BorderLayout.NORTH);
        addPostPanel.add(addPostButton , BorderLayout.SOUTH);
        //addPostPanel.add(addPost);
        addPostPanel.add(addPostButton);


        newPanel.add(searchPanel , BorderLayout.NORTH);
        newPanel.add(new JPanel() , BorderLayout.CENTER);
        newPanel.add(addPostPanel , BorderLayout.SOUTH);


        newsFeed.add(newPanel , BorderLayout.NORTH);

        JPanel addPostAndSearchPanel = new JPanel();
        addPostAndSearchPanel.setLayout(new BorderLayout());



        JPanel postsPanel = new JPanel();

        if (allPost.size() > 1) {
            postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        }
        List<User> blocked = signedInUser.getBlocked();
        //JPanel test = new JPanel();
        for (int i = 0; i < allPost.size(); i++) {
            int verify = 0;
            User author = allPost.get(i).getAuthor();
            for (int j = 0; j < blocked.size(); i++) {
                if (blocked.get(i).equal(author)) {
                    verify++;
                }
            }
            if (verify == 0) {
                JPanel post = createPostPanel(allPost.get(i), String.valueOf(i), "NewsFeed");
                postsPanel.add(post);
            }
        }
        //posts.add(postPanel);
        //posts.add(postPanel);
        JScrollPane scrollPane = new JScrollPane(postsPanel);
        scrollPane.setBorder(new LineBorder(newsFeed.getBackground()));

        //scrollPane.setVerticalScrollBar();
        newsFeed.add(scrollPane, BorderLayout.EAST);

        JPanel userPanel = new JPanel();
        userPanel.setBackground(Color.white);
        userPanel.setLayout(new BorderLayout());

        JPanel friendPanel = new JPanel();
        friendPanel.setLayout(new BorderLayout());
        friendPanel.setBackground(Color.white);
        friendPanel.add(new JLabel(" ") , BorderLayout.NORTH);
        friendPanel.add(new JLabel("Friends: ") , BorderLayout.WEST);
        JLabel friendNum = new JLabel(String.valueOf(signedInUser.getFriends().size()));
        friendPanel.add(friendNum , BorderLayout.EAST);

        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BorderLayout());
        postPanel.setBackground(Color.white);
        postPanel.add(new JLabel("Post: ") , BorderLayout.WEST);
        postPanel.add(new JLabel(String.valueOf(postByUser.size())) , BorderLayout.EAST);

        JTextPane bio = new JTextPane();
        bio.setText(signedInUser.getProfile().getBio());
        bio.setPreferredSize(new Dimension(144 , 120));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BorderLayout());
        JLabel username = new JLabel(signedInUser.getUsername() , JLabel.CENTER);
        Font bold = new Font(username.getFont().getName() , Font.BOLD , 12);
        username.setFont(bold);
        JLabel name = new JLabel(signedInUser.getProfile().getName() , JLabel.CENTER);
        namePanel.add(username , BorderLayout.NORTH);
        namePanel.add(name , BorderLayout.SOUTH);
        namePanel.setBackground(Color.white);

        JPanel userInfo = new JPanel();
        userInfo.setLayout(new BoxLayout(userInfo , BoxLayout.Y_AXIS));
        userInfo.add(friendPanel);
        userInfo.add(postPanel);
        userInfo.setBackground(Color.white);

        userPanel.add(namePanel , BorderLayout.NORTH);
        userPanel.add(userInfo , BorderLayout.CENTER);

        JPanel bioPanel = new JPanel();
        bioPanel.setLayout(new BoxLayout(bioPanel , BoxLayout.PAGE_AXIS));
        bioPanel.add(new JLabel("Bio"));
        bioPanel.add(new JLabel(" "));
        bioPanel.add(new JScrollPane(bio));
        bioPanel.setBackground(Color.white);

        JPanel bioPanel1 = new JPanel();
        bioPanel1.add(bioPanel);

        userPanel.add(bioPanel , BorderLayout.SOUTH);


        userPanel.setBorder(new EmptyBorder(10, 10 , 10, 10));
        JPanel southPanel = new JPanel();
        southPanel.add(userPanel);


        newsFeed.add(southPanel , BorderLayout.WEST);

        newsFeed.setVisible(true);
        newsFeed.setResizable(false);
    }

    /**
     * creates a post panel for the post
     * takes in the post that is being displayed, the index of the post in the posts array, and which page it will be displayed in
     * the index of the post is used to direct the server on which post to modify
     * the post includes the username of the post's author with the ability to
     * display the comments of the post, like/dislike the post, and to friend/unfriend the post
     */

    public JPanel createPostPanel(Post post , String num , String page) {
        JPanel postPanel = new JPanel();

        postPanel.setSize(435, 219);
        postPanel.setBackground(Color.white);
        postPanel.setLayout(new BorderLayout());

        JLabel username = new JLabel(post.getAuthor().getUsername());
        Font bold = new Font(username.getFont().getName() , Font.BOLD , 12);
        username.setFont(bold);

        postPanel.add(username , BorderLayout.NORTH);

        JTextPane ja = new JTextPane();
        ja.setText(post.getContent());

        ja.setPreferredSize(new Dimension(435, 200));
        postPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        if (page.equals("NewsFeed")) {

            JButton comment = new JButton("Comment");
            comment.setBorder(new LineBorder(new Color(14 , 219 , 245)));
            comment.addActionListener(new GUI());
            comment.setActionCommand("comment" + page + "-" + num);
            JButton like = new JButton("Like " + post.getLikes());
            like.addActionListener(new GUI());
            like.setBorder(new LineBorder(new Color(46 , 247 , 13)));
            like.setActionCommand("like" + page + "-" + num);
            JButton dislike = new JButton("Dislike " + post.getDislikes());
            dislike.setBorder(new LineBorder(new Color(236 , 12 , 12)));
            dislike.addActionListener(new GUI());
            dislike.setActionCommand("disLike" + page + "-" + num);

            JPanel buttonPanel = new JPanel();

            comment.setPreferredSize(new Dimension(100, 20));
            like.setPreferredSize(new Dimension(100, 20));
            dislike.setPreferredSize(new Dimension(100, 20));
            ja.setEditable(false);


            buttonPanel.add(comment);
            buttonPanel.add(like);
            buttonPanel.add(dislike);
            buttonPanel.setBackground(Color.white);

            JButton friend = new JButton("Friend");
            friend.setBorder(new LineBorder(new Color(108 , 24 , 245)));

            if (!signedInUser.isFriend(post.getAuthor())) {
                friend.addActionListener(new GUI());
                friend.setActionCommand(num + "-friend" + page + "-" + post.getAuthor().getUsername());
            } else {
                friend.setText("Unfriend");
                friend.addActionListener(new GUI());
                friend.setActionCommand(num + "-unFriend" + page + "-" + post.getAuthor().getUsername());
            }
            friend.setPreferredSize(new Dimension(100, 20));
            buttonPanel.add(friend);


            postPanel.add(buttonPanel, BorderLayout.SOUTH);
        }
        postPanel.add(new JScrollPane(ja), BorderLayout.CENTER);

        return postPanel;
    }

    /**
     * creates a comment board
     * displays all the posts comment an allows the user to like or dislike a comment
     * also allows the user to add a comment to the post
     */

    public void createComment(Post post , int postIndex) {
        commentBoard = new JFrame();
        commentBoard.setSize(346 , 300);
        commentBoard.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ImageIcon icon = new ImageIcon("Flicksy.PNG");
        commentBoard.setIconImage(icon.getImage());

        JPanel addCommentPanel = new JPanel();
        addCommentPanel.setLayout(new BorderLayout());
        addCommentPanel.setBackground(Color.white);
        addCommentPanel.setBorder(new EmptyBorder(10 , 10 , 10 , 10));
        addCommentContent = new JTextArea();
        //addCommentContent.setPreferredSize(new Dimension(251 , 68));
        addCommentContent.setText("Add A Comment\n");
        addCommentContent.setLineWrap(true);

        addCommentPanel.add(addCommentContent , BorderLayout.NORTH);
        addComment = new JButton("Add Comment");
        addComment.addActionListener(new GUI());
        addComment.setActionCommand(String.valueOf(postIndex));
        addCommentPanel.add(addComment , BorderLayout.SOUTH);

        commentBoard.add(addCommentPanel , BorderLayout.NORTH);

        List<Comment> listOfComments = post.getComments();

        JPanel commentPanel = new JPanel();

        if (listOfComments.size() > 1) {
            commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
        }

        for (int i = 0; i < listOfComments.size(); i++) {
            JPanel comment = new JPanel();
            comment.setLayout(new BorderLayout());
            comment.setBackground(Color.white);

            JLabel commentAuthor = new JLabel(listOfComments.get(i).getAuthor().getUsername());
            Font bold = new Font(commentAuthor.getFont().getName() , Font.BOLD , 12);
            commentAuthor.setFont(bold);
            comment.add(commentAuthor , BorderLayout.NORTH);

            JTextPane jt = new JTextPane();
            jt.setText(listOfComments.get(i).getContent());
            jt.setPreferredSize(new Dimension(282 , 113));
            jt.setEditable(false);
            comment.add(jt , BorderLayout.CENTER);
            comment.setBorder(new EmptyBorder(10 , 10 , 10 ,10));
            commentPanel.add(comment);

            JButton like = new JButton("Like " + listOfComments.get(i).getLikes());
            like.addActionListener(new GUI());
            like.setBorder(new LineBorder(new Color(46 , 247 ,13)));
            like.setActionCommand(postIndex + "-likeCommentBoard-" + i);
            JButton dislike = new JButton("Dislike " + listOfComments.get(i).getDislikes());
            dislike.addActionListener(new GUI());
            dislike.setBorder(new LineBorder(new Color(236 , 12 , 12)));
            dislike.setActionCommand(postIndex + "-disLikeCommentBoard-" + i);

            like.setPreferredSize(new Dimension(100 , 20));
            dislike.setPreferredSize(new Dimension(100 , 20));

            JPanel buttonPanel = new JPanel();

            buttonPanel.add(like);
            buttonPanel.add(dislike);

            buttonPanel.setBackground(Color.white);

            comment.add(buttonPanel , BorderLayout.SOUTH);

            commentPanel.add(new JPanel());
        }

        JScrollPane scrollPane = new JScrollPane(commentPanel);
        commentBoard.add(scrollPane , BorderLayout.CENTER);
        commentBoard.setVisible(true);
        commentBoard.setResizable(false);

    }

    /**
     * method for the user to interact with the GUI by pressing buttons
     * checks the source of the action and modify the GUI accordingly
     */

    public void actionPerformed(ActionEvent e) {
        try {

            JButton checkButton = new JButton();

            if (!(e.getSource() == newsFeedButton) && !(e.getSource() == usersProfileButton)) {
                //checks if the source is not from the newsfeed or user profile button
                //if source is not from the menu bar, casts the source as a button
                checkButton = (JButton) e.getSource();
            }


            if (e.getSource() == signIn && signIn.getActionCommand().equals("Sign In")) {
                //checks if the source is from the sign in button and the action command equals "Sign In"
                //if so, gets the username and password entered in their respective text fields
                //turns the password into a string and checks if either of the text fields are empty
                //if there is an empty field the program displays an error message asking the user to fill out all the fields
                //if both the text fields are filed, sends the information to the server to add the user to the database
                //assigns the signed in user to the user that signed in
                //also checks if the password is correct, if the password is incorrect displays an error message
                //takes the user to the newsfeed
                //gets all the posts made previously
                String username = getUserName.getText();
                char[] password = getPassword.getPassword();
                String pass = "";
                for (int i = 0; i < password.length; i++) {
                    pass += password[i];
                }

                if (pass.isEmpty() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill out all text fields",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    client.sendMessageToServer("sign in");
                    client.sendMessageToServer(username);
                    client.sendMessageToServer(pass);

                    //String response = (String) inputStream.readObject();
                    String response = (String) client.receiveMessageFromServer();
                    if (response.equals("true")) {
                        //Object check = inputStream.readObject();
                        signedInUser = (User) client.receiveMessageFromServer();

                        int size = (Integer) client.receiveMessageFromServer();
                        for (int i = 0; i < size; i++) {
                            Post post = (Post) client.receiveMessageFromServer();

                            //Post post = (Post) inputStream.readObject();
                            postByUser.add(post);
                        }

                        signInFrame.setVisible(false);

                        client.sendMessageToServer("get all posts");
                        size = (Integer) client.receiveMessageFromServer();
                        List<Post> allPost = new ArrayList<>();
                        for (int i = 0; i < size; i++) {
                            Post post = (Post) client.receiveMessageFromServer();
                            allPost.add(post);
                        }

                        posts = allPost;
                        createNewsFeed(allPost);
                    } else {
                        JOptionPane.showMessageDialog(null, "Username or Password is incorrect",
                                "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else if (e.getSource() == signUp && signUp.getActionCommand().equals("New to Flicksy? Join now")) {
                //checks if the source is from the sign up button and if the action command is asking to join Flicksy
                //if so it changes the text to fit the sign up display
                signIn.setActionCommand("Sign Up");
                signIn.setText("Sign Up");
                signUp.setActionCommand("Already on Flicksy? Sign in");
                signUp.setText("Already on Flicksy? Sign in");
                welcomeMessage.setText("Make the Most of Flicksy");

            } else if (e.getSource() == signIn && signIn.getActionCommand().equals("Sign Up")) {
                //checks if the source is sign in and the action command is sign up
                //turns the password into a string and gets the username
                //checks if any of the fields are empty, if so displays a error message
                //if all the fields are filled, the server checks if the user is already added/created
                //if the user is already added, shows an error message, if not takes the user to create their profile
                String username = getUserName.getText();
                char[] password = getPassword.getPassword();
                String pass = "";
                for (int i = 0; i < password.length; i++) {
                    pass += password[i];
                }

                if (pass.isEmpty() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill out all text fields",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    client.sendMessageToServer("sign up");
                    client.sendMessageToServer(username);
                    client.sendMessageToServer(pass);

                    String response = (String) client.receiveMessageFromServer();

                    if (response.equals("creating user")) {

                        signInFrame.setVisible(false);
                        String filler = "";
                        Profile profile = new Profile(filler, filler);
                        signedInUser = new User(username, pass, profile);
                        createProfilePage();
                    } else {
                        JOptionPane.showMessageDialog(null, response,
                                "Error", JOptionPane.INFORMATION_MESSAGE);
                    }

                }

            } else if (e.getSource() == signUp && signUp.getActionCommand().equals("Already on Flicksy? Sign in")) {
                //checks if the source is signup and if the action command is asking to sign in
                //changes the frame according to the sign in page
                signIn.setActionCommand("Sign In");
                signIn.setText("Sign In");
                signUp.setActionCommand("New to Flicksy? Join now");
                signUp.setText("New to Flicksy? Join now");
                welcomeMessage.setText("Welcome to your Flicksy Community");
            } else if (e.getSource() == addComment && !addCommentContent.getText().equals("Add A Comment\n")) {
                //checks if the source is from add comment and if the addCommentContent text doesn't equal the base text
                //if everything is okay, adds a comment to the post and updates the comment board
                Comment comment = new Comment(signedInUser , addCommentContent.getText());
                int postIndex = Integer.parseInt(checkButton.getActionCommand());

                client.sendMessageToServer("add comment");
                client.sendMessageToServer(postIndex);
                client.sendMessageToServer(comment);

                client.sendMessageToServer("get all posts");
                int size = (Integer) client.receiveMessageFromServer();
                List<Post> allPost = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Post post = (Post) client.receiveMessageFromServer();
                    allPost.add(post);
                }

                //allPost.get(postIndex).addComment(comment);
                commentBoard.setVisible(false);
                createComment(allPost.get(postIndex) , postIndex);
            } else if (e.getSource() == addPostButton && !addPost.getText().equals("Start a Post...")) {
                //checks if the source is fromm the add post button and if the add post content doesn't equal the base text
                //if everything is okay, adds a post and updates the newsfeed with the new post
                Post post = new Post(signedInUser , addPost.getText());

                client.sendMessageToServer("add post");
                client.sendMessageToServer(post);

                postByUser.add(post);
                newsFeed.setVisible(false);

                client.sendMessageToServer("get all posts");
                int size = (Integer) client.receiveMessageFromServer();
                List<Post> allPost = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Post post1 = (Post) client.receiveMessageFromServer();
                    allPost.add(post1);
                }

                createNewsFeed(allPost);
            } else if (e.getSource() == searchButton && !search.getText().equals("Search...")) {
                //checks if the source is from search button and the search text doesn't equal the base text
                //if everything is okay, sends the search string to the server to find all the users with username that contains the search message
                //if there is no result/users found displays an error message
                //if there is a result displays the option pane of all the users found

                client.sendMessageToServer("search");
                client.sendMessageToServer(search.getText());

                //int size = (Integer) inputStream.readObject();
                int size = (Integer) client.receiveMessageFromServer();
                String[] searchResultString = new String[size];
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        //searchResultString[i] = (String) inputStream.readObject();
                        searchResultString[i] = (String) client.receiveMessageFromServer();
                    }


                    String choice = (String) JOptionPane.showInputDialog(null, "What is your choice?",
                            "Search Client", JOptionPane.QUESTION_MESSAGE, null,
                            searchResultString, searchResultString[0]);

                    client.sendMessageToServer("find user based on username");
                    client.sendMessageToServer(choice);

                    User foundUser = (User) client.receiveMessageFromServer();

                    client.sendMessageToServer("find post by user");
                    client.sendMessageToServer(foundUser.getUsername());

                    size = (Integer) client.receiveMessageFromServer();

                    List<Post> usersPost = new ArrayList<>();

                    for (int i = 0; i < size; i++) {
                        Post post = (Post) client.receiveMessageFromServer();
                        usersPost.add(post);
                    }

                    newsFeed.setVisible(false);
                    sneakPeakOfUser(foundUser , usersPost);


                } else {
                    JOptionPane.showMessageDialog(null, "Search failed, no user with t" +
                                    "hat username are associated with Flicksy", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (e.getSource() == seeFullProfile) {
                //checks to see if the source is the from the see full profile button
                //gets the username of the profile the user wishes to see
                //finds the user based on the username via server
                //finds all the post based on the username
                //takes the user to full profile of the user
                String username = seeFullProfile.getActionCommand();

                client.sendMessageToServer("find user based on username");
                client.sendMessageToServer(username);

                User foundUser = (User) client.receiveMessageFromServer();

                client.sendMessageToServer("find post by user");
                client.sendMessageToServer(foundUser.getUsername());

                int size = (Integer) client.receiveMessageFromServer();

                List<Post> usersPost = new ArrayList<>();

                for (int i = 0; i < size; i++) {
                    Post post = (Post) client.receiveMessageFromServer();
                    usersPost.add(post);
                }

                newsFeed.setVisible(false);
                sneakPeakProfile.setVisible(false);
                createUserProfile(foundUser , usersPost);

            } else if (e.getSource() == friendButton && friendButton.getText().equals("Friend")) {
                String username = friendButton.getActionCommand();
                //checks if the source is from the friend button in the user profile and the text equals "Friend"
                //if so communicates with the server to find the friend and adds the friend
                //if the user is already added, blocked by the user, or if the user equals the signed user displays a error message

                client.sendMessageToServer("friend");
                client.sendMessageToServer(username);
                client.sendMessageToServer(signedInUser);

                String response = (String) client.receiveMessageFromServer();

                if (response.equals("adding a friend")) {

                    User friend = (User) client.receiveMessageFromServer();

                    signedInUser.addFriend(friend);

                    client.sendMessageToServer("find post by user");
                    client.sendMessageToServer(friend.getUsername());

                    int size = (Integer) client.receiveMessageFromServer();

                    List<Post> usersPost = new ArrayList<>();

                    for (int i = 0; i < size; i++) {
                        Post post = (Post) client.receiveMessageFromServer();
                        usersPost.add(post);
                    }

                    friendButton.setText("UnFriend");
                    createUserProfile(friend , usersPost);
                } else if (response.equals("you have already added this user as a friend")
                        || response.equals("you have been blocked by this user")
                        || response.equals("friend and user are equal")) {
                    JOptionPane.showMessageDialog(null, response , "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (e.getSource() == friendButton && friendButton.getText().equals("Unfriend")) {
                String username = friendButton.getActionCommand();
                //checks if the source is from the friend button in the user profile and the text equals "Unfriend"
                //if so communicates with the server to find the friend and removes the friend
                //displays an error message if the user and friend are equal

                client.sendMessageToServer("unfriend");
                client.sendMessageToServer(username);
                client.sendMessageToServer(signedInUser);

                String response = (String) client.receiveMessageFromServer();

                if (response.equals("adding a friend")) {

                    User friend = (User) client.receiveMessageFromServer();

                    signedInUser.removeFriend(friend);
                    newsFeed.setVisible(false);
                    friendButton.setText("Friend");
                } else if (response.equals("friend and user are equal")) {
                    JOptionPane.showMessageDialog(null, response , "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (e.getSource() == block && block.getText().equals("Block")) {
                String username = block.getActionCommand();
                //checks if the source is from the block button in the user profile and the text equals "Block"
                //if so communicates with the server to find the user and blocks the user
                //displays an error message if there is an exception thrown when blocking the user, ie the user is already blocked

                client.sendMessageToServer("block");
                client.sendMessageToServer(username);
                client.sendMessageToServer(signedInUser);

                String response = (String) client.receiveMessageFromServer();

                if (response.equals("blocking user")) {
                    User blocked = (User) client.receiveMessageFromServer();
                    signedInUser.blockUser(blocked);
                    block.setText("Unblock");
                } else {
                    JOptionPane.showMessageDialog(null, response , "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (e.getSource() == block && block.getText().equals("Unblock")) {
                //checks if the source is from the block button in the user profile and the text equals "Unblock"
                //if so communicates with the server to find the user and unblocks the user
                String username = block.getActionCommand();
                client.sendMessageToServer("unblock");
                client.sendMessageToServer(username);
                client.sendMessageToServer(signedInUser);

                User unblocked = (User) client.receiveMessageFromServer();
                signedInUser.unblockUser(unblocked);
                block.setText("Block");
            } else if (e.getSource() == newsFeedButton) {
                //checks if the source is from the news feed button
                //if so creates the news feed and closes any open frames
                userProfile.setVisible(false);
                newsFeed.setVisible(false);
                commentBoard.setVisible(false);
                sneakPeakProfile.setVisible(false);
                signInFrame.setVisible(false);

                client.sendMessageToServer("get all posts");
                int size = (Integer) client.receiveMessageFromServer();
                List<Post> allPost = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Post post = (Post) client.receiveMessageFromServer();
                    allPost.add(post);
                }

                createNewsFeed(allPost);
            } else if (e.getSource() == usersProfileButton) {
                //checks if the source is from the user profile button
                //if so creates the user's profile and closes any open frames
                userProfile.setVisible(false);
                newsFeed.setVisible(false);
                commentBoard.setVisible(false);
                sneakPeakProfile.setVisible(false);
                signInFrame.setVisible(false);

                createUserProfile(signedInUser , postByUser);
            } else if (e.getSource() == makeProfile) {
                //checks if the source is from make profile
                //gets the name and bio of the user and sets the signed user to these text fields
                //communicates with the server to set the name and bio
                //takes the user to the newsfeed
                client.sendMessageToServer("make profile");
                client.sendMessageToServer(bio.getText());
                client.sendMessageToServer(name.getText());
                client.sendMessageToServer(signedInUser.getUsername());

                signedInUser.getProfile().setBio(bio.getText());
                signedInUser.getProfile().setName(name.getText());
                createProfile.setVisible(false);

                client.sendMessageToServer("get all posts");
                int size = (Integer) client.receiveMessageFromServer();
                List<Post> allPost = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Post post = (Post) client.receiveMessageFromServer();
                    allPost.add(post);
                }


                createNewsFeed(allPost);
            }
            int index = checkButton.getActionCommand().indexOf("-");

            if (checkButton.getActionCommand().contains("commentNewsFeed") ||
                    checkButton.getActionCommand().contains("commentSneakPeakPage")) {
                //checks if the source is from the news feed or from sneak peak page
                //if so creates the comment board with the post and the index of the post
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(index + 1));
                System.out.println(checkButton.getActionCommand());

                client.sendMessageToServer("get all posts");
                int size = (Integer) client.receiveMessageFromServer();
                List<Post> allPost = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Post post = (Post) client.receiveMessageFromServer();
                    allPost.add(post);
                }

                //System.out.println(allPost);
                //System.out.println();
                //System.out.println(allPost.get(postIndex));
                //System.out.println(allPost.get(postIndex).getComments());

                createComment(allPost.get(postIndex) , postIndex);
            } else if (checkButton.getActionCommand().contains("likeNewsFeed") ||
                    checkButton.getActionCommand().contains("likeSneakPeakPage")) {
                //checks if the source is from the news feed or from the sneak peak page
                //if so communicates with the server like the post
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(index + 1));

                posts.get(postIndex).like();
                checkButton.setText("Likes " + posts.get(postIndex).getLikes());

                client.sendMessageToServer("like post");
                client.sendMessageToServer(postIndex);
            } else if (checkButton.getActionCommand().contains("disLikeNewsFeed") ||
                    checkButton.getActionCommand().contains("disLikeSneakPeakPage")) {
                //checks if the source is from the news feed or from the sneak peak page
                //if so communicates with the server dislike the post
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(index + 1));

                posts.get(postIndex).dislike();
                checkButton.setText("Dislikes " + posts.get(postIndex).getDislikes());

                client.sendMessageToServer("dislike post");
                client.sendMessageToServer(postIndex);
            } else if (checkButton.getActionCommand().contains("likeCommentBoard")) {
                //checks if the source is from the comment board
                //if so communicates with the server like the comment
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(0 , index));
                String updated = checkButton.getActionCommand().substring(index + 1);

                index = updated.indexOf("-");
                int commentIndex = Integer.parseInt(updated.substring(index + 1));

                client.sendMessageToServer("like comment");
                client.sendMessageToServer(postIndex);
                client.sendMessageToServer(commentIndex);

                client.sendMessageToServer("get all posts");
                int size = (Integer) client.receiveMessageFromServer();
                List<Post> allPost = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Post post = (Post) client.receiveMessageFromServer();
                    allPost.add(post);
                }
                posts = allPost;
                checkButton.setText("Like " + posts.get(postIndex).getComments().get(commentIndex).getLikes());


            } else if (checkButton.getActionCommand().contains("disLikeCommentBoard")) {
                //checks if the source is from the comment board
                //if so communicates with the server dislikes the comment
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(0 , index));
                String updated = checkButton.getActionCommand().substring(index + 1);

                index = updated.indexOf("-");
                int commentIndex = Integer.parseInt(updated.substring(index + 1));

                client.sendMessageToServer("get all posts");
                int size = (Integer) client.receiveMessageFromServer();
                List<Post> allPost = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Post post = (Post) client.receiveMessageFromServer();
                    allPost.add(post);
                }
                posts = allPost;

                posts.get(postIndex).getComments().get(commentIndex).dislike();
                checkButton.setText("Dislike " + posts.get(postIndex).getComments().get(commentIndex).getDislikes());

                client.sendMessageToServer("dislike comment");
                client.sendMessageToServer(postIndex);
                client.sendMessageToServer(commentIndex);
            } else if (checkButton.getActionCommand().contains("friendNewsFeed")) {
                //checks if the source is coming from the news feed page
                //adds the author of the post as a friend
                //if there is an exception thrown when adding the user as a friend displays a error message
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(0 , index));
                String updated = checkButton.getActionCommand().substring(index + 1);

                index = updated.indexOf("-");
                String username = updated.substring(index + 1);

                client.sendMessageToServer("friend");
                client.sendMessageToServer(username);
                client.sendMessageToServer(signedInUser);
                String response = (String) client.receiveMessageFromServer();
                if (response.equals("adding a friend")) {
                    User friend = (User) client.receiveMessageFromServer();

                    signedInUser.addFriend(friend);
                    newsFeed.setVisible(false);

                    client.sendMessageToServer("get all posts");
                    int size = (Integer) client.receiveMessageFromServer();
                    List<Post> allPost = new ArrayList<>();
                    for (int i = 0; i < size; i++) {
                        Post post = (Post) client.receiveMessageFromServer();
                        allPost.add(post);
                    }

                    createNewsFeed(allPost);
                } else if (response.equals("you have already added this user as a friend")
                        || response.equals("you have been blocked by this user")
                        || response.equals("friend and user are equal")) {
                    JOptionPane.showMessageDialog(null, response , "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (checkButton.getActionCommand().contains("unFriendNewsFeed")) {
                //checks if the source is coming from the news feed page
                //removes the author of the post as a friend
                //if there is an exception thrown when adding the user as a friend displays a error message
                String updated = checkButton.getActionCommand().substring(index + 1);

                index = updated.indexOf("-");

                String username = updated.substring(index + 1);
                client.sendMessageToServer("unfriend");
                client.sendMessageToServer(username);
                client.sendMessageToServer(signedInUser);
                String response = (String) client.receiveMessageFromServer();

                if (response.equals("adding a friend")) {

                    User friend = (User) client.receiveMessageFromServer();

                    signedInUser.removeFriend(friend);
                    newsFeed.setVisible(false);

                    client.sendMessageToServer("get all posts");
                    int size = (Integer) client.receiveMessageFromServer();
                    List<Post> allPost = new ArrayList<>();
                    for (int i = 0; i < size; i++) {
                        Post post = (Post) client.receiveMessageFromServer();
                        allPost.add(post);
                    }

                    createNewsFeed(allPost);
                } else if (response.equals("friend and user are equal")) {
                    JOptionPane.showMessageDialog(null, response , "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
