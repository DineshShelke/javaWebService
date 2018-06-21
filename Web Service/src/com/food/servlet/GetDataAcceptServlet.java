package com.food.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.food.bean.UserDetails;
import com.food.controller.Controller;

/**
 * Servlet implementation class GetDataAcceptServlet
 */
@WebServlet("/GetDataAcceptServlet")
public class GetDataAcceptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDataAcceptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		UserDetails userDetails = new UserDetails();
		String date;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        date = sdf.format(new Date());
        
        userDetails.setDate(date);
		
		Controller controller = new Controller();
		JSONObject json;
		json = controller.acceptPostData(userDetails);
		
		if(json!= null) {
			//System.out.println("Get data GetDataAcceptServlet "+ json);
			
			response.getWriter().write(json.toString());
		}else {
			System.out.println("something happen wrong with get json data");
		}
		
	}

}
