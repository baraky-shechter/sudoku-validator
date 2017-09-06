package edu.frostburg.cosc460;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Checks a sudoku solution, specify sudoku filename in commandline argument
 * 
 * @author Barak Shechter
 * @version 2016-3-25
 */
public class Main {

	public static Scanner scan;
	public static ExecutorService pool;
	public static Integer solution[][];
	public static boolean passed = true;
	public static ArrayList<Future<Integer>> futures;
	
	public static Row row = new Row();
	public static Column column = new Column();
	
	/**
	 * Creats the thread pool, and threads for checking the sudoku solution
	 * 
	 * @param args the filename of the sudoku text file
	 */
	public static void main(String[] args) {
		solution = new Integer[9][9];
		futures = new ArrayList<>();
		if (args.length == 0) {
			System.out.println("Write the filename of the sudoku solution in the commandline.");
		}
		else{
			File f = new File(args[0]);
			try {
				scan = new Scanner(f);
				while(scan.hasNext()) {
					System.out.println("The solution that was entered is: ");
					for (int i = 0; i < 9; ++i) {
						System.out.println("");
						for (int j=0; j< 9; ++j) {
							solution[i][j] = scan.nextInt();
							System.out.print(solution[i][j]+" ");
						}
					}
				}
			
			} catch (FileNotFoundException e) {
				System.out.println("Error creating the Scanner");
			}
			System.out.println("\n\nStarting Tests\n");
			pool = Executors.newFixedThreadPool(11);
				
			Future<Integer> future = pool.submit(row);
			futures.add(future);
				
			future = pool.submit(column);
			futures.add(future);
			
			future = pool.submit(new Block(1));
			futures.add(future);
			
			future = pool.submit(new Block(2));
			futures.add(future);
			
			future = pool.submit(new Block(3));
			futures.add(future);
			future = pool.submit(new Block(4));
			futures.add(future);
			future = pool.submit(new Block(5));
			futures.add(future);
			future = pool.submit(new Block(6));
			futures.add(future);
			future = pool.submit(new Block(7));
			futures.add(future);
			future = pool.submit(new Block(8));
			futures.add(future);
			future = pool.submit(new Block(9));
			futures.add(future);
			
			for(Future<Integer> fut: futures) {
				try {
					if(fut.get()==0) {
						passed = false;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(passed) {
				System.out.println("The sudoku solution is valid!");
			}
			else {
				System.out.println("The sudoku solution is INVALID!");
			}
		}
	}

}
