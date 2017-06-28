package servlet;

import Dao.ProductDao;
import entity.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//1.get the data£¬get the list
		ProductDao dao = new ProductDao();
		List<Product> list = dao.findAll();
		
		
		//2.show the data in browser
		PrintWriter writer = response.getWriter();
		String html = "";
		
		html += "<html>";
		html += "<head>";
		html += "<title>the list of product</title>";
		html += "</head>";
		html += "<body>";
		html += "<table border='1' align='center' width='600px'>";
		html += "<tr>";
		html += "<th>id</th><th>proName</th><th>proType</th><th>price</th>";
		html += "</tr>";
		//traverse the product
		if(list!=null){
			for(Product p:list){
				html += "<tr>";
		
				html += "<td>"+p.getId()+"</td><td><a href='"+request.getContextPath()+"/DetailServlet?id="+p.getId()+"'>"+p.getProName()+"</a></td><td>"+p.getProType()+"</td><td>"+p.getPrice()+"</td>";
				html += "<tr>";
			}
		}
		html += "</table>";
		
		/**
		 * the list of read product
		 */
		html += "Recenent reading product£º<br/>";
		//get prodHist from cookie
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("prodHist")){
					String prodHist = cookie.getValue(); // 3,2,1
					String[] ids = prodHist.split(",");
					//traverse the stored id
					for (String id : ids) {
						//get the right product
						Product p = dao.findById(id);
						//show the right product
						html += ""+p.getId()+"&nbsp;"+p.getProName()+"&nbsp;"+p.getPrice()+"<br/>";
					}
				}
			}
		}

		
		html += "</body>";
		html += "</html>";
		
		writer.write(html);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
