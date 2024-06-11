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
import encoding.MD5;
import validation.Validation;
@WebServlet("/user/register")
public class BuyerRegisterController extends HttpServlet{
	AccountDao accountDao = new AccountDao();
	CartDao cartDao = new CartDao(); 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/user/register.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String fullname = req.getParameter("fullname").trim(); 
		String username = req.getParameter("username").trim();
		String password = req.getParameter("password").trim();
		String repassword = req.getParameter("re-password");
		String email = req.getParameter("email").trim();
		
		Account account = new Account();
		account.setFullname(fullname);
		account.setUsername(username);
		account.setPassword(MD5.getMD5(password));
		account.setEmail(email);
		account.setIsAdmin(0); // user
		
		if(Validation.isBlank(fullname)) {
			sendRedirectAndMessage(req, resp, "invalidFullname", account); return;
		}
		
		if(Validation.isBlank(username) || !Validation.isUsername(username) ) {
			sendRedirectAndMessage(req, resp, "invalidUsername", account); return;
		}
		
		if(Validation.isBlank(password)) { 
			sendRedirectAndMessage(req, resp, "invalidPassword", account); return;
		}
		
		if(!repassword.equals(password)) { 
			
			sendRedirectAndMessage(req, resp, "invalidRePassword", account); return;
		}
		
		if(Validation.isBlank(email) || !Validation.isEmail(email) ) {
			sendRedirectAndMessage(req, resp, "invalidEmail", account); return;
		}
		
		
		boolean isExists= accountDao.getAccountByUsernameOrEmail(account);
		if(isExists) { 
			HttpSession session = req.getSession();
			session.setAttribute("msg", "failRegister");
			resp.sendRedirect(req.getContextPath() + "/user/register");
		}else { 
			accountDao.addNewAccount(account);
			HttpSession session = req.getSession();
			session.setAttribute("msg", "successRegister");
						// create 1 giỏ hang
			cartDao.createNewCart(account.getId());
			
			resp.sendRedirect(req.getContextPath() + "/user/register");
		}
		
	}
	
	private static void sendRedirectAndMessage(HttpServletRequest req, 
			HttpServletResponse resp, 
			String msg, Account account) 
					throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);
		session.setAttribute("account", account);
		resp.sendRedirect(req.getContextPath() + "/user/register");
	}
	
}
