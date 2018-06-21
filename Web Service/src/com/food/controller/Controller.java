package com.food.controller;

import com.food.bean.AcceptPost;
import com.food.bean.Barcode;
import com.food.bean.GetData;
import com.food.bean.Login;
import com.food.bean.Order;
import com.food.bean.PostData;
import com.food.bean.Register;
import com.food.bean.UserDetails;
import com.food.connection.ManageDatabase;
import com.food.dao.Dao;
import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONObject;

public class Controller {

		Dao dao = new Dao();
		
       public JSONObject UserRegistration(Register register) {
    	   JSONObject json = null;
			try(Connection con = ManageDatabase.getConnection()){
				json = dao.UserRegistration(register,con);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			return json;
			
		}
		
		public JSONObject UserLogin(Login login) {
			
			JSONObject json = null;
			
			try(Connection con = ManageDatabase.getConnection()){
				json = dao.UserLogin(login,con);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			return json;
		}
		
		public JSONObject setPostData(PostData postdata) {
			
			JSONObject json = null;
			try(Connection con = ManageDatabase.getConnection()){
				json = dao.setPostData(postdata,con);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return json;
		}
		
		public JSONObject getPostData(GetData getdata) {
			
			JSONObject json = null;
			try(Connection con = ManageDatabase.getConnection()){
				json = dao.getPostData(getdata,con);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return json;
		}
		
		public JSONObject getAcceptPostData(AcceptPost acceptPost) {
			
			JSONObject json = null;
			try(Connection con = ManageDatabase.getConnection()){
				json = dao.getAcceptPostData(acceptPost,con);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return json;
		}
		
		public JSONObject acceptPostData(UserDetails userDetails) {
			
			JSONObject json = null;
			try(Connection con = ManageDatabase.getConnection()){
				json = dao.acceptPostData(userDetails,con);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return json;
		}
		
		public JSONObject getOrderPostData(Order order) {
			
			JSONObject json = null;
			try(Connection con = ManageDatabase.getConnection()){
				json = dao.getOrderPostData(order,con);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return json;
		}
		
		public JSONObject getOrderAccept(GetData getdata) {
			
			JSONObject json = null;
			try(Connection con = ManageDatabase.getConnection()){
				json = dao.getOrderAccept(getdata,con);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return json;
		}
		
		public JSONObject getUserDetails(UserDetails userDetails) {
			
			JSONObject json = null;
			try(Connection con = ManageDatabase.getConnection()){
				json = dao.getUserDetails(userDetails,con);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return json;
		}
		
		public JSONObject getUserDetailsTODeleveryBoy(GetData getData) {
			
			JSONObject json = null;
			try(Connection con = ManageDatabase.getConnection()){
				json = dao.getUserDetailsTODeleveryBoy(getData,con);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return json;
		}
		
		public JSONObject getBarcodeData(Barcode barcode) {
			
			JSONObject json = null;
			try(Connection con = ManageDatabase.getConnection()){
				json = dao.getBarcodeData(barcode,con);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return json;
		}
		
		public JSONObject fetchBarcodeData() {
			
			JSONObject json = null;
			try(Connection con = ManageDatabase.getConnection()){
				json = dao.fetchBarcodeData(con);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return json;
		}
		
		public JSONObject getBarcodeData(GetData getdata) {
			
			JSONObject json = null;
			try(Connection con = ManageDatabase.getConnection()){
				json = dao.getBarcodeData(getdata,con);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return json;
		}
		
}
