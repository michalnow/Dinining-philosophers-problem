package pl.michal.app;

import java.util.ArrayList;
import java.util.List;

import pl.michal.fork.Fork;
import pl.michal.knife.Knife;
import pl.michal.philosoper.Philosopher;

public class AppStart {

	final static int PHILOSOPHERS_QUANTITY = 5;
	final static int KNIVES_QUANTITY = 5;
	final static int FORKS_QUANTITY = 5;

	public static void main(String[] args) {

		List<Philosopher> philosophers = new ArrayList<Philosopher>();
		List<Fork> forks = new ArrayList<Fork>();
		List<Knife> knives = new ArrayList<Knife>();

		for (int i = 0; i < KNIVES_QUANTITY; i++) {
			knives.add(new Knife(i+1));
		}

		for (int i = 0; i < FORKS_QUANTITY; i++) {
			forks.add(new Fork(i+1));
		}

		for (int i = 0; i < PHILOSOPHERS_QUANTITY; i++) {
			philosophers.add(new Philosopher(i + 1,forks.get(i), knives.get(i)));

			Thread thread = new Thread(philosophers.get(i), "Philosopher " + (i + 1));
			thread.start();
		}

	}

}
