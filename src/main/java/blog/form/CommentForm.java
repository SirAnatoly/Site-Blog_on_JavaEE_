package blog.form;

public class CommentForm {

    private Integer idArticle;
    private String content;
    private String authToken;

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getContent() {
        return content;
    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public String getAuthToken() {
        return authToken;
    }

    @Override
    public String toString() {
        return "CommentForm{" +
                "idArticle=" + idArticle +
                ", content='" + content + '\'' +
                '}';
    }
}
