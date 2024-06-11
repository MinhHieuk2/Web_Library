package servlet.buyer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.Account;
import account.AccountDao;
import cart.Cart;
import cart.CartDao;
import cart.CartItem;
import encoding.MD5;
@WebServlet("/user/login")
public class BuyerLoginController extends HttpServlet{
	AccountDao accountDao = new AccountDao();
	CartDao cartDao = new CartDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/user/login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Account account = new Account();
		if(username.contains("@")) {
			account.setEmail(username);
		}else {
			account.setUsername(username);
		}
		
		account.setPassword(MD5.getMD5(password));
		
		boolean ok = accountDao.checkLogin(account);
		
		if(ok && account.getIsAdmin() == 0) { //  dang nhap thanh cong voi chuc nang user nhé
			HttpSession session = req.getSession();
			session.setAttribute("account", account);
			
			Cart cart = cartDao.getCartById(account.getId());
			
			session.setAttribute("cart", cart);
			
			resp.sendRedirect(req.getContextPath() + "/user/home");
		}else {
			
			HttpSession session = req.getSession();
			session.setAttribute("msg", "loginError");
			resp.sendRedirect(req.getContextPath() + "/user/login");
		}
	}

}
