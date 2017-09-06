package edu.frostburg.cosc460;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Class for row-checking thread
 * 
 * @author Barak Shechter
 * @version 2016-3-25
 *
 */
public class Row implements Callable {
	
	private Thread t;
	private String threadName;
	private HashSet<Integer> checks;
	
	/**
	 * Creates a new row-checking thread
	 * 
	 * @return 0 if the test failed, 1 if the test passed
	 */
	public Row() {
		threadName = "Row";
		checks = new HashSet<>();
	}
	
	public Integer call() {
		for (int i = 0; i < 9; ++i) {
			for (int j=0; j< 9; ++j) {
				checks.add(Main.solution[i][j]);
			}
			if (checks.size()!=9) {
				System.out.println("Failed row test");
				return 0;
			}
		}
		System.out.println("Passed row test");
		return 1;
	}
}
