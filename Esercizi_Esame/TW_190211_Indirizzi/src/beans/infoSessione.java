package beans;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class infoSessione {

	private boolean isLogged;
	private LocalDateTime created;
	private LocalDateTime lastAction;
	private int timeToLive;

	private List<String> queries = new LinkedList<String>();

	public infoSessione(boolean isLogged, int timeToLive) {
		this.isLogged = isLogged;
		this.created = LocalDateTime.now();
		this.lastAction = LocalDateTime.now();
		this.timeToLive = timeToLive;
	}

	public boolean isLogged() {
		return isLogged;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}


	public LocalDateTime getLastAction() {
		return lastAction;
	}

	private void setLastAction(LocalDateTime lastAction) {
		this.lastAction = lastAction;
	}

	public List<String> getQueries() {
		return queries;
	}

	public void updateLastAction() {
		this.setLastAction(LocalDateTime.now());

	}

	public LocalDateTime getCreated() {
		return created;
	}

	public boolean isStillValid() {
		if (lastAction.plusSeconds(timeToLive).isBefore(LocalDateTime.now()))
			return false;
		return true;
	}

		

	public void addQuery(String result) {
		this.getQueries().add(result);
		
	}

	public LocalDateTime getEnd() {
		return this.getLastAction().plusSeconds(timeToLive);
		
	}

}
