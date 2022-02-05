package servlet.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {  //vo  = value object. db에 저장되는 데이터랑 1대1 매칭되는 오브젝트들을 vo라고 하고,

    private String title;
    private String content;

}
