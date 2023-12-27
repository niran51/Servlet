package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String enteredEmail=request.getParameter("email");
		
		String enteredPassword=request.getParameter("pswd");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/1project?allowPublicKeyRetrieval=true","root","Pass33word@");
			String query="select * from students where Student_email=? and Student_password= ?";
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			
			preparedStatement.setString(1, enteredEmail);
			preparedStatement.setString(2, enteredPassword);
			
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				request.getRequestDispatcher("congratulation.html").forward(request, response);
			}
			else {
				request.setAttribute("msg", "PLZ TRY AGAIN");
				request.getRequestDispatcher("login2.html").forward(request, response);
				
			}
				
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}