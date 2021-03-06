package model;

public enum MapSize {
	SMALL(7, new Boat[] { new Boat(1, BoatType.DESTROYER),
			new Boat(2, BoatType.SUBMARINE),
			new Boat(1, BoatType.CRUISER) }),
    MEDIUM(10, new Boat[] { new Boat(2, BoatType.DESTROYER),
					new Boat(2, BoatType.SUBMARINE),
					new Boat(2, BoatType.CRUISER),
					new Boat(1, BoatType.AIRCRAFT_CARRIER) }),
	LARGE(15,new Boat[] { new Boat(3, BoatType.SUBMARINE),
					new Boat(5, BoatType.CRUISER),
					new Boat(5, BoatType.AIRCRAFT_CARRIER) });

	private MapSize(int size, Boat[] allBoat) {
		this.size = size;
		this.allBoat = allBoat;
	}

	int size;
	Boat[] allBoat;
}

class Boat {
	int count;
	BoatType type;

	public Boat(int nbr, BoatType type) {
		this.count = nbr;
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int nbr) {
		this.count = nbr;
	}

	public BoatType getType() {
		return type;
	}

	public void setType(BoatType type) {
		this.type = type;
	}

}
