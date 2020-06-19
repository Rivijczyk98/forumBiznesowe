package pl.edu.pb.wi.forumbiznesowe.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsernameResponse {
    String username;

    public UsernameResponse(String username) {
        this.username = username;
    }
}
