package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import item.Defensive;
import item.Offensive;
import item.Spell;
import main.FileManager;
import main.Main;
import main.Print;
import monster.Agility;
import monster.Intelligence;
import monster.Monster;
import monster.Strength;
import user.User;

public class FightMenu {

	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	FileManager f = new FileManager();
	Print p = new Print();
	
	User user;
	ArrayList<Monster> generateEnemy = Main.monster;
	boolean turns, win, lose;
	
	void dataMonster(Monster e) {
		System.out.println("Monster Information");
		if (e instanceof Strength) {
			Strength s = (Strength) e;
			System.out.println("========================================");
			System.out.printf( "|Name            : %-20s|\n", s.getName());
			System.out.printf( "|Health          : %-20.2f|\n", s.getHealth());
			System.out.printf( "|Damage          : %-20.2f|\n", s.getDamage());
			System.out.printf( "|Armor           : %-20.2f|\n", s.getArmor());
			System.out.println("========================================");
		} else if (e instanceof Intelligence) {
			Intelligence i = (Intelligence) e;
			System.out.println("========================================");
			System.out.printf( "|Name            : %-20s|\n", i.getName());
			System.out.printf( "|Health          : %-20.2f|\n", i.getHealth());
			System.out.printf( "|Damage          : %-20.2f|\n", i.getDamage());
			System.out.println("========================================");
		} else if (e instanceof Agility) {
			Agility a = (Agility) e;
			System.out.println("========================================");
			System.out.printf( "|Name            : %-20s|\n", a.getName());
			System.out.printf( "|Health          : %-20.2f|\n", a.getHealth());
			System.out.printf( "|Damage          : %-20.2f|\n", a.getDamage());
			System.out.printf( "|Critical        : %-20.2f|\n", a.getCritical());
			System.out.println("========================================");
		}
	}
	
	void dataPlayer() {
		System.out.println("Player Information " + user.getEmail());
		System.out.println("========================================");
		System.out.printf( "|Health          : %-20.2f|\n", user.getHealth());
		System.out.printf( "|Money           : %-20d|\n", user.getMoney());
		System.out.printf( "|Mana            : %-20.2f|\n", user.getMana());
		System.out.printf( "|Base Damage     : %-20.2f|\n", user.getAttack());
		System.out.println("========================================");
		if (user.getItems() != null) {
			System.out.println("Item Information");
			p.ownedItem(user);
		}
	}
	
	void basicAttack(Monster enemy) {
		p.clearScreen(); p.attacking();
		System.out.printf("Attacking %s\n", enemy.getName());
		System.out.printf("%s got damage %.2f\n", enemy.getName(), user.getAttack());
		System.out.println();
		System.out.println("Enter To Continue...."); sc.nextLine();
		enemy.setHealth(enemy.getHealth() - user.getAttack());
	}
	
	void offensiveAttack(Monster e, Offensive o) {
		p.clearScreen(); p.attacking();
		o.setTimeUsed(o.getTimeUsed() + 1);
		System.out.printf("Attacking %s with %s\n", e.getName(), o.getName());
		System.out.printf("%s was used. There is %d times left to use this item\n", o.getName(), o.getMaxUse() - o.getTimeUsed());
		System.out.println();
		System.out.printf("%s Attacked with %.2f amount of damage\n", e.getName(), o.getDamage() + user.getAttack());
		System.out.println();
		if (e instanceof Strength) {
			Strength es = (Strength) e;
			System.out.printf("But attack got deflected by %.2f because of armor\n", es.getArmor());
			double reduceDamage = o.getDamage() + user.getAttack() - es.getArmor();
			e.setHealth(e.getHealth() - reduceDamage);
		} else {
			e.setHealth(e.getHealth() - (o.getDamage() + user.getAttack()));
		}
		System.out.println("Updated Monster Information");
		dataMonster(e);
		System.out.println();
	}
	
