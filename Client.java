import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
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
    static JFrame signInFrame;
    public static JButton signIn;
    public static JButton signUp;
    public static JButton addComment;
    public static JPasswordField getPassword;
    public static JTextField getUserName;
    public static JTextArea addCommentContent;
    private static Client client;
    static JFrame userProfile;
    static JFrame newsFeed;
    static JFrame commentBoard;
    private static User signedInUser;
    static List<Post> posts;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        posts = new ArrayList<>();
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

            initialPage();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initialPage() {
        signInFrame = new JFrame();
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

    public static void createUserProfile() {

    }

    public static void  createNewsFeed() {
        newsFeed = new JFrame();
        newsFeed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newsFeed.setSize(675, 663);
        //frame.setLayout(new BoxLayout(frame , BoxLayout.X_AXIS));
        newsFeed.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu Bar");

        JMenuItem m1 = new JMenuItem("Newsfeed");
        JMenuItem m2 = new JMenuItem("Profile");

        menu.add(m1);
        menu.add(m2);

        menuBar.add(menu);

        newsFeed.setJMenuBar(menuBar);

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
        userPanel.setSize(171 , 191);
        userPanel.setBackground(Color.white);
        userPanel.setLayout(new BoxLayout(userPanel , BoxLayout.PAGE_AXIS));
        JLabel usersName = new JLabel("Jessica Day");

        userPanel.setBorder(new EmptyBorder(10, 10 , 10, 10));
        userPanel.setPreferredSize(new Dimension(171 , 191));
        JPanel southPanel = new JPanel();
        southPanel.add(userPanel);

        JPanel friendPanel = new JPanel();
        friendPanel.setLayout(new BorderLayout());
        friendPanel.setBackground(Color.white);
        friendPanel.add(new JLabel("Friends: ") , BorderLayout.WEST);
        JLabel friendNum = new JLabel("10");
        friendPanel.add(friendNum , BorderLayout.EAST);

        userPanel.add(usersName);
        userPanel.add(friendPanel);


        newsFeed.add(southPanel , BorderLayout.WEST);

        newsFeed.setVisible(true);
        newsFeed.setResizable(false);
    }

    private static JPanel createPostPanel(Post post , String num , String page) {
        JPanel postPanel = new JPanel();

        postPanel.setSize(435, 219);
        postPanel.setBackground(Color.white);
        postPanel.setLayout(new BorderLayout());

        postPanel.add(new JLabel(post.getAuthor().getUsername()) , BorderLayout.NORTH);

        JTextPane ja = new JTextPane();
        ja.setText(post.getContent());

        ja.setPreferredSize(new Dimension(435, 200));
        postPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton comment = new JButton("Comment");
        comment.addActionListener(client);
        comment.setActionCommand("comment" + page + "-" +  num);
        JButton like = new JButton("Like " + post.getLikes());
        like.addActionListener(client);
        like.setActionCommand("like" + page + "-" + num);
        JButton dislike = new JButton("Dislike " + post.getDislikes());
        dislike.addActionListener(client);
        dislike.setActionCommand("disLike" + page + "-" + num);

        comment.setPreferredSize(new Dimension(100, 20));
        like.setPreferredSize(new Dimension(100, 20));
        dislike.setPreferredSize(new Dimension(100, 20));
        ja.setEditable(false);

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(comment);
        buttonPanel.add(like);
        buttonPanel.add(dislike);
        buttonPanel.setBackground(Color.white);

        postPanel.add(buttonPanel, BorderLayout.SOUTH);
        postPanel.add(new JScrollPane(ja), BorderLayout.CENTER);

        return postPanel;
    }

    public static void createComment(Post post , int postIndex) {
        commentBoard = new JFrame();
        commentBoard.setSize(346 , 300);

        JPanel addCommentPanel = new JPanel();
        addCommentPanel.setLayout(new BorderLayout());
        addCommentPanel.setBackground(Color.white);
        addCommentPanel.setBorder(new EmptyBorder(10 , 10 , 10 , 10));
        addCommentContent = new JTextArea();
        //addCommentContent.setPreferredSize(new Dimension(251 , 68));
        addCommentContent.setText("Add A Comment\n");

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
            JButton checkButton = (JButton) e.getSource();

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

                    outputStream.writeObject("sign in");
                    outputStream.writeObject(username);
                    outputStream.writeObject(pass);
                    outputStream.flush();

                    String response = (String) inputStream.readObject();
                    //System.out.println(response);
                    if (response.equals("true")) {
                        Object check = inputStream.readObject();
                        if (check instanceof User) {
                            signedInUser = (User) check;
                        }
                        int size = (Integer) inputStream.readObject();
                        System.out.println(size);
                        for (int i = 0; i < size; i++) {
                            Post post = (Post) inputStream.readObject();
                            //System.out.println("Post" + post);
                            posts.add((post));
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

                    outputStream.writeObject("sign up");
                    outputStream.writeObject(username);
                    outputStream.writeObject(pass);
                    outputStream.flush();

                    signInFrame.setVisible(false);
                    userProfile.setVisible(true);

                }

            } else if (e.getSource() == signUp && signUp.getActionCommand().equals("Already on LinkedLin? Sign in")) {
                signIn.setActionCommand("Sign In");
                signIn.setText("Sign In");
                signUp.setActionCommand("New to LinkedIn? Join now");
                signUp.setText("New to LinkedIn? Join now");
                //System.out.println("get sign up");
            } else if (e.getSource() == addComment) {
                Comment comment = new Comment(signedInUser , addCommentContent.getText());
                int postIndex = Integer.parseInt(checkButton.getActionCommand());
                outputStream.writeObject("add comment");
                outputStream.writeObject(postIndex);
                outputStream.writeObject(comment);
                outputStream.flush();
                posts.get(postIndex).addComment(comment);
                commentBoard.setVisible(false);
                createComment(posts.get(postIndex) , postIndex);
            }

            int index = checkButton.getActionCommand().indexOf("-");

            if (checkButton.getActionCommand().contains("commentNewsFeed")) {
                //System.out.println("comment");
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(index + 1));
                //System.out.println(postIndex);
                createComment(posts.get(postIndex) , postIndex);
            } else if (checkButton.getActionCommand().contains("likeNewsFeed")) {
                //System.out.println("like");
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(index + 1));

                posts.get(postIndex).like();
                checkButton.setText("Likes " + posts.get(postIndex).getLikes());
                outputStream.writeObject("like post");
                outputStream.writeObject(postIndex);
                outputStream.flush();
            } else if (checkButton.getActionCommand().contains("disLikeNewsFeed")) {
                //System.out.println("dislike ");
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(index + 1));

                posts.get(postIndex).dislike();
                checkButton.setText("Dislikes " + posts.get(postIndex).getDislikes());
                outputStream.writeObject("dislike post");
                outputStream.writeObject(postIndex);
                outputStream.flush();
            } else if (checkButton.getActionCommand().contains("likeCommentBoard")) {
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(0 , index));
                String updated = checkButton.getActionCommand().substring(index + 1);

                index = updated.indexOf("-");
                int commentIndex = Integer.parseInt(updated.substring(index + 1));
                posts.get(postIndex).getComments().get(commentIndex).like();
                checkButton.setText("Like " + posts.get(postIndex).getComments().get(commentIndex).getLikes());
                outputStream.writeObject("like comment");
                outputStream.writeObject(postIndex);
                outputStream.writeObject(commentIndex);
                outputStream.flush();
            } else if (checkButton.getActionCommand().contains("disLikeCommentBoard")) {
                int postIndex = Integer.parseInt(checkButton.getActionCommand().substring(0 , index));
                String updated = checkButton.getActionCommand().substring(index + 1);

                index = updated.indexOf("-");
                int commentIndex = Integer.parseInt(updated.substring(index + 1));
                posts.get(postIndex).getComments().get(commentIndex).dislike();
                checkButton.setText("Like " + posts.get(postIndex).getComments().get(commentIndex).getDislikes());
                outputStream.writeObject("dislike comment");
                outputStream.writeObject(postIndex);
                outputStream.writeObject(commentIndex);
                outputStream.flush();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
