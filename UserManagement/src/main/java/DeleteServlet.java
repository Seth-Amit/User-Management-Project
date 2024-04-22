import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
	 private final static String query="delete from user where id=?";
		@Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  	//get Print Writer
	  	PrintWriter pWriter=resp.getWriter();
	  	int id=Integer.parseInt(req.getParameter("id"));
	  	// set content type
	  	resp.setContentType("text/html");
	  	//link the boot strap
	  	pWriter.println("<link href='css/bootstrap.css' rel='stylesheet'>");
	  	//

	  	try {
	  		Class.forName("com.mysql.cj.jdbc.Driver");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	  	
	  	try {
	  		Connection connection=DriverManager.getConnection("jdbc:mysql:///usermgmt","root","12345");
				PreparedStatement ps=connection.prepareStatement(query);
				ps.setInt(1,id);
				int count=ps.executeUpdate();
				pWriter.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
				if(count>0)
				{
					pWriter.println("<h2 class='bg-danger text-light text-center'>Un-Registered Successful !!</h2>");
				}
				else {
					pWriter.println("<h2 class='bg-danger text-light text-center'>Delete Registration Not Done Please Retry !!</h2>");
				}
				
				
				
				
			} catch (SQLException e) {
			      e.printStackTrace();
			}
	  	pWriter.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
	  	pWriter.println("</div>");
	  	
	  }
	     @Override
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  	doGet(req, resp);
	  }
}
