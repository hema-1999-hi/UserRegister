package userRegistration.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import userRegistration.model.SaveOrUpdateException;
import userRegistration.model.User;
import userRegistration.servlet.DisplayServlet;
import userRegistration.util.ConnectionUtill;

public class UserService {
	private static final Logger log = LogManager.getLogger(DisplayServlet.class);

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		// step 1: establishing a connection
		try {
			Connection con = ConnectionUtill.getConnection();
			log.info("connection success..");
			// step 2 create a statement using connection
			PreparedStatement pstmt = con.prepareStatement("select id,Name,Email,Phonenum,Country from APP.Users");
			log.info(pstmt);
			// step 3 exceute query
			ResultSet rs = pstmt.executeQuery();
			// step 4 reterive the data
			while (rs.next()) {
				User u = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("phonenum"), rs.getString("country"));
				users.add(u);
				System.out.println(u);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public void addUser(User user){
		try {
			Connection con = ConnectionUtill.getConnection();
			PreparedStatement	pstmt = con.prepareStatement("insert into APP.Users (id,Name,Email,Phonenum,Country) values(?, ?, ?, ?, ?)");
			log.info("statement created");
			pstmt.setInt(1, getNextId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPhonenum());
			pstmt.setString(5, user.getCountry());
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public  boolean deleteUser(int id){
		boolean rowDeleted = false;
		try {
			Connection	con = ConnectionUtill.getConnection();
			log.info("Connection success..");
			PreparedStatement	pstmt = con.prepareStatement("delete from App.Users where id=? ");
			log.info("Statement created");
			pstmt.setInt(1, id);
			log.info(pstmt);
			pstmt.executeUpdate();
			rowDeleted = pstmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowDeleted;
		
	}
	
	public  User getUser(int id){
		User user = null;
		try{
			//establish connection
			Connection con = ConnectionUtill.getConnection();
			//create statment using connection
			PreparedStatement pstmt = con.prepareStatement("select * from APP.Users where id=?");
			pstmt.setInt(1, id);
			log.info(pstmt);
			//execute query
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int Id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phonenum = rs.getString("phonenum");
				String country = rs.getString("country");
				user = new User(Id, name, email, phonenum, country);
				log.info(user);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
	public  boolean updateUser(User user) throws SaveOrUpdateException{
		boolean rowUpdated = false;
		try{
			log.info("updateUser().....Start");
			Connection con = ConnectionUtill.getConnection();
			PreparedStatement pstmt = con.prepareStatement("UPDATE APP.Users SET Name=?, Email=?, Phonenum=?, Country=? where id=?");
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPhonenum());
			pstmt.setString(4, user.getCountry());
			pstmt.setInt(5, user.getId());
			
			rowUpdated = pstmt.executeUpdate() > 0 ;
			log.info("updateUser().....before exception ");

			if (user.getId()>1) {
				throw new Exception("Sample exception ");
			}

		}catch (Exception e) {
			log.error("Exception occur - Exception : "+e.getMessage());
			//TODO get app message from prop file 
			throw new SaveOrUpdateException(e.getMessage(), "Something went wrong! Dont worry! contact app IT  help desk! \n Details: Exception while update user data with id : "+user.getId());
		}
		log.info("updateUser().....End");

		return rowUpdated;
		
	}

	/**
	 * get the last used id and increment by 1 and return the same 
	 * 
	 * @return
	 * @throws Exception 
	 */
	public   int getNextId() throws Exception {
		Connection con = ConnectionUtill.getConnection();
		log.info("connection sucess");
		 int id =0;
		PreparedStatement pstmt1 = con.prepareStatement("select max(id) from APP.Users");
		ResultSet rs = pstmt1.executeQuery();
		if (rs.next()) {
			id = rs.getInt(1);
			id++;
		}
		return id;
	}

	

}
