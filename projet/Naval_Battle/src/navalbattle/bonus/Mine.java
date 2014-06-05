package navalbattle.bonus;

import java.util.ArrayList;
import navalbattle.boats.Boat;
import navalbattle.boats.PositionNotSetException;
import navalbattle.protocol.common.NavalBattleProtocol;
import positionboatbase.PlayerGrid;
// TO-DO: Mines can trigger other mines
public class Mine extends Bonus {

    // Position wich will trigger the mine
    private final int x;
    private final int y;
    private final int radius;

    public Mine(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public ArrayList<DamageDueToMine> fire(int x, int y, PlayerGrid playerWhoWillSufferDamages, PlayerGrid playerWhoFired) {
        if (x != this.x && y != this.y) {
            return null;
        }
        
        ArrayList<DamageDueToMine> ret = new ArrayList<>();
        
        // Processing the range of the mine
        
        int startX = x - radius;
        int startY = y - radius;
        int endY = startY + radius;
        int endX = startX + radius;
        
        if (endY >= playerWhoWillSufferDamages.getGridSizeY())
            endY = playerWhoWillSufferDamages.getGridSizeY();
        
        if (endX >= playerWhoWillSufferDamages.getGridSizeX())
            endX = playerWhoWillSufferDamages.getGridSizeX();
        
        if (startX < 0) startX = 0;
        if (startY < 0) startY = 0;

        // Damaging nearby boats!
        
        ArrayList<Boat> allBoats = playerWhoWillSufferDamages.getAllBoats();
        
        for (int i =  startX; i < endX ; ++i)
        {
            for (int j =  startY; j < endY ; ++j)
            {
                for (Boat boat : allBoats)
                {
                    try {
                        if (boat.isPositionOnShip(x, y))
                        {
                            // Boom
                            NavalBattleProtocol.HIT_ON_BOAT hit = boat.fire(x, y);
                            
                            if (hit == NavalBattleProtocol.HIT_ON_BOAT.HIT || hit == NavalBattleProtocol.HIT_ON_BOAT.SINKED)
                                ret.add(new DamageDueToMine(x, y)); // Damaged the boat
                       }
                    } catch (PositionNotSetException ex) {
                        // Should never happen
                    }
                }
            }
        }
        
        return ret;
    }
}
