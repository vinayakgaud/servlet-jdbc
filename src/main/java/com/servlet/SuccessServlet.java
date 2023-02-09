package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class SuccessServlet extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
		res.setContentType("text/html");
		String name= req.getParameter("username");
		PrintWriter pWriter = res.getWriter();
		pWriter.println(name);
		pWriter.println("Successfully registered");
//		Cookie[] c = req.getCookies();
//		for(Cookie c1 : c) {
//			printWriter.println(c1.getName() +" " + c1.getValue());
//		}
	}
}
