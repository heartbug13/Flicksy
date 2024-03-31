public interface Comments {
    User getAuthor();
    String getContent();
    int getLikes();
    int getDislikes();
    void like();
    void dislike();

}
