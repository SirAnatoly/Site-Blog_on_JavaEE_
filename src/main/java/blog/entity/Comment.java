package blog.entity;

import java.sql.Timestamp;

public class Comment extends AbstractEntity<Integer> {

    private Integer idArticle;
    private Account account;
    private String content;
    private Timestamp created;
    public Comment() {
        super();
    }
    public Comment(Integer idArticle, Account account, String content, Timestamp created) {
        super();
        this.idArticle = idArticle;
        this.account = account;
        this.content = content;
        this.created = created;
    }
    public Integer getIdArticle() {
        return idArticle;
    }
    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Timestamp getCreated() {
        return created;
    }
    public void setCreated(Timestamp created) {
        this.created = created;
    }

}
