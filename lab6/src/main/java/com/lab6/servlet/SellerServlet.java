package com.lab6.servlet;

import com.lab6.dao.SellerDAO;
import com.lab6.model.Seller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/seller/*")
public class SellerServlet extends HttpServlet {
	private SellerDAO sellerDAO;
	
	@Override
	public void init() {
		sellerDAO = new SellerDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getPathInfo();
		try {
			switch (action) {
				case "/list": {
					list(request, response);
					break;
				}
				case "/new": {
					showForm(request, response);
					break;
				}
				case "/insert": {
					insert(request, response);
					break;
				}
				case "/edit": {
					showEditForm(request, response);
					break;
				}
				case "/update": {
					update(request, response);
					break;
				}
				case "/delete": {
					delete(request, response);
					break;
				}
				case "/popular": {
					popular(request, response);
					break;
				}
				default: {
					list(request, response);
				}
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	/** Показ списку продавців */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("sellers", sellerDAO.getAll());
		request.getRequestDispatcher("seller-list.jsp").forward(request, response);
	}
	
	/** Додавання нового продавця */
	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		sellerDAO.add(new Seller(name, phone, email));
		response.sendRedirect("list");
	}
	
	/** Оновлення продавця */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Seller s = new Seller(id, request.getParameter("name"), request.getParameter("phone"), request.getParameter("email"));
		sellerDAO.update(s);
		response.sendRedirect("list");
	}
	
	/** Видалення продавця */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		sellerDAO.delete(id);
		response.sendRedirect("list");
	}
	
	/** Показ форми додавання нового продавця */
	private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("seller-form.jsp");
		dispatcher.forward(request, response);
	}
	
	/** Показ форми редагування продавця */
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Seller s = sellerDAO.getById(id);
		request.setAttribute("seller", s);
		request.getRequestDispatcher("seller-form.jsp").forward(request, response);
	}
	
	/** Найкращий продавець за загальною вартістю продажів */
	private void popular(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("popular", sellerDAO.getTop());
		request.getRequestDispatcher("top-seller.jsp").forward(request, response);
	}
}
