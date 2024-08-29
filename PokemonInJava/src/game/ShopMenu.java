package game;

import java.util.ArrayList;
import java.util.Scanner;

import item.Defensive;
import item.Item;
import item.Offensive;
import item.Spell;
import main.Main;
import main.Print;
import user.User;

public class ShopMenu {
	
	Scanner sc = new Scanner(System.in);
	ArrayList<Item> item = Main.item;
	Print p = new Print();
	
	void offensiveMenu(User cur) {
		String inputBuy;
	
		p.offensive();
		System.out.printf("Your money is %d\n", cur.getMoney());
		p.offensiveBar();
		
		for (Item i : item) {
			if (i instanceof Offensive) {
				System.out.printf("|%-10s|%-30s|%-15s|%-10d|%-10.2f|%-10d|\n", i.getId(), i.getName(), "Offensive", i.getPrice(), ((Offensive) i).getDamage(), ((Offensive) i).getMaxUse());
			}
		}
		System.out.println("============================================================================================"); System.out.println();
		System.out.println();
		System.out.print("Input Item's ID ['Exit' to cancel] : ");
		inputBuy = sc.nextLine();
		if (inputBuy.equalsIgnoreCase("exit")) {
			return;
		}
		
		for (Item i : item) {
		    if (i instanceof Offensive) {
		        Offensive list = (Offensive) i;
		        if (cur.getItems() == null) {
		        	cur.setItems(new ArrayList<>());
		        	cur.getItems().add(list);
		        	p.ownedItem(cur);
		            System.out.println("Bought The Item");
		            System.out.println();
		            System.out.println("Enter To Continue...."); sc.nextLine();
		            cur.setMoney(cur.getMoney() - list.getPrice());
		            return;
		        } else {
			        if (inputBuy.equals(list.getId())) {
			            if (cur.getMoney() - list.getPrice() <= 0) {
			                System.out.println("You don't have enough money");
			                System.out.println("Enter To Continue...."); sc.nextLine();
			                return; 
			            }
			            for (Item userItem : cur.getItems()) {
			                if (userItem instanceof Offensive && userItem.getId().equals(list.getId())) {
			                    System.out.println("You already have the item and it is offensive type, resetting item's use amount");
			                    ((Offensive) userItem).setTimeUsed(0);
			                    System.out.println("Enter To Continue...."); sc.nextLine();
			                    return;
			                }
			            }
			            cur.getItems().add(list);
			            p.ownedItem(cur);
			            System.out.println("Bought The Item"); 
			            System.out.println();
			            System.out.println("Enter To Continue....");
			            cur.setMoney(cur.getMoney() - list.getPrice());
			            sc.nextLine();
			            return;
			        }
		        }
		    }
		}
		System.out.println("Item not found");
		System.out.println();
		System.out.println("Enter To Continue...."); sc.nextLine();
	}
	
	void defensiveMenu(User cur) {
		String inputBuy;
		
		p.offensive();
		System.out.printf("Your money is %d\n", cur.getMoney());
		p.offensiveBar();
		
		for (Item i : item) {
			if (i instanceof Defensive) {
				System.out.printf("|%-10s|%-30s|%-15s|%-10d|%-10.2f|%-10d|\n", i.getId(), i.getName(), "Defensive", i.getPrice(), ((Defensive) i).getDeflect(), ((Defensive) i).getMaxUse());
			}
		}
		System.out.println("============================================================================================"); System.out.println();
		System.out.println();
		System.out.print("Input Item's ID ['Exit' to cancel] : ");
		inputBuy = sc.nextLine();
		if (inputBuy.equalsIgnoreCase("exit")) {
			return;
		}
		
		for (Item i : item) {
		    if (i instanceof Defensive) {
		    	Defensive list = (Defensive) i;
		        if (cur.getItems() == null) {
		        	cur.setItems(new ArrayList<>());
		        	cur.getItems().add(list);
		        	p.ownedItem(cur);
		            System.out.println("Bought The Item");
		            System.out.println();
		            System.out.println("Enter To Continue...."); sc.nextLine();
		            cur.setMoney(cur.getMoney() - list.getPrice());
		            return;
		        } else {
			        if (inputBuy.equals(list.getId())) {
			            if (cur.getMoney() - list.getPrice() <= 0) {
			                System.out.println("You don't have enough money");
			                System.out.println("Enter To Continue...."); sc.nextLine();
			                return; 
			            }
			            for (Item userItem : cur.getItems()) {
			                if (userItem instanceof Offensive && userItem.getId().equals(list.getId())) {
			                    System.out.println("You already have the item and it is defensive type, resetting item's use amount");
			                    ((Offensive) userItem).setTimeUsed(0);
			                    System.out.println("Enter To Continue...."); sc.nextLine();
			                    return;
			                }
			            }
			            cur.getItems().add(list);
			            p.ownedItem(cur);
			            System.out.println("Bought The Item"); 
			            System.out.println();
			            System.out.println("Enter To Continue...."); sc.nextLine();
			            cur.setMoney(cur.getMoney() - list.getPrice());
			            return;
			        }
		        }
		    }
		}
		System.out.println("Item not found");
		System.out.println();
		System.out.println("Enter To Continue...."); sc.nextLine();
	}
	
