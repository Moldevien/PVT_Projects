package com.lab6.servlet;

import com.lab6.dao.ProductDAO;
import com.lab6.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = {"/product/list", "/product/menu", "/product/new", "/product/insert",
		"/product/edit", "/product/update", "/product/delete", "/product/popular"})
public class ProductServlet extends HttpServlet {
	private ProductDAO productDAO;
	
	@Override
	public void init() {
		productDAO = new ProductDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
				case "/product/list": { list(request, response); break; }
				case "/product/insert": { insert(request, response); break; }
				case "/product/new": { showForm(request, response); break; }
				case "/product/edit": { showEditForm(request, response); break; }
				case "/product/update": { update(request, response); break; }
				case "/product/delete": { delete(request, response); break; }
				case "/product/popular": { popular(request, response); break; }
				default: { list(request, response); break; }
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	/** Додавання нового товару */
	private void insert(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		Product product = new Product(name, price);
		productDAO.add(product);
		response.sendRedirect("list");
	}
	
	/** Оновлення товару */
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		Product product = new Product(id, name, price);
		productDAO.update(product);
		response.sendRedirect("list");
	}
	
	/** Видалення товару */
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		productDAO.delete(id);
		response.sendRedirect("list");
	}
	
	/** Показ списку товарів */
	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("products", productDAO.getAll());
		request.getRequestDispatcher("product-list.jsp").forward(request, response);
	}
	
	/** Показ форми додавання нового товару */
	private void showForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("product-form.jsp").forward(request, response);
	}
	
	/** Показ форми редагування товару */
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("product", productDAO.getById(id));
		request.getRequestDispatcher("product-form.jsp").forward(request, response);
	}
	
	private void popular(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("popular", productDAO.getMostPopular());
		request.getRequestDispatcher("top-product.jsp").forward(request, response);
	}
}
