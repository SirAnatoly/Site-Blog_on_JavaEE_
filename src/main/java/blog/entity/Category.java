package blog.entity;

public class Category extends AbstractEntity<Integer> {
    private String name,url;
    private int articles;

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setArticles(int articles) {
        this.articles = articles;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getArticles() {
        return articles;
    }
}
