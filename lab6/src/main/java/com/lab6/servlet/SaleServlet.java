package com.lab6.servlet;

import com.lab6.dao.ClientDAO;
import com.lab6.dao.ProductDAO;
import com.lab6.dao.SaleDAO;
import com.lab6.dao.SellerDAO;
import com.lab6.model.Client;
import com.lab6.model.Product;
import com.lab6.model.Sale;
import com.lab6.model.Seller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/sale/*")
public class SaleServlet extends HttpServlet {
	private SellerDAO sellerDAO;
	private ClientDAO clientDAO;
	private ProductDAO productDAO;
	private SaleDAO saleDAO;
	
	@Override
	public void init() {
		sellerDAO = new SellerDAO();
		clientDAO = new ClientDAO();
		productDAO = new ProductDAO();
		saleDAO = new SaleDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// використовується для форм; перенаправляємо в doGet для простоти
		doGet(request, response);
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
				case "/by-date": {
					filterByDate(request, response);
					break;
				}
				case "/between": {
					filterBetween(request, response);
					break;
				}
				case "/by-seller": {
					filterBySeller(request, response);
					break;
				}
				case "/by-client": {
					filterByClient(request, response);
					break;
				}
				case "/avg": {
					avg(request, response);
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
	
	// ---------- Sales ----------
	
	/** Показ списку угод */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("sales", saleDAO.getAll());
		request.getRequestDispatcher("sale-list.jsp").forward(request, response);
	}
	
	/** Додавання нової угоди */
	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int sellerId = Integer.parseInt(request.getParameter("sellerId"));
		int clientId = Integer.parseInt(request.getParameter("clientId"));
		int productId = Integer.parseInt(request.getParameter("productId"));
		LocalDate date = LocalDate.parse(request.getParameter("saleDate"));
		Seller s = sellerDAO.getById(sellerId);
		Client b = clientDAO.getById(clientId);
		Product p = productDAO.getById(productId);
		saleDAO.add(new Sale(s, b, p, date));
		response.sendRedirect("list");
	}
	
	/** Оновлення угоди */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int sellerId = Integer.parseInt(request.getParameter("sellerId"));
		int clientId = Integer.parseInt(request.getParameter("clientId"));
		int productId = Integer.parseInt(request.getParameter("productId"));
		LocalDate date = LocalDate.parse(request.getParameter("saleDate"));
		Seller s = sellerDAO.getById(sellerId);
		Client b = clientDAO.getById(clientId);
		Product p = productDAO.getById(productId);
		Sale sale = new Sale(id, s, b, p, date);
		saleDAO.update(sale);
		response.sendRedirect("list");
	}
	
	/** Видалення угоди */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		saleDAO.delete(id);
		response.sendRedirect("list");
	}
	
	/** Показ форми додавання нової угоди */
	private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// підкласти списки продавців/покупців/товарів
		request.setAttribute("sellers", sellerDAO.getAll());
		request.setAttribute("clients", clientDAO.getAll());
		request.setAttribute("products", productDAO.getAll());
		request.getRequestDispatcher("sale-form.jsp").forward(request, response);
	}
	
	/** Показ форми редагування угоди */
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Sale sale = saleDAO.getById(id);
		request.setAttribute("sale", sale);
		request.setAttribute("sellers", sellerDAO.getAll());
		request.setAttribute("clients", clientDAO.getAll());
		request.setAttribute("products", productDAO.getAll());
		request.getRequestDispatcher("sale-form.jsp").forward(request, response);
	}
	
	// ---------- Reports ----------
	
	/** Угоди за певну дату */
	private void filterByDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateStr = request.getParameter("date");
		LocalDate date = (dateStr == null || dateStr.isEmpty()) ? LocalDate.now() : LocalDate.parse(dateStr);
		request.setAttribute("sales", saleDAO.getByDate(date));
		request.setAttribute("title", "Угоди за дату: " + date);
		request.getRequestDispatcher("sale-date.jsp").forward(request, response);
	}
	
	/** Угоди між двома датами */
	private void filterBetween(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		LocalDate d1 = (from == null || from.isEmpty()) ? LocalDate.now().minusMonths(1) : LocalDate.parse(from);
		LocalDate d2 = (to == null || to.isEmpty()) ? LocalDate.now() : LocalDate.parse(to);
		request.setAttribute("sales", saleDAO.getBetweenDates(d1, d2));
		request.setAttribute("title", "Угоди з " + d1 + " по " + d2);
		request.getRequestDispatcher("sale-date.jsp").forward(request, response);
	}
	
	/** Угоди певного продавця */
	private void filterBySeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("sellerId"));
		request.setAttribute("sales", saleDAO.getBySeller(id));
		request.setAttribute("title", "Угоди продавця: " + sellerDAO.getById(id).getName());
		request.getRequestDispatcher("sales-by-seller.jsp").forward(request, response);
	}
	
	/** Угоди певного покупця */
	private void filterByClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("clientId"));
		request.setAttribute("sales", saleDAO.getByClient(id));
		request.setAttribute("title", "Угоди покупця: " + clientDAO.getById(id).getName());
		request.getRequestDispatcher("sales-by-client.jsp").forward(request, response);
	}
	
	/** Середня вартість покупки */
	private void avg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Double avg = saleDAO.getAveragePurchase();
		request.setAttribute("avg", avg);
		request.getRequestDispatcher("average-purchase.jsp").forward(request, response);
	}
	
	// ---------- Demo data ----------
	
	/** Генерація демонстраційних даних, якщо БД порожня */
	private void generateDemoData() {
		// Sellers
		Seller s1 = new Seller("Ivanov Sales", "+380501112233", "ivanov@sales.com");
		Seller s2 = new Seller("GlobalTrade", "+380672223344", "info@globaltrade.com");
		sellerDAO.add(s1);
		sellerDAO.add(s2);
		
		// Clients
		Client b1 = new Client("Petro", "+380931234567", "petro@mail.com");
		Client b2 = new Client("Olena", "+380631112233", "olena@mail.com");
		clientDAO.add(b1);
		clientDAO.add(b2);
		
		// Products
		Product p1 = new Product("Laptop Model A", 1200.00);
		Product p2 = new Product("Keyboard X", 45.50);
		Product p3 = new Product("Monitor 24\"", 180.00);
		productDAO.add(p1);
		productDAO.add(p2);
		productDAO.add(p3);
		
		// Sales
		saleDAO.add(new Sale(s1, b1, p1, LocalDate.now().minusDays(3)));
		saleDAO.add(new Sale(s1, b2, p2, LocalDate.now().minusDays(2)));
		saleDAO.add(new Sale(s2, b1, p3, LocalDate.now().minusDays(1)));
		saleDAO.add(new Sale(s2, b2, p1, LocalDate.now()));
	}
}