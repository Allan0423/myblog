package info.enjoycoding.myblog.model;

public class Blogger {

    private String bloggerName;
    private String bloggerPwd;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloggerName() {
        return bloggerName;
    }

    public void setBloggerName(String bloggerName) {
        this.bloggerName = bloggerName;
    }

    public String getBloggerPwd() {
        return bloggerPwd;
    }

    public void setBloggerPwd(String bloggerPwd) {
        this.bloggerPwd = bloggerPwd;
    }
}
