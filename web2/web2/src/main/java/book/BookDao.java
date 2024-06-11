package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import category.Category;
import dao.ConnectDB;

public class BookDao {
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Connection conn = null;
	
	public List<BookStat> getBookStat(){
		String sql =  "select t1.*, c.name \r\n"
				+ "from (\r\n"
				+ "	select b.id, b.title, b.author,  b.releasedate, b.pages, sum(bd.qty) as salesamount, b.categoryid , b.is_availabel\r\n"
				+ "    from book b left join billdetail bd \r\n"
				+ "    on bd.bookid = b.id group by b.id ) as t1 \r\n"
				+ "join category c on t1.categoryid = c.id and t1.is_availabel = 1 "
				+ " order by t1.id asc";
		List<BookStat> bookStats = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				BookStat bs = new BookStat();
				bs.setId(rs.getInt("id"));
				bs.setTitle(rs.getString("title"));
				bs.setAuthor(rs.getString("author"));
				bs.setReleasedate(rs.getDate("releasedate"));
				bs.setPages(rs.getInt("pages"));
				bs.setSalesAmount(rs.getInt("salesamount"));
				
				Category c = new Category();
				c.setName(rs.getString("name"));
				
				bs.setCategory(c);
				
				bookStats.add(bs);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return bookStats;
	}
	public Book getBookById(int id) {
		String sql = "select * from book b join category c on b.is_availabel = 1 and b.categoryid = c.id and b.id = " + id;
		Book book = new Book();
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			if(rs.next()) {
				book.setId(rs.getInt(1));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setDescription(rs.getString("description"));
				book.setReleasedate(rs.getDate("releasedate"));
				book.setPages(rs.getInt("pages"));
				book.setCover(rs.getString("cover"));
				
				Category category = new Category(rs.getInt("categoryid"), rs.getString("name"));
				
				book.setCategory(category);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(book.getId() != 0) return book;
		else return null;
	}
	public boolean addNewBook(Book book) {
		String sql = "insert into book(title, author, description,  releasedate, pages, cover, categoryid, is_availabel) "
				+ "values(?, ?, ?, ?, ?,?, ?, 1)";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, book.getTitle());
			pstm.setString(2, book.getAuthor());
			pstm.setString(3, book.getDescription());
			pstm.setDate(4, book.getReleasedate());
			pstm.setInt(5, book.getPages());
			pstm.setString(6, book.getCover());
			pstm.setInt(7, book.getCategory().getId());
			int effectrow = pstm.executeUpdate();
			if(effectrow == 1) return true;
			else return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	public boolean getBookByTitleAndAuthor(Book book) {
		String sql = "";
		if(book.getId() == 0) {
			sql = "select * from book where title = ? and author = ? and is_availabel = 1";	
		}else {
			sql = "select * from book where title = ? and author = ? and id != " + book.getId() + " and is_availabel = 1";
		}
		
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, book.getTitle());
			pstm.setString(2, book.getAuthor());
			rs = pstm.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public void deleteBookById(int bid) {
		String sql = "update book set is_availabel = 0 where id = " + bid;
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			stm.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void editBook(Book book) {
		String sql = "update book set title=?, author=?, description=?, releasedate=?, pages=?, cover=?, categoryid=? where id = ? and is_availabel = 1";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, book.getTitle());
			pstm.setString(2, book.getAuthor());
			pstm.setString(3, book.getDescription());
			pstm.setDate(4, book.getReleasedate());
			pstm.setInt(5, book.getPages());
			pstm.setString(6, book.getCover());
			pstm.setInt(7, book.getCategory().getId());
			pstm.setInt(8, book.getId());
			pstm.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// phần user
	public List<Book> getAllBook(){
		String sql =  "select * from book where is_availabel = 1";
		List<Book> books = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setTitle(rs.getString("title"));
				b.setAuthor(rs.getString("author"));
				b.setDescription(rs.getString("description"));
				b.setReleasedate(rs.getDate("releasedate"));
				b.setPages(rs.getInt("pages"));
				b.setCover(rs.getString("cover"));				
				books.add(b);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return books;
		
	}
	
	
}
