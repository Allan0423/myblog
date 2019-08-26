package info.enjoycoding.myblog.model;

public class Blogger {

    private String bloggerName;
    private String bloggerPwd;
    private String email;
    private String profile;
    private String signature;
    private String profilePicName;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getProfilePicName() {
        return profilePicName;
    }

    public void setProfilePicName(String profilePicName) {
        this.profilePicName = profilePicName;
    }
}
