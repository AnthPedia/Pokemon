package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import game.GameMenu;
import item.Item;
import monster.Monster;
import user.User;

public class Main {
	
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	
	int height = 300, weight = 300;
	char[][] globalMap = new char[height][weight];

	public static ArrayList<User> user = new ArrayList<>();
	public static ArrayList<Monster> monster = new ArrayList<>();
	public static ArrayList<Item> item = new ArrayList<>();
	
	FileManager f = new FileManager();
	Initialize i = new Initialize();
	GameMenu g = new GameMenu();
	Print p = new Print();
	
	boolean validation(String str) {
		if (str.isEmpty()) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if(!Character.isLetterOrDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	void register() {
		String mail, pass, local, domain, tld;
		boolean checkDot, checkAt, checkEmail, checkPassword, checkUnique;
		int atIdx, dotIdx;
		do {
			atIdx = -1; dotIdx = -1; checkEmail = true; checkDot = true; checkAt = true; checkPassword = true; checkUnique = true;
			p.registerLogo();
			System.out.println("Input 'Exit' to quit from Login Menu");
			System.out.println();
			System.out.print("Input new email: ");
			mail = sc.nextLine();
			if(mail.equalsIgnoreCase("Exit")) {
				return;
			}
			
			atIdx = mail.lastIndexOf('@');
			dotIdx = mail.lastIndexOf('.');

			if (!mail.contains("@")) {
				checkAt = false;
			}
			if (!mail.contains(".")) {
				checkDot = false;
			}
			if (mail == null || mail.isEmpty()) {
				checkEmail = false;
			}
			if (atIdx == -1 || atIdx == 0 || dotIdx == 0|| dotIdx == -1 || atIdx == mail.length() - 1) {
				checkEmail = false;
			} else {
				local = mail.substring(0, atIdx);
				if (!validation(local)) {
					checkEmail = false;
				}
				domain = mail.substring(atIdx + 1, dotIdx);
				if (!validation(domain)) {
					checkEmail = false;
				}
				tld = mail.substring(dotIdx + 1);
				if (!validation(tld)) {
					checkEmail = false;
				}
			}
			
			System.out.print("Input new password: ");
			pass = sc.nextLine();
			if (pass.equalsIgnoreCase("Exit")) {
				return;
			}
			
			if (pass.length() < 8 || pass.length() > 30) {
				checkPassword = false;
			}
			
			for (int i = 0; i < user.size(); i++) {
				if(mail.equals(user.get(i).getEmail())) {
					checkUnique = false; break;
				}
			}
			
			if (!checkAt) {
				System.out.printf("There must be a '@' in the Email\n\nEnter To Continue...."); sc.nextLine();
				p.clearScreen();
			} else if(!checkDot) {
				System.out.printf("There must be a '.' in the Email\n\nEnter To Continue...."); sc.nextLine();
				p.clearScreen();
			} else if(!checkEmail) {
				System.out.printf("Invalid Email\n\nEnter To Continue...."); sc.nextLine();
				p.clearScreen();
			} else if(!checkPassword) {
				System.out.printf("Password Length must be between 8 and 30\n\nEnter To Continue...."); sc.nextLine();
				p.clearScreen();
			} else if(!checkUnique) {
				System.out.printf("Email is already used\n\nEnter To Continue...."); sc.nextLine();
				p.clearScreen();
			}
		} while (!checkAt || !checkDot || !checkEmail || !checkPassword || !checkUnique);
		f.addNewUser(mail, pass);
		System.out.printf("Register Success\n\nEnter To Continue...."); sc.nextLine();
	}
	
	void login() {
		String mail, pass;
		boolean found;
		do {
			found = false;
			p.clearScreen(); p.loginLogo();
			System.out.println("Input 'Exit' to quit from Login Menu");
			System.out.println();
			System.out.print("Input email: ");
			mail = sc.nextLine();
			if (mail.equalsIgnoreCase("Exit")) return;
			
			System.out.print("Input password: ");
			pass = sc.nextLine();
			if (pass.equalsIgnoreCase("Exit")) return;
			
			for (int i = 0; i < user.size(); i++) {
				User cur = user.get(i);
				if(mail.equals(cur.getEmail()) && pass.equals(cur.getPassword())) {
					System.out.printf("Logging in....\n\nEnter To Continue...."); sc.nextLine();
					g.menu(globalMap, cur);
					found = true;
				}
			}
			
			if (!found) {
				System.out.printf("Incorrect credential\n\nEnter To Continue..."); sc.nextLine();
			}
		} while (!found);
	}
	
	public Main() {
		p.splashScreen(); sc.nextLine();
		f.readItem(); f.readMonster(); f.readUser();
		i.initMap(globalMap, height, weight);
		
		String opt;
		int input;
		do {
			do {
				try {
					p.clearScreen(); p.loginLogo();
					System.out.println("1. Login");
					System.out.println("2. Register");
					System.out.println("3. Exit");
					System.out.print(">> ");
					opt = sc.nextLine();
					input = Integer.parseInt(opt);
				} catch (Exception e) {
					input = -1;
				}
				if (input < 1 || input > 3) {
					System.out.println("Invalid input. Please enter a valid integer."); 
					System.out.println();
					System.out.println("Enter To Continue...."); sc.nextLine();
				}
			} while (input < 1 || input > 3);
			p.clearScreen();
			switch (input) {
			case 1:
				login();
				break;
			case 2:
				register();
				break;
			}
		} while (input != 3);
	}

	public static void main(String[] args) {
		new Main();
	}
}
