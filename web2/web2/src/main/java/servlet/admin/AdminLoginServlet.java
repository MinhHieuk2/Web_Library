package servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.Account;
import account.AccountDao;
import encoding.MD5;
@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet{
	AccountDao accountDao = new AccountDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("sidebar", "login");
		req.getRequestDispatcher("/view/admin/login.jsp").forward(req, resp);
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
		if(ok && account.getIsAdmin() == 1) { //  dang nhap thanh cong
			HttpSession session = req.getSession();
			session.setAttribute("account", account);
			resp.sendRedirect(req.getContextPath() + "/admin/home");
		}else {
			
			HttpSession session = req.getSession();
			session.setAttribute("msg", "loginError");
			resp.sendRedirect(req.getContextPath() + "/admin/login");
		}
	}
}
