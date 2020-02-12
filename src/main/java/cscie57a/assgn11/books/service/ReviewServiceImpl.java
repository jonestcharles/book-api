package cscie57a.assgn11.books.service;

import cscie57a.assgn11.books.exception.ResourceNotFoundException;
import cscie57a.assgn11.books.model.Review;
import cscie57a.assgn11.books.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void deleteReview(Long reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            logger.error("cannot delete Review because ID does not exist");
            throw new ResourceNotFoundException("A review does not exist with id=" + reviewId);
        }
    }

    @Override
    public Review findReviewById(Long reviewId) throws ResourceNotFoundException {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isPresent()) {
            return review.get();
        }
        throw new ResourceNotFoundException("A review does not exists with id=" + reviewId);
    }

}
