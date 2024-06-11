package bill;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import account.Account;

public class Bill {
	private int id;
	private Date saleday;
	private String receiver;
	private String telReceiver;
	private String addressReceiver;
	private String emailReceiver;
	private String zipcode;
	private Account account;
	private List<BillDetail> billDetails;
	public Bill() {
		billDetails = new ArrayList<>();
	}
	public Bill(int id, Date saleday, String receiver, String telReceiver, String addressReceiver, String emailReceiver,
			String zipcode, Account account) {
		super();
		this.id = id;
		this.saleday = saleday;
		this.receiver = receiver;
		this.telReceiver = telReceiver;
		this.addressReceiver = addressReceiver;
		this.emailReceiver = emailReceiver;
		this.zipcode = zipcode;
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getSaleday() {
		return saleday;
	}
	public void setSaleday(Date saleday) {
		this.saleday = saleday;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getTelReceiver() {
		return telReceiver;
	}
	public void setTelReceiver(String telReceiver) {
		this.telReceiver = telReceiver;
	}
	public String getAddressReceiver() {
		return addressReceiver;
	}
	public void setAddressReceiver(String addressReceiver) {
		this.addressReceiver = addressReceiver;
	}
	public String getEmailReceiver() {
		return emailReceiver;
	}
	public void setEmailReceiver(String emailReceiver) {
		this.emailReceiver = emailReceiver;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
	public List<BillDetail> getBillDetails() {
		return billDetails;
	}
	public void setBillDetails(List<BillDetail> billDetails) {
		this.billDetails = billDetails;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", saleday=" + saleday + ", receiver=" + receiver + ", telReceiver=" + telReceiver
				+ ", addressReceiver=" + addressReceiver + ", emailReceiver=" + emailReceiver + ", zipcode=" + zipcode
				+ ", account=" + account + ", billDetails=" + billDetails + "]";
	}
	
	
	
	
}
