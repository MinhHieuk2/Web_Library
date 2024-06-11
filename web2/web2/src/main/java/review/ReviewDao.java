package review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import account.Account;
import dao.ConnectDB;

public class ReviewDao {
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Connection conn = null;
	
	public void addReview(Review review) {
		String sql = "insert into review(content, star, bookid, accountid, created) "
				+ " values(?, ?, ?, ?, ?)";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, review.getContent());
			pstm.setFloat(2, review.getStar());
			pstm.setInt(3, review.getBookid());
			pstm.setInt(4, review.getAccount().getId());
			pstm.setDate(5, review.getCreated());
			pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Review> getReviewsByBookIdEach3(int bookid, int exists){
		String sql = "select r.*, a.fullname from review r join account a\r\n"
				+ "on r.accountid = a.id and r.bookid = " + bookid + "  ORDER BY r.id desc limit 3 offset " + exists;
		List<Review> reviews = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Review r = new Review();
				r.setCreated(rs.getDate("created"));
				r.setContent(rs.getString("content"));
				r.setStar(rs.getFloat("star"));
				r.setBookid(rs.getInt("bookid"));
				
				Account account = new Account();
				account.setFullname(rs.getString("fullname"));
				
				r.setAccount(account);
				
				reviews.add(r);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return reviews;
	}
}
