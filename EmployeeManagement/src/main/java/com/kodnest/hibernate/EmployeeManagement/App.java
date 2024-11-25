package com.kodnest.hibernate.EmployeeManagement;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
	private static Scanner sc = new Scanner(System.in);
	private static Configuration conf = new Configuration().configure("hibernate.cfg.xml");
    public static void main(String[] args) {
    	
    	System.out.println("EMPLOYEE MANAGEMENT SYSTEM");
    	
    	while(true) {
			System.out.println("press 1------> READ EMPLOYEE");
			System.out.println("press 2------> ADD EMPLOYEE");
			System.out.println("press 3------> DELETE EMPLOYEE");
			System.out.println("press 4------> UPDATE EMPLOYEE");

			int choice = sc.nextInt();

			switch (choice) {
			case 1-> readEmployee();
			case 2-> createEmployee();
			case 3-> deleteEmployee();
			case 4-> updateEmployee();
			default -> {
				System.out.println("Program Terminated...");
				return;
			}
			}
    	}
    	
    }
    
    public static void readEmployee() {
    	
    	SessionFactory factory = conf.buildSessionFactory();
    	Session session = factory.openSession();
    	Transaction transaction = session.beginTransaction();
    	
    	System.out.println("Enter Employee ID to show details");
    	int id = sc.nextInt();
    	
    	Employee emp = session.get(Employee.class, id);
    	
    	if (emp != null) {
    		System.out.println("Employee ID: " + emp.getId());
    		System.out.println("Employee Name: " + emp.getName());
    		System.out.println("Employee Salary: " + emp.getSalary());
    		System.out.println("Employee Phone No: " + emp.getPhone());
    		System.out.println("Employee Email: " + emp.getEmail());
    		System.out.println("Employee Type: " + emp.getType());
    	} else {
    		System.out.println("Employee with ID "+ id + " not found.");
    	}
    	
    	transaction.commit();
    	session.close();
    	
    }
    
    public static void createEmployee() {
    	System.out.println("Entering Employee Details");
    	SessionFactory factory = conf.buildSessionFactory();
    	Session session = factory.openSession();
    	System.out.println();
    	
    	System.out.println("Enter Employee ID: ");
    	int id = sc.nextInt();
    	System.out.println("Enter Employee Name: ");
    	String name = sc.next();
    	System.out.println("Enter Employee Salary: ");
    	int salary = sc.nextInt();
    	System.out.println("Enter Employee Phone No: ");
    	int phone = sc.nextInt();
    	System.out.println("Enter Employee Email: ");
    	String email = sc.next();
    	System.out.println("Enter Employee Type(Intern/Full-Time): ");
    	String type = sc.next();
    	
    	Transaction transaction = session.beginTransaction();
    	Employee emp = new Employee();
    	emp.setId(id);
    	emp.setName(name);
    	emp.setSalary(salary);
    	emp.setPhone(phone);
    	emp.setEmail(email);
    	emp.setType(type);
    	session.persist(emp);
    	transaction.commit();
    	session.close();
    }
    
    public static void deleteEmployee() {
    	System.out.println("DELETING EMPLOYEE DETAILS");
    	System.out.println("Enter Employee ID to delete: ");
    	int id = sc.nextInt();
    	SessionFactory factory = conf.buildSessionFactory();
    	Session session = factory.openSession();
    	Transaction transaction = session.beginTransaction();
    	
    	Employee emp = session.get(Employee.class, id);
    	if (emp != null) {
    		session.remove(emp);
    	} else {
    		System.out.println("Employee record not present with ID "+ id + " to DELETE");
    	}
    	
    	transaction.commit();
    	session.close();
    }
    
    public static void updateEmployee() {
    	System.out.println("UPDATING EMPLOYEE DETAILS");
    	System.out.println();
    	System.out.println("Enter Employee ID to update: ");
    	int id = sc.nextInt();
    	
    	SessionFactory factory = conf.buildSessionFactory();
    	Session session = factory.openSession();
    	Transaction transaction = session.beginTransaction();
    	
    	Employee emp = session.get(Employee.class, id);
    	
    	if (emp != null) {
    		System.out.println("Employee found...");
    		System.out.println("Employee ID: " + emp.getId());
    		System.out.println("Employee Name: " + emp.getName());
    		System.out.println("Employee Salary: " + emp.getSalary());
    		System.out.println("Employee Phone No: " + emp.getPhone());
    		System.out.println("Employee Email: " + emp.getEmail());
    		System.out.println("Employee Type: " + emp.getType());
    		
    		
    		System.out.println();
    		System.out.println("Do you want to update Name? (yes/no)");
    		if (sc.next().equalsIgnoreCase("yes")) {
    			System.out.println("Enter new Name: ");
    			emp.setName(sc.next());
    		}
    		
    		System.out.println("Do you want to update Salary? (yes/no)");
    		if (sc.next().equalsIgnoreCase("yes")) {
    			System.out.println("Enter new Salary: ");
    			emp.setSalary(sc.nextInt());
    		}
    		
    		System.out.println("Do you want to update Phone No? (yes/no)");
    		if (sc.next().equalsIgnoreCase("yes")) {
    			System.out.println("Enter new Phone No: ");
    			emp.setPhone(sc.nextInt());
    		}
    		
    		System.out.println("Do you want to update Email? (yes/no)");
    		if (sc.next().equalsIgnoreCase("yes")) {
    			System.out.println("Enter new Email: ");
    			emp.setEmail(sc.next());
    		}
    		
    		System.out.println("Do you want to update Employee Type? (yes/no)");
    		if (sc.next().equalsIgnoreCase("yes")) {
    			System.out.println("Enter new employee type(Intern/Full-Time): ");
    			emp.setType(sc.next());
    		}
    		session.update(emp);
    		System.out.println("Employee details updated successfully..");

    	} else {
    		System.out.println("Employee with ID "+ id + " not found.");
    	}
    	transaction.commit();
    	session.close();
    	
    	
    }
}
