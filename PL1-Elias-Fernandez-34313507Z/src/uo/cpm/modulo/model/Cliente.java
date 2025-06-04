package uo.cpm.modulo.model;

public class Cliente {
	private String id;
	private String code;
	private boolean hasPlayed;
	private int timesOrdered;
	
	public Cliente(String id, String code) {
		setId(id);
		setCode(code);
		setHasPlayed(false);
		setTimesOrdered(0);
	}
	public String getId() {
		return id;
	}
	private void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	private void setCode(String code) {
		this.code = code;
	}
	public boolean isHasPlayed() {
		return hasPlayed;
	}
	private void setHasPlayed(boolean hasPlayed) {
		this.hasPlayed = hasPlayed;
	}
	public void editPlayState(boolean newState) {
		setHasPlayed(newState);
	}
	public void editTimesOrdered(int timesOrdered) {
		setTimesOrdered(timesOrdered);
	}
	public int getTimesOrdered() {
		return timesOrdered;
	}
	private void setTimesOrdered(int timesOrdered) {
		this.timesOrdered = timesOrdered;
	}
	
}
