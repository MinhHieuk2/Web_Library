package bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import book.Book;
import dao.ConnectDB;

public class BillDao {
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Connection conn = null;
	
	public void createBill(Bill bill) {
		String sql = "insert into bill(saleday, receiver, tel_receiver, address_receiver, email_receiver, zipcode, accountid ) "
				+ "values(?,?,?,?,?,?,?)";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setDate(1, bill.getSaleday());
			pstm.setString(2, bill.getReceiver());
			pstm.setString(3, bill.getTelReceiver());
			pstm.setString(4, bill.getAddressReceiver());
			pstm.setString(5, bill.getEmailReceiver());
			pstm.setString(6, bill.getZipcode());
			pstm.setInt(7, bill.getAccount().getId());
			pstm.executeUpdate();
			// lấy id của bill vừa được tạo
			sql = "select last_insert_id() as idnew";
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			if(rs.next()) {
				bill.setId(rs.getInt("idnew")); 
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeBill(int id) {
		String sql = "delete from bill where id = " + id;
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			stm.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addBillDetail(BillDetail billDetail) {
		String sql = "insert into billdetail(qty, bookid, billid) values(?,?,?)";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, billDetail.getQuantity());
			pstm.setInt(2, billDetail.getBook().getId());
			pstm.setInt(3, billDetail.getBillid());
			pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Bill> getBillsByAccountId(int accountid){
		String sql = "select b.id, b.saleday, b.receiver, b.tel_receiver, b.address_receiver, b.email_receiver, b.zipcode, bd.qty, bd.bookid, k.title, k.cover\r\n"
				+ "from bill b join billdetail bd join book k on b.id = bd.billid and bd.bookid = k.id and b.accountid = " + accountid;
		List<Bill> bills = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Bill bill = new Bill();
				bill.setId(rs.getInt("id"));
				bill.setSaleday(rs.getDate("saleday"));
				bill.setReceiver(rs.getString("receiver"));
				bill.setTelReceiver(rs.getString("tel_receiver"));
				bill.setAddressReceiver(rs.getString("address_receiver"));
				bill.setEmailReceiver(rs.getString("email_receiver"));
				bill.setZipcode(rs.getString("zipcode"));
				
				BillDetail bd = new BillDetail();
				bd.setQuantity(rs.getInt("qty"));
				
				Book book = new Book();
				book.setId(rs.getInt("bookid"));
				book.setTitle(rs.getString("title"));
				book.setCover(rs.getString("cover"));
				
				bd.setBook(book);
				bill.getBillDetails().add(bd);
				bills.add(bill);
			}
			
			HashMap<Integer, Bill> map = new HashMap<>();
			for(Bill b : bills) {
				if(map.containsKey(b.getId())) {
					map.get(b.getId()).getBillDetails().add(b.getBillDetails().get(0));
				}else {
					map.put(b.getId(), b);
				}
			}
			bills.clear();
			for(Map.Entry<Integer, Bill> entry : map.entrySet()) {
				bills.add(entry.getValue());
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bills;
	}
	
	public void cancelBill(int billid) {
		String sql = "delete from bill where id = " + billid;
		try {
			conn = ConnectDB.getConnection();
			stm = conn .createStatement();
			stm.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
}
