import org.junit.Test;
import org.junit.After;
import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Before;
import org.junit.rules.Timeout;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Purdue CS
 * @version June 13, 2022
 */
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
     * <p>Purdue University -- CS18000 -- Summer 2022</p>
     *
     * @author Purdue CS
     * @version June 13, 2022
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

        @Test(timeout = 1000)
        public void testAmusement() {
            try {
                String bio = "Adventure seeker and coffee enthusiast. With a passion for exploring the outdoors and " +
                        "capturing moments through photography, I'm always on the lookout for the next breathtaking " +
                        "view. When I'm not hiking or snapping photos, you'll find me immersed in a good book or " +
                        "experimenting with new recipes in the kitchen. Forever curious and ready for new experiences, " +
                        "I believe in living life to the fullest and embracing every opportunity that comes my way";
                String bio2 = "Tech-savvy dreamer with a knack for creativity. As a digital artist and avid gamer, " +
                        "I thrive in the virtual realm, constantly pushing the boundaries of imagination. Whether " +
                        "designing digital masterpieces or delving into immersive gaming worlds, I find inspiration " +
                        "in the pixelated landscapes and endless possibilities of cyberspace. Beyond the screen, I'm a" +
                        " nature enthusiast, finding solace in the tranquility of forests and the majesty of mountains. " +
                        "With a passion for both the digital and natural worlds, I strive to blend the two in unique and " +
                        "innovative ways, creating experiences that captivate and inspire.";
                String bioFriend1 = "Hey everyone! Welcome to my world. From singing on stage to acting on screen, I'm on " +
                        "a journey to explore and create. With a passion for music and a love for storytelling, I believe " +
                        "in the power of art to inspire and connect us all. Let's spread love, positivity, and good vibes " +
                        "wherever we go. Remember, dreams do come true if you work hard and believe in yourself. Let's make" +
                        " some magic together!";
                String bioFriend2 = "Hey everyone! Welcome to my world. From singing on stage to acting on screen, I'm on a " +
                        "journey to explore and create. With a passion for music and a love for storytelling, I believe in the" +
                        " power of art to inspire and connect us all. Let's spread love, positivity, and good vibes wherever we " +
                        "go. Remember, dreams do come true if you work hard and believe in yourself. Let's make some magic together!";
                String bioFriend3 = "Hey everyone! Thanks for being part of this amazing journey with us. Your support means " +
                        "everything. Let's keep making memories and spreading love wherever we go. Remember, always stay true " +
                        "to yourself and never stop dreaming big!";
                String bioFriend4 = "Hey Rushers! Thanks for all the love and support over the years. Can't wait to rock " +
                        "out with you all at our next concert! Keep chasing those dreams and spreading positivity wherever " +
                        "you go. Remember, we're all in this together!";

                Profile profiles = new Profile("Aurora Brooks" , bio);
                Profiles profileFriend1 = new Profile("Logan Henderson" , bioFriend1);
                Profiles profileFriend2 = new Profile("James Maslow" , bioFriend2);
                Profiles profileFriend3 = new Profile("Carlos PenaVega" , bioFriend3);
                Profiles profileFriend4 = new Profile("Kendall Schmidt" , bioFriend4);

                String testName = profiles.getName();
                String testBio = profiles.getBio();

                assertEquals("Ensure your getName() method in Profile.java returns the correct value!" , "Aurora Brooks" , testName);
                assertEquals("Ensure your getBio() method in Profile.java returns the correct value!" , bio , testBio);

                profiles.setName("Jasper Knight");
                profiles.setBio(bio2);

                assertEquals("Ensure your setName() method in Profile.java sets name to the correct value!", "Jasper Knight", profiles.getName());
                assertEquals("Ensure your setBio() method in Profile}.java sets admission cost to the correct value!", bio2, profiles.getBio());

                User users = new User("FrostyBreeze82", "Sunflower$76" , profiles);

                String testUsername = users.getUsername();
                Profile testProfile = users.getProfile();
                List<User> testFriendsList = users.getFriends();
                boolean testVerifyPassword1 = users.verifyPassword("Sunflower$76");
                boolean testVerifyPassword2 = users.verifyPassword("Sunflower$76123");

                assertEquals("Ensure your getUsername() method in User.java returns the correct value!" , "FrostyBreeze82" , testUsername);
                assertEquals("Ensure your getProfile() method in User.java returns the correct value!" , profiles , testProfile);
                assertTrue("Ensure your verifyPassword() method in User.java returns the correct value", testVerifyPassword1);
                assertFalse("Ensure your verifyPassword() method in User.java returns the correct value", testVerifyPassword2);

                users.setPassword("Whisper$78");

                assertEquals("Ensure your getUsername() method in User.java returns the correct value!" , "Whisper$78" , users.getUsername());



                /*
                assertEquals("Ensure your getName() method in AmusementPark.java returns the correct value!", "The Valley Adventure", actName);
                assertEquals("Ensure your getAdmissionCost() method in AmusementPark.java returns the correct value!", 10.5, actAdmissionCost, 0.01);
                assertEquals("Ensure your getLand() method in AmusementPark.java returns the correct value!", 100, actLand, 0.01);
                ArrayList<Ride> expectedRidesCopy = new ArrayList<Ride>();
                for (Ride r : expectedRides) {
                    expectedRidesCopy.add(r);
                }
                assertEquals("Ensure your isIndoor() method in AmusementPark.java returns the correct value!", true, actIndoor);
                assertEquals("Ensure your isOutdoor() method in AmusementPark.java returns the correct value!", true, actOutdoor);
                assertEquals("Ensure your isArcade() method in AmusementPark.java returns the correct value!", false, actArcade);
                assertEquals("Ensure your isBowling() method in AmusementPark.java returns the correct value!", false, actBowling);
                for (int i = 0; i < 4; i++) {
                    assertEquals("Ensure your getSeasons() method in AmusementPark.java returns the correct value!", true, actSeasons[i]);
                }
                testAmusement.setName("The Super Valley Adventure");
                testAmusement.setAdmissionCost(12.80);
                testAmusement.setArcade(true);
                testAmusement.setBowling(true);
                testAmusement.setSeasons(new boolean[]{true, true, true, false});
                assertEquals("Ensure your setName() method in AmusementPark.java sets name to the correct value!", "The Super Valley Adventure", testAmusement.getName());
                assertEquals("Ensure your setAdmissionCost() method in AmusementPark.java sets admission cost to the correct value!", 12.80, testAmusement.getAdmissionCost(), 0.01);
                assertEquals("Ensure your setArcade() method in AmusementPark.java sets arcade to the correct value!", true, testAmusement.isArcade());
                assertEquals("Ensure your setBowling() method in AmusementPark.java sets bowling to the correct value!", true, testAmusement.isBowling());
                for (int i = 0; i < 4; i++) {
                    boolean expectedSeasons = true;
                    if (i == 3) {
                        expectedSeasons = false;
                    }
                    assertEquals("Ensure your setSeasons() method in AmusementPark.java sets seasons to the correct value!", expectedSeasons, testAmusement.getSeasons()[i]);
                }
                Ride newRide = new Rollercoaster("Testing Track", "Orange", 42, 16, false);
                expectedRidesCopy.add(newRide);
                try {
                    testAmusement.addRide(newRide);
                } catch (WrongRideException e) {
                    fail("Ensure your addRide() method throws a WrongRideException in the correct situations!");
                }
                actRides = testAmusement.getRides();
                for (int i = 0; i < expectedRidesCopy.size(); i++) {
                    String expectedName = expectedRidesCopy.get(i).getName();
                    String expectedColor = expectedRidesCopy.get(i).getColor();
                    int expectedMinHeight = expectedRidesCopy.get(i).getMinHeight();
                    int expectedMaxRiders = expectedRidesCopy.get(i).getMaxRiders();
                    actName = actRides.get(i).getName();
                    String actColor = actRides.get(i).getColor();
                    int actMinHeight = actRides.get(i).getMinHeight();
                    int actMaxRiders = actRides.get(i).getMaxRiders();
                    assertEquals("Ensure your addRide() method in AmusementPark.java updates the rides instance variable to be a list of rides with the proper names!", expectedName, actName);
                    assertEquals("Ensure your addRide() method in AmusementPark.java updates the rides instance variable to be a list of rides with the proper colors!", expectedColor, actColor);
                    assertEquals("Ensure your addRide() method in AmusementPark.java updates the rides instance variable to be a list of rides with the proper minHeight values!", expectedMinHeight, actMinHeight);
                    assertEquals("Ensure your addRide() method in AmusementPark.java updates the rides instance variable to be a list of rides with the proper maxRiders values!", expectedMaxRiders, actMaxRiders);
                }

                 */
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
    }
}

