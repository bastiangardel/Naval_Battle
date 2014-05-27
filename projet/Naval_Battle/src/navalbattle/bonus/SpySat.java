package navalbattle.bonus;

import java.util.ArrayList;
import java.util.Random;
import navalbattle.boats.Boat;
import navalbattle.boats.PositionNotSetException;
import positionboatbase.PlayerGrid;

public class SpySat extends Bonus {

    // Position which will trigger the bonus
    private final int x;
    private final int y;
    
    private final int NUMBER_MIN_TO_REVEAL = 2; // in percentage
    private final int NUMBER_MAX_TO_REVEAL = 10;

    public SpySat(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public ArrayList<RevelationsDueToSat> fire(int x, int y, PlayerGrid playerWhoWillSufferDamages, PlayerGrid playerWhoFired) {
        if (x != this.x && y != this.y) {
            return null;
        }
        
        ArrayList<RevelationsDueToSat> revelations = new ArrayList<>();
        Random rng = new Random();
        double percentageToReveal = (rng.nextDouble() * (NUMBER_MIN_TO_REVEAL + NUMBER_MAX_TO_REVEAL)) - NUMBER_MIN_TO_REVEAL;
        long numberOfPositionsToReveal = Math.round(percentageToReveal * playerWhoWillSufferDamages.getGridSizeX() * playerWhoWillSufferDamages.getGridSizeY());
        
        // Picking some random points
        
        int gridSizeX = playerWhoWillSufferDamages.getGridSizeX();
        int gridSizeY = playerWhoWillSufferDamages.getGridSizeY();
        
        ArrayList<Boat> boats = playerWhoWillSufferDamages.getAllBoats();
        
        for (long i = 0; i < numberOfPositionsToReveal ; ++i)
        {
            int randomX = rng.nextInt(gridSizeX);
            int randomY = rng.nextInt(gridSizeY);
            
            boolean isThereAShipOnThisPosition = false;
            
            for (Boat boat : boats)
            {
                try {
                    if (boat.isPositionOnShip(randomX, randomY))
                    {
                        isThereAShipOnThisPosition = true;
                        break;
                    }
                } catch (PositionNotSetException ex) {
                    // Should never happen
                }
            }
            
            revelations.add(new RevelationsDueToSat(x, y, isThereAShipOnThisPosition));
        }
        
        return revelations;
    }
    
}
