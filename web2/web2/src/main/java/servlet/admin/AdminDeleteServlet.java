package servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import book.BookDao;
@WebServlet("/admin/delete")
public class AdminDeleteServlet extends HttpServlet{
	BookDao bookDao = new BookDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bidString = req.getParameter("bid");
		bookDao.deleteBookById(Integer.parseInt(bidString));
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("redirect", req.getContextPath() + "/admin/home");
		resp.setContentType("application/json");
		PrintWriter pr = resp.getWriter();
		pr.print(jsonObject);
		pr.flush();
	}
}
