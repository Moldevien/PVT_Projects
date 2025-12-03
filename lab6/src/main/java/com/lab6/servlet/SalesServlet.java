package com.lab6.servlet;

import com.lab6.dao.*;
import com.lab6.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "SalesServlet", urlPatterns = {
		"/", "/index",
		"/seller/list", "/seller/new", "/seller/insert", "/seller/edit", "/seller/update", "/seller/delete",
		"/clients", "/client/new", "/client/insert", "/client/edit", "/client/update", "/client/delete",
		"/products", "/product/new", "/product/insert", "/product/edit", "/product/update", "/product/delete",
		"/sales", "/sale/new", "/sale/insert", "/sale/edit", "/sale/update", "/sale/delete",
		"/reports/sales-by-date", "/reports/sales-between", "/reports/sales-by-seller", "/reports/sales-by-client",
		"/reports/top-seller", "/reports/top-client", "/reports/average-purchase", "/reports/popular-product"
})
public class SalesServlet extends HttpServlet {
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
		
		// генерація тестових даних якщо пусто
		generateDemoData();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// використовується для форм; перенаправляємо в doGet для простоти
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		try {
			switch (path) {
				case "/":
				case "/index": {
					request.getRequestDispatcher("index.jsp").forward(request, response);
					break;
				}
				
				// ---------- Sellers ----------
				case "/seller/list": {
					listSellers(request, response);
					break;
				}
				case "/seller/new": {
					showSellerNew(request, response);
					break;
				}
				case "/seller/insert": {
					insertSeller(request, response);
					break;
				}
				case "/seller/edit": {
					showSellerEdit(request, response);
					break;
				}
				case "/seller/update": {
					updateSeller(request, response);
					break;
				}
				case "/seller/delete": {
					deleteSeller(request, response);
					break;
				}
				
				// ---------- Clients ----------
				case "/client/list": {
					listClients(request, response);
					break;
				}
				case "/client/new": {
					showClientNew(request, response);
					break;
				}
				case "/client/insert": {
					insertClient(request, response);
					break;
				}
				case "/client/edit": {
					showClientEdit(request, response);
					break;
				}
				case "/client/update": {
					updateClient(request, response);
					break;
				}
				case "/client/delete": {
					deleteClient(request, response);
					break;
				}
				
				// ---------- Products ----------
				case "/product/list": {
					listProducts(request, response);
					break;
				}
				case "/product/new": {
					showProductNew(request, response);
					break;
				}
				case "/product/insert": {
					insertProduct(request, response);
					break;
				}
				case "/product/edit": {
					showProductEdit(request, response);
					break;
				}
				case "/product/update": {
					updateProduct(request, response);
					break;
				}
				case "/product/delete": {
					deleteProduct(request, response);
					break;
				}
				
				// ---------- Sales ----------
				case "/sale/list": {
					listSales(request, response);
					break;
				}
				case "/sale/new": {
					showSaleNew(request, response);
					break;
				}
				case "/sale/insert": {
					insertSale(request, response);
					break;
				}
				case "/sale/edit": {
					showSaleEdit(request, response);
					break;
				}
				case "/sale/update": {
					updateSale(request, response);
					break;
				}
				case "/sale/delete": {
					deleteSale(request, response);
					break;
				}
				
				// ---------- Reports ----------
				case "/reports/sales-by-date": {
					salesByDate(request, response);
					break;
				}
				case "/reports/sales-between": {
					salesBetween(request, response);
					break;
				}
				case "/reports/sales-by-seller": {
					salesBySeller(request, response);
					break;
				}
				case "/reports/sales-by-client": {
					salesByClient(request, response);
					break;
				}
				case "/reports/top-seller": {
					topSeller(request, response);
					break;
				}
				case "/reports/top-client": {
					topClient(request, response);
					break;
				}
				case "/reports/avg": {
					averagePurchase(request, response);
					break;
				}
				case "/reports/popular-product": {
					popularProduct(request, response);
					break;
				}
				
				default: {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
					break;
				}
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	// ---------- Sellers handlers ----------
	
	/** Показ списку продавців */
	private void listSellers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("sellers", sellerDAO.getAll());
		request.setAttribute("title", "Продавці");
		request.getRequestDispatcher("seller-list.jsp").forward(request, response);
	}
	
	/** Показ форми додавання нового продавця */
	private void showSellerNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("seller-form.jsp");
		dispatcher.forward(request, response);
	}
	
	/** Додавання нового продавця */
	private void insertSeller(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		sellerDAO.add(new Seller(name, phone, email));
		response.sendRedirect("list");
	}
	
