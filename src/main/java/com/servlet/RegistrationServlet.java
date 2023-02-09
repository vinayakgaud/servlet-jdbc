package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.catalina.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;

public class RegistrationServlet extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
		String name = req.getParameter("usr_name");
		String email = req.getParameter("usr_email");
		String passwd = String.valueOf(req.getParameter("usr_passwd").hashCode());
		String course = req.getParameter("usr_course");
		String gender = req.getParameter("usr_gender");
		String imgName = req.getParameter("usr_image");
		boolean acceptedCond = req.getParameter("usr_accept")==null ? false:true ;
		res.setContentType("text/html");
		PrintWriter pWriter = res.getWriter();
		Connection connection = null;
		if(acceptedCond) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/learningjdbc", "root", "root");
				pWriter.println("<h2>Connection established...</h2>");
				String quString = "insert into user(userName,email,passwd,course,gender,imgName) value(?,?,?,?,?,?)";
				PreparedStatement pStatement = connection.prepareStatement(quString);
				pStatement.setString(1, name);
				pStatement.setString(2, email);
				pStatement.setString(3, passwd);
				pStatement.setString(4, course);
				pStatement.setString(5, gender);
				pStatement.setString(6, imgName);
				
				pStatement.executeUpdate();
				pWriter.println("<h2>Data added successfully!</h2>");
				
				String retQuString = "select * from user";
				Statement statement = connection.createStatement();
				ResultSet set = statement.executeQuery(retQuString);
				pWriter.println("<h2>Fetching details from table...</h2>");
				pWriter.println("<table style='border: 4px solid black;border-collapse: collapse;border-color:#ccccff'>");
				pWriter.println("<tr>"
						+ "<th style='border: 4px solid black;border-collapse: collapse;border-color:#ccccff'> user ID </th>"
						+ "<th style='border: 4px solid black;border-collapse: collapse;border-color:#ccccff'> User Name </th>"
						+ "<th style='border: 4px solid black;border-collapse: collapse;border-color:#ccccff'> User Email </th>"
						+ "<th style='border: 4px solid black;border-collapse: collapse;border-color:#ccccff'> User Course </th>"
						+ "<th style='border: 4px solid black;border-collapse: collapse;border-color:#ccccff'> User Gender </th>"
						+ "<th style='border: 4px solid black;border-collapse: collapse;border-color:#ccccff'> Uploaded Image Name </th>"
						+ "</tr>");
				while(set.next()) {
					pWriter.println("<tr>"
							+ "<td style='border: 4px solid black;border-collapse: collapse;border-color:#ccccff'> "+set.getInt("uid")+" </td>"
							+ "<td style='border: 4px solid black;border-collapse: collapse;border-color:#ccccff'> "+set.getString("userName")+" </td>"
							+ "<td style='border: 4px solid black;border-collapse: collapse;border-color:#ccccff'> "+set.getString("email")+" </td>"
							+ "<td style='border: 4px solid black;border-collapse: collapse;border-color:#ccccff'> "+set.getString("course")+" </td>"
							+ "<td style='border: 4px solid black;border-collapse: collapse;border-color:#ccccff'> "+set.getString("gender")+" </td>"
							+ "<td style='border: 4px solid black;border-collapse: collapse;border-color:#ccccff'> "+set.getString("imgName")+" </td>"
							+ "</tr>");
				}
				pWriter.println("</table>");
			} catch (Exception e) {
				e.getMessage();
			}finally {
				try {
					connection.close();
					pWriter.println("<h2>Connection terminated...</h2>");
				} catch (SQLException e) {
					e.getMessage();
				}
			}
		}else {
			pWriter.println("<h2>Please accept the condition</h2>");
			RequestDispatcher rDispatcher = req.getRequestDispatcher("index.html");
			rDispatcher.include(req, res);
		}
	}
}
