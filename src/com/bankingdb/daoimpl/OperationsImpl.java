package com.bankingdb.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.bankingdb.dao.Operations;
import com.bankingdb.driver.Application;
import com.bankingdb.utility.DataConnection;

public class OperationsImpl implements Operations{


	Scanner sc=new Scanner(System.in);
	Application a=new Application();
	DataConnection d=new DataConnection();
	
	@Override
	public void withdrawal(long acc_num) {
		
		
		Connection con=d.connect();
		try {
			PreparedStatement preparedstatement=con.prepareStatement("update users set balance=(balance-?) where account_num=? and balance>?");
			System.out.println("Enter amount to be withdrawn");
			long amount=sc.nextLong();
			preparedstatement.setLong(1, amount);
			preparedstatement.setLong(2, acc_num);
			preparedstatement.setLong(3, amount);
			
			int i=preparedstatement.executeUpdate();
			if (i==1) {
				System.out.println("Withdrawn successful");
			} else {
				System.err.println("Insufficient balance");
			}
			a.operations(acc_num);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	@Override
	public void deposit(long acc_num) {
		

		Connection con=d.connect();
		try {
			PreparedStatement preparedstatement=con.prepareStatement("update users set balance=(balance+?) where account_num=? ");
			System.out.println("Enter amount to be deposited");
			long amount=sc.nextLong();
			preparedstatement.setLong(1, amount);
			preparedstatement.setLong(2, acc_num);
			
			
			int i=preparedstatement.executeUpdate();
			if (i==1) {
				System.out.println("Deposited successful");
			} else {
				System.err.println("Error Deposited");
			}
			
			a.operations(acc_num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
