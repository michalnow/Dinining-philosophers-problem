package pl.michal.waiter;

import java.util.ArrayList;
import java.util.List;

import pl.michal.fork.Fork;
import pl.michal.knife.Knife;

public class Waiter {
	private int forkQuantity = 3;
	private int knifeQuantity = 4;
	private List<Fork> forks;
	private List<Knife> knives;
	private boolean forkIsFree = true;
	private boolean knifeIsFree = true;
	private List<Fork> usedForks;
	private List<Knife> usedKnives;
	private int knifeCount = 0;
	private int forkCount = 0;

	public Waiter(int forkQuantity, int knifeQuantity) {

		usedForks = new ArrayList<Fork>();
		usedKnives = new ArrayList<Knife>();
		forks = new ArrayList<Fork>();
		knives = new ArrayList<Knife>();

		for (int i = 0; i < forkQuantity; i++) {
			forks.add(new Fork(i + 1));
		}

		for (int i = 0; i < knifeQuantity; i++) {
			knives.add(new Knife(i + 1));
		}
	}

	public synchronized Knife provideKnife() {

		if (usedKnives.size() != knifeQuantity && usedForks.size() != forkQuantity) {
			knifeCount++;
			knives.get(knifeCount - 1).pickUp();
			usedKnives.add(knives.get(knifeCount - 1));
			System.out.println("GIVING KNIFE " + knives.get(knifeCount - 1).getId());
			return knives.get(knifeCount - 1);
		}

		return null;
	}

	public synchronized Fork provideFork() {

		if (usedForks.size() != forkQuantity && usedKnives.size() != knifeQuantity) {
			forkCount++;
			forks.get(forkCount - 1).pickUp();
			usedForks.add(forks.get(forkCount - 1));
			System.out.println("GIVING FORK  " + forks.get(forkCount - 1).getId());
			return forks.get(forkCount - 1);
		}

		return null;
	}

	public synchronized void takeBackKnife(int id) {
		for (int i = 0; i < usedKnives.size(); i++) {
			if (usedKnives.get(i).getId() == id) {
				usedKnives.remove(i);
				knifeCount--;
			}
		}
		notify();
	}

	public synchronized void takeBackFork(int id) {
		for (int i = 0; i < usedForks.size(); i++) {
			if (usedForks.get(i).getId() == id) {
				usedForks.remove(i);
				forkCount--;
			}
		}
		notify();
	}

	public boolean cutleryAvaiable() {
		if (forkIsFree || knifeIsFree) {
			return true;
		}
		return false;
	}

	public synchronized boolean forkIsFree() {
		System.out.println(forkQuantity);
		if (usedForks.size() != forkQuantity) {
			return true;
		} else {
			return false;
		}
	}

	public synchronized boolean knifeIsFree() {
		System.out.println(knifeQuantity);
		if (usedKnives.size() != knifeQuantity) {
			return true;
		} else {
			return false;
		}
	}
}
