package pl.edu.pb.wi.forumbiznesowe.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRequest {

    private Long authorId;

    private String reportedObjectType;

    private Long reportedObjectId;

    private String text;
}
