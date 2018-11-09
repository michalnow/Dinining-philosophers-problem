package pl.michal.waiter;

import java.util.List;

import pl.michal.fork.Fork;
import pl.michal.knife.Knife;

public class Waiter {
	private int forkQuntity;
	private int knifeQuantity;
	private List<Fork> forks;
	private List<Knife> knives;
	private boolean forkIsFree;
	private boolean knifeIsFree;

	public Waiter(int forkQuntit, int knifeQuantity) {
		for (int i = 0; i < forkQuntity; i++) {
			forks.add(new Fork(i + 1));
		}

		for (int i = 0; i < knifeQuantity; i++) {
			knives.add(new Knife(i + 1));
		}
	}

	public synchronized Knife provideKnife() {

		for (int i = 0; i < knives.size(); i++) {
			if (!knives.get(i).isBeingUsed()) {
				knives.get(i).pickUp();
				forkIsFree = false;
				return knives.get(i);
			}
		}

		return null;
	}

	public synchronized Fork provideFork() {

		for (int i = 0; i < forks.size(); i++) {
			if (!forks.get(i).isBeingUsed()) {
				forks.get(i).pickUp();
				forkIsFree = false;
				return forks.get(i);
			}
		}

		return null;
	}

	public synchronized void takeBackKnife(int id) {
		for (int i = 0; i < knives.size(); i++) {
			if (knives.get(i).getId() == id) {
				knives.get(i).putDown();
				knifeIsFree = true;
			}
		}
		notify();
	}

	public synchronized void takeBackFork(int id) {
		for (int i = 0; i < forks.size(); i++) {
			if (forks.get(i).getId() == id) {
				forks.get(i).putDown();
				forkIsFree = true;
			}
		}
		notify();
	}

	public boolean cutleryAvaiable() {
		if (forkIsFree && knifeIsFree) {
			return true;
		}
		return false;
	}

}