	void spellAttack(Monster e, Spell s) {
		p.clearScreen(); p.attacking();
		System.out.printf("Attacking %s with %s\n", e.getName(), s.getName());
		System.out.printf("Used %.2f of mana\n", s.getMana());
		user.setMana(user.getMana() - s.getMana());
		System.out.println();
		System.out.printf("%s Attacked with %.2f amount of damage\n", e.getName(), s.getDamage() + user.getAttack());
		if (e instanceof Strength) {
			Strength es = (Strength) e;
			System.out.printf("But attack got deflected by %.2f because of armor\n", es.getArmor());
			double reduceDamage = s.getDamage() + user.getAttack() - es.getArmor();
			e.setHealth(e.getHealth() - reduceDamage);
		} else {
			e.setHealth(e.getHealth() - (s.getDamage() + user.getAttack()));
		}
		System.out.println();
		System.out.println("Updated Monster Information");
		dataMonster(e);
		System.out.println();
	}
	
	void itemAttack(Monster enemy) {
		String search;
		boolean found = false;
		
		do {
			System.out.println("=========================================================================================================");
			System.out.println("|ID        |Name                          |Type           |Price     |Damage    |Max Use/Mana|Use Left  |");
			System.out.println("=========================================================================================================");
			for (int i = 0; i < user.getItems().size(); i++) {
				if (user.getItems().get(i) instanceof Offensive) {
					Offensive off = (Offensive) user.getItems().get(i);
					System.out.printf("|%-10s|%-30s|%-15s|%-10d|%-10.2f|%-12d|%-10d|\n", off.getId(), off.getName(), "Offensive", off.getPrice(), off.getDamage(), off.getMaxUse(), off.getMaxUse() - off.getTimeUsed());
				} else if (user.getItems().get(i) instanceof Spell) {
					Spell sp = (Spell) user.getItems().get(i);
					System.out.printf("|%-10s|%-30s|%-15s|%-10d|%-10.2f|%-12.2f|%-10s|\n", sp.getId(), sp.getName(), "Spells", sp.getPrice(), sp.getDamage(), sp.getMana(), "-");
				}
			}
			System.out.println("=========================================================================================================");
			System.out.println("Choose Item");
			System.out.print("Input Item's ID : ");
			search = sc.nextLine();
			
			for (int i = 0; i < user.getItems().size(); i++) {
				if (search.equals(user.getItems().get(i).getId())) {
					if (user.getItems().get(i) instanceof Offensive) {
						Offensive o = (Offensive) user.getItems().get(i);
						offensiveAttack(enemy, o);
						if (o.checkMaxUse(o.getTimeUsed(), o.getMaxUse()) == true) {
							user.getItems().remove(i);
						}
						System.out.println("Enter To Continue...."); sc.nextLine();
						found = true;
					} else if (user.getItems().get(i) instanceof Spell) {
						Spell s = (Spell) user.getItems().get(i);
						if (user.getMana() - s.getMana() < 0) {
							System.out.println("You don't have enough mana");
							System.out.println("Enter To Continue...."); 
							found = false;
						} else {
							spellAttack(enemy, s);
							System.out.println("Enter To Continue...."); sc.nextLine();
							found = true;
						}
					}
				}
			}
		} while (!found);
	}
	
	void storeMana() {
		System.out.println("Storing mana......");
		System.out.println("Added 10.00 mana");
		System.out.println();
		System.out.println("Enter To Continue...."); sc.nextLine();
		user.setMana(user.getMana() + 10);
	}
	
