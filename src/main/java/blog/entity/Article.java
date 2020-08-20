package blog.entity;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;

public class Article extends AbstractEntity<Integer> {
    private String title,url,logo,desc,content;
    private int idCategory,comments;
    private Timestamp created;
    private int views;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getLogo() {
        return logo;
    }

    public String getDesc() {
        return desc;
    }

    public String getContent() {
        return content;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public int getComments() {
        return comments;
    }

    public Timestamp getCreated() {
        return created;
    }

    public int getViews() {
        return views;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getArticleLink(){
        return "/Site_Blog_on_JavaEE_war/article/" + getId() + getUrl();
    }

    public String getShortTitle(){
        if(StringUtils.length(title) > 20) {
            return title.substring(0, 17)+"...";
        }
        else{
            return title;
        }
    }
    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", logo='" + logo + '\'' +
                ", desc='" + desc + '\'' +
                ", idCategory=" + idCategory +
                ", comments=" + comments +
                ", created=" + created +
                ", views=" + views +
                '}';
    }
}
