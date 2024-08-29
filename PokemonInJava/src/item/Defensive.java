package item;

public class Defensive extends Item implements ability {
	private double deflect;
	private int maxUse;
	private int timeUsed;
	
	public Defensive(String id, String name, int price, double deflect, int maxUse, int timeUsed) {
		super(id, name, price);
		this.deflect = deflect;
		this.maxUse = maxUse;
		this.timeUsed = timeUsed;
	}

	
	public double getDeflect() {
		return deflect;
	}
	public void setDeflect(double deflect) {
		this.deflect = deflect;
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
		return this.deflect;
	}

	@Override
	public boolean checkMaxUse(int a, int b) {
		if (a == b) {
			return true;
		}
		return false;
	}
	
}
