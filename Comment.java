public class Comment implements Comments{
    private User author;
    private String content;
    private int likes;
    private int dislikes;

    public Comment(User author , String content) {
        this.content = content;
        this.author = author;
    }

    public User getAuthor() {
        return this.author;
    }
    public String getContent() {
        return this.content;
    }
    public int getLikes() {
        return this.likes;
    }
    public int getDislikes() {
        return this.dislikes;
    }

    public void like() {
        this.likes++;
    }
    public void dislike() {
        this.dislikes++;
    }

}
