package account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import dao.ConnectDB;

public class AccountDao {
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Connection conn = null;
	public boolean checkLogin(Account account) {
		String sql = "select * from account where password = ? and (username=? or email = ?)";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, account.getPassword());
			pstm.setString(2, account.getUsername());
			pstm.setString(3, account.getEmail());
			rs = pstm.executeQuery();
			if(rs.next()) {
				account.setId(rs.getInt("id"));
				account.setFullname(rs.getString("fullname"));
				account.setUsername(rs.getString("username"));
				account.setPassword(rs.getString("password"));
				account.setEmail(rs.getString("email"));
				account.setIsAdmin(rs.getInt("is_admin"));
				return true;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean getAccountByUsernameOrEmail(Account account) {
		String sql = "select * from account where username=? or email = ?";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, account.getUsername());
			pstm.setString(2, account.getEmail());
			rs = pstm.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return false;
	}
	
	public void addNewAccount(Account account) { 
		String sql = "insert into account(fullname, username, password, email, is_admin) "
				+ "values(?, ?, ?, ?, 0);";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, account.getFullname());
			pstm.setString(2, account.getUsername());
			pstm.setString(3, account.getPassword());
			pstm.setString(4, account.getEmail());
			pstm.executeUpdate();	
			
			sql = "select last_insert_id() as idnew";
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			if(rs.next()) {
				account.setId(rs.getInt("idnew")); 
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
