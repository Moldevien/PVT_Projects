package com.lab6.servlet;

import com.lab6.dao.ClientDAO;
import com.lab6.model.Client;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ClientServlet", urlPatterns = {"/client/list", "/client/menu", "/client/new", "/client/insert",
		"/client/edit", "/client/update", "/client/delete", "/client/popular"})
public class ClientServlet extends HttpServlet {
	private ClientDAO clientDAO;
	
	@Override
	public void init() {
		clientDAO = new ClientDAO();
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
				case "/client/list": { list(request, response); break; }
				case "/client/new": { showForm(request, response); break; }
				case "/client/insert": { insert(request, response); break; }
				case "/client/edit": { showEditForm(request, response); break; }
				case "/client/update": { update(request, response); break; }
				case "/client/delete": { delete(request, response); break; }
				case "/client/popular": { popular(request, response); break; }
				default: { list(request, response); break; }
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	/** Додавання нового клієнта */
	private void insert(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String seller = request.getParameter("seller");
		Client client = new Client(name, price, seller);
		clientDAO.add(client);
		response.sendRedirect("list");
	}
	
	/** Оновлення клієнта */
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		Client client = new Client(name, phone, email);
		clientDAO.update(client);
		response.sendRedirect("list");
	}
	
	/** Видалення клієнта */
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		clientDAO.delete(id);
		response.sendRedirect("list");
	}
	
	/** Показ списку клієнтів */
	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("clients", clientDAO.getAll());
		request.getRequestDispatcher("client-list.jsp").forward(request, response);
	}
	
	/** Показ форми додавання нового клієнта */
	private void showForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("client-form.jsp").forward(request, response);
	}
	
	/** Показ форми редагування клієнта */
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("client", clientDAO.getById(id));
		request.getRequestDispatcher("client-form.jsp").forward(request, response);
	}
	
	/** Найкращий клієнт за загальною вартістю покупок */
	private void popular(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("popular", clientDAO.getTop());
		request.getRequestDispatcher("top-client.jsp").forward(request, response);
	}
}