	/** Показ форми редагування продавця */
	private void showSellerEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Seller s = sellerDAO.getById(id);
		request.setAttribute("seller", s);
		request.getRequestDispatcher("seller-form.jsp").forward(request, response);
	}
	
	/** Оновлення продавця */
	private void updateSeller(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Seller s = new Seller(id, request.getParameter("name"), request.getParameter("phone"), request.getParameter("email"));
		sellerDAO.update(s);
		response.sendRedirect("list");
	}
	
	/** Видалення продавця */
	private void deleteSeller(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		sellerDAO.delete(id);
		response.sendRedirect("list");
	}
	
	// ---------- Clients ----------
	
	/** Показ списку клієнтів */
	private void listClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("clients", clientDAO.getAll());
		request.setAttribute("title", "Клієнти");
		request.getRequestDispatcher("client-list.jsp").forward(request, response);
	}
	
	/** Показ форми додавання нового клієнта */
	private void showClientNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("client-form.jsp");
		dispatcher.forward(request, response);
	}
	
	/** Додавання нового клієнта */
	private void insertClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
		clientDAO.add(new Client(request.getParameter("name"), request.getParameter("phone"), request.getParameter("email")));
		response.sendRedirect("list");
	}
	
	/** Показ форми редагування клієнта */
	private void showClientEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Client b = clientDAO.getById(id);
		request.setAttribute("client", b);
		request.getRequestDispatcher("client-form.jsp").forward(request, response);
	}
	
	/** Оновлення клієнта */
	private void updateClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Client b = new Client(id, request.getParameter("name"), request.getParameter("phone"), request.getParameter("email"));
		clientDAO.update(b);
		response.sendRedirect("list");
	}
	
	/** Видалення клієнта */
	private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		clientDAO.delete(id);
		response.sendRedirect("list");
	}
	
	// ---------- Products ----------
	
	/** Показ списку товарів */
	private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("products", productDAO.getAll());
		request.setAttribute("title", "Товари");
		request.getRequestDispatcher("product-list.jsp").forward(request, response);
	}
	
	/** Показ форми додавання нового товару */
	private void showProductNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("product-form.jsp").forward(request, response);
	}
	
	/** Додавання нового товару */
	private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		productDAO.add(new Product(name, price));
		response.sendRedirect("list");
	}
	
	/** Показ форми редагування товару */
	private void showProductEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Product p = productDAO.getById(id);
		request.setAttribute("product", p);
		request.getRequestDispatcher("product-form.jsp").forward(request, response);
	}
	
	/** Оновлення товару */
	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Product p = new Product(id, request.getParameter("name"), Double.parseDouble(request.getParameter("price")));
		productDAO.update(p);
		response.sendRedirect("list");
	}
	
	/** Видалення товару */
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		productDAO.delete(id);
		response.sendRedirect("list");
	}
	
	// ---------- Sales ----------
	
	/** Показ списку угод */
	private void listSales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("sales", saleDAO.getAll());
		request.setAttribute("title", "Угоди");
		request.getRequestDispatcher("sale-list.jsp").forward(request, response);
	}
	
	/** Показ форми додавання нової угоди */
	private void showSaleNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// підкласти списки продавців/покупців/товарів
		request.setAttribute("sellers", sellerDAO.getAll());
		request.setAttribute("clients", clientDAO.getAll());
		request.setAttribute("products", productDAO.getAll());
		request.getRequestDispatcher("sale-form.jsp").forward(request, response);
	}
	
	/** Додавання нової угоди */
	private void insertSale(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
	
	/** Показ форми редагування угоди */
	private void showSaleEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Sale sale = saleDAO.getById(id);
		request.setAttribute("sale", sale);
		request.setAttribute("sellers", sellerDAO.getAll());
		request.setAttribute("clients", clientDAO.getAll());
		request.setAttribute("products", productDAO.getAll());
		request.getRequestDispatcher("sale-form.jsp").forward(request, response);
	}
	
	/** Оновлення угоди */
	private void updateSale(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
	private void deleteSale(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		saleDAO.delete(id);
		response.sendRedirect("list");
	}
	
	// ---------- Reports ----------
	
	/** Угоди за певну дату */
	private void salesByDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateStr = request.getParameter("date");
		LocalDate date = (dateStr == null || dateStr.isEmpty()) ? LocalDate.now() : LocalDate.parse(dateStr);
		request.setAttribute("sales", saleDAO.getByDate(date));
		request.setAttribute("title", "Угоди за дату: " + date);
		request.getRequestDispatcher("sale-date.jsp").forward(request, response);
	}
	
	/** Угоди між двома датами */
	private void salesBetween(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		LocalDate d1 = (from == null || from.isEmpty()) ? LocalDate.now().minusMonths(1) : LocalDate.parse(from);
		LocalDate d2 = (to == null || to.isEmpty()) ? LocalDate.now() : LocalDate.parse(to);
		request.setAttribute("sales", saleDAO.getBetweenDates(d1, d2));
		request.setAttribute("title", "Угоди з " + d1 + " по " + d2);
		request.getRequestDispatcher("sale-date.jsp").forward(request, response);
	}
	
	/** Угоди певного продавця */
	private void salesBySeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("sellerId"));
		request.setAttribute("sales", saleDAO.getBySeller(id));
		request.setAttribute("title", "Угоди продавця: " + sellerDAO.getById(id).getName());
		request.getRequestDispatcher("sales-by-seller.jsp").forward(request, response);
	}
	
	/** Угоди певного покупця */
	private void salesByClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("clientId"));
		request.setAttribute("sales", saleDAO.getByClient(id));
		request.setAttribute("title", "Угоди покупця: " + clientDAO.getById(id).getName());
		request.getRequestDispatcher("sales-by-client.jsp").forward(request, response);
	}
	
	/** Найкращий продавець за загальною вартістю продажів */
	private void topSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object[] r = saleDAO.getTopSeller();
		request.setAttribute("result", r);
		request.getRequestDispatcher("top-seller.jsp").forward(request, response);
	}
	
	/** Найкращий клієнт за загальною вартістю покупок */
	private void topClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object[] r = saleDAO.getTopClient();
		request.setAttribute("result", r);
		request.getRequestDispatcher("top-client.jsp").forward(request, response);
	}
	
	/** Середня вартість покупки */
	private void averagePurchase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Double avg = saleDAO.getAveragePurchase();
		request.setAttribute("avg", avg);
		request.getRequestDispatcher("average-purchase.jsp").forward(request, response);
	}
	
	/** Найпопулярніший товар за кількістю продажів */
	private void popularProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object[] r = saleDAO.getMostPopularProduct();
		request.setAttribute("result", r);
		request.getRequestDispatcher("popular-product.jsp").forward(request, response);
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