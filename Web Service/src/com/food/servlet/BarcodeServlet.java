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

import com.food.bean.Barcode;
import com.food.controller.Controller;

/**
 * Servlet implementation class BarcodeServlet
 */
@WebServlet("/BarcodeServlet")
public class BarcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarcodeServlet() {
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
		
		Barcode barcode = new Barcode();
		
		String date;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        date = sdf.format(new Date());
		  
		barcode.setItemId(request.getParameter("itemId"));
		barcode.setOrderId(request.getParameter("order_id"));
		barcode.setUserId(request.getParameter("userId"));
		barcode.setDate(date);
		
		System.out.println("Barcode Data from Barcode"+request.getParameter("itemId")+" "+request.getParameter("order_id"));
		
		Controller controller = new Controller();
		JSONObject json;
		json = controller.getBarcodeData(barcode);
		
		if(json!= null) {
			response.getWriter().write(json.toString());
			//System.out.println("Barcode Servlet Data "+ json);
		}else {
			System.out.println("something happen wrong with get json data");
		}	
	}

}
