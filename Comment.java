public interface Comment {
    User getAuthor();
    String getContent();
    int getLikes();
    int getDislikes();
    void like();
    void dislike();

}
