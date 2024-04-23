import java.io.*;
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

public class Database {
    private ArrayList<User> users;
    private ArrayList<Post> posts;

    public Database() {
        users = new ArrayList<>();
        posts = new ArrayList<>();
    }

    /**
     * adds a user to the list of users
     * first checks if the user is already added to the database
     */

    public synchronized void addUser(User user) {
        int verify = 0;
        //checks if there is user is already a friend
        for (User value : users) {
            if (value.getUsername().equals(user.getUsername())) {
                verify++;
            }
        }
        if (verify == 0) {
            users.add(user);
        }

    }
    public User getUserByUsername(String username) {
        User foundUser = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                foundUser = user;
            }
        }
        return foundUser;
    }

    public synchronized void addPost(Post post) {
        int verify = 0;
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).equal(post)) {
                verify++;
            }
        }
        if (verify == 0) {
            posts.add(post);
        }
    }

    public List<Post> getPostsByUser(User user) {
        List<Post> post = new ArrayList<>();
        for (Post value : posts) {
            if (value.getAuthor().equals(user)) {
                post.add(value);
            }
        }
        return post;
    }
    public synchronized void addCommentToPost(Comment comment, Post post) {
        for (Post value : posts) {
            if (value.equal(post)) {
                value.addComment(comment);
            }
        }
    }
    public List<Comment> getCommentsForPost(Post post) {
        List<Comment> comments = new ArrayList<>();
        for (Post value : posts) {
            if (value.equal(post)) {
                comments = post.getComments();
            }
        }
        return comments;
    }

    public void writeDatabase() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("userFiles"));
            new FileWriter("userFiles", false).close();
            for (int i = 0; i < users.size(); i++) {
                //System.out.println(users.get(i));
                pw.println(users.get(i));
                pw.println();
                pw.flush();
            }

            pw = new PrintWriter(new FileWriter("postFiles"));
            new FileWriter("postFiles", false).close();
            for (int i = 0; i < posts.size(); i++) {
                //System.out.println(posts.get(i));
                pw.println(posts.get(i));
                for (int j = 0; j < posts.get(i).getComments().size(); j++) {
                    pw.println("CommentAuthor: " + posts.get(i).getComments().get(j).getAuthor().getUsername());
                    pw.println("CommentContent: " + posts.get(i).getComments().get(j).getContent());
                    pw.println("CommentLikes: " + posts.get(i).getComments().get(j).getLikes());
                    pw.println("CommentDislikes: " + posts.get(i).getComments().get(j).getDislikes());
                }
                pw.println();
                pw.flush();

            }

            pw = new PrintWriter(new FileWriter("friendFiles"));
            for (int i = 0; i < users.size(); i++) {
                for (int j = 0; j < users.get(i).getFriends().size(); j++) {
                    if (j == 0) {
                        pw.println("Username: " + users.get(i).getUsername());
                    }
                    pw.println("FriendUsername: " + users.get(i).getFriends().get(j).getUsername());
                }
                pw.println();
            }

            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void readDatabase() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("userFiles"));
            String line = "";
            int count = 0;
            String name = "";
            String bio = "";
            String username = "";
            String password = "";
            while ((line = bfr.readLine()) != null) {
                int index = line.indexOf(" ");
                if (line.contains("Name: ")) {
                    name = line.substring(index + 1);
                    //System.out.println(name);
                }
                if (line.contains("Bio: ")) {
                    bio = line.substring(index + 1);
                    //System.out.println(bio);
                }
                if (line.contains("Username: ")) {
                    username = line.substring(index + 1);
                    //System.out.println(username);
                }
                if (line.contains("Password: ")) {
                    password = line.substring(index + 1);
                    count = 0;
                    Profile profile = new Profile(name , bio);
                    addUser(new User(username , password , profile));
                    //System.out.println(password);
                }

                count++;
            }

            //System.out.println(users);
            //System.out.println(users.get(0).getUsername());
            //System.out.println(users.get(1).getUsername());

            String authorUsername = "";
            String content = "";
            int likes = 0;
            int dislikes = 0;
            String commentAuthorUsername = "";
            String commentContent = "";
            int commentLikes = 0;
            int commentDislikes = 0;
            bfr = new BufferedReader(new FileReader("postFiles"));
            User author = null;
            Post post = null;
            while ((line = bfr.readLine()) != null) {
                //System.out.println(line);
                int index = line.indexOf(" ");
                if (line.contains("Author: ") && !line.contains("Comment")) {
                    authorUsername = line.substring(index + 1);
                    //System.out.println(authorUsername);
                    //System.out.println(line);
                }
                if (line.contains("Content: ") && !line.contains("Comment")) {
                    //System.out.println(line);
                    content = line.substring(index + 1);
                    //System.out.println(content);
                }
                if (line.contains("Likes: ") && !line.contains("Comment")) {
                    //System.out.println(line);
                    //System.out.println(line.substring(index + 1));
                    likes = Integer.parseInt(line.substring(index + 1));
                    //System.out.println(likes);
                }
                if (line.contains("Dislikes: ") && !line.contains("Comment")) {
                    //System.out.println(line);
                    //System.out.println(line.substring(index + 1));
                    dislikes = Integer.parseInt(line.substring(index + 1));
                    author = getUserByUsername(authorUsername);
                    post = new Post(author, content);
                    post.setLikes(likes);
                    post.setDislikes(dislikes);
                    //System.out.println(likes);
                    //System.out.println(post.getLikes());
                    addPost(post);
                    //System.out.println(dislikes);
                }
                if (line.contains("CommentAuthor: ")) {
                    commentAuthorUsername = line.substring(index + 1);
                    //System.out.println(commentAuthorUsername);
                }
                if (line.contains("CommentContent: ")) {
                    commentContent = line.substring(index + 1);
                    //System.out.println(commentContent);
                }
                if (line.contains("CommentLikes: ")) {
                    commentLikes = Integer.parseInt(line.substring(index + 1));
                    //System.out.println(commentLikes);
                }
                if (line.contains("CommentDislikes: ")) {
                    commentDislikes = Integer.parseInt(line.substring(index + 1));
                    //System.out.println(commentDislikes);
                    User postsAuthor = getUserByUsername(commentAuthorUsername);
                    Comment newComment = new Comment(postsAuthor, commentContent);
                    newComment.setLikes(commentLikes);
                    newComment.setDislikes(commentDislikes);
                    addCommentToPost(newComment, post);
                }

            }

            bfr = new BufferedReader(new FileReader("friendFiles"));

            User main = null;
            int index = 0;
            while ((line = bfr.readLine()) != null) {
                index = line.indexOf(" ");
                if (line.contains("Username") && !line.contains("Friends")) {
                    username = line.substring(index + 1);
                    main = getUserByUsername(username);
                } else if (line.contains("FriendUsername")) {
                    username = line.substring(index + 1);
                    User friend = getUserByUsername(username);
                    if (main != null) {
                        main.addFriend(friend);
                    }
                }
            }


            bfr.close();

            //System.out.println(users);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }



}
