package servlet.buyer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import account.Account;
import review.Review;
import review.ReviewDao;

@WebServlet("/user/review")
public class BuyerReviewController extends HttpServlet {
	ReviewDao reviewDao = new ReviewDao();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String bidString = req.getParameter("bookid");
		String starString = req.getParameter("star");
		String content = req.getParameter("content");
		
		// khi đó thì đảm bảo là session có account rồi
		HttpSession session = req.getSession();
		Account account = (Account)session.getAttribute("account");
		
		Review review = new Review();
		review.setContent(content);
		review.setStar(Float.parseFloat(starString));
		review.setAccount(account);
		review.setBookid(Integer.parseInt(bidString));
		
		java.util.Date currDate = new Date();
		java.sql.Date sqlDate = new java.sql.Date(currDate.getTime());
		review.setCreated(sqlDate);
		
		reviewDao.addReview(review);
		
		//Tạo 1 thông báo là add new thành công
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("msg", "successAddReview");
		resp.setContentType("application/json");
		PrintWriter pr = resp.getWriter();
		pr.print(jsonObject);
		pr.flush();		
	}
}
