package userRegistration.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import userRegistration.service.UserService;

/**
 * Servlet implementation class RemoveUser
 */
@WebServlet("/removeuser")
public class RemoveServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(RemoveServlet.class);

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Removeservlet user start");
		UserService service = new UserService();
		int id = Integer.parseInt(request.getParameter("id"));
		service.deleteUser(id);
		log.info(id);
		request.setAttribute("UserDeleteMsg", "user deleted successfully...");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/displayusers");
		dispatcher.forward(request, response);
		log.info("removeservlet user end");
	}

	
}
