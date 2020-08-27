package blog.form;

public class ContactForm  {
    private String name;
    private String email;
    private String text;
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getText() {
        return text;
    }

}