package com.food.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.food.bean.Register;
import com.food.controller.Controller;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		System.out.println("Servlet called Post");
		Register register = new Register();
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");
		String role = request.getParameter("role");
		String password = request.getParameter("password");
		
		/*System.out.println("Name "+name);
		System.out.println("Email "+email);
		System.out.println("Contact "+contact);
		System.out.println("address "+address);
		System.out.println("role "+role);
		System.out.println("Password "+password);*/
		
		
		register.setName(name);
		register.setEmail(email);
		register.setContact(contact);
		register.setAddress(address);
		register.setRole(role);
		register.setPassword(password);
		
		Controller controller = new Controller();
		JSONObject json;
		json = controller.UserRegistration(register);

		if(json!= null) {
			response.getWriter().write(json.toString());
		}
		else {
			System.out.println("Register Fail");
		}
		
	}

}
