package cscie57a.assgn11.books.repository;

import cscie57a.assgn11.books.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByIdAndBookId(Long reviewId, Long bookId);

}
