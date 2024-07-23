package study.post.domain.guestbook.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GuestBookDto {

    private Long id;

    @NotEmpty(message = "이름은 필수입니다.")
    private String username;

    @NotEmpty(message = "내용을 적어주세요")
    private String content;

    private LocalDateTime localDateTime;

}
