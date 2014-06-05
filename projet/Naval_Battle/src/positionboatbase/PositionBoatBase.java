/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package positionboatbase;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import navalbattle.boats.Aircraftcarrier;
import navalbattle.boats.Boat;
import navalbattle.boats.Submarine;
import navalbattle.protocol.common.NavalBattleProtocol;

public class PositionBoatBase {

    public static void main(String[] args) {
        PlayerGrid grid1 = new PlayerGrid(new Dimension(10, 10));
        
        
        Aircraftcarrier boat1 = new Aircraftcarrier();
        boat1.setPosition(0, 0, NavalBattleProtocol.BOAT_ORIENTATION.HORIZONTAL);
        
        Submarine boat2 = new Submarine();
        boat2.setPosition(0, 7, NavalBattleProtocol.BOAT_ORIENTATION.VERTICAL);
        
        ArrayList<Boat> allBoats = new ArrayList<>();
        allBoats.add(boat1);
        allBoats.add(boat2);
        
        try {
            grid1.positionBoats(allBoats);
        } catch (Exception ex) {
        }
        
        try {
            grid1.addBonusses();
        } catch (BoatsNotPlacedException ex) {
        }
        
        try {
            grid1.fire(1, 0);
            grid1.fire(1, 1);
            grid1.fire(5, 5);
        } catch (AlreadyFiredOnThisPositionException ex) {
        }

        grid1.print(System.out);
    }
    
}
