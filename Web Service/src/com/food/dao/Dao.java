package com.food.dao;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.food.bean.AcceptPost;
import com.food.bean.Barcode;
import com.food.bean.GetData;
import com.food.bean.Login;
import com.food.bean.Order;
import com.food.bean.PostData;
import com.food.bean.Register;
import com.food.bean.UserDetails;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;

public class Dao {
	
	JSONObject ParentObject = new JSONObject();
	JSONArray jsonArray = new JSONArray();
	
	public JSONObject UserRegistration(Register register, Connection con) {
		
		System.out.println("User Registration Dao");
		
		JSONObject ParentObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		PreparedStatement pstmt;
		try {
			
			pstmt = con.prepareStatement("SELECT `User_id`,`User_email` FROM `tbl_food_master` WHERE `User_email`=?");
			pstmt.setString(1, register.getEmail());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("res", "You are allready register");
				jsonArray.put(jsonObject);
				
				return ParentObject.put("Response", jsonArray);
				
			}
			else {
				
				//pstmt = con.prepareStatement("INSERT INTO `tbl_food_master`(`User_name`, `User_email`, `User_contact`, `User_password`) VALUES(?,?,?,?)");
				pstmt = con.prepareStatement("INSERT INTO `tbl_food_master`(`User_name`, `User_email`, `User_contact`, `User_address`, `User_role`, `User_password`) VALUES (?,?,?,?,?,?)");
				pstmt.setString(1, register.getName());
				pstmt.setString(2, register.getEmail());
				pstmt.setString(3, register.getContact());
				pstmt.setString(4, register.getAddress());
				pstmt.setString(5, register.getRole());
				pstmt.setString(6, register.getPassword());
				pstmt.executeUpdate();
			
				JSONObject jsonObject = new JSONObject();
			
				jsonObject.put("res", "Register Successfully");
				jsonArray.put(jsonObject);
			
				return ParentObject.put("Response", jsonArray);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}// end UserRegistration
	
		public JSONObject UserLogin(Login login, Connection con) {
		
		int a = 0;
		try {
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM `tbl_food_master` WHERE `User_email`=? AND `User_password`=? AND `User_role`=?");
			pstmt.setString(1, login.getEmail());
			pstmt.setString(2, login.getPassword());
			pstmt.setString(3, login.getRole());
			ResultSet rs = pstmt.executeQuery();
			
			JSONObject ParentObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			
			
			if(rs.next()) {
				int  user_id = rs.getInt("User_id");
				String user_name = rs.getString("user_name");
				String user_email = rs.getString("user_email");
				String user_password = rs.getString("user_password");
				
				System.out.println("User Id : "+user_id);
				System.out.println("User Name : "+user_name);
				System.out.println("User Email : "+user_email);
				System.out.println("User Password : "+user_password);
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", user_id);
				jsonObject.put("name", user_name);
				jsonObject.put("email", user_email);
				jsonObject.put("password", user_password);
				jsonObject.put("success", "Login Successfully");
				 
				jsonArray.put(jsonObject);
				
				return ParentObject.put("Response", jsonArray);
			}
			else
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("success", "U not register");
				jsonArray.put(jsonObject);
				return ParentObject.put("Response", jsonArray);
			}
			
			
			//pstmt.setString(1, login.get);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	
	} // end Login
	
		
	public JSONObject setPostData(PostData postdata, Connection con) {
		int status = 0;
			try {
					PreparedStatement stmtInsert = con.prepareStatement("INSERT INTO `tbl_hotel_item`(`Item_name`, `Item_qty`, `Item_price`, `Item_higgintime`, `Date`, `Status`) VALUES (?,?,?,?,?,?)");
					stmtInsert.setString(1, postdata.getName());
					stmtInsert.setString(2, postdata.getQty());
					stmtInsert.setString(3, postdata.getPrice());
					stmtInsert.setInt(4, Integer.parseInt(postdata.getHiggintime()));
					stmtInsert.setString(5, postdata.getDate());
					stmtInsert.setString(6, postdata.getStatus());
					status = stmtInsert.executeUpdate();
					
					JSONObject jsonObject=null;
					
					PreparedStatement stmtSelect = con.prepareStatement("SELECT `Item_id`, `Item_name`, `Item_qty`, `Item_price`, `Item_higgintime`, `Date` FROM `tbl_hotel_item` WHERE `Date`=?");
					stmtSelect.setString(1, postdata.getDate());
					ResultSet rs = stmtSelect.executeQuery();
					
					while(rs.next()) {
						
						jsonObject = new JSONObject();
						
						jsonObject.put("id", rs.getString("Item_id"));
						jsonObject.put("name", rs.getString("Item_name"));
						jsonObject.put("qty", rs.getString("Item_qty"));
						jsonObject.put("price", rs.getString("Item_price"));
						jsonObject.put("higgintime", rs.getString("Item_higgintime"));
						jsonObject.put("date", rs.getString("Date"));
						
						jsonArray.put(jsonObject);
					}
									
					return ParentObject.put("Response", jsonArray);
									
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return null;
		}
	
	public JSONObject getPostData(GetData getdata, Connection con) {
		JSONObject jsonObject=null;
		try {
			PreparedStatement stmtSelect = con.prepareStatement("SELECT `Item_id`, `Item_name`, `Item_qty`, `Item_price`, `Item_higgintime`, `Date`,`Status` FROM `tbl_hotel_item` WHERE `Date`=?");
			stmtSelect.setString(1, getdata.getDate());
			ResultSet rs = stmtSelect.executeQuery();
			
			while(rs.next()) {
				jsonObject = new JSONObject();
				
				jsonObject.put("id", rs.getString("Item_id"));
				jsonObject.put("name", rs.getString("Item_name"));
				jsonObject.put("qty", rs.getString("Item_qty"));
				jsonObject.put("price", rs.getString("Item_price"));
				jsonObject.put("higgintime", rs.getString("Item_higgintime"));
				jsonObject.put("date", rs.getString("Date"));
				jsonObject.put("status", rs.getString("Status"));
				
				jsonArray.put(jsonObject);
			}
			return ParentObject.put("Response", jsonArray);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public JSONObject getAcceptPostData(AcceptPost acceptPost, Connection con) {
		JSONObject jsonObject=null;
		try {
			PreparedStatement stmtUpdate = con.prepareStatement("UPDATE `tbl_hotel_item` SET `Status`= 1 WHERE `Item_id`=?");
			stmtUpdate.setString(1, acceptPost.getId());
			//ResultSet rs = stmtSelect.executeQuery();
			int result = stmtUpdate.executeUpdate();
			
			if(result == 1) {
				
				PreparedStatement stmtInsert = con.prepareStatement("INSERT INTO `tbl_hotel_item_accept_post`(`Item_id`, `Item_name`, `Item_qty`, `Item_price`, `Item_higgintime`, `Date`) VALUES (?,?,?,?,?,?)");
				stmtInsert.setString(1, acceptPost.getId());
				stmtInsert.setString(2, acceptPost.getName());
				stmtInsert.setString(3, acceptPost.getQty());
				stmtInsert.setString(4, acceptPost.getPrice());
				stmtInsert.setString(5, acceptPost.getHiggintime());
				stmtInsert.setString(6, acceptPost.getDate());
				//stmtInsert.setString(7, acceptPost.getStatus());
				int InsertResult = stmtInsert.executeUpdate();
				
				System.out.println("Result Inserted " + ""+InsertResult);
				
				PreparedStatement stmtSelect = con.prepareStatement("SELECT `Item_id`, `Item_name`, `Item_qty`, `Item_price`, `Item_higgintime`, `Date`,`Status` FROM `tbl_hotel_item` WHERE `Date`=?");
				stmtSelect.setString(1, acceptPost.getDate());
				ResultSet rs = stmtSelect.executeQuery();
				
				while(rs.next()) {
					jsonObject = new JSONObject();
					
					jsonObject.put("id", rs.getString("Item_id"));
					jsonObject.put("name", rs.getString("Item_name"));
					jsonObject.put("qty", rs.getString("Item_qty"));
					jsonObject.put("price", rs.getString("Item_price"));
					jsonObject.put("higgintime", rs.getString("Item_higgintime"));
					jsonObject.put("date", rs.getString("Date"));
					jsonObject.put("status", rs.getString("Status"));
					
					jsonArray.put(jsonObject);
				}
				
			}
			
			return ParentObject.put("Response", jsonArray);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	public JSONObject acceptPostData(UserDetails userDetails, Connection con) {
		JSONObject jsonObject=null;
		try {	
				PreparedStatement stmtSelect = con.prepareStatement("SELECT * FROM `tbl_hotel_item_accept_post` WHERE `Date` = ?");
				stmtSelect.setString(1, userDetails.getDate());
				ResultSet rs = stmtSelect.executeQuery();
				
				while(rs.next()) {
					jsonObject = new JSONObject();
					
					jsonObject.put("id", rs.getString("Item_id"));
					jsonObject.put("name", rs.getString("Item_name"));
					jsonObject.put("qty", rs.getString("Item_qty"));
					jsonObject.put("price", rs.getString("Item_price"));
					jsonObject.put("higgintime", rs.getString("Item_higgintime"));
					jsonObject.put("date", rs.getString("Date"));
					
					jsonArray.put(jsonObject);
				}
				
			return ParentObject.put("Response", jsonArray);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public JSONObject getOrderPostData(Order order, Connection con) {
		JSONObject jsonObject=null;
		try {	
			
			/*PreparedStatement stmtUpdate = con.prepareStatement("UPDATE `tbl_hotel_item_accept_post` SET `Status`=1 WHERE Item_id = ?");
			stmtUpdate.setString(1, order.getId());
			//ResultSet rs = stmtSelect.executeQuery();
			int result = stmtUpdate.executeUpdate();*/
			
			
				PreparedStatement stmtInsert = con.prepareStatement("INSERT INTO `tbl_item_order_details`(`Item_id`, `User_id`, `Item_name`, `Item_qty`, `Item_price`, `Item_higgintime`, `Date`, `Item_quantity`, `Total_item_price`) VALUES (?,?,?,?,?,?,?,?,?)");
																	
				stmtInsert.setString(1, order.getId());
				stmtInsert.setString(2, order.getUserId());
				stmtInsert.setString(3, order.getName());
				stmtInsert.setString(4, order.getQty());
				stmtInsert.setString(5, order.getPrice());
				stmtInsert.setString(6, order.getHiggintime());
				stmtInsert.setString(7, order.getDate());
				stmtInsert.setString(8, order.getCounter());
				stmtInsert.setString(9, order.getFinalprice());
				
				int status = stmtInsert.executeUpdate();
				
				System.out.println("Result Inserted " + ""+status);
				
				PreparedStatement stmtSelect = con.prepareStatement("SELECT `Item_id`, `Item_name`, `Item_qty`, `Item_price`, `Item_higgintime`, `Date` FROM `tbl_hotel_item_accept_post` WHERE `Date`= ?");
				stmtSelect.setString(1, order.getDate());
				ResultSet rs = stmtSelect.executeQuery();	
					
				while(rs.next()) {
					jsonObject = new JSONObject();
						
					jsonObject.put("id", rs.getString("Item_id"));
					jsonObject.put("name", rs.getString("Item_name"));
					jsonObject.put("qty", rs.getString("Item_qty"));
					jsonObject.put("price", rs.getString("Item_price"));
					jsonObject.put("higgintime", rs.getString("Item_higgintime"));
					jsonObject.put("date", rs.getString("Date"));
					//jsonObject.put("status", rs.getString("Status"));
						
					jsonArray.put(jsonObject);
				}
					
			
				
			return ParentObject.put("Response", jsonArray);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	
	public JSONObject getOrderAccept(GetData getdata, Connection con) {
		JSONObject jsonObject=null;
		try {
			//PreparedStatement stmtSelect = con.prepareStatement("SELECT tbl_item_order_details.*,tbl_food_master.* FROM tbl_item_order_details,tbl_food_master WHERE tbl_item_order_details.User_id = tbl_food_master.User_id AND tbl_item_order_details.Date = ?");
			PreparedStatement stmtSelect = con.prepareStatement("SELECT tbl_item_order_details.*,tbl_food_master.* FROM tbl_item_order_details,tbl_food_master WHERE tbl_item_order_details.User_id = tbl_food_master.User_id AND tbl_item_order_details.Date = ?");
			//stmtSelect.setString(1, getdata.getUserId());
			stmtSelect.setString(1, getdata.getDate());
			ResultSet rs = stmtSelect.executeQuery();
			
			while(rs.next()) {
				jsonObject = new JSONObject();
				
				jsonObject.put("id", rs.getString("Item_id"));
				jsonObject.put("order_id", rs.getString("Order_id"));
				jsonObject.put("name", rs.getString("Item_name"));
				jsonObject.put("qty", rs.getString("Item_qty"));
				jsonObject.put("price", rs.getString("Item_price"));
				jsonObject.put("higgintime", rs.getString("Item_higgintime"));
				jsonObject.put("date", rs.getString("Date"));
				jsonObject.put("itemquantity", rs.getString("Item_quantity"));
				jsonObject.put("totalitemprice", rs.getString("Total_item_price"));
				
				jsonObject.put("User_name", rs.getString("User_name"));
				jsonObject.put("User_email", rs.getString("User_email"));
				jsonObject.put("User_contact", rs.getString("User_contact"));
				jsonObject.put("User_address", rs.getString("User_address"));
				jsonObject.put("User_role", rs.getString("User_role"));
				jsonObject.put("User_password", rs.getString("User_password"));
				
				jsonArray.put(jsonObject);
			}
			return ParentObject.put("Response", jsonArray);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public JSONObject getUserDetails(UserDetails userDetails, Connection con) {
		JSONObject jsonObject=null;
		try {
			PreparedStatement stmtSelect = con.prepareStatement("SELECT tbl_food_master.*,tbl_item_order_details.* FROM tbl_food_master,tbl_item_order_details WHERE tbl_food_master.User_id = tbl_item_order_details.User_id AND tbl_item_order_details.Item_id = ? AND tbl_item_order_details.User_id = ?");
			stmtSelect.setString(1, userDetails.getItemId());
			stmtSelect.setString(2, userDetails.getUserId());
			ResultSet rs = stmtSelect.executeQuery();
			
			while(rs.next()) {
				jsonObject = new JSONObject();
				
				jsonObject.put("User_name", rs.getString("User_name"));
				jsonObject.put("User_email", rs.getString("User_email"));
				jsonObject.put("User_contact", rs.getString("User_contact"));
				jsonObject.put("User_address", rs.getString("User_address"));
				jsonObject.put("User_role", rs.getString("User_role"));
				jsonObject.put("User_password", rs.getString("User_password"));
				jsonObject.put("Item_id", rs.getString("Item_id"));
				jsonObject.put("User_id", rs.getString("User_id"));
				jsonObject.put("Item_name", rs.getString("Item_name"));
				jsonObject.put("Item_qty", rs.getString("Item_qty"));
				jsonObject.put("Item_price", rs.getString("Item_price"));
				jsonObject.put("Item_higgintime", rs.getString("Item_higgintime"));
				jsonObject.put("Date", rs.getString("Date"));
				
				jsonArray.put(jsonObject);
			}
			return ParentObject.put("Response", jsonArray);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	public JSONObject getUserDetailsTODeleveryBoy(GetData getdata, Connection con) {
		JSONObject jsonObject=null;
		try {
			PreparedStatement stmtSelect = con.prepareStatement("SELECT tbl_item_order_details.*,tbl_food_master.* FROM tbl_item_order_details,tbl_food_master WHERE tbl_item_order_details.User_id = tbl_food_master.User_id AND tbl_item_order_details.Date = ?");
			
			stmtSelect.setString(1, getdata.getDate());
			ResultSet rs = stmtSelect.executeQuery();
			
			while(rs.next()) {
				jsonObject = new JSONObject();
				
				jsonObject.put("id", rs.getString("Item_id"));
				jsonObject.put("name", rs.getString("Item_name"));
				jsonObject.put("qty", rs.getString("Item_qty"));
				jsonObject.put("price", rs.getString("Item_price"));
				jsonObject.put("higgintime", rs.getString("Item_higgintime"));
				jsonObject.put("date", rs.getString("Date"));
				jsonObject.put("itemquantity", rs.getString("Item_quantity"));
				jsonObject.put("totalitemprice", rs.getString("Total_item_price"));
				
				jsonObject.put("User_name", rs.getString("User_name"));
				jsonObject.put("User_email", rs.getString("User_email"));
				jsonObject.put("User_contact", rs.getString("User_contact"));
				jsonObject.put("User_address", rs.getString("User_address"));
				jsonObject.put("User_role", rs.getString("User_role"));
				jsonObject.put("User_password", rs.getString("User_password"));
				
				jsonArray.put(jsonObject);
			}
			return ParentObject.put("Response", jsonArray);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONObject getBarcodeData(Barcode barcode, Connection con) {
		JSONObject jsonObject=null;
		try {
			PreparedStatement stmtInsert = con.prepareStatement("INSERT INTO `tbl_barcode_data`(`Order_id`, `Item_id`, `User_id`, `Date`) VALUES (?,?,?,?)");
			stmtInsert.setString(1, barcode.getOrderId());
			stmtInsert.setString(2, barcode.getItemId());
			stmtInsert.setString(3, barcode.getUserId());
			stmtInsert.setString(4, barcode.getDate());
			int result = stmtInsert.executeUpdate();
			
			return ParentObject.put("Response", "Barcode Data Successfully Inserted");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public JSONObject fetchBarcodeData(Connection con) {
		JSONObject jsonObject=null;
		try {
			PreparedStatement stmtSelect = con.prepareStatement("SELECT tbl_item_order_details.*,tbl_barcode_data.* FROM tbl_item_order_details,tbl_barcode_data WHERE tbl_item_order_details.User_id = tbl_barcode_data.User_id AND tbl_item_order_details.Item_id = tbl_barcode_data.Item_id AND tbl_item_order_details.Order_id = tbl_barcode_data.Order_id AND tbl_item_order_details.Date = tbl_barcode_data.Date");
			//stmtSelect.setString(1, getdata.getDate());
			ResultSet rs = stmtSelect.executeQuery();
			
			while(rs.next()) {
				jsonObject = new JSONObject();
				
				jsonObject.put("barcode_id", rs.getString("Barcode_id"));
				jsonObject.put("order_id", rs.getString("Order_id"));
				jsonObject.put("item_id", rs.getString("Item_id"));
				jsonObject.put("user_id", rs.getString("User_id"));
				
				jsonArray.put(jsonObject);
			}

			return ParentObject.put("Response", jsonArray);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public JSONObject getBarcodeData(GetData getdata, Connection con) {
		JSONObject jsonObject=null;
		try {
			PreparedStatement stmtSelect = con.prepareStatement("SELECT `Order_id` FROM `tbl_item_order_details` WHERE Date = ? AND User_id = ?");
			stmtSelect.setString(1, getdata.getDate());
			stmtSelect.setString(2, getdata.getUserId());
			
			ResultSet rs = stmtSelect.executeQuery();
			
			while(rs.next()) {
				jsonObject = new JSONObject();
			
				jsonObject.put("order_id", rs.getString("Order_id"));
				
				jsonArray.put(jsonObject);
			}
			return ParentObject.put("Response", jsonArray);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
}
