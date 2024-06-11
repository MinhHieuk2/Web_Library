package servlet.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/401")
public class Error401Page extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String role = req.getParameter("r");
		req.setAttribute("role", role);
		req.getRequestDispatcher("/view/common/page401.jsp").forward(req, resp);
	}
}
