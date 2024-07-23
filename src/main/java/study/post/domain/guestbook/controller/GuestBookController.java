package study.post.domain.guestbook.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import study.post.domain.guestbook.dto.GuestBookDto;
import study.post.domain.guestbook.dto.GuestBookRequestDto;
import study.post.domain.guestbook.service.GuestBookService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/guestbook")
public class GuestBookController {

    private final GuestBookService guestBookService;


    @GetMapping("/list")
    public ResponseEntity<List<GuestBookDto>> getAllGuestBooks(){
        List<GuestBookDto> bookList = guestBookService.findAll();
        return ResponseEntity.ok(bookList);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveGuestBook(@Valid @RequestBody GuestBookRequestDto guestBookRequestDto, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        try{
            GuestBookDto guestBookDto = guestBookService.saveBook(guestBookRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(guestBookDto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 작성중 오류발생");
        }


    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<Object> updateGuestBook(@PathVariable("bookId") Long id
            ,@Valid @RequestBody GuestBookRequestDto guestBookRequestDto){
        try {
             guestBookService.update(id, guestBookRequestDto.getUsername(), guestBookRequestDto.getContent());
             GuestBookDto findGuestBook = guestBookService.findById(id);
             return ResponseEntity.ok(findGuestBook);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not find Id"+id);
        }
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<Object> deleteGuestBook(@PathVariable("bookId") Long id){
        try {
            guestBookService.delete(id);
            //GuestBookDto byId = guestBookService.findById(id);
            return ResponseEntity.ok("성공?");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제실패?");
        }

    }

}
