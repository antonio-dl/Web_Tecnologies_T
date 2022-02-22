package servlets;

import java.io.Serializable;

public class InfoSessione implements Serializable {
	private long start;
	private int counter;
	/**
	 * @return the start
	 */
	public long getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(long start) {
		this.start = start;
	}
	/**
	 * @return the counter
	 */
	public synchronized int  getCounter() {
		return counter;
	}
	/**
	 * @param counter the counter to set
	 */
	public synchronized void setCounter(int counter) {
		this.counter = counter;
	}
	
	public synchronized void addCounter() {
		this.counter++;
	}
	

}
