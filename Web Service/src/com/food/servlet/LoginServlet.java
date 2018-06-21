package com.food.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.food.bean.Login;
import com.food.controller.Controller;

/**
 * Servlet implementation class WebServiceController
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Servlet called Get");
		
		/*String name = request.getParameter("name");
		String email = request.getParameter("emal");
		String password = request.getParameter("password");*/
		
		
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		System.out.println("LoginServlet Servlet called");
		Login login = new Login();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		login.setEmail(email);
		login.setPassword(password);
		login.setRole(role);
		
		Controller controller = new Controller();
		JSONObject json;
		json = controller.UserLogin(login);
		
		if(json!=null) {
			response.getWriter().write(json.toString());
		}
		else {
			System.out.println("Login Fail");
		}
		
	}

}
