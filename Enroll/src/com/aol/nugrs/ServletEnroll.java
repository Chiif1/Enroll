package com.aol.nugrs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ServletEnroll extends HttpServlet{
	
	public void service(HttpServletRequest req, HttpServletResponse res) {
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		int zipCode = 0;
		try {
			 zipCode = Integer.parseInt(req.getParameter("zipCode"));
		    } catch (NumberFormatException nfe) {
		      nfe.printStackTrace();
		    }

		String email = req.getParameter("email");
		
	if (firstName != null & lastName != null & zipCode != 0 & email != null ) {

		//int zipCodeInt =  Integer.parseInt(zipCode);
			
		//System.out.print(zipCode + " "  + firstName);

			String url = "jdbc:mysql://aa44l19pet7o4r.ctgxpa81ul8a.us-east-2.rds.amazonaws.com:3306/recruitment";
			//String url = "jdbc:mysql://localhost:3306/recruitment";
			//String name = "root";
			String name = "Root";
			String pass = "Survival1";
			//String query = "SELECT firstName FROM recruitment WHERE id=1";
			//String query = "insert into recruitment (firstName, lastName, zipCode, email)" + 
							//"values (" + "'" +  firstName + "'" + ", " + lastName + ", " + zipCode + ", " + email + ");";
	
					//"values (\"NAMER \", \"bER\", 23345, \"eer@gmail.com\");";
			
			
			
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUrl(url);
			dataSource.setUser(name);
			dataSource.setPassword(pass);
			dataSource.setDatabaseName("recruitment");
	
			Connection con;
			try {
				con = dataSource.getConnection();
			Statement st = con.createStatement();
			//int rs = st.executeUpdate(query);

			PrintWriter out;
			try {
				out = res.getWriter();
				//out.print(rs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
String sql = "insert into recruitment values (?,?,?,?,?)";
			
			PreparedStatement pst = 
					  con.prepareStatement(sql);
					pst.setInt(1, 0);
					  pst.setString(2, firstName);
					  pst.setString(3, lastName);
					  pst.setInt(4, zipCode);
					  pst.setString(5, email);
					  
					  int numRowsChanged = pst.executeUpdate();
			System.out.println("Short Result is: "+ numRowsChanged);

			//rs.next();
			//String theName = rs.getString("firstName");
			
			con.close();
			st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				res.sendRedirect("SuccessPage.html");
				//res.sendRedirect("/Enroll/SuccessPage.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//System.out.print(firstName + ", " + lastName + ", " +  zipCode + ", " + email);
		}
	}
	
}