	void itemDefend(Monster enemy) {
		String target;
		boolean found = false;
		System.out.println("=======================================================================================================");
		System.out.println("|ID        |Name                          |Type           |Price     |Damage    |Max Use   |Use Left  |");
		System.out.println("=======================================================================================================");
		for (int i = 0; i < user.getItems().size(); i++) {
			if (user.getItems().get(i) instanceof Defensive) {
				Defensive def = (Defensive) user.getItems().get(i);
				System.out.printf("|%-10s|%-30s|%-15s|%-10d|%-10.2f|%-10d|%-10d|\n", def.getId(), def.getName(), "Defensive" , def.getPrice() , def.getDeflect(), def.getMaxUse(), def.getTimeUsed());
			}
		}
		System.out.println("=======================================================================================================");
		
		do {
			System.out.print("Input Item's ID : ");
			target = sc.nextLine();
			for (int i = 0; i < user.getItems().size(); i++) {
				if (target.equals(user.getItems().get(i).getId()) && user.getItems().get(i) instanceof Defensive) {
					Defensive def = (Defensive) user.getItems().get(i);
					if (enemy instanceof Agility) {
						Agility a = (Agility) enemy;
						double critDamage = a.getDamage() * a.getCritical();
						System.out.printf("%s is attacking with a critical damage of %.2f\n", a.getName(), critDamage);
						System.out.printf("%s is equipped\n", def.getName());
						def.setTimeUsed(def.getTimeUsed() + 1);
						System.out.printf("%s was used. There is %d times left to use this item\n", def.getName(), def.getMaxUse() - def.getTimeUsed());
						System.out.println();
						System.out.printf("Receive Damage %.2f, but deflected %.2f using %s\n", critDamage, def.getDeflect(), def.getName());
						if (def.checkMaxUse(def.getTimeUsed(), def.getMaxUse()) == true) {
							user.getItems().remove(i);
						}
						user.setHealth(user.getHealth() + (def.getDeflect() - critDamage));
					} else if (enemy instanceof Strength) {
						Strength s = (Strength) enemy;
						System.out.printf("%s is attacking with a critical damage of %.2f\n", s.getName(), s.getDamage());
						System.out.printf("%s is equipped\n", def.getName());
						def.setTimeUsed(def.getTimeUsed() + 1);
						System.out.printf("%s was used. There is %d times left to use this item\n", def.getName(), def.getMaxUse() - def.getTimeUsed());
						System.out.println();
						System.out.printf("Receive Damage %.2f, but deflected %.2f using %s\n", s.getDamage(), def.getDeflect(), def.getName());
						if (def.checkMaxUse(def.getTimeUsed(), def.getMaxUse()) == true) {
							user.getItems().remove(i);
						}
						user.setHealth(user.getHealth() + (def.getDeflect() - s.getDamage()));
					} else if (enemy instanceof Intelligence) {
						Intelligence in = (Intelligence) enemy;
						double ability = in.specialSkill(30 + rand.nextInt(20));
						System.out.printf("%s is attacking with a base damage of %.2f\n", in.getName(), in.getDamage());
						System.out.printf("%s is an Intelligence hero, using skill gave bonus damage %.2f\n", in.getName(), ability);
						System.out.println();
						System.out.printf("%s is equipped\n", def.getName());
						def.setTimeUsed(def.getTimeUsed() + 1);
						System.out.printf("%s was used. There is %d times left to use this item\n", def.getName(), def.getMaxUse() - def.getTimeUsed());
						System.out.println();
						System.out.printf("Receive Damage %.2f, but deflected %.2f using %s\n", in.getDamage() + ability, def.getDeflect(), def.getName());
						if (def.checkMaxUse(def.getTimeUsed(), def.getMaxUse()) == true) {
							user.getItems().remove(i);
						}
						user.setHealth(user.getHealth() - (in.getDamage() + ability));
					}
					System.out.println();
					System.out.println("Enter To Continue...."); sc.nextLine();
					found = true;
				}
			}
		} while (!found);
	}
	
