package study.mytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mytest.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