	void spellMenu(User cur) {
		String inputBuy;
		
		p.spell();
		System.out.printf("Your money is %d\n", cur.getMoney());
		p.offensiveBar();
		
		for (Item i : item) {
			if (i instanceof Spell) {
				System.out.printf("|%-10s|%-30s|%-15s|%-10d|%-10.2f|%-10.2f|\n", i.getId(), i.getName(), "Spell", i.getPrice(), ((Spell) i).getDamage(), ((Spell) i).getMana());
			}
		}
		System.out.println("============================================================================================"); System.out.println();
		System.out.println();
		System.out.print("Input Item's ID ['Exit' to cancel] : ");
		inputBuy = sc.nextLine();
		if (inputBuy.equalsIgnoreCase("exit")) {
			return;
		}
		
		for (Item i : item) {
		    if (i instanceof Spell) {
		    	Spell list = (Spell) i;
		        if (cur.getItems() == null) {
		        	cur.setItems(new ArrayList<>());
		        	cur.getItems().add(list);
		        	p.ownedItem(cur);
		            System.out.println("Bought The Item");
		            System.out.println();
		            System.out.println("Enter To Continue...."); sc.nextLine();
		            cur.setMoney(cur.getMoney() - list.getPrice());
		            return;
		        } else {
			        if (inputBuy.equals(list.getId())) {
			            if (cur.getMoney() - list.getPrice() <= 0) {
			                System.out.println("You don't have enough money");
			                System.out.println("Enter To Continue...."); sc.nextLine();
			                return; 
			            }
			            for (Item userItem : cur.getItems()) {
			                if (userItem instanceof Spell && userItem.getId().equals(list.getId())) {
			                    System.out.println("You already have the item and it is spell type");
			                    System.out.println("Enter To Continue...."); sc.nextLine();
			                    return;
			                }
			            }
			            cur.getItems().add(list);
			            p.ownedItem(cur);
			            System.out.println("Bought The Item"); 
			            System.out.println();
			            System.out.println("Enter To Continue...."); sc.nextLine();
			            cur.setMoney(cur.getMoney() - list.getPrice());
			            return;
			        }
		        }
		    }
		}
		System.out.println("Item not found");
		System.out.println();
		System.out.println("Enter To Continue...."); sc.nextLine();
	}
	
	public void menu(User cur) {
		String opt;
		int input;
		do {
			do {
				try {
					p.clearScreen(); p.printShop();
					System.out.println("1. Buy Offensive Item");
					System.out.println("2. Buy Defensive Item");
					System.out.println("3. Buy Spells Item");
					System.out.println("4. Exit");
					System.out.print(">> ");
					opt = sc.nextLine();
					input = Integer.parseInt(opt);
				} catch (Exception e) {
					input = -1;
				}
				if (input < 1 || input > 4) {
					System.out.println("Invalid input. Please enter a valid integer."); 
					System.out.println();
					System.out.println("Enter To Continue...."); sc.nextLine();
				}
			} while (input < 1 || input > 4);
			p.clearScreen();
			switch (input) {
			case 1:
				offensiveMenu(cur);
				break;
			case 2:
				defensiveMenu(cur);
				break;
			case 3:
				spellMenu(cur);
				break;
			}
		} while (input != 4);
		
	}
	
}
