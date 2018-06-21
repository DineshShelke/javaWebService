package com.food.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ManageDatabase {
	
	public static void main(String[] args) {
		 ManageDatabase.getConnection();
	}
		
		static Logger log = Logger.getLogger(ManageDatabase.class.getName());
		public static Connection getConnection(){
			 Connection connection=null;
			try{
				Class.forName("com.mysql.jdbc.Driver");
				log.info("Driver Load");
				
				connection= DriverManager.getConnection("jdbc:mysql://localhost/food", "root","");
				//connection= DriverManager.getConnection("jdbc:mysql://localhost/hornplea_eyeshospital", "hornplea1","c=1G0gcwXR");
				
		
				
				log.info("Connection Created");
				System.out.println("Connection Created");
		
				
				/*Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");

				// Look up our data source
				DataSource ds;
				ds = (DataSource) envCtx.lookup("jdbc/eboxpro_eyeshospital");// localhos........
				//	ds = (DataSource) envCtx.lookup("jdbc/hornplea_eyeshospital");//server.......
				return ds.getConnection();*/
			
			
			} 
			catch (Exception se) {
				se.printStackTrace();
			}
			
			return connection;
		}
		
		public void closeConnection( Connection connection){
			
			if(connection!=null){
				try {
					connection.close();
				} 
				catch (SQLException e) {				
					e.printStackTrace();
				}
			}
		}

}


