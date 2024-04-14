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
public class Profile implements Profiles {
    private String name;
    private String bio;
    public Profile(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }

    /**
     * getter and setter methods for bio and name
     */

    public String getName() {
        return this.name;
    }

    public String getBio() {
        return this.bio;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

}
