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
 * Servlet implementation class GetUser
 */
@WebServlet("/getuser")
public class GetServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(GetServlet.class);

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("getservlet user start..");
		UserService service = new UserService();
		int id = Integer.parseInt(request.getParameter("id"));
		User u = service.getUser(id);
		request.setAttribute("user", u);
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditUser.jsp");
		dispatcher.forward(request, response);
		log.info("getservlet user end");

	}

	

}
