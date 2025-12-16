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

@WebServlet(name = "SellerServlet", urlPatterns = {"/seller/list", "/seller/menu", "/seller/new", "/seller/insert",
		"/seller/edit", "/seller/update", "/seller/delete", "/seller/popular"})
public class SellerServlet extends HttpServlet {
	private SellerDAO sellerDAO;
	
	@Override
	public void init() {
		sellerDAO = new SellerDAO();
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
				case "/seller/list": { list(request, response); break; }
				case "/seller/new": { showForm(request, response); break; }
				case "/seller/insert": { insert(request, response); break; }
				case "/seller/edit": { showEditForm(request, response); break; }
				case "/seller/update": { update(request, response); break; }
				case "/seller/delete": { delete(request, response); break; }
				case "/seller/popular": { popular(request, response); break; }
				default: { list(request, response); break; }
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	/** Додавання нового продавця */
	private void insert(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		Seller seller = new Seller(name, phone, email);
		sellerDAO.add(seller);
		response.sendRedirect("list");
	}
	
	/** Оновлення продавця */
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		Seller seller = new Seller(id, name, phone, email);
		sellerDAO.update(seller);
		response.sendRedirect("list");
	}
	
	/** Видалення продавця */
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		sellerDAO.delete(id);
		response.sendRedirect("list");
	}
	
	/** Показ списку продавців */
	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("sellers", sellerDAO.getAll());
		request.getRequestDispatcher("seller-list.jsp").forward(request, response);
	}
	
	/** Показ форми додавання нового продавця */
	private void showForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("seller-form.jsp").forward(request, response);
	}
	
	/** Показ форми редагування продавця */
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Seller seller = sellerDAO.getById(id);
		request.setAttribute("seller", seller);
		request.getRequestDispatcher("seller-form.jsp").forward(request, response);
	}
	
	/** Найкращий продавець за загальною вартістю продажів */
	private void popular(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("popular", sellerDAO.getTop());
		request.getRequestDispatcher("top-seller.jsp").forward(request, response);
	}
}
