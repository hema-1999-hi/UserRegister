package userRegistration.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import userRegistration.model.User;
import userRegistration.service.UserService;

/**
 * Servlet implementation class DisplayUsers
 */
@WebServlet("/displayusers")
public class DisplayServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(DisplayServlet.class);

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayServlet() {
		super();
	}

	/**
	 * This is the starting point of the application. Will display list of users
	 * on the page
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Display user start");
		UserService userservice = new UserService();
		List<User> userlist = userservice.getAllUsers();
		log.info(userlist);
		request.setAttribute("userlist", userlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Display.jsp");
		dispatcher.forward(request, response);

		log.info("Display user end ...");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
