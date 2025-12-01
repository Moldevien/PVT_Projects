package com.lab5.servlet;

import java.io.*;
import java.util.List;
import java.util.Optional;

import com.lab5.dao.NotebookDAO;
import com.lab5.model.Notebook;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "NotebookServlet", value = {"/", "/new", "/insert", "/update", "/list", "/edit", "/delete",
		"/report/countries", "/report/manufacturers", "/report/country-max", "/report/country-min",
		"/report/manufacturer-max", "/report/manufacturer-min",
		"/filter/hard", "/filter/soft", "/filter/by-country", "/filter/page-style",	"/filter/pages",
		"/filter/circulation"})
public class NotebookServlet extends HttpServlet {
	private NotebookDAO notebookDAO;
	
	public void init() {
		notebookDAO = new NotebookDAO();
		
		// Генерація демонстраційних даних (розкоментувати при першому запуску)
		notebookDAO.generateDemoData();
	}
	
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
				case "/":
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertNotebook(request, response);
					break;
				case "/update":
					updateNotebook(request, response);
					break;
				case "/list":
					listNotebook(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/delete":
					deleteNotebook(request, response);
					break;
				
				// reports
				case "/report/countries":
					showCountries(request, response);
					break;
				case "/report/manufacturers":
					showManufacturers(request, response);
					break;
				case "/report/country-max":
					showCountryMax(request, response);
					break;
				case "/report/country-min":
					showCountryMin(request, response);
					break;
				case "/report/manufacturer-max":
					showManufacturerMax(request, response);
					break;
				case "/report/manufacturer-min":
					showManufacturerMin(request, response);
					break;
				
				// filters
				case "/filter/hard":
					filterHard(request, response);
					break;
				case "/filter/soft":
					filterSoft(request, response);
					break;
				case "/filter/by-country":
					filterByCountry(request, response);
					break;
				case "/filter/page-style":
					filterPageStyle(request, response);
					break;
				case "/filter/pages":
					filterPages(request, response);
					break;
				case "/filter/circulation":
					filterCirculation(request, response);
					break;
				
				default:
					listNotebook(request, response);
					break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	// ---------- CRUD handlers ----------
	/**
	 * Показати список блокнотів
	 */
	private void listNotebook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Notebook> notebooks = notebookDAO.getAllNotebook();
		request.setAttribute("listNotebook", notebooks);
		request.setAttribute("title", "Список блокнотів");
		RequestDispatcher dispatcher = request.getRequestDispatcher("notebook-list.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Показати форму для створення нового блокнота
	 */
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("notebook-form.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Показати форму редагування блокнота
	 */
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Notebook existingNotebook = notebookDAO.getNotebook(id);
		request.setAttribute("notebook", existingNotebook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("notebook-form.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Додати новий блокнот
	 */
	private void insertNotebook(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Notebook notebook = readNotebookFromRequest(request);
		notebookDAO.addNotebook(notebook);
		response.sendRedirect("list");
	}
	
	/**
	 * Оновити блокнот
	 */
	private void updateNotebook(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Notebook notebook = readNotebookFromRequest(request);
		notebook.setId(id);
		notebookDAO.updateNotebook(notebook);
		response.sendRedirect("list");
	}
	
	/**
	 * Видалити блокнот
	 */
	private void deleteNotebook(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		notebookDAO.deleteNotebook(id);
		response.sendRedirect("list");
	}
	
	/**
	 * Прочитати параметри з HttpServletRequest та створити об'єкт Notebook
	 */
	private Notebook readNotebookFromRequest(HttpServletRequest request) {
		String manufacturer = request.getParameter("manufacturer");
		String notebookName = request.getParameter("notebookName");
		int pages = parseIntOrZero(request.getParameter("pages"));
		String coverType = request.getParameter("coverType");
		String country = request.getParameter("country");
		int circulation = parseIntOrZero(request.getParameter("circulation"));
		String pageStyle = request.getParameter("pageStyle");
		return new Notebook(manufacturer, notebookName, pages, coverType, country, circulation, pageStyle);
	}
	
	/**
	 * Спробувати розпарсити ціле число, якщо не вийшло - повернути 0
	 */
	private int parseIntOrZero(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return 0;
		}
	}
	
	// ---------- Reports ----------
	/**
	 * Показати країни та кількість блокнотів для кожної країни
	 */
	private void showCountries(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Object[]> data = notebookDAO.getCountriesWithCount();
		request.setAttribute("report", data);
		request.setAttribute("title", "Країни та кількість блокнотів");
		request.setAttribute("filter", "Країна");
		RequestDispatcher dispatcher = request.getRequestDispatcher("report-count.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Показати виробників та кількість блокнотів для кожного виробника
	 */
	private void showManufacturers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Object[]> data = notebookDAO.getManufacturersWithCount();
		request.setAttribute("report", data);
		request.setAttribute("title", "Виробники та кількість блокнотів");
		request.setAttribute("filter", "Виробник");
		RequestDispatcher dispatcher = request.getRequestDispatcher("report-count.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Показати країну з найбільшою кількістю блокнотів
	 */
	private void showCountryMax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Optional<Object[]> r = notebookDAO.getCountryMax();
		request.setAttribute("single", r.orElse(null));
		request.setAttribute("title", "Країна з найбільшою кількістю блокнотів");
		RequestDispatcher dispatcher = request.getRequestDispatcher("report.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Показати країну з найменшою кількістю блокнотів
	 */
	private void showCountryMin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Optional<Object[]> r = notebookDAO.getCountryMin();
		request.setAttribute("single", r.orElse(null));
		request.setAttribute("title", "Країна з найменшою кількістю блокнотів");
		RequestDispatcher dispatcher = request.getRequestDispatcher("report.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Показати виробника з найбільшою кількістю блокнотів
	 */
	private void showManufacturerMax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Optional<Object[]> r = notebookDAO.getManufacturerMax();
		request.setAttribute("single", r.orElse(null));
		request.setAttribute("title", "Виробник з найбільшою кількістю блокнотів");
		RequestDispatcher dispatcher = request.getRequestDispatcher("report.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Показати виробника з найменшою кількістю блокнотів
	 */
	private void showManufacturerMin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Optional<Object[]> r = notebookDAO.getManufacturerMin();
		request.setAttribute("single", r.orElse(null));
		request.setAttribute("title", "Виробник з найменшою кількістю блокнотів");
		RequestDispatcher dispatcher = request.getRequestDispatcher("report.jsp");
		dispatcher.forward(request, response);
	}
	
	// ---------- Filters ----------
	/**
	 * Показати блокноти з твердою обкладинкою
	 */
	private void filterHard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Notebook> list = notebookDAO.getHardCover();
		request.setAttribute("listNotebook", list);
		request.setAttribute("title", "Блокноти з твердою обкладинкою");
		RequestDispatcher dispatcher = request.getRequestDispatcher("filter-results.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Показати блокноти з м'якою обкладинкою
	 */
	private void filterSoft(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Notebook> list = notebookDAO.getSoftCover();
		request.setAttribute("listNotebook", list);
		request.setAttribute("title", "Блокноти з м’якою обкладинкою");
		RequestDispatcher dispatcher = request.getRequestDispatcher("filter-results.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Показати блокноти певної країни
	 */
	private void filterByCountry(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String country = request.getParameter("country");
		List<Notebook> list = notebookDAO.getByCountry(country);
		request.setAttribute("listNotebook", list);
		request.setAttribute("title", "Блокноти з країни: " + country);
		RequestDispatcher dispatcher = request.getRequestDispatcher("filter-results.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Показати блокноти за стилем сторінки
	 */
	private void filterPageStyle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String style = request.getParameter("pageStyle");
		List<Notebook> list = notebookDAO.filterByPageStyle(style);
		request.setAttribute("listNotebook", list);
		request.setAttribute("title", "Блокноти зі стилем сторінки: " + style);
		RequestDispatcher dispatcher = request.getRequestDispatcher("filter-results.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Показати блокноти за кількістю сторінок
	 */
	private void filterPages(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int min = parseIntOrZero(request.getParameter("minPages"));
		int max = parseIntOrZero(request.getParameter("maxPages"));
		List<Notebook> list = notebookDAO.filterByPages(min, max);
		request.setAttribute("listNotebook", list);
		request.setAttribute("title", "Блокноти за кількістю сторінок: " + min + " - " + max);
		RequestDispatcher dispatcher = request.getRequestDispatcher("filter-results.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Показати блокноти за тиражем
	 */
	private void filterCirculation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int min = parseIntOrZero(request.getParameter("minCirculation"));
		int max = parseIntOrZero(request.getParameter("maxCirculation"));
		List<Notebook> list = notebookDAO.filterByCirculation(min, max);
		request.setAttribute("listNotebook", list);
		request.setAttribute("title", "Блокноти за тиражем: " + min + " - " + max);
		RequestDispatcher dispatcher = request.getRequestDispatcher("filter-results.jsp");
		dispatcher.forward(request, response);
	}
}