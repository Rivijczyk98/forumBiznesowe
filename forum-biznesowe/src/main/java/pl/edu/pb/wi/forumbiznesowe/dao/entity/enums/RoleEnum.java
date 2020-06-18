package pl.edu.pb.wi.forumbiznesowe.dao.entity.enums;

public enum RoleEnum {
    ROLE_USER("ROLE_USER"),
    ROLE_VIP("ROLE_VIP"),
    ROLE_MODERATOR("ROLE_MODERATOR"),
    ROLE_ADMIN("ROLE_ADMIN");

    String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
