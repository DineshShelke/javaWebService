package com.food.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.food.bean.Login;
import com.food.bean.PostData;
import com.food.controller.Controller;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
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
		
		System.out.println("LoginServlet Servlet called");
		PostData postdata = new PostData();
		
		postdata.setName(request.getParameter("name"));
		postdata.setQty(request.getParameter("qty"));
		postdata.setPrice(request.getParameter("price"));
		postdata.setHiggintime(request.getParameter("higgintime"));
		postdata.setDate(request.getParameter("date"));
		postdata.setStatus("0");
		
		Controller controller = new Controller();
		JSONObject json;
		json = controller.setPostData(postdata);
		
		if(json!= null) {
			//System.out.println(json.toString());
			response.getWriter().write(json.toString());
		}else {
			System.out.println("something happen wrong");
		}	
	}
}
