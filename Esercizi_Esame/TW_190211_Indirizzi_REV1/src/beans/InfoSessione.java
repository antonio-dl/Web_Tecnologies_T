package beans;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

public class InfoSessione implements Serializable {
	private String id;
	private long startTime;
	private long endTime;
	private List<String> requests = new ArrayList<String>(20);
	private boolean isRegistrato;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public List<String> getRequests() {
		return requests;
	}

	public void setRequests(List<String> requests) {
		this.requests = requests;
	}

	public boolean isRegistrato() {
		return isRegistrato;
	}

	public void setRegistrato(boolean isRegistrato) {
		this.isRegistrato = isRegistrato;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfoSessione other = (InfoSessione) obj;
		return Objects.equals(id, other.id);
	}
	public String printHTML() {
		String result = "";
		result += "Id Sessione: " + this.getId() + "<br> Data inizio: "
				+ LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime),TimeZone.getDefault().toZoneId()) + "<br> Data Fine: "
				+ LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime),TimeZone.getDefault().toZoneId());
		result += "<br>Lista Risultati:<br>";
		for(String s : this.getRequests()) {
			result += s;
			result += "<br>";
		}
		return result;

	}

}
