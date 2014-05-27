package model;

import navalbattle.protocol.common.NavalBattleProtocol.COORDINATE_TYPE;

public class GameBoard {
	MapSize mapDefinition;
	COORDINATE_TYPE[][] playfield;
	COORDINATE_TYPE[][] remotePlayfield;
	
	public GameBoard(MapSize mapDefinition) {
		int mapSize = this.mapDefinition.size;
		this.mapDefinition = mapDefinition;
		playfield = new COORDINATE_TYPE[mapSize][mapSize];
		remotePlayfield = new COORDINATE_TYPE[mapSize][mapSize];
		
		for(int i = 0 ; i<mapSize; i++)
		{
			for(int j = 0 ; j<mapSize; j++)
			{
				playfield[i][j] = COORDINATE_TYPE.EMPTY;
				remotePlayfield[i][j] = COORDINATE_TYPE.EMPTY;
			}
		}
	}
	
	public MapSize getMapDefinition() {
		return mapDefinition;
	}

	public COORDINATE_TYPE[][] getPlayfield() {
		return playfield;
	}
	
	public void setBoat(BoatType boat, BoatDirection direction, int x, int y)
	{
		
	}
	
	public void setState(COORDINATE_TYPE state, int x, int y)
	{
		
	}

}

