package servlet.domain.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginDto {

    private String name;
    private String password;
}
