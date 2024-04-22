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
@WebServlet("/editurl")
public class Edit extends HttpServlet {
	 private final static String query="select fname,lname,birthday,gender,email,phone,course from user where id=?";
		@Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  	//get Print Writer
	  	PrintWriter pWriter=resp.getWriter();
	  	
	  	// set content type
	  	resp.setContentType("text/html");
	  	//link the boot strap
	  	pWriter.println("<link href='css/bootstrap.css' rel='stylesheet'>");
	  	//
	  	int id=Integer.parseInt(req.getParameter("id"));
	  	try {
	  		Class.forName("com.mysql.cj.jdbc.Driver");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	  	
	  	try {
	  		Connection connection=DriverManager.getConnection("jdbc:mysql:///usermgmt","root","12345");
				PreparedStatement ps=connection.prepareStatement(query);
				ps.setInt(1, id);
				ResultSet rs=ps.executeQuery();
				rs.next();
				pWriter.println("<div style='margin:auto;width:500px;margin-top:50px'>");
				pWriter.println("<form form action='edit>id="+id+"' method='post'>");
				pWriter.println("<table class='table table-hover table-striped'>");
				pWriter.println("<tr>");
				pWriter.println("<td>First name:</td>");
				pWriter.println("<td><input type='text' name='fname' value='"+rs.getString(1)+"'></td>");
				pWriter.println("</tr>");
				pWriter.println("<tr>");
				pWriter.println("<td>Last name:</td>");
				pWriter.println("<td><input type='text' name='lname' value='"+rs.getString(2)+"'></td>");
				pWriter.println("</tr>");
				pWriter.println("<tr>");
				pWriter.println("<td>Birthday:</td>");
				pWriter.println("<td><input type='text' name='birth' value='"+rs.getString(3)+"'></td>");
				pWriter.println("</tr>");
				pWriter.println("<tr>");
				pWriter.println("<td>Gender:</td>");
				pWriter.println("<td><input type='text' name='gender' value='"+rs.getString(4)+"'></td>");
				pWriter.println("</tr>");
				pWriter.println("<tr>");
				pWriter.println("<td>Email:</td>");
				pWriter.println("<td><input type='text' name='email' value='"+rs.getString(5)+"'></td>");
				pWriter.println("</tr>");
				pWriter.println("<tr>");
				pWriter.println("<td>Phone:</td>");
				pWriter.println("<td><input type='text' name='phone' value='"+rs.getString(6)+"'></td>");
				pWriter.println("</tr>");
				pWriter.println("<tr>");
				pWriter.println("<td>Course:</td>");
				pWriter.println("<td><input type='text' name='course' value='"+rs.getString(7)+"'></td>");
				pWriter.println("</tr>");
				pWriter.println("<tr>");
				pWriter.println("<td><button class='btn btn-outline-success datablock-block' type='submit' >Edit</button></td>");
				pWriter.println("</tr>");
				pWriter.println("</table>");
				
				pWriter.println("</form>");
				
				
				
				
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
