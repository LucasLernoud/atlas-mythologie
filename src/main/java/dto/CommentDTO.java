package dto;

import lombok.Data;

@Data
public class CommentDTO {
    private String content;
    private Long userId;
    private Long mythId;
}
