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

import userRegistration.model.User;
import userRegistration.service.UserService;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/registeruser")
public class RegisterServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(RegisterServlet.class);

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * this is new uer registration request Will fetch next user id values and
	 * forward to add jsp
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("do post reg user start ...");
		// 2. find next id from service
		UserService service = new UserService();
		int nextId;
		try {
			nextId = service.getNextId();
			log.info(nextId);
			//3. set in request scope
			request.setAttribute("nextId", nextId);
			//4. forward to add jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("AddUser.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("do post reg user end...");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = new UserService();
		String name = request.getParameter("Name").toString();
		String email = request.getParameter("Email").toString();
		String Phonenum =request.getParameter("Phonenum").toString();
		String country =request.getParameter("Country").toString();
		User user = new User(name,email,Phonenum,country);
		service.addUser(user);
		log.info(user);
		/*if(name == "" ){
			request.setAttribute("userError", "user name is manditory");
			RequestDispatcher dispatcher = request.getRequestDispatcher("AddUser.jsp");
			dispatcher.forward(request, response);
		}*/
		request.setAttribute("userAddMsg", "User Add successfullyy...");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/displayusers");
		dispatcher.forward(request, response);
		log.info("Add user end...");
	}

}
