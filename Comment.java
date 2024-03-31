public class Comment implements Comments {
    private User author;
    private String content;
    private int likes;
    private int dislikes;

    public Comment(User author , String content) {
        this.content = content;
        this.author = author;
        this.likes = 0;
        this.dislikes = 0;
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

    public boolean equal(Comment comment) {
        return content.equals(comment.getContent()) && author.equal(comment.getAuthor());
    }

}
