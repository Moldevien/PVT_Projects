package com.task4_3;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/Task4_3")
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("low") == null) {
			session.setAttribute("low", 0);
			session.setAttribute("high", 100);
		}
		
		int low = (int) session.getAttribute("low");
		int high = (int) session.getAttribute("high");
		
		int mid = (low + high) / 2;
		
		response.setContentType("text/html");
		
		try (PrintWriter out = response.getWriter()) {
			out.println(
					"<h2>Це число " + mid + "?</h2>" +
					"<form method='post'>" +
						"<button name='answer' value='more'>Більше</button>" +
						"<button name='answer' value='less'>Менше</button>" +
						"<button name='answer' value='equal'>Дорівнює</button>" +
					"</form>"
			);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		
		int low = (int) session.getAttribute("low");
		int high = (int) session.getAttribute("high");
		
		int mid = (low + high) / 2;
		
		String answer = request.getParameter("answer");
		
		if ("more".equals(answer)) {
			low = mid;
		} else if ("less".equals(answer)) {
			high = mid;
		} else if ("equal".equals(answer)) {
			// Комп’ютер відгадав
			response.setContentType("text/html");
			response.getWriter().println("<h1>Загадане число = " + mid + "</h1>");
			session.invalidate();
			return;
		}
		
		// Оновлюємо діапазон
		session.setAttribute("low", low);
		session.setAttribute("high", high);
		
		// Якщо комп’ютер точно знає число
		if (low == high) {
			response.setContentType("text/html");
			response.getWriter().println("<h1>Загадане число = " + low + "</h1>");
			session.invalidate();
			return;
		}
		
		// Повертаємося до GET
		response.sendRedirect("Task4_3");
	}
}