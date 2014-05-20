package model;

public class GameBoard {
	MapSize mapDefinition;
	PlayField[][] playfield;
	
	
	
}

class PlayField {
	boolean occupied;
	boolean hit;
	
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public boolean isHit() {
		return hit;
	}
	public void setHit(boolean hit) {
		this.hit = hit;
	}
	public PlayField(boolean occupied, boolean hit) {
		super();
		this.occupied = occupied;
		this.hit = hit;
	}
	
}
