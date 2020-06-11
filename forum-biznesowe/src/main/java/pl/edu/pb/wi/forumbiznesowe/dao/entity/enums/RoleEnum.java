package pl.edu.pb.wi.forumbiznesowe.dao.entity.enums;

public enum RoleEnum {
    ROLE_USER ("User"),
    ROLE_VIP ("VIP"),
    ROLE_MODERATOR ("Moderator"),
    ROLE_ADMIN("Admin");

    String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String toString(){
        return value;
    }
}
