package pl.michal.fork;

import pl.michal.cutleryInterface.CutleryUsage;

public class Fork implements CutleryUsage {

	private int id;
	private boolean isBeingUsed;

	public Fork(int id) {
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
