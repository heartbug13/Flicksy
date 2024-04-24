import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

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

public class Client implements Clients , ActionListener {

    static Socket socket;
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;
    public static JButton signIn;
    public static JButton signUp;
    public static JButton addComment;
    public static JPasswordField getPassword;
    public static JTextField getUserName;
    public static JTextArea addCommentContent;
    public static JTextArea bio;
    public static JTextField name;
    public static JTextArea addPost;
    public static JTextField search;
    public static JButton makeProfile;
    public static JButton addPostButton;
    public static JButton searchButton;
    public static JButton seeFullProfile;
    public static JButton friendButton;
    public static JButton block;
    public static JMenuBar menuBar;
    public static JMenuItem newsFeedButton = new JMenuItem("News Feed");
    public static JMenuItem usersProfileButton = new JMenuItem("Profile");
    private static Client client;
    static JFrame userProfile = new JFrame();
    static JFrame newsFeed = new JFrame();
    static JFrame commentBoard = new JFrame();
    static JFrame sneakPeakProfile = new JFrame();
    static JFrame signInFrame = new JFrame();
    static JFrame createProfile = new JFrame();
    private static User signedInUser;
    static List<Post> posts;
    static List<Post> postByUser;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        posts = new ArrayList<>();
        postByUser = new ArrayList<>();
    }

    public Socket getSocket() {
        return socket;
    }

    public User makeUser(String username, String password, Profile profile) throws InvalidPasswordException, InvalidUserException {
        return new User(username, password, profile);
    }

    public Profile makeProfile(String name, String bio) {
        return new Profile(name, bio);
    }

    public Post makePost(User author, String content) {
        return new Post(author, content);
    }

    public Comment makeComment(User author, String content) {
        return new Comment(author, content);
    }

    public void sendMessageToServer(Object message) throws IOException {
        try {
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e ) {
            System.out.println("Error Sending message to server: " + e.getMessage());
            throw e;
        }

    }

    public Object receiveMessageFromServer() throws IOException, ClassNotFoundException {
        try {
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error recieving message from server: " + e.getMessage());
        }
        return null;

    }

    public void close() throws IOException {
        try {
            if (socket != null) {
                socket.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }


        } catch (IOException e) {
            System.out.println("Error closing the client system resources");
        }

    }


    @Override
    public User makeUser() {
        return null;
    }

    @Override
    public Profile makeProfile() {
        return null;
    }

    @Override
    public Post makePost() {
        return null;
    }

    @Override
    public Comment makeComment() {
        return null;
    }

    public static void main(String[] args) {
        try  {
            Socket socket = new Socket("localhost" , 8080);
            client = new Client(socket);

            menuBar = new JMenuBar();
            JMenu menu = new JMenu("Menu Bar");

            newsFeedButton.addActionListener(client);
            usersProfileButton.addActionListener(client);
            menu.add(newsFeedButton);
            menu.add(usersProfileButton);

            menuBar.add(menu);

            initialPage();

            /*
            userProfile
            newsFeed
            commentBoard = new JFrame();
            sneakPeakProfile = new JFrame();
             signInFrame = new JFrame();
             createProfile = new JFrame();

             */



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initialPage() {
        signInFrame = new JFrame();
        ImageIcon icon = new ImageIcon("Flicksy.PNG");
        signInFrame.setIconImage(icon.getImage());
        signInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signInFrame.setSize(470, 470);
        signInFrame.setLayout(new BorderLayout());

        JLabel welcomeMessage = new JLabel("Welcome to your Professional Community");
        welcomeMessage.setFont(new Font("SansSerif", Font.PLAIN, 20));
        //frame.add(welcomeMessage , BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel topPanel = new JPanel();
        topPanel.add(welcomeMessage);
        signInFrame.add(leftPanel, BorderLayout.WEST);
        signInFrame.add(rightPanel, BorderLayout.EAST);
        signInFrame.add(bottomPanel, BorderLayout.SOUTH);
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
        signIn.addActionListener(client);
        signUp = new JButton("New to LinkedIn? Join now");
        signUp.addActionListener(client);

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

    public static void createProfilePage() {
        createProfile = new JFrame();
        createProfile.setSize(370, 270);

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
        makeProfile.addActionListener(client);

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

    public static void createUserProfile(User user , List<Post> usersPost) {
        userProfile = new JFrame();
        userProfile.setSize(675 , 663);
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        userProfile.setJMenuBar(menuBar);

        JPanel postsPanel = new JPanel();

        if (usersPost.isEmpty()) {
            System.out.println("hows it going");
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
            for (int i = 0; i < usersPost.size(); i++) {
                int verify = 0;
                User author = usersPost.get(i).getAuthor();
                for (int j = 0; j < blocked.size(); i++) {
                    if (blocked.get(i).equal(author)) {
                        verify++;
                    }
                }
                if (verify == 0 && !user.equal(signedInUser)) {
                    JPanel post = createPostPanel(usersPost.get(i), String.valueOf(i), "NewsFeed");
                    postsPanel.add(post);
                } else if (verify == 0) {
                    JPanel post = createPostPanel(usersPost.get(i), String.valueOf(i), "UsersProfile");
                    postsPanel.add(post);
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
            friendButton.addActionListener(client);
            friendButton.setActionCommand(user.getUsername());

            if (signedInUser.isFriend(user)) {
                friendButton.setText("Unfriend");
            }
            if (signedInUser.isBlocked(user)) {
                block.setText("Unblock");
            }
            block.setPreferredSize(new Dimension(171, 18));
            block.addActionListener(client);
            block.setActionCommand(user.getUsername());

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BorderLayout());
            buttonPanel.add(friendButton, BorderLayout.NORTH);
            buttonPanel.add(new JPanel(), BorderLayout.CENTER);
            buttonPanel.add(block, BorderLayout.SOUTH);

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

    public static void sneakPeakOfUser(User user , List<Post> usersPost) {
        sneakPeakProfile = new JFrame();
        sneakPeakProfile.setSize(493, 620);

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

        //System.out.println("users post" + usersPost);
        //System.out.println("size " + usersPost.size());
        if (usersPost.isEmpty()) {
            //System.out.println("hello");
            JPanel emptyPost = new JPanel();
            emptyPost.setBorder(new EmptyBorder(40, 10, 40, 10));
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
        seeFullProfile.addActionListener(client);
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

    public static void  createNewsFeed() {
        newsFeed = new JFrame();
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
        searchButton.addActionListener(client);
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(search , BorderLayout.NORTH);
        searchPanel.add(searchButton , BorderLayout.SOUTH);

        JPanel addPostPanel = new JPanel();
        addPostPanel.setBorder(new EmptyBorder(10 , 10 , 10 ,10));
        addPostPanel.setBackground(Color.white);
        addPostButton = new JButton("Add Post");
        addPostButton.addActionListener(client);
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

        if (posts.size() > 1) {
            postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        }
        List<User> blocked = signedInUser.getBlocked();

        //JPanel test = new JPanel();
        for (int i = 0; i < posts.size(); i++) {
            int verify = 0;
            User author = posts.get(i).getAuthor();
            for (int j = 0; j < blocked.size(); i++) {
                if (blocked.get(i).equal(author)) {
                    verify++;
                }
            }
            if (verify == 0) {
                JPanel post = createPostPanel(posts.get(i), String.valueOf(i), "NewsFeed");
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
        //userPanel.setPreferredSize(new Dimension(171 , 270));
        JPanel southPanel = new JPanel();
        southPanel.add(userPanel);


        newsFeed.add(southPanel , BorderLayout.WEST);

        newsFeed.setVisible(true);
        newsFeed.setResizable(false);
    }

    private static JPanel createPostPanel(Post post , String num , String page) {
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
            comment.addActionListener(client);
            comment.setActionCommand("comment" + page + "-" + num);
            JButton like = new JButton("Like " + post.getLikes());
            like.addActionListener(client);
            like.setActionCommand("like" + page + "-" + num);
            JButton dislike = new JButton("Dislike " + post.getDislikes());
            dislike.addActionListener(client);
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
            if (!signedInUser.isFriend(post.getAuthor())) {
                friend.addActionListener(client);
                friend.setActionCommand(num + "-friend" + page + "-" + post.getAuthor().getUsername());
            } else {
                friend.setText("Unfriend");
                friend.addActionListener(client);
                friend.setActionCommand(num + "-unFriend" + page + "-" + post.getAuthor().getUsername());
            }
            friend.setPreferredSize(new Dimension(100, 20));
            buttonPanel.add(friend);


            postPanel.add(buttonPanel, BorderLayout.SOUTH);
        }
        postPanel.add(new JScrollPane(ja), BorderLayout.CENTER);

        return postPanel;
    }

    public static void createComment(Post post , int postIndex) {
        commentBoard = new JFrame();
        commentBoard.setSize(346 , 300);
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
        addComment.addActionListener(client);
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
            like.addActionListener(client);
            like.setActionCommand(postIndex + "-likeCommentBoard-" + i);
            JButton dislike = new JButton("Dislike " + listOfComments.get(i).getDislikes());
            dislike.addActionListener(client);
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

    public void actionPerformed(ActionEvent e) {
        try {

            JButton checkButton = new JButton();

            if (e.getSource() == newsFeedButton || e.getSource() == usersProfileButton) {
                System.out.println("hello");
            } else {

                checkButton = (JButton) e.getSource();
            }


            if (e.getSource() == signIn && signIn.getActionCommand().equals("Sign In")) {
                //System.out.println("signing in");
                String username = getUserName.getText();
                char[] password = getPassword.getPassword();
                String pass = "";
                for (int i = 0; i < password.length; i++) {
                    pass += password[i];
                }

                //System.out.println(pass);

                if (pass.isEmpty() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill out all text fields",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    /*
                    outputStream.writeObject("sign in");
                    outputStream.writeObject(username);
                    outputStream.writeObject(pass);
                    outputStream.flush();

                     */

                    client.sendMessageToServer("sign in");
                    client.sendMessageToServer(username);
                    client.sendMessageToServer(pass);

                    //String response = (String) inputStream.readObject();
                    String response = (String) client.receiveMessageFromServer();
                    //System.out.println(response);
                    if (response.equals("true")) {
                        //Object check = inputStream.readObject();
                        signedInUser = (User) client.receiveMessageFromServer();
                        //int size = (Integer) inputStream.readObject();
                        int size = (Integer) client.receiveMessageFromServer();
                        System.out.println(size);
                        for (int i = 0; i < size; i++) {
                            Post post = (Post) client.receiveMessageFromServer();
                            //Post post = (Post) inputStream.readObject();
                            //System.out.println("Post" + post);
                            posts.add((post));
                        }

                        //size = (Integer) inputStream.readObject();
                        size = (Integer) receiveMessageFromServer();
                        for (int i = 0; i < size; i++) {
                            Post post = (Post) client.receiveMessageFromServer();

                            //Post post = (Post) inputStream.readObject();
                            postByUser.add(post);
                        }

                        //System.out.println(posts);
                        //System.out.println(posts);
                        //System.out.println(signedInUser);
                        signInFrame.setVisible(false);
                        createNewsFeed();
                    } else {
                        JOptionPane.showMessageDialog(null, "Username or Password is incorrect",
                                "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else if (e.getSource() == signUp && signUp.getActionCommand().equals("New to LinkedIn? Join now")) {
                signIn.setActionCommand("Sign Up");
                signIn.setText("Sign Up");
                signUp.setActionCommand("Already on LinkedLin? Sign in");
                signUp.setText("Already on Linkedin? Sign in");

                //System.out.println("get sign up");
            } else if (e.getSource() == signIn && signIn.getActionCommand().equals("Sign Up")) {
                //System.out.println("signing up");
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
/*
                    outputStream.writeObject("sign up");
                    outputStream.writeObject(username);
                    outputStream.writeObject(pass);
                    outputStream.flush();

 */
                    client.sendMessageToServer("sign up");
                    client.sendMessageToServer(username);
                    client.sendMessageToServer(pass);

                    signInFrame.setVisible(false);
                    String filler = "";
                    Profile profile = new Profile(filler , filler);
                    signedInUser = new User(username , pass , profile);
                    createProfilePage();

                }

            } else if (e.getSource() == signUp && signUp.getActionCommand().equals("Already on LinkedLin? Sign in")) {
                signIn.setActionCommand("Sign In");
                signIn.setText("Sign In");
                signUp.setActionCommand("New to LinkedIn? Join now");
                signUp.setText("New to LinkedIn? Join now");
                //System.out.println("get sign up");
            } else if (e.getSource() == addComment && !addCommentContent.getText().equals("Add A Comment\n")) {
                Comment comment = new Comment(signedInUser , addCommentContent.getText());
                int postIndex = Integer.parseInt(checkButton.getActionCommand());
                /*
                outputStream.writeObject("add comment");
                outputStream.writeObject(postIndex);
                outputStream.writeObject(comment);
                outputStream.flush();

                 */

                client.sendMessageToServer("add comment");
                client.sendMessageToServer(postIndex);
                client.sendMessageToServer(comment);

                posts.get(postIndex).addComment(comment);
                commentBoard.setVisible(false);
                createComment(posts.get(postIndex) , postIndex);
            } else if (e.getSource() == addPostButton && !addPost.getText().equals("Start a Post...")) {
                Post post = new Post(signedInUser , addPost.getText());

                //outputStream.writeObject("add post");
               // outputStream.writeObject(post);
                //outputStream.flush();

                client.sendMessageToServer("add post");
                client.sendMessageToServer(post);

                posts.add(post);
                postByUser.add(post);
                newsFeed.setVisible(false);
                createNewsFeed();
            } else if (e.getSource() == searchButton && !search.getText().equals("Search...")) {
                //outputStream.writeObject("search");
                //outputStream.writeObject(search.getText());
                //outputStream.flush();
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

                    //System.out.println(Arrays.toString(searchResultString));

                    String choice = (String) JOptionPane.showInputDialog(null, "What is your choice?",
                            "Search Client", JOptionPane.QUESTION_MESSAGE, null,
                            searchResultString, searchResultString[0]);

                    //System.out.println(choice);

                    //outputStream.writeObject("find user based on username");
                    //outputStream.writeObject(choice);
                    //outputStream.flush();
                    client.sendMessageToServer("find user based on username");
                    client.sendMessageToServer(choice);

                    //User foundUser = (User) inputStream.readObject();
                    User foundUser = (User) client.receiveMessageFromServer();

                    //outputStream.writeObject("find post by user");
                    //outputStream.writeObject(foundUser.getUsername());
                    //outputStream.flush();

                    client.sendMessageToServer("find post by user");
                    client.sendMessageToServer(foundUser.getUsername());

                    //size = (Integer) inputStream.readObject();
                    size = (Integer) client.receiveMessageFromServer();

                    List<Post> usersPost = new ArrayList<>();

                    for (int i = 0; i < size; i++) {
                        //Post post = (Post) inputStream.readObject();
                        Post post = (Post) client.receiveMessageFromServer();
                        usersPost.add(post);
                    }

                    //System.out.println(usersPost);

                    sneakPeakOfUser(foundUser , usersPost);


                } else {
                    JOptionPane.showMessageDialog(null, "Search failed, no user with t" +
                            "hat username are associated with Flicksy", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (e.getSource() == seeFullProfile) {
                String username = seeFullProfile.getActionCommand();

                //outputStream.writeObject("find user based on username");
                //outputStream.writeObject(username);

                //User foundUser = (User) inputStream.readObject();

                //outputStream.writeObject("find post by user");
                //outputStream.writeObject(foundUser.getUsername());
                //outputStream.flush();

                //int size = (Integer) inputStream.readObject();

                sendMessageToServer("find user based on username");
                sendMessageToServer(username);

                User foundUser = (User) client.receiveMessageFromServer();

                sendMessageToServer("find post by user");
                sendMessageToServer(foundUser.getUsername());

                int size = (Integer) client.receiveMessageFromServer();

                List<Post> usersPost = new ArrayList<>();

                for (int i = 0; i < size; i++) {
                    //Post post = (Post) inputStream.readObject();
                    Post post = (Post) client.receiveMessageFromServer();
                    usersPost.add(post);
                }

                newsFeed.setVisible(false);
                sneakPeakProfile.setVisible(false);
                createUserProfile(foundUser , usersPost);

            } else if (e.getSource() == friendButton && friendButton.getText().equals("Friend")) {
                String username = friendButton.getActionCommand();
                System.out.println("username " + username);
                //outputStream.writeObject("friend");
                //outputStream.writeObject(username);
                //outputStream.writeObject(signedInUser);
                //outputStream.flush();

                //String response = (String) inputStream.readObject();

                client.sendMessageToServer("friend");
                client.sendMessageToServer(username);
                client.sendMessageToServer(signedInUser);

                String response = (String) client.receiveMessageFromServer();

                if (response.equals("adding a friend")) {

                    //User friend = (User) inputStream.readObject();
                    User friend = (User) client.receiveMessageFromServer();

                    //posts.get(postIndex).getAuthor().addFriend(friend);
                    //System.out.println(posts.get(postIndex).getAuthor().getFriends());
                    signedInUser.addFriend(friend);

                    //outputStream.writeObject("find post by user");
                    //outputStream.writeObject(friend.getUsername());
                    //outputStream.flush();

                    client.sendMessageToServer("find post by user");
                    client.sendMessageToServer(friend.getUsername());

                    //int size = (Integer) inputStream.readObject();
                    int size = (Integer) client.receiveMessageFromServer();

                    List<Post> usersPost = new ArrayList<>();

                    for (int i = 0; i < size; i++) {
                        //Post post = (Post) inputStream.readObject();
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

                //outputStream.writeObject("unfriend");
                //outputStream.writeObject(username);
                //outputStream.writeObject(signedInUser);
                //outputStream.flush();

                //String response = (String) inputStream.readObject();

                sendMessageToServer("unfriend");
                sendMessageToServer(username);
                sendMessageToServer(signedInUser);

                String response = (String) client.receiveMessageFromServer();

                if (response.equals("adding a friend")) {

                    //User friend = (User) inputStream.readObject();
                    User friend = (User) client.receiveMessageFromServer();

                    //posts.get(postIndex).getAuthor().addFriend(friend);
                    signedInUser.removeFriend(friend);
                    newsFeed.setVisible(false);
                    friendButton.setText("Friend");
                } else if (response.equals("friend and user are equal")) {
                    JOptionPane.showMessageDialog(null, response , "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (e.getSource() == block && block.getText().equals("Block")) {
                String username = block.getActionCommand();
                //outputStream.writeObject("block");
                //outputStream.writeObject(username);
                //outputStream.writeObject(signedInUser);
                //outputStream.flush();

                //String response = (String) inputStream.readObject();

                client.sendMessageToServer("block");
                client.sendMessageToServer(username);
                client.sendMessageToServer(signedInUser);

                String response = (String) client.receiveMessageFromServer();

                if (response.equals("blocking user")) {
                    //User blocked = (User) inputStream.readObject();
                    User blocked = (User) client.receiveMessageFromServer();
                    signedInUser.blockUser(blocked);
                    block.setText("Unblock");
                } else {
                    JOptionPane.showMessageDialog(null, response , "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (e.getSource() == block && block.getText().equals("Unblock")) {
                String username = block.getActionCommand();
                client.sendMessageToServer("unblock");
                client.sendMessageToServer(username);
                client.sendMessageToServer(signedInUser);
                /*
                outputStream.writeObject("unblock");
                outputStream.writeObject(username);
                outputStream.writeObject(signedInUser);
                outputStream.flush();

                 */

                //User unblocked = (User) inputStream.readObject();
                User unblocked = (User) client.receiveMessageFromServer();
                signedInUser.unblockUser(unblocked);
                block.setText("Block");
            } else if (e.getSource() == newsFeedButton) {
                userProfile.setVisible(false);
                newsFeed.setVisible(false);
                commentBoard.setVisible(false);
                sneakPeakProfile.setVisible(false);
                signInFrame.setVisible(false);
                createNewsFeed();
            } else if (e.getSource() == usersProfileButton) {
                userProfile.setVisible(false);
                newsFeed.setVisible(false);
                commentBoard.setVisible(false);
                sneakPeakProfile.setVisible(false);
                signInFrame.setVisible(false);

                createUserProfile(signedInUser , postByUser);
            } else if (e.getSource() == makeProfile) {
                sendMessageToServer("make profile");
                sendMessageToServer(bio.getText());
                sendMessageToServer(name.getText());
                sendMessageToServer(signedInUser.getUsername());

                signedInUser.getProfile().setBio(bio.getText());
                signedInUser.getProfile().setName(name.getText());
                createProfile.setVisible(false);
                createNewsFeed();
            }
            int index = checkButton.getActionCommand().indexOf("-");

            if (checkButton.getActionCommand().contains("commentNewsFeed") ||
                    checkButton.getActionCommand().contains("commentSneakPeakPage")) {
                //System.out.println("comment");
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(index + 1));
                //System.out.println(postIndex);
                createComment(posts.get(postIndex) , postIndex);
            } else if (checkButton.getActionCommand().contains("likeNewsFeed") ||
                    checkButton.getActionCommand().contains("likeSneakPeakPage")) {
                //System.out.println("like");
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(index + 1));

                posts.get(postIndex).like();
                checkButton.setText("Likes " + posts.get(postIndex).getLikes());
                //outputStream.writeObject("like post");
                //outputStream.writeObject(postIndex);
                //outputStream.flush();

                sendMessageToServer("like post");
                sendMessageToServer(postIndex);
            } else if (checkButton.getActionCommand().contains("disLikeNewsFeed") ||
                    checkButton.getActionCommand().contains("disLikeSneakPeakPage")) {
                //System.out.println("dislike ");
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(index + 1));

                posts.get(postIndex).dislike();
                checkButton.setText("Dislikes " + posts.get(postIndex).getDislikes());
                //outputStream.writeObject("dislike post");
                //outputStream.writeObject(postIndex);
                //outputStream.flush();
                sendMessageToServer("dislike post");
                sendMessageToServer(postIndex);
            } else if (checkButton.getActionCommand().contains("likeCommentBoard")) {
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(0 , index));
                String updated = checkButton.getActionCommand().substring(index + 1);

                index = updated.indexOf("-");
                int commentIndex = Integer.parseInt(updated.substring(index + 1));
                posts.get(postIndex).getComments().get(commentIndex).like();
                checkButton.setText("Like " + posts.get(postIndex).getComments().get(commentIndex).getLikes());
                //outputStream.writeObject("like comment");
                //outputStream.writeObject(postIndex);
                //outputStream.writeObject(commentIndex);
                //outputStream.flush();
                sendMessageToServer("like comment");
                sendMessageToServer(postIndex);
                sendMessageToServer(commentIndex);
            } else if (checkButton.getActionCommand().contains("disLikeCommentBoard")) {
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(0 , index));
                String updated = checkButton.getActionCommand().substring(index + 1);

                index = updated.indexOf("-");
                int commentIndex = Integer.parseInt(updated.substring(index + 1));
                posts.get(postIndex).getComments().get(commentIndex).dislike();
                checkButton.setText("Dislike " + posts.get(postIndex).getComments().get(commentIndex).getDislikes());
                //outputStream.writeObject("dislike comment");
                //outputStream.writeObject(postIndex);
                //outputStream.writeObject(commentIndex);
                //outputStream.flush();
                client.sendMessageToServer("dislike comment");
                client.sendMessageToServer(postIndex);
                client.sendMessageToServer(commentIndex);
            } else if (checkButton.getActionCommand().contains("friendNewsFeed")) {
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(0 , index));
                String updated = checkButton.getActionCommand().substring(index + 1);
                System.out.println("action command " + checkButton.getActionCommand());
                System.out.println("updated " + updated);
                index = updated.indexOf("-");
                System.out.println(index);
                String username = updated.substring(index + 1);
                System.out.println("username " + username);
                //outputStream.writeObject("friend");
                //outputStream.writeObject(username);
                //outputStream.writeObject(signedInUser);
                //outputStream.flush();

                //String response = (String) inputStream.readObject();
                client.sendMessageToServer("friend");
                client.sendMessageToServer(username);
                client.sendMessageToServer(signedInUser);
                String response = (String) client.receiveMessageFromServer();
                if (response.equals("adding a friend")) {

                    //User friend = (User) inputStream.readObject();
                    User friend = (User) client.receiveMessageFromServer();

                    signedInUser.addFriend(friend);
                    newsFeed.setVisible(false);
                    createNewsFeed();
                } else if (response.equals("you have already added this user as a friend")
                        || response.equals("you have been blocked by this user")
                        || response.equals("friend and user are equal")) {
                    JOptionPane.showMessageDialog(null, response , "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (checkButton.getActionCommand().contains("unFriendNewsFeed")) {
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(0 , index));
                String updated = checkButton.getActionCommand().substring(index + 1);

                index = updated.indexOf("-");

                String username = updated.substring(index + 1);

                //outputStream.writeObject("unfriend");
                //outputStream.writeObject(username);
                //outputStream.writeObject(signedInUser);
                //outputStream.flush();

                //String response = (String) inputStream.readObject();
                sendMessageToServer("unfriend");
                sendMessageToServer(username);
                sendMessageToServer(signedInUser);
                String response = (String) client.receiveMessageFromServer();

                if (response.equals("adding a friend")) {

                    //User friend = (User) inputStream.readObject();
                    User friend = (User) client.receiveMessageFromServer();

                    //posts.get(postIndex).getAuthor().addFriend(friend);
                    System.out.println(posts.get(postIndex).getAuthor().getFriends());
                    signedInUser.removeFriend(friend);
                    newsFeed.setVisible(false);
                    createNewsFeed();
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
