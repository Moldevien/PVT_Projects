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

@WebServlet("/client/*")
public class ClientServlet extends HttpServlet {
	private ClientDAO clientDAO;
	
	@Override
	public void init() {
		clientDAO = new ClientDAO();
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
	
	/** Показ списку клієнтів */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("clients", clientDAO.getAll());
		request.getRequestDispatcher("client-list.jsp").forward(request, response);
	}
	
	/** Додавання нового клієнта */
	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		clientDAO.add(new Client(request.getParameter("name"), request.getParameter("phone"), request.getParameter("email")));
		response.sendRedirect("list");
	}
	
	/** Оновлення клієнта */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Client client = new Client(id, request.getParameter("name"), request.getParameter("phone"), request.getParameter("email"));
		clientDAO.update(client);
		response.sendRedirect("list");
	}
	
	/** Видалення клієнта */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		clientDAO.delete(id);
		response.sendRedirect("list");
	}
	
	/** Показ форми додавання нового клієнта */
	private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("client-form.jsp");
		dispatcher.forward(request, response);
	}
	
	/** Показ форми редагування клієнта */
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Client client = clientDAO.getById(id);
		request.setAttribute("client", client);
		request.getRequestDispatcher("client-form.jsp").forward(request, response);
	}
	
	/** Найкращий клієнт за загальною вартістю покупок */
	private void popular(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("popular", clientDAO.getTop());
		request.getRequestDispatcher("top-client.jsp").forward(request, response);
	}
}
