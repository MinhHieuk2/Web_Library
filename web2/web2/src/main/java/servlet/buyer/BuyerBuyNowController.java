package servlet.buyer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import account.Account;
import book.BookDao;
import cart.CartItem;

@WebServlet("/user/buynow")
public class BuyerBuyNowController extends HttpServlet {
	BookDao bookDao = new BookDao();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//kiểm tra đăng nhập chưa
		HttpSession session = req.getSession();
		Account account =  (Account) session.getAttribute("account");
		if(account == null) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.append("redirect", req.getContextPath() + "/user/login");
			resp.setContentType("application/json");
			PrintWriter pr = resp.getWriter();
			pr.print(jsonObject);
			pr.flush();
		}else {
			String bidString = req.getParameter("bid");
			String qtyString = req.getParameter("qty");
			
			CartItem ci = new CartItem();
			ci.setQuantity(Integer.parseInt(qtyString));
			ci.setBook(bookDao.getBookById(Integer.parseInt(bidString)));
			
			session.setAttribute("buynow", ci);
			
			JSONObject jsonObject=new JSONObject();
			jsonObject.append("redirectCheckout", req.getContextPath() + "/user/checkout");
			resp.setContentType("application/json");
			PrintWriter pr = resp.getWriter();
			pr.print(jsonObject);
			pr.flush();
			
		}
		
		
		
	}
}
