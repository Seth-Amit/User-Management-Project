import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/add")
public class RegisterServlet extends HttpServlet{
      private final static String query="insert into user (fname,lname,birthday,gender,email,phone,course) values(?,?,?,?,?,?,?)";
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//get Print Writer
    	PrintWriter pWriter=resp.getWriter();
    	
    	// set content type
    	resp.setContentType("text/html");
    	//link the boot strap
    	pWriter.println("<link href='css/bootstrap.css' rel='stylesheet'>");
    	//
    	String fname =req.getParameter("fname");
    	String lname =req.getParameter("lname");
    	String birth =req.getParameter("birth");
    	String gender =req.getParameter("inlineRadioOptions");
    	String email =req.getParameter("email");
    	String phone=req.getParameter("phone");
    	String subject =req.getParameter("course");
    	
    	System.out.println(fname);
    	System.out.println(lname);
    	System.out.println(birth);
    	System.out.println(gender);
    	System.out.println(email);
    	System.out.println(phone);
    	System.out.println(subject);
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	try {
    		Connection connection=DriverManager.getConnection("jdbc:mysql:///usermgmt","root","12345");
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, birth);
			ps.setString(4, gender);
			ps.setString(5, email);
			ps.setString(6, phone);
			ps.setString(7, subject);
			int count=ps.executeUpdate();
			pWriter.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
			if(count>0)
			{
				pWriter.println("<h2 class='bg-danger text-light text-center'>Registration Successful !!</h2>");
			}
			else {
				pWriter.println("<h2 class='bg-danger text-light text-center'>Registration Not Done Please Retry !!</h2>");
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
