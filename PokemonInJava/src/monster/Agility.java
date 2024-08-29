package monster;

public class Agility extends Monster {
	private double critical;

	public Agility(String name, double damage, double health, double critical) {
		super(name, damage, health);
		this.critical = critical;
	}

	public double getCritical() {
		return critical;
	}
	public void setCritical(double critical) {
		this.critical = critical;
	}
	
}
