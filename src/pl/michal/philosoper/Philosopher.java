package pl.michal.philosoper;

import pl.michal.fork.Fork;
import pl.michal.knife.Knife;

public class Philosopher implements Runnable  {

	private Fork fork;
	private Knife knife;
	private boolean pickedKnife;
	private boolean pickedFork;
	private int id;
	
	
	public Philosopher(int id, Fork fork, Knife knife) {
		this.id = id;
		this.fork = fork;
		this.knife = knife;
	}

	public synchronized void pickUpFork() {
		System.out.println(Thread.currentThread().getName() + " Picked up fork " + fork.getId());
		pickedFork = true;
	}

	public synchronized void pickUpKnife() {
		System.out.println(Thread.currentThread().getName() + " Picked up knife " + knife.getId());
		pickedKnife = true;
	}

	public synchronized void think() {
		System.out.println(Thread.currentThread().getName() + " is....thinking....");
	}

	public synchronized void putDownFork() {
		System.out.println(Thread.currentThread().getName() + " Put down fork " +  fork.getId());
		pickedFork = false;
	}

	public synchronized void putDownKnife() {
		System.out.println(Thread.currentThread().getName() + " Put down knife " + knife.getId());
		pickedKnife = false;
	}

	public synchronized void eat() {
		System.out.println(Thread.currentThread().getName() + " IS E A T I N G !!");
	}

	public synchronized void backToThink() {
		System.out.println(Thread.currentThread().getName() + " AGAIN THINKING");
	}
	

	public int getId() {
		return id;
	}

	@Override
	public void run() {
		try {
		while (true) {
			Thread.sleep((long) (Math.random()*5000) + 3000);
			think();
			Thread.sleep((long) (Math.random()*5000) + 3000);
			pickUpFork();
			Thread.sleep((long) (Math.random()*5000) + 3000);
			pickUpKnife();
			if (pickedKnife && pickedFork) {
				eat();
			}
			Thread.sleep((long) (Math.random()*5000) + 3000);
			putDownFork();
			Thread.sleep((long) (Math.random()*5000) + 3000);
			putDownKnife();
			backToThink();
		}
		
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	
	}
}
