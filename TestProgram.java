import org.junit.Test;
import org.junit.After;
import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Before;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.Timeout;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Group Project - Social Media
 *
 * recreates a social media platform
 *
 * @author Samantha Grief, Connor Mccarthy, Ehssan Kanamkandy, Jahiem Cruickshank, L10
 *
 * @version April 1, 2024
 *
 */
@RunWith(Enclosed.class)
public class TestProgram {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    /**
     * A set of public test cases.
     *
     * <p>Purdue University -- CS18000 -- Spring 2023</p>
     *
     * @author Samantha Grief
     * @version March 31, 2024
     */
    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        //testing user.java and profile.java
        @Test(timeout = 1000)
        public void testUserAndProfile() {
            try {
                String bio = "Adventure seeker and coffee enthusiast. With a passion for exploring the outdoors and " +
                        "capturing moments through photography, I'm always on the lookout for the next breathtaking " +
                        "view. When I'm not hiking or snapping photos, you'll find me immersed in a good book or " +
                        "experimenting with new recipes in the kitchen. Forever curious and ready for new experiences, " +
                        "" +
                        "I believe in living life to the fullest and embracing every opportunity that comes my way";
                String bio2 = "Tech-savvy dreamer with a knack for creativity. As a digital artist and avid gamer, " +
                        "I thrive in the virtual realm, constantly pushing the boundaries of imagination. Whether " +
                        "designing digital masterpieces or delving into immersive gaming worlds, I find inspiration " +
                        "in the pixelated landscapes and endless possibilities of cyberspace. Beyond the screen, I'm a"
                        +
                        " nature enthusiast, finding solace in the tranquility of forests and the majesty of mountains. " +
                        "" +
                        "With a passion for both the digital and natural worlds, I strive to blend the two in unique " +
                        "and " +
                        "innovative ways, creating experiences that captivate and inspire.";
                String bioFriend1 = "Hey everyone! Welcome to my world. From singing on stage to acting on screen, " +
                        "I'm on " +
                        "a journey to explore and create. With a passion for music and a love for storytelling, " +
                        "I believe " +
                        "in the power of art to inspire and connect us all. Let's spread love, positivity, and good " +
                        "vibes " +
                        "wherever we go. Remember, dreams do come true if you work hard and believe in yourself. " +
                        "Let's make" +
                        " some magic together!";
                String bioFriend2 = "Hey everyone! Welcome to my world. From singing on stage to acting on screen," +
                        " I'm on a " +
                        "journey to explore and create. With a passion for music and a love for storytelling, " +
                        "I believe in the" +
                        " power of art to inspire and connect us all. Let's spread love, positivity, and good vibes " +
                        "wherever we " +
                        "go. Remember, dreams do come true if you work hard and believe in yourself. Let's make some " +
                        "magic together!";
                String bioFriend3 = "Hey everyone! Thanks for being part of this amazing journey with us. Your support "
                        + "means " +
                        "everything. Let's keep making memories and spreading love wherever we go. Remember, always " +
                        "stay true " +
                        "to yourself and never stop dreaming big!";
                String bioFriend4 = "Hey Rushers! Thanks for all the love and support over the years. Can't wait to" +
                        " rock " +
                        "out with you all at our next concert! Keep chasing those dreams and spreading positivity" +
                        " wherever " +
                        "you go. Remember, we're all in this together!";
                String bioRemoveAndBlockFriend = "Free-spirited wanderer with a love for stargazing and " +
                        "storytelling. " +
                        "Whether lost in the pages of a fantasy novel or exploring the cosmos through a telescope," +
                        " I find " +
                        "magic in the mysteries of the universe. With a heart full of wanderlust and a mind brimming" +
                        " with " +
                        "imagination, I chase after dreams like shooting stars, always reaching for the next " +
                        "adventure.";

                Profile profiles = new Profile("Aurora Brooks" , bio);
                Profiles profileFriend1 = new Profile("Logan Henderson" , bioFriend1);
                Profiles profileFriend2 = new Profile("James Maslow" , bioFriend2);
                Profiles profileFriend3 = new Profile("Carlos PenaVega" , bioFriend3);
                Profiles profileFriend4 = new Profile("Kendall Schmidt" , bioFriend4);
                Profile profileRemoveAndBlockFriend = new Profile("Maya Rodriguez" , bioRemoveAndBlockFriend);

                String testName = profiles.getName();
                String testBio = profiles.getBio();

                assertEquals("Ensure your getName() method in Profile.java returns the correct value!" ,
                        "Aurora Brooks" , testName);
                assertEquals("Ensure your getBio() method in Profile.java returns the correct value!" ,
                        bio , testBio);

                profiles.setName("Jasper Knight");
                profiles.setBio(bio2);

                assertEquals("Ensure your setName() method in Profile.java sets name to the correct value!",
                        "Jasper Knight", profiles.getName());
                assertEquals("Ensure your setBio() method in Profile.java sets admission cost to the correct" +
                        " value!", bio2, profiles.getBio());

                User users = new User("FrostyBreeze82", "Sunflower$76" , profiles);
                User users1 = new User("LoganHenderson4ever", "LoganLover*321" , profiles);
                User users2 = new User("MaslowOfficial", "MaslowMagic$123" , profiles);
                User users3 = new User("CarlosPenaVegaFan", "KendallIsAwesome!23" , profiles);
                User users4 = new User("KendallLover99", "BTRforever#2024" , profiles);
                User users5 = new User("LunaSky23", "Celestial#42Journey" ,
                        profileRemoveAndBlockFriend);

                ArrayList<User> expectedFriendList = new ArrayList<>();
                expectedFriendList.add(users1);
                expectedFriendList.add(users2);
                expectedFriendList.add(users3);
                expectedFriendList.add(users4);
                expectedFriendList.add(users5);

                String testUsername = users.getUsername();
                Profile testProfile = users.getProfile();

                users.addFriend(users1);
                users.addFriend(users2);
                users.addFriend(users3);
                users.addFriend(users4);
                users.addFriend(users5);

                List<User> testFriendsList = users.getFriends();

                for (int i = 0; i < testFriendsList.size(); i++) {
                    assertEquals("Ensure your getFriends() method in User.java returns the correct value!",
                            expectedFriendList.get(i), testFriendsList.get(i));
                }

                expectedFriendList.remove(users5);
                users.removeFriend(users5);

                for (int i = 0; i < testFriendsList.size(); i++) {
                    assertEquals("Ensure your removeFriend() method in User.java correctly removes the user!",
                            expectedFriendList.get(i), testFriendsList.get(i));
                }

                boolean testVerifyPassword1 = users.verifyPassword("Sunflower$76");
                boolean testVerifyPassword2 = users.verifyPassword("Sunflower$76123");

                assertEquals("Ensure your getUsername() method in User.java returns the correct value!" ,
                        "FrostyBreeze82" , testUsername);
                assertEquals("Ensure your getProfile() method in User.java returns the correct value!" ,
                        profiles , testProfile);
                assertTrue("Ensure your verifyPassword() method in User.java returns the correct value",
                        testVerifyPassword1);
                assertFalse("Ensure your verifyPassword() method in User.java returns the correct value",
                        testVerifyPassword2);

                Post post1 = new Post(users , "Rushers, let's talk about 'Nothing Even Matters.' From the " +
                        "first " +
                        "note to the last, this song hits differently. It's like BTR knows exactly what we're " +
                        "feeling and" +
                        " puts it into music. The lyrics remind us that love is what truly matters. Here's to '" +
                        "Nothing Even" +
                        " Matters,' the song that speaks to our hearts every time. #BTRForever");
                Post post2 = new Post(users5 , "I just saw four dudes singing around a blond girl in the" +
                        " airport. " +
                        "Literally why?");

                String bio6 = "Adrenaline junkie and thrill-seeker with a passion for speed and exploration." +
                        " Whether tearing " +
                        "up the trails on my mountain bike or revving the engine of my motorcycle, I'm always " +
                        "chasing that rush" +
                        " of excitement. When I'm not pushing my limits outdoors, you'll find me immersed in the " +
                        "world of video " +
                        "games, conquering virtual challenges with precision and finesse.";

                Profile profile6 = new Profile("Logan Smith" , bio6);

                Comment comment = new Comment(users4 , "Couldn't agree more, Rushers! 'Nothing Even Matters' " +
                        "holds a " +
                        "special place in our hearts too. It's incredible to see how our music resonates with you " +
                        "all. " +
                        "Thanks for the love and support, always. Keep rocking out to your favorite tunes!" +
                        " #BTRForever");

                User users6 = new User("BlazeRunner88" , "Velocity!67Quest" , profile6);

                Comment comment1 = new Comment(users6 , "Absolutely! 'Nothing Even Matters' is pure gold. " +
                        "It's " +
                        "one of those songs that never fails to hit right in the feels. Thank you, Big Time Rush, " +
                        "for " +
                        "creating music that speaks to us on such a deep level. Here's to many more unforgettable " +
                        "tunes!");

                post1.like();
                post1.like();
                post1.like();
                post1.like();
                post1.like();

                post1.addComment(comment1);
                post1.addComment(comment);

                post2.dislike();

                User testAuthor = post1.getAuthor();
                String testPostContent = post1.getContent();
                int testPostLikes = post1.getLikes();
                int testPostDislikes = post2.getDislikes();
                List<Comment> expectedComments = new ArrayList<>();
                expectedComments.add(comment1);
                expectedComments.add(comment);
                List<Comment> testComments = post1.getComments();

                for (int i = 0; i < testComments.size(); i++) {
                    assertEquals("Ensure your getComments() method in Post.java returns the correct value!",
                            expectedComments.get(i), testComments.get(i));
                }

                assertEquals("Ensure your getAuthor() method in Post.java returns the correct value!" ,
                        users , testAuthor);
                assertEquals("Ensure your getContent() method in Post.java returns the correct value!" ,
                        "Rushers, let's talk about 'Nothing Even Matters.' From the first " +
                        "note to the last, this song hits differently. It's like BTR knows exactly what we're " +
                                "feeling and" +
                        " puts it into music. The lyrics remind us that love is what truly matters. Here's to " +
                                "'Nothing Even" +
                        " Matters,' the song that speaks to our hearts every time. #BTRForever", testPostContent);
                assertEquals("Ensure your likes() method in Post.java returns the correct value!" ,
                        5 , testPostLikes);
                assertEquals("Ensure your dislikes() method in Post.java returns the correct value!" ,
                        1 , testPostDislikes);

                User testCommentAuthor = comment.getAuthor();
                String testCommentContent = comment.getContent();
                comment.like();
                comment.like();
                int testCommentLike = comment.getLikes();

                assertEquals("Ensure your getAuthor() method in Comment.java returns the correct value!" ,
                        users4 , testCommentAuthor);
                assertEquals("Ensure your getContent() method in Comment.java returns the correct value!" ,
                        "Couldn't agree more, Rushers! 'Nothing Even Matters' holds a " + "special place " +
                                "in our hearts too. It's incredible to see how our music resonates with you all. "
                                + "Thanks for the love and support, always. Keep rocking out to your favorite tunes! " +
                                "#BTRForever" , testCommentContent);
                assertEquals("Ensure your getLikes() method in Post.java returns the correct value!" ,
                        2 , testCommentLike);


            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
    }
}

