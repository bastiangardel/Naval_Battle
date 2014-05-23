package model;

public enum BoatType {
	
	DESTROYER(2,"Destroyer"),SUBMARINE(3,"Submarine"),
	CRUISER(4,"Cruiser"),AIRCRAFT_CARRIER(5,"Aircraft carrier");
	
	BoatType(int nbrcase,String name)
	{
		this.name = name;
		this.nbrcase = nbrcase;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNbrcase() {
		return nbrcase;
	}
	public void setNbrcase(int nbrcase) {
		this.nbrcase = nbrcase;
	}

	String name;
	int nbrcase;
}
