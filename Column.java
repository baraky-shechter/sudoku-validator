package edu.frostburg.cosc460;

import java.util.HashSet;
import java.util.concurrent.Callable;

/**
 * Class for creating Column-checking thread
 * 
 * @author Barak Shechter
 * @version 2016-3-25
 *
 */
public class Column implements Callable {

	private Thread t;
	private String threadName;
	private HashSet<Integer> checks;
	
	/**
	 * Creates a new column-checking thread
	 */
	public Column() {
		threadName = "Column";
		checks = new HashSet<>();
	}
	
	/**
	 * Goes down the solution column by column
	 * 
	 * @return 0 if the test has failed, 1 if the test paseed
	 */
	public Integer call() {
		for (int i = 0; i < 9; ++i) {
			for (int j=0; j< 9; ++j) {
				checks.add(Main.solution[j][i]);
			}
			if (checks.size()!=9) {
				System.out.println("Failed column test");
				return 0;
			}
		}
		System.out.println("Passed column test");
		return 1;
	}
}

