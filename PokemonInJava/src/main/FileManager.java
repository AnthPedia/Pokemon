package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import item.Defensive;
import item.Item;
import item.Offensive;
import item.Spell;
import monster.Agility;
import monster.Intelligence;
import monster.Monster;
import monster.Strength;
import user.User;

public class FileManager {
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	
	ArrayList<User> user = Main.user;
	ArrayList<Monster> monster = Main.monster;
	ArrayList<Item> item = Main.item;

	public void readUser() {
		String line;
		try (BufferedReader fr = new BufferedReader(new FileReader("credential.txt"))){
			
			String email, password;
			int money;
			double health, mana;
			
			while ((line = fr.readLine()) != null) {
	            String[] split = line.split("#");

	            email = split[0];
	            password = split[1];

	            if (split.length > 2) {
	                money = Integer.parseInt(split[2]);
	                health = Double.parseDouble(split[3]);
	                mana = Double.parseDouble(split[4]);

	                String line2 = split[split.length - 1];
	                if (line2.trim().isEmpty()) {
	                    user.add(new User(email, password, money, health, mana, 30, null));
	                } else {
	                    String[] items = line2.split("-");
	                    
	                    String ID;
	                    int timeUsed;
	                    ArrayList<Item> itemList = new ArrayList<>();
	                    
	                    for (String u : items) {
	                        String[] used = u.split("@");
	                        ID = used[0];
	                        timeUsed = Integer.parseInt(used[1]);
	                        
	                        for (Item a : item) {
	                            if (ID.equals(a.getId())) {
	                                if (a instanceof Defensive) {
	                                    Defensive d = (Defensive) a;
	                                    itemList.add(new Defensive(d.getId(), d.getName(), d.getPrice(), d.getDeflect(), d.getMaxUse(), timeUsed));
	                                } else if (a instanceof Offensive) {
	                                    Offensive o = (Offensive) a;
	                                    itemList.add(new Offensive(o.getId(), o.getName(), o.getPrice(), o.getDamage(), o.getMaxUse(), timeUsed));
	                                } else if (a instanceof Spell) {
	                                    Spell s = (Spell) a;
	                                    itemList.add(new Spell(s.getId(), s.getName(), s.getPrice(), s.getDamage(), s.getMana()));
	                                }
	                                break;
	                            }
	                        }
	                    }
	                    user.add(new User(email, password, money, health, mana, 30, itemList));
	                }
	            } else {
	                user.add(new User(email, password, 100, 300, 30, 30, null));
	            }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addNewUser(String mail, String pass) {
		String line = String.format("%s#%s", mail, pass);
		try {
			BufferedWriter fa = new BufferedWriter(new FileWriter("credential.txt", true));
			fa.write(line + "\n");
			user.add(new User(mail, pass, 100, 300, 30, 30, null));
			fa.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateUserData() {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			BufferedWriter fa = new BufferedWriter(new FileWriter("credential.txt"));
			for (int i = 0; i < user.size(); i++) {
				if (user.get(i).getItems() == null || user.get(i).getItems().isEmpty()) {
					String line = String.format("%s#%s#%d#%.2f#%.2f# ", user.get(i).getEmail(), user.get(i).getPassword(), user.get(i).getMoney(), user.get(i).getHealth(), user.get(i).getMana());
					fa.write(line + "\n");
				} else {
					for (int j = 0; j < user.get(i).getItems().size(); j++) {
						Item k = user.get(i).getItems().get(j);
						if (k instanceof Defensive) {
							stringBuilder.append(String.format("%s@%d-", k.getId(), ((Defensive) k).getTimeUsed()));
						} else if (k instanceof Offensive) {
							stringBuilder.append(String.format("%s@%d-", k.getId(), ((Offensive) k).getTimeUsed()));
						} else if (k instanceof Spell) {
							stringBuilder.append(String.format("%s@%d-", k.getId(), 0));
						}
					}
					
					if (stringBuilder.length() > 0) {
	                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
	                }
					String line = String.format("%s#%s#%d#%.2f#%.2f#%s", user.get(i).getEmail(), user.get(i).getPassword(), user.get(i).getMoney(), user.get(i).getHealth(), user.get(i).getMana(), stringBuilder);
					fa.write(line + "\n");
				}
			}
			fa.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readMonster() {
		String line;
		int type = 0, damage, health;
		try(BufferedReader fr = new BufferedReader(new FileReader("monster.txt"))){
			while((line = fr.readLine()) != null) {
				String[] split = line.split("#");
				for (int i = 0; i < split.length; i++) {
					if (type == 0) {
						damage = 20 + rand.nextInt(11);
						health = 200 + rand.nextInt(11) - damage;
						int armor = 20 + rand.nextInt(21);
						monster.add(new Strength(split[i], damage, health, armor));
					} else if (type == 1) {
						damage = 10 + rand.nextInt(11);
						health = 100 + rand.nextInt(11);
						monster.add(new Intelligence(split[i], damage, health));
					} else if (type == 2) {
						damage = 40 + rand.nextInt(11);
						health = 100 + rand.nextInt(11) - damage;
						int critical = rand.nextInt(3) + 1;
						monster.add(new Agility(split[i], damage, health, critical));
					}
				}
				type++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readItem() {
		String line;
		try(BufferedReader fr = new BufferedReader(new FileReader("item.txt"))){
			String id, name, type;
			int maxUse, damage, price, mana;
			while((line = fr.readLine()) != null) {
				String[] split = line.split("#");
				id = split[0];
				name = split[1];
				type = split[2];
				price = Integer.parseInt(split[3]);
				damage = Integer.parseInt(split[4]);
				if (type.equals("spell")) {
					mana = Integer.parseInt(split[5]);
					item.add(new Spell(id, name, price, damage, mana));
				} else if(type.equals("offensive")) {
					maxUse = Integer.parseInt(split[5]);
					item.add(new Offensive(id, name, price, damage, maxUse, 0));
				} else if(type.equals("defensive")) {
					maxUse = Integer.parseInt(split[5]);
					item.add(new Defensive(id, name, price, damage, maxUse, 0));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
