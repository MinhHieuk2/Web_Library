package servlet.buyer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import account.Account;
import bill.Bill;
import bill.BillDao;

@WebServlet("/user/bills")
public class BuyerBillsController extends HttpServlet {
	BillDao billDao = new BillDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy account đang đăng nhập
		HttpSession session = req.getSession();
		Account account = (Account) session.getAttribute("account");
		if (account != null && account.getIsAdmin() == 0) {
			List<Bill> bills = billDao.getBillsByAccountId(account.getId());
			session.setAttribute("bills", bills);
		}
		req.getRequestDispatcher("/view/user/bills.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String billidString = req.getParameter("billid");
		int billid = Integer.parseInt(billidString);
		
		String action = req.getParameter("action");
		
		// lấy ra danh sách Bill đã lưu trên session
		HttpSession session = req.getSession();
		List<Bill> bills = (List<Bill>) session.getAttribute("bills");
		Bill bill = null;
		for (Bill b : bills) {
			if (b.getId() == billid) {
				bill = b;
				break;
			}
		}
		
		
		if(action.equals("post")) {
			req.setAttribute("bill", bill);
			req.getRequestDispatcher("/view/user/billdetail.jsp").forward(req, resp);
		}else if(action.equals("delete")) {
			// xóa bill trong db
			billDao.cancelBill(billid);
			// xóa bill trong danh sách bill ở session
			bills.remove(bill);
			session.setAttribute("bills", bills);
			//xóa xong thì quay lại trang bills
			JSONObject jsonObject = new JSONObject();
			jsonObject.append("redirect", req.getContextPath() + "/user/bills");
			resp.setContentType("application/json");
			PrintWriter pr = resp.getWriter();
			pr.print(jsonObject);
			pr.flush();
		}
		
		
		
	}
}
