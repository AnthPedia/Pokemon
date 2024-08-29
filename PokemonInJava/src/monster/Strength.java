package monster;

public class Strength extends Monster {
	private double armor;

	public Strength(String name, double damage, double health, double armor) {
		super(name, damage, health);
		this.armor = armor;
	}

	public double getArmor() {
		return armor;
	}
	public void setArmor(double armor) {
		this.armor = armor;
	}
	
}
