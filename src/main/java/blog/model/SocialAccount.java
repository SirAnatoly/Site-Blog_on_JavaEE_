package blog.model;

public final class SocialAccount extends AbstractModel{
    private final String name;
    private final String email;
    private final String avatar;

    public SocialAccount(String name, String email, String avatar) {
        this.name = name;
        this.email = email;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    @Override
    public String toString() {
        return "SocialAccount{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
