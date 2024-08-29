package user;

import java.util.ArrayList;

import item.Item;

public class User extends NewUser {
	
	private double mana, health, attack;
	private int money, x, y;
	private boolean logged = false;
	private ArrayList<Item> items;
	
	
	public User(String email, String password, int money, double health, double mana, double attack, ArrayList<Item> items) {
		super(email, password);
		this.mana = mana;
		this.health = health;
		this.attack = attack;
		this.money = money;
		this.items = items;
	}
	
	
	public double getMana() {
		return mana;
	}
	public void setMana(double mana) {
		this.mana = mana;
	}
	public double getHealth() {
		return health;
	}
	public void setHealth(double health) {
		this.health = health;
	}
	public double getAttack() {
		return attack;
	}
	public void setAttack(double attack) {
		this.attack = attack;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isLogged() {
		return logged;
	}
	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
}
