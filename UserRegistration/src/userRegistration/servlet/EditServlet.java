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

import userRegistration.model.SaveOrUpdateException;
import userRegistration.model.User;
import userRegistration.service.UserService;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/edituser")
public class EditServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(EditServlet.class);

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("editit user start...");
		String success = "/displayusers";
		UserService service = new UserService();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("Name").toString();
		String email = request.getParameter("Email").toString();
		String Phonenum = request.getParameter("Phonenum").toString();
		String country = request.getParameter("Country").toString();
		User user = new User(id, name, email, Phonenum, country);
		try {
			service.updateUser(user);
		} catch (SaveOrUpdateException e) {
			log.error("Error: dopost(): Exception from Service!");
			log.error("Forwarding to error page!");
			success = "errorPage.jsp";
			request.setAttribute("exception", e);

//			throw e;
		}
		log.info(user);
		request.setAttribute("UserUpdateMsg", "User updated successfullyy...");
		RequestDispatcher dispatcher = request.getRequestDispatcher(success);
		dispatcher.forward(request, response);
		log.info("Edit user end...");
	}

}
