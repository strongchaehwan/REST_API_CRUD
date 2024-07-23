package study.post.domain.guestbook.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
//이 클래스는 클라이언트가 데이터를 입력해서 Controller를 통해 데이터를 받는 클래스
public class GuestBookRequestDto {

    @NotEmpty(message = "이름은 입력은 필수입니다.")
    private String username;

    @NotEmpty(message = "내용을 적어주세요!")
    private String content;

}
