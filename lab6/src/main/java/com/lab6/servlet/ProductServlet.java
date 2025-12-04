package com.lab6.servlet;

import com.lab6.dao.ProductDAO;
import com.lab6.model.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/product/*")
public class ProductServlet extends HttpServlet {
	private ProductDAO productDAO;
	
	@Override
	public void init() {
		productDAO = new ProductDAO();
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
	
	/** Показ списку товарів */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("products", productDAO.getAll());
		request.getRequestDispatcher("product-list.jsp").forward(request, response);
	}
	
	/** Додавання нового товару */
	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		productDAO.add(new Product(name, price));
		response.sendRedirect("list");
	}
	
	/** Оновлення товару */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Product p = new Product(id, request.getParameter("name"), Double.parseDouble(request.getParameter("price")));
		productDAO.update(p);
		response.sendRedirect("list");
	}
	
	/** Видалення товару */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		productDAO.delete(id);
		response.sendRedirect("list");
	}
	
	/** Показ форми додавання нового товару */
	private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("product-form.jsp").forward(request, response);
	}
	
	/** Показ форми редагування товару */
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Product p = productDAO.getById(id);
		request.setAttribute("product", p);
		request.getRequestDispatcher("product-form.jsp").forward(request, response);
	}
	
	private void popular(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("popular", productDAO.getMostPopular());
		request.getRequestDispatcher("/product/product-popular.jsp").forward(request, response);
	}
}
