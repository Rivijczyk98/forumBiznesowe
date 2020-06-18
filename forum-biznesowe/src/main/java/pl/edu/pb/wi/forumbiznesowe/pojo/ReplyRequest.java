package pl.edu.pb.wi.forumbiznesowe.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReplyRequest {
    Long id;
    Long author;
    String text;
    Long post;
    Date postedDate;
}
