import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Result;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/showdata")
public class ShowUserServlet extends HttpServlet {
    private final static String query="Select * from user";
	@Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  	//get Print Writer
  	PrintWriter pWriter=resp.getWriter();
  	
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
			ResultSet rs=ps.executeQuery();
			pWriter.println("<div style='margin:auto;width:800px;margin-top:50px'>");
			pWriter.println("<table class='table table-hover table-striped'>");
			pWriter.println("<tr>");
			pWriter.println("<th>Id</th>");
			pWriter.println("<th>First Name</th>");
			pWriter.println("<th>Last Name</th>");
			pWriter.println("<th>Birthday</th>");
			pWriter.println("<th>Gender</th>");
			pWriter.println("<th>Email</th>");
			pWriter.println("<th>Phone</th>");
			pWriter.println("<th>Course</th>");
			pWriter.println("<th>Edit</th>");
			pWriter.println("<th>Delete</th>");
			pWriter.println("</tr>");
			while(rs.next())
			{
				pWriter.println("<tr>");
				pWriter.println("<td>"+rs.getInt(1) +"</td>");
				pWriter.println("<td>"+rs.getString(2)+"</td>");
				pWriter.println("<td>"+rs.getString(3)+"</td>");
				pWriter.println("<td>"+rs.getString(4)+"</td>");
				pWriter.println("<td>"+rs.getString(5)+"</td>");
				pWriter.println("<td>"+rs.getString(6)+"</td>");
				pWriter.println("<td>"+rs.getString(7)+"</td>");
				pWriter.println("<td>"+rs.getString(8)+"</td>");
				pWriter.println("<td><a href='editurl?id="+rs.getInt(1)+"'>Edit</a></td>");
				pWriter.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");
				
				pWriter.println("</tr>");
			}
			pWriter.println("</table>");
			
			
			
			
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
