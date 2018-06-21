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
import com.food.bean.AcceptPost;
import com.food.bean.GetData;
import com.food.controller.Controller;

/**
 * Servlet implementation class AcceptPostServlet
 */
@WebServlet("/AcceptPostServlet")
public class AcceptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptServlet() {
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
			
		AcceptPost acceptPost = new AcceptPost();
		
		acceptPost.setId(request.getParameter("id"));
		acceptPost.setName(request.getParameter("name"));
		acceptPost.setQty(request.getParameter("qty"));
		acceptPost.setPrice(request.getParameter("price"));
		acceptPost.setHiggintime(request.getParameter("higgintime"));
		acceptPost.setDate(request.getParameter("date"));
		acceptPost.setStatus("0");
		
		Controller controller = new Controller();
		JSONObject json;
		json = controller.getAcceptPostData(acceptPost);
		
		if(json!= null) {
			response.getWriter().write(json.toString());
		}else {
			System.out.println("something happen wrong with get json data");
		}
	}

}