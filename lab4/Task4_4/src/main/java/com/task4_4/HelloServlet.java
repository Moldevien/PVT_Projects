package com.task4_4;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/Task4_4")
public class HelloServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		double a = Double.parseDouble(req.getParameter("a"));
		double b = Double.parseDouble(req.getParameter("b"));
		double c = Double.parseDouble(req.getParameter("c"));
		
		String operation = req.getParameter("operation");
		
		double result = 0;
		
		switch (operation) {
			case "max":
				result = Math.max(a, Math.max(b, c));
				break;
			
			case "min":
				result = Math.min(a, Math.min(b, c));
				break;
			
			case "avg":
				result = (a + b + c) / 3;
				break;
		}
		
		resp.setContentType("text/html");
		resp.getWriter().println(
				"<h2>Результат: " + result + "</h2>" +
				"<a href='index.jsp'>Повернутися</a>"
		);
	}
}