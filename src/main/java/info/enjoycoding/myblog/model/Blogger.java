package info.enjoycoding.myblog.model;

public class Blogger {

    private String name;
    private String pwd;
    private String email;
    private String profile;
    private String signature;
    private String profilePicName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    @Override
    public String toString() {
        return "Blogger{" +
                "bloggerName='" + name + '\'' +
                ", bloggerPwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", profile='" + profile + '\'' +
                ", signature='" + signature + '\'' +
                ", profilePicName='" + profilePicName + '\'' +
                '}';
    }
}
