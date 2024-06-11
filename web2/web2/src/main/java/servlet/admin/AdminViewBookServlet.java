package servlet.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import book.Book;
import book.BookDao;
import category.Category;
import category.CategoryDao;
import encoding.UniqueImageGen;
import validation.Validation;

@WebServlet(urlPatterns = { "/admin/view" })
@MultipartConfig
public class AdminViewBookServlet extends HttpServlet {
	CategoryDao categoryDao = new CategoryDao();
	BookDao bookDao = new BookDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		String bidString = req.getParameter("bid");
		List<Category> categories = categoryDao.getAllCategory();
		req.setAttribute("categories", categories);
		req.setAttribute("bid", bidString);
		if (bidString.equals("0")) { // thêm sách
			req.setAttribute("status", "add");
			req.getRequestDispatcher("/view/admin/view.jsp").forward(req, resp);
		} else { // xem sách
			req.setAttribute("status", "view");
			if (bidString != null) {
				Book book = bookDao.getBookById(Integer.parseInt(bidString));
				req.setAttribute("book", book);
				req.getRequestDispatcher("/view/admin/view.jsp").forward(req, resp);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		String bidString = req.getParameter("bid");
		
		// Lấy dữ liệu từ form và validate
		String title = req.getParameter("title");
		if(Validation.isBlank(title) || title.length() > 50) {
			sendRedirectAndMessage(req, resp, "invalidTitle", bidString); return ;
		}
		
		String author = req.getParameter("author");
		if(Validation.isBlank(author) || author.length() > 50) {
			sendRedirectAndMessage(req, resp, "invalidAuthor", bidString); return ;
		}
		
		String description = req.getParameter("description");
		
		String releasedate = req.getParameter("releasedate");
		if(!Validation.isDate(releasedate) || !Validation.isReleaseDate(releasedate)) {
			sendRedirectAndMessage(req, resp, "invalidReleaseDate", bidString); return ;
		}
		
		String pages = req.getParameter("pages");
		
		// create new book
		Book book = null;
		if(bidString.equals("0")){ // add book
			book = new Book();	
		}else { // update book
			book = bookDao.getBookById(Integer.parseInt(bidString));
		}
		
		int categoryid = Integer.parseInt(req.getParameter("category"));
		List<Category> categories = categoryDao.getAllCategory();
		Category category = categories.get(categoryid - 1);
		book.setCategory(category);
		
		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		//set releasedate
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date parsedDate = dateFormat.parse(releasedate);
			Date sqlDate = new java.sql.Date(parsedDate.getTime());
			book.setReleasedate(sqlDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// set pages
		if (pages.equals("")) {
			book.setPages(0);
		} else {
			book.setPages(Integer.parseInt(pages));
		}
		
		//set bìa
		Part part = req.getPart("cover");
		if (part.getSubmittedFileName().length() > 0) {
			String covername = "cover" + File.separator + UniqueImageGen.generate(part.getSubmittedFileName());
			// tạo thư mục nếu thư mục chứa ảnh chưa tồn tại
			File filepath = new File(req.getServletContext().getRealPath("/cover"));
			if (!filepath.exists()) {
				filepath.mkdir();
			}

			String realpath = req.getServletContext().getRealPath("/");
			String coverpath = realpath + covername;
			// ghi ảnh vào bộ nhớ
			part.write(coverpath);
			// set covẻr
			book.setCover(covername);
		}
		book.setId(Integer.parseInt(bidString));
		
		if (bookDao.getBookByTitleAndAuthor(book)) { // check trùng tác giả và tiêu đề
			sendRedirectAndMessage(req, resp, "addbookerror", bidString );
		}else {
			if (bidString.equals("0")) { // thêm sách 
				bookDao.addNewBook(book);
				resp.sendRedirect(req.getContextPath() + "/admin/home");
			}else { // update sách
				bookDao.editBook(book);
				resp.sendRedirect(req.getContextPath() + "/admin/home");
			}
		}
	}
	
	private static void sendRedirectAndMessage(HttpServletRequest req, 
			HttpServletResponse resp, 
			String msg, String bid) 
					throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);
		resp.sendRedirect(req.getContextPath() + "/admin/view?bid=" + bid);
	}
	
}

