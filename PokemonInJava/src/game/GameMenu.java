package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import main.FileManager;
import main.Main;
import main.Print;
import user.User;

public class GameMenu {
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	
	ArrayList<User> user = Main.user;
	
	FightMenu fight = new FightMenu();
	ShopMenu shop = new ShopMenu();
	FileManager f = new FileManager();
	Print p = new Print();
	
	User current;
	boolean play;
	
	void movement(char[][] map, int y, int x) {
		int newY = current.getY() + y;
		int newX = current.getX() + x;
		char prev = map[current.getY()][current.getX()];
		if (map[newY][newX] == ' ') {
			map[current.getY()][current.getX()] = prev;
		} else if (map[newY][newX] == 'v' || map[newY][newX] == 'V') {
			map[current.getY()][current.getX()] = prev;
			int fightChance = rand.nextInt(101);
			if (fightChance <= 30) {
				fight.fightStart(current);
				map[current.getY()][current.getX()] = ' ';
			} else {
				map[current.getY()][current.getX()] = prev;
			}
		} else if (map[newY][newX] == 'O') {
			int newMoney = current.getMoney() + 50;
			current.setMoney(newMoney);
			map[newY][newX] = ' ';
		} else if (map[newY][newX] == '#') {
			return;
		}
		current.setY(newY);
		current.setX(newX);
	}
	
	void playerChoice(char[][] map, char inp) {
		switch (Character.toLowerCase(inp)) {
		case 'w':
			movement(map, -1, 0);
			break;
		case 'a':
			movement(map, 0, -1);
			break;
		case 's':
			movement(map, 1, 0);
			break;
		case 'd':
			movement(map, 0, 1);
			break;
		case 'i':
			p.clearScreen(); p.itemsList(); p.ownedItem(current);
			System.out.println();
			System.out.println("Press Enter To Continue...."); sc.nextLine();
			break;
		case 'z':
			shop.menu(current);
			break;
		case 'e':
			play = false;
			break;
		default:
			break;
		}
	}
	
	void games(char[][] map, int y, int x) {
		play = true;
		String opt;
		char inp = ' ';
		while (play) {
			p.printDisplay(map, current, current.getY(), current.getX(), y, x);
			System.out.print(">> ");
			try {
				opt = sc.nextLine();
				inp = opt.charAt(0);
			} catch (Exception e) {
				inp = '0';
			}
			playerChoice(map, inp);
			p.clearScreen();
		}
		f.updateUserData();
		p.exit(); sc.nextLine();
	}
	
	public void menu(char[][] map, User cur) {
		current = cur;
		String opt;
		int input;
		int displayY = 15 / 2, displayX = 35 / 2;
		map[150][150] = ' ';
		if(!cur.isLogged()) {
			cur.setX(150); cur.setY(150);
			cur.setLogged(true);
		}
		
		do {
			do {
				try {
					p.clearScreen(); p.gameLogo();
					System.out.println("1. Start Game");
					System.out.println("2. Game Guide");
					System.out.println("3. Exit Game");
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
				games(map, displayY, displayX);
				break;
			case 2:
				p.guide(); sc.nextLine();
				break;
			}
		} while (input != 3);
		f.updateUserData();
		p.clearScreen(); p.exit();
	}

}
