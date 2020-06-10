package pl.edu.pb.wi.forumbiznesowe.dao.entity;

public enum ERole {
    ROLE_USER ("User"),
    ROLE_VIP ("VIP"),
    ROLE_MODERATOR ("Moderator"),
    ROLE_ADMIN("Admin");

    String value;

    ERole(String value) {
        this.value = value;
    }

    public String toString(){
        return value;
    }
}
