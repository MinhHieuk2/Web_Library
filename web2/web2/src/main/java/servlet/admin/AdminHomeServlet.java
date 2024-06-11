package servlet.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.Book;
import book.BookDao;
import book.BookStat;

@WebServlet(urlPatterns = {"/admin", "/admin/home"})
public class AdminHomeServlet extends HttpServlet {
	BookDao bookDao = new BookDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BookStat> bookList = bookDao.getBookStat();
		req.setAttribute("books", bookList);
		req.setAttribute("sidebar", "home");
		req.getRequestDispatcher("/view/admin/home.jsp").forward(req, resp);
	}
}
