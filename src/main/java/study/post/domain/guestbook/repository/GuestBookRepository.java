package study.post.domain.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.post.domain.guestbook.entity.GuestBook;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook,Long> {

}
