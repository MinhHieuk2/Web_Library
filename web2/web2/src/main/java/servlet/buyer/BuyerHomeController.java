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
@WebServlet(urlPatterns = {"/user", "/user/home"})
public class BuyerHomeController extends HttpServlet {
	BookDao bookDao = new BookDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = bookDao.getAllBook();
		req.setAttribute("books", books);		
		req.setAttribute("nav", "home");
		req.getRequestDispatcher("/view/user/home.jsp").forward(req, resp);
	}

}
