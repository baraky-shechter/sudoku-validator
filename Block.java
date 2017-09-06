package edu.frostburg.cosc460;

import java.util.HashSet;
import java.util.concurrent.Callable;

/**
 * Class for creating a Block-checking thread
 * 
 * @author Barak Shechter
 *
 */
public class Block implements Callable {
	
	private Thread t;
	private String threadName;
	private HashSet<Integer> checks;
	private int blockNumber;
	
	/**
	 * Creates a new Block-checking thread
	 * 
	 * @param i The block number
	 */
	public Block(int i) {
		blockNumber = i;
		threadName = "Block " + blockNumber;
		checks = new HashSet<>();
	}
	
	/**
	 * Checks the specified block
	 * 
	 * @return 0 if the test failed, 1 if the test passed
	 */
	public Integer call() {
		int i=0;
		int j=0;
		switch(blockNumber) {
		case 1:
			i = 0;
			j = 0;
			break;
		case 2:
			i = 3;
			j = 0;
			break;
		case 3:
			i = 6;
			j = 0;
		case 4:
			i = 0;
			j = 3;
			break;
		case 5:
			i=3;
			j=3;
			break;
		case 6:
			i = 6;
			j = 3;
			break;
		case 7:
			i = 0;
			j = 6;
			break;
		case 8:
			i = 3;
			j = 6;
			break;
		case 9:
			i = 6;
			j=6;
			break;
		}
		int iBound = i+2;
		int jBound = j+2;
		for (int k = i; k<= iBound; ++k) {
			for (int l = j; l<=jBound; ++l) {
				checks.add(Main.solution[k][l]);
			}
			
		}
		if (checks.size()!=9) {
			System.out.println(checks.size());
			System.out.println("Failed block test " + blockNumber);
			return 0;
		}
		System.out.println("Passed block test " + blockNumber);
		return 1;
	}
}
