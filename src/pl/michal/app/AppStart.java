package pl.michal.app;

import java.util.ArrayList;
import java.util.List;

import pl.michal.fork.Fork;
import pl.michal.knife.Knife;
import pl.michal.philosoper.Philosopher;
import pl.michal.waiter.Waiter;

public class AppStart {

	final static int PHILOSOPHERS_QUANTITY = 5;
	final static int KNIVES_QUANTITY = 3;
	final static int FORKS_QUANTITY = 4;
	final static String[] names = { "Archimedes", "Sokrates", "Platon", "Arystoteles", "Zenon", "ANAKSAGORAS" };

	public static void main(String[] args) {
		Waiter waiter = new Waiter(FORKS_QUANTITY, KNIVES_QUANTITY);
		List<Philosopher> philosophers = new ArrayList<Philosopher>();

		for (int i = 0; i < PHILOSOPHERS_QUANTITY; i++) {
			philosophers.add(new Philosopher(waiter, names[i]));

			Thread thread = new Thread(philosophers.get(i), "Philosopher " + (i + 1));
			thread.start();
		}

	}

}