	void monsterAttack(Monster enemy) {
		String choice = "no";
		System.out.println("Monster is going to attack");
		if (user.getItems() != null) {
			for (int i = 0; i < user.getItems().size(); i++) {
				if (user.getItems().get(i) instanceof Defensive) {
					do {
						System.out.println("Do you want to use your defensive item");
						System.out.print("Yes | No [Case Insensitive] : ");
						choice = sc.nextLine();
					} while (!choice.equalsIgnoreCase("yes") && !choice.equalsIgnoreCase("no"));
					break;
				}
			}
		}
		
		if (choice.equalsIgnoreCase("yes")) {
			itemDefend(enemy);
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println();
			if (enemy instanceof Agility) {
				Agility a = (Agility) enemy;
				double critDamage = a.getDamage() * a.getCritical();
				System.out.printf("%s is attacking with a critical damage of %.2f\n", a.getName(), critDamage);
				System.out.printf("Receive Damage %.2f\n", critDamage);
				user.setHealth(user.getHealth() - critDamage);
			} else if (enemy instanceof Strength) {
				Strength s = (Strength) enemy;
				System.out.printf("%s is attacking with a critical damage of %.2f\n", s.getName(), s.getDamage());
				System.out.printf("Receive Damage %.2f\n", s.getDamage());
				user.setHealth(user.getHealth() - s.getDamage());
			} else if (enemy instanceof Intelligence) {
				Intelligence i = (Intelligence) enemy;
				double ability = i.specialSkill(30 + rand.nextInt(20));
				System.out.printf("%s is attacking with a base damage of %.2f\n", i.getName(), i.getDamage());
				System.out.printf("%s is an Intelligence hero, using skill gave bonus damage %.2f\n", i.getName(), ability);
				System.out.println();
				System.out.printf("Receive Damage %.2f\n", i.getDamage() + ability);
				user.setHealth(user.getHealth() - (i.getDamage() + ability));
			} 
			System.out.println();
			System.out.println("Enter To Continue...."); sc.nextLine();
		}
	}
	
	public void fightStart(User player) {
		win = true; lose = true;
		user = player;
		p.clearScreen(); p.fight();
		Monster enemy = generateEnemy.get(rand.nextInt(generateEnemy.size() - 1));
		System.out.println("Welcome to the Fight Scene");
		System.out.println("You are fighting " + enemy.getName());
		System.out.println("");
		System.out.println("Enter To Continue...."); sc.nextLine();
		int turnRandom = rand.nextInt(2), input;
		String opt;
		
		if (turnRandom == 0) {
			turns = true;
		} else {
			turns = false;
		}
		
		while (win && lose) {
			p.clearScreen();
			if (turns) {
				do {
					try {
						p.clearScreen(); p.playerTurn();
						dataMonster(enemy);
						System.out.println();
						System.out.println();
						dataPlayer();
						System.out.println();
						System.out.println("Your Choice");
						System.out.println();
						System.out.println("1. Pure Attack");
						System.out.println("2. Attack With Item");
						System.out.println("3. Store Mana");
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
					basicAttack(enemy);
					turns = false;
					break;
				case 2:
					if (user.getItems() == null) {
						System.out.println("You dont have any item offensive currently");
						System.out.println("Enter To Continue...."); sc.nextLine();
						turns = true;
						break;
					}
					itemAttack(enemy);
					turns = false;
					break;
				case 3:
					storeMana();
					turns = false;
					break;
				}
			} else {
				p.monsterTurn();
				monsterAttack(enemy);
				turns = true;
			}
			if (enemy.getHealth() <= 0) {
				win = false;
			}
			if (player.getHealth() <= 0) {
				lose = false;
			}
			p.clearScreen(); p.information();
			System.out.println();
			dataMonster(enemy); System.out.println(); System.out.println();
			dataPlayer();
			System.out.println("Enter To Continue...."); sc.nextLine();
		}
		
		if (!win) {
			p.clearScreen(); p.win();
			System.out.printf("%s has been killed\n", enemy.getName());
			System.out.println("Enter To Continue...."); sc.nextLine();
			user.setMoney(user.getMoney() + 30);
			Main.monster = null; f.readMonster();
		}
		if (!lose) {
			p.clearScreen(); p.lose();
			System.out.println("You have been killed");
			System.out.println("");
			System.out.println("Resetting your player information");
			System.out.println("Enter To Continue...."); sc.nextLine();
			user.setHealth(300); user.setMoney(100); user.setMana(30); user.setAttack(30); user.setLogged(false); user.setItems(null);
		}
		
	}

}
