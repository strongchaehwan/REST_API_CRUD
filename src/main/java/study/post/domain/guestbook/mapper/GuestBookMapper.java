package study.post.domain.guestbook.mapper;

import study.post.domain.guestbook.dto.GuestBookDto;
import study.post.domain.guestbook.dto.GuestBookRequestDto;
import study.post.domain.guestbook.entity.GuestBook;

import java.time.LocalDateTime;

public class GuestBookMapper {

    //GuestBook 데이터를 받아서 GuestBookDto로 변환
    public static GuestBookDto toDto(GuestBook guestBook){
        return GuestBookDto.builder()
                .id(guestBook.getId())
                .content(guestBook.getContent())
                .username(guestBook.getUserName())
                .localDateTime(guestBook.getLocalDateTime())
                .build();
    }

    //사용자가 입력한 데이터들을 우리의 엔티티인 GuestBook클래스로 매핑
    public static GuestBook toGuestBook(GuestBookRequestDto guestBookRequestDto){
        return GuestBook.builder()
                .userName(guestBookRequestDto.getUsername())
                .content(guestBookRequestDto.getContent())
                .localDateTime(LocalDateTime.now())
                .build();

    }
}
