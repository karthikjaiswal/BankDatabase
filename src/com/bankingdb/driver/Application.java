package com.bankingdb.driver;

import java.util.Scanner;

import com.bankingdb.dao.Login;
import com.bankingdb.dao.Operations;
import com.bankingdb.dao.Registration;
import com.bankingdb.daoimpl.LoginImpl;
import com.bankingdb.daoimpl.OperationsImpl;
import com.bankingdb.daoimpl.RegistrationImpl;

public class Application {
	static Scanner sc=new Scanner(System.in);
	
	 static Application a=new Application();
	
	public void operations(long acc_num)
	{
		Operations o=new OperationsImpl();
		System.out.println("==========Choose Option===========");
		System.out.println("1. Withdrawl \n 2. Deposit \n  3. Exit");
		int n=sc.nextInt();
		
		switch(n)
		{
		case 1:
			o.withdrawal(acc_num);
			break;
		case 2:
			o.deposit(acc_num);
			break;
		case 3:
			System.exit(0);
		}
		
		
	}
	

	public static void main(String[] args) {
		
		
		
		int n=a.choice();
	
		if(n==1)
		{
		Registration r=new RegistrationImpl();
		r.registration();
		a.choice();
		}
		else if(n==2)
		{
			
		Login l=new LoginImpl();
		long acc_num=l.login();
		a.operations(acc_num);
		
		}
		else
		{
		
		System.exit(0);
	
		}
		}


	public int choice() {
	
		System.out.println("=========Welcome to Bank=======");
		System.out.println("1. Registration \n2. Login \n3. Exit");
		
		int n=sc.nextInt();
		return n;
		
	}


}
