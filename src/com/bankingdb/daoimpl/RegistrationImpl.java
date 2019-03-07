package com.bankingdb.daoimpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import com.bankingdb.dao.Registration;
import com.bankingdb.model.User;
import com.bankingdb.utility.DataConnection;

public class RegistrationImpl implements Registration{

	User user=new User();
	DataConnection d=new DataConnection();
	Connection con=d.connect();
	
	@Override
	public void registration() {
		
		try {
			PreparedStatement preparedStatement=con.prepareStatement("insert into users values(?,?,?,?,?,?,?,?,?,?)");
			
			
			getUserInfo();
			
			
			preparedStatement.setLong(1,user.getAccountNo());
			preparedStatement.setString(2,user.getFirstName());
			preparedStatement.setString(3,user.getLastName());
			preparedStatement.setLong(4, user.getAadharCardNo());
			preparedStatement.setString(5,user.getAccountType());
			preparedStatement.setString(6, user.getPanCard());
			preparedStatement.setLong(7, user.getPhoneNo());
			preparedStatement.setString(8, user.getAddress());
			preparedStatement.setString(9,user.getPassword());
			preparedStatement.setLong(10, 0);
			
			int i=preparedStatement.executeUpdate();
			if (i==1) {
				System.out.println("Registered successfully with account number"+user.getAccountNo());
			} else {
				System.err.println("Enter proper details");
				registration();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void getUserInfo() {
		Random r=new Random();
		Scanner scanner=new Scanner(System.in);
		System.out.println("=============Registration Page============");
		
		System.out.println("First name");
		user.setFirstName(scanner.next());
		System.out.println("Last name");
		user.setLastName(scanner.next());
		System.out.println("Aadhar number");
		user.setAadharCardNo(scanner.nextLong());
		System.out.println("Account Type");
		user.setAccountType(scanner.next());
		System.out.println("Pancar number");
		user.setPanCard(scanner.next());
		System.out.println("Phone number");
		user.setPhoneNo(scanner.nextLong());
		System.out.println("Address");
		user.setAddress(scanner.next());
		System.out.println("Create password");
		user.setPassword(scanner.next());
		
		if(validateUser(user.getAadharCardNo()))
		{
			user.setAccountNo(Math.abs(r.nextInt()));
		}
		else
		{
			System.err.println("User already exists ");
			registration();
		}
			
		
	}
	
	public boolean validateUser(long aadharCardNo) {
		boolean check = true;
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from users");
			while(rs.next())
			{
				if(rs.getLong(4)==aadharCardNo)
				{
				check = false;
				break;
				}
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
		
	}

	
}
