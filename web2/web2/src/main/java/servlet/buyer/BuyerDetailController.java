package servlet.buyer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.Book;
import book.BookDao;
import review.Review;
import review.ReviewDao;

@WebServlet("/user/detail")
public class BuyerDetailController extends HttpServlet {
	BookDao bookDao = new BookDao();
	ReviewDao reviewDao = new ReviewDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bidString = req.getParameter("bid");
		int bid = Integer.parseInt(bidString);

		Book book = bookDao.getBookById(bid);
		req.setAttribute("book", book);

		List<Review> reviews = reviewDao.getReviewsByBookIdEach3(bid, 0);
		req.setAttribute("reviews", reviews);
		
		req.setAttribute("nav", "detail");
		req.getRequestDispatcher("/view/user/detail.jsp").forward(req, resp);
	}
	
}
