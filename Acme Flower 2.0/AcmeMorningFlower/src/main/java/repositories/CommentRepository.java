package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer>{
	@Query("select c from Comment c where c.flower.id=?1 and c.parent is null")
	Collection<Comment> findByFlower(int flowerId);
	
	@Query("select c.responses from Comment c where c.id=?1")
	Collection<Comment> findResponses(int commentId);
}
