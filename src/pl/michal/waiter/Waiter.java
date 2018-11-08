package pl.michal.waiter;

import java.util.List;

import pl.michal.fork.Fork;
import pl.michal.knife.Knife;

public class Waiter {
	private int forkQuntity;
	private int knifeQuantity;
	private List<Fork> forks;
	private List<Knife> knives;

	public Waiter(int forkQuntit, int knifeQuantity) {
		for (int i = 0; i < forkQuntity; i++) {
			forks.add(new Fork(i + 1));
		}

		for (int i = 0; i < knifeQuantity; i++) {
			knives.add(new Knife(i + 1));
		}
	}
	
	public Knife pickUpKnife(int id) {
		
		
		return null;
	}

}
