package study.post.domain.guestbook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.post.domain.guestbook.dto.GuestBookDto;
import study.post.domain.guestbook.dto.GuestBookRequestDto;
import study.post.domain.guestbook.entity.GuestBook;
import study.post.domain.guestbook.mapper.GuestBookMapper;
import study.post.domain.guestbook.repository.GuestBookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class GuestBookService {

    private final GuestBookRepository guestBookRepository;

    @Transactional
    public GuestBookDto saveBook(GuestBookRequestDto guestBookRequest){
        try{
            GuestBook guestBook = GuestBookMapper.toGuestBook(guestBookRequest);

            GuestBook saveGuestBook = guestBookRepository.save(guestBook);

            return GuestBookMapper.toDto(saveGuestBook);

        }catch (Exception e){
            log.info("게시글 작성 실패",e);
            throw new RuntimeException("작성 실패",e);
        }
    }

    public List<GuestBookDto> findAll(){
         return guestBookRepository.findAll().stream()
                .map(GuestBookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(Long id, String name,String content){

         Optional<GuestBook> findGuestBook = guestBookRepository.findById(id);
         if (findGuestBook.isPresent()){
             GuestBook guestBook = findGuestBook.get();
             guestBook.setUserName(name);
             guestBook.setContent(content);
             GuestBookMapper.toDto(guestBook);
         }else {
            throw new RuntimeException("게시글 수정실패");
         }
    }

    @Transactional
    public void delete(Long id){
        Optional<GuestBook> findGuestBook = guestBookRepository.findById(id);
        GuestBook guestBook = findGuestBook.get();
        guestBookRepository.delete(guestBook);
    }

    public GuestBookDto findById(Long id){
        Optional<GuestBook> findGuestBook = guestBookRepository.findById(id);
        if (findGuestBook.isPresent()){
            GuestBook guestBook = findGuestBook.get();
             return GuestBookMapper.toDto(guestBook);
        }else {
            throw new RuntimeException("사용자 없음");
        }
    }



}
