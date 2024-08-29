package item;

public class Spell extends Item{
	private double mana;
	private double damage;
	
	public Spell(String id, String name, int price, double damage, double mana) {
		super(id, name, price);
		this.mana = mana;
		this.damage = damage;
	}

	public double getMana() {
		return mana;
	}
	public void setMana(double mana) {
		this.mana = mana;
	}
	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}
	
}
