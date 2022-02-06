package servlet.domain.board.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BoardReqDto {

    private String title;
    private String content;

}
