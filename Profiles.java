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
public interface Profiles {
    //methods for accessing and modifying a users profile info (inc. name and bio)
    String getName();

    String getBio();
    void setName(String name);
    void setBio(String bio);
}
