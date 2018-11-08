package pl.michal.knife;

import pl.michal.cutleryInterface.Cutlery;

public class Knife implements Cutlery {

	private int id;
	private boolean isBeingUsed;

	public Knife(int id) {
		this.id = id;
	}

	@Override
	public void pickUp() {
		isBeingUsed = true;
	}

	@Override
	public void putDown() {
		isBeingUsed = false;
	}

	public int getId() {
		return id;
	}

	public boolean isBeingUsed() {
		return isBeingUsed;
	}

	
}
