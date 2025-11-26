package com.task4_3;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/Task4_3")
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		
		// Якщо гри ще нема — створюємо
		if (session.getAttribute("low") == null) {
			session.setAttribute("low", 0);
			session.setAttribute("high", 100);
		}
		
		int low = (int) session.getAttribute("low");
		int high = (int) session.getAttribute("high");
		
		int mid = (low + high) / 2;
		
		resp.setContentType("text/html");
		resp.getWriter().println(
				"<h2>Це число " + mid + "?</h2>" +
				"<form method='post'>" +
					"<button name='answer' value='more'>Більше</button>" +
					"<button name='answer' value='less'>Менше</button>" +
					"<button name='answer' value='equal'>Дорівнює</button>" +
				"</form>"
		);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		
		int low = (int) session.getAttribute("low");
		int high = (int) session.getAttribute("high");
		
		int mid = (low + high) / 2;
		
		String answer = req.getParameter("answer");
		
		if ("more".equals(answer)) {
			low = mid;
		} else if ("less".equals(answer)) {
			high = mid;
		} else if ("equal".equals(answer)) {
			// Комп’ютер відгадав
			resp.setContentType("text/html");
			resp.getWriter().println("<h1>Загадане число = " + mid + "</h1>");
			session.invalidate();
			return;
		}
		
		// Оновлюємо діапазон
		session.setAttribute("low", low);
		session.setAttribute("high", high);
		
		// Якщо комп’ютер точно знає число
		if (low == high) {
			resp.setContentType("text/html");
			resp.getWriter().println("<h1>Загадане число = " + low + "</h1>");
			session.invalidate();
			return;
		}
		
		// Повертаємося до GET
		resp.sendRedirect("Task4_3");
	}
}