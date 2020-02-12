package cscie57a.assgn11.books.service;

import cscie57a.assgn11.books.exception.ResourceNotFoundException;
import cscie57a.assgn11.books.model.Review;

public interface ReviewService {

    void deleteReview(Long reviewId);

    Review findReviewById(Long reviewId) throws ResourceNotFoundException;

}
