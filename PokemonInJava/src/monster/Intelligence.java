package monster;

public class Intelligence extends Monster {

	public Intelligence(String name, double damage, double health) {
		super(name, damage, health);
	}
	
	public double specialSkill(double damage) {
		return damage;
	}
	
}
