package navalbattle.bonus;

import positionboatbase.PlayerGrid;


public abstract class Bonus {
    
    public abstract Object fire(int x, int y, PlayerGrid playerWhoWillSufferDamages, PlayerGrid playerWhoFired);
}
