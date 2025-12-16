package com.task4_2;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/Task4_2")
public class HelloServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		
		String numStr = request.getParameter("number");
		
		try (PrintWriter out = response.getWriter()) {
			out.println("<html><head><title>Таблиця множення</title></head><body>");
			
			if (numStr != null && !numStr.trim().isEmpty()) {
				try {
					int n = Integer.parseInt(numStr.trim());
					
					out.println("<h2>Таблиця множення для " + n + "</h2>");
					out.println("<table border='1'>");
					
					for (int i = 1; i <= 10; i++) {
						out.println("<tr><td>" + n + " × " + i + "</td><td>" + (n * i) + "</td></tr>");
					}
					
					out.println("</table>");
				} catch (Exception e) {
					out.println("<p style='color:red'>Виникла помилка.</p>");
				}
			} else {
				out.println("<p style='color:red'>Будь ласка, введіть число.</p>");
			}
			
			out.println(
					"<br><a href=\"index.jsp\">Повернутись</a>" +
					"</body></html>");
		}
	}
}