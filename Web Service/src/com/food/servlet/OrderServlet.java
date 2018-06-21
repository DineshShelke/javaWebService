package com.food.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.food.bean.Order;
import com.food.controller.Controller;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		Order order = new Order();
		order.setId(request.getParameter("id"));
		order.setName(request.getParameter("name"));
		order.setQty(request.getParameter("qty"));
		order.setPrice(request.getParameter("price"));
		order.setHiggintime(request.getParameter("higgintime"));
		order.setDate(request.getParameter("date"));
		order.setUserId(request.getParameter("userId"));
		order.setCounter(request.getParameter("counter"));
		order.setFinalprice(request.getParameter("totalprice"));
		
		System.out.println(request.getParameter("id")+" "+request.getParameter("name")+" "+request.getParameter("qty")+" "+request.getParameter("price")+" "+request.getParameter("higgintime")+" "+request.getParameter("date"));
		
		Controller controller = new Controller();
		JSONObject json;
		json = controller.getOrderPostData(order);
		
		if(json!= null) {
			response.getWriter().write(json.toString());
		}else {
			System.out.println("something happen wrong with get json data");
		}
		
		
		
	}
}
