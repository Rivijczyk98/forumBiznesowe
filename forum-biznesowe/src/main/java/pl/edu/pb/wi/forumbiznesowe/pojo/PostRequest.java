package pl.edu.pb.wi.forumbiznesowe.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostRequest {
    Long id;
    Long author;
    Long category;
    String title;
    String text;
    boolean isObserved;
    String status;
    Date postedDate;

    public PostRequest(Long id, Long author, Long category, String title, String text,
                       boolean isObserved, String status, Date postedDate) {
        this.id = id;
        this.author = author;
        this.category = category;
        this.title = title;
        this.text = text;
        this.isObserved = isObserved;
        this.status = status;
        this.postedDate = postedDate;
    }
}
