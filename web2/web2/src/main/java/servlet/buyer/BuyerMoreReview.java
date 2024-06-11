package servlet.buyer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import review.Review;
import review.ReviewDao;
@WebServlet("/loadmorereview")
public class BuyerMoreReview extends HttpServlet{
	ReviewDao reviewDao = new ReviewDao();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bookidString = req.getParameter("bookid");
		String existsString = req.getParameter("exists");
		
		List<Review> reviewmore = reviewDao.getReviewsByBookIdEach3(Integer.parseInt(bookidString), Integer.parseInt(existsString));
		
		JSONArray jsonArray = new JSONArray(reviewmore);
		resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pr = resp.getWriter();
        pr.println(jsonArray);
        pr.flush();
	}
}
