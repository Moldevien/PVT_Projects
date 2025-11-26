package com.task4_1;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
	
	public void init() {
		message = "Any fool can write code that a computer can understand. Good programmers write code that humans can understand";
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>Martin Fowler:</h2>");
		out.println(message);
		out.println("</body></html>");
	}
}