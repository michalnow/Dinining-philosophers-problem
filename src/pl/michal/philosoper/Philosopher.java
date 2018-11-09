package pl.michal.philosoper;

import pl.michal.fork.Fork;
import pl.michal.knife.Knife;
import pl.michal.waiter.Waiter;

public class Philosopher implements Runnable {

	private Waiter waiter;
	private String name;
	private boolean hasKnife;
	private boolean hasFork;
	private int hungryLevel = 0;
	private Knife knife = null;
	private Fork fork = null;
	private int eatTurns = 0;

	public Philosopher(Waiter waiter, String name) {
		this.waiter = waiter;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private void takeKnife() {
		if (knife.isBeingUsed()) {
			knife = waiter.provideKnife();
			hasKnife = true;
		} else {
			try {
				System.out.println(getName() + "....IS WAITING FOR KNIFE....");
				wait();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	private void takeFork() {
		if (knife.isBeingUsed()) {
			fork = waiter.provideFork();
			hasFork = true;
		} else {
			try {
				System.out.println(getName() + "....IS WAITING FOR FORK....");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void think() {
		if (fork == null || knife == null) {
			System.out.println(getName() + " IS....THINKING ....");
			try {
				Thread.sleep((long) (Math.random() * 3500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void eat() {
		while (hungryLevel != 5) {
			System.out.println(getName() + "IS....EATING OM NO OM....");
			try {
				Thread.sleep((long) (Math.random() * 3500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			++hungryLevel;
		}
		eatTurns++;
		waiter.takeBackFork(fork.getId());
		waiter.takeBackKnife(fork.getId());
		System.out.println(getName() + "IS....BACK TO THINKING....");
		try {
			Thread.sleep((long) (Math.random() * 3500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		think();
		while (eatTurns != 3) {
			takeKnife();
			takeFork();
			if (fork != null && knife != null) {
				eat();
			}

		}

	}

	/*
	 * public synchronized void pickUpFork() {
	 * System.out.println(Thread.currentThread().getName() + " Picked up fork " +
	 * fork.getId()); pickedFork = true; }
	 * 
	 * public synchronized void pickUpKnife() {
	 * System.out.println(Thread.currentThread().getName() + " Picked up knife " +
	 * knife.getId()); pickedKnife = true; }
	 * 
	 * public synchronized void think() {
	 * System.out.println(Thread.currentThread().getName() + " is....thinking....");
	 * }
	 * 
	 * public synchronized void putDownFork() {
	 * System.out.println(Thread.currentThread().getName() + " Put down fork " +
	 * fork.getId()); pickedFork = false; }
	 * 
	 * public synchronized void putDownKnife() {
	 * System.out.println(Thread.currentThread().getName() + " Put down knife " +
	 * knife.getId()); pickedKnife = false; }
	 * 
	 * public synchronized void eat() {
	 * System.out.println(Thread.currentThread().getName() + " IS E A T I N G !!");
	 * }
	 * 
	 * public synchronized void backToThink() {
	 * System.out.println(Thread.currentThread().getName() + " AGAIN THINKING"); }
	 * 
	 * 
	 * public int getId() { return id; }
	 * 
	 * @Override public void run() { try { while (true) { Thread.sleep((long)
	 * (Math.random()*5000) + 3000); think(); Thread.sleep((long)
	 * (Math.random()*5000) + 3000); pickUpFork(); Thread.sleep((long)
	 * (Math.random()*5000) + 3000); pickUpKnife(); if (pickedKnife && pickedFork) {
	 * eat(); } Thread.sleep((long) (Math.random()*5000) + 3000); putDownFork();
	 * Thread.sleep((long) (Math.random()*5000) + 3000); putDownKnife();
	 * backToThink(); }
	 * 
	 * }catch(InterruptedException e) { e.printStackTrace(); }
	 * 
	 * }
	 */
}
