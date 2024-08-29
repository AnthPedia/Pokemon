package item;

public class Offensive extends Item implements ability {
	private double damage;
	private int maxUse;
	private int timeUsed;
	
	public Offensive(String id, String name, int price, double damage, int maxUse, int timeUsed) {
		super(id, name, price);
		this.damage = damage;
		this.maxUse = maxUse;
		this.timeUsed = timeUsed;
	}
	

	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}
	public int getMaxUse() {
		return maxUse;
	}
	public void setMaxUse(int maxUse) {
		this.maxUse = maxUse;
	}
	public int getTimeUsed() {
		return timeUsed;
	}
	public void setTimeUsed(int timeUsed) {
		this.timeUsed = timeUsed;
	}


	@Override
	public double useAbility() {
		return this.damage;
	}

	@Override
	public boolean checkMaxUse(int a, int b) {
		if (a == b) {
			return true;
		}
		return false;
	}
	
}
