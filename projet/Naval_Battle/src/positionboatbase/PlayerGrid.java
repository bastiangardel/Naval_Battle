package positionboatbase;

import java.awt.Dimension;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import navalbattle.boats.Boat;
import navalbattle.boats.BoatIsOutsideGridException;
import navalbattle.boats.BoatPosition;
import navalbattle.boats.BoatUtils;
import navalbattle.boats.PositionNotSetException;
import navalbattle.bonus.Mine;
import navalbattle.bonus.SpySat;
import navalbattle.protocol.common.NavalBattleProtocol;

public class PlayerGrid {

    private ArrayList<Boat> allBoats;
    private final HashMap<Integer, HashMap<Integer, Mine>> allMines = new HashMap<Integer, HashMap<Integer, Mine>>();
    private final HashMap<Integer, HashMap<Integer, SpySat>> allSpySat = new HashMap<Integer, HashMap<Integer, SpySat>>();
    private HashSet<HitPosition> missedFires = new HashSet<>();

    final Random rng = new Random();
    final int numberOfMines = 10;
    final int numberOfSpySatBonus = 10;
    final int minesRadius = 2;

    private final int gridSizeX;
    private final int gridSizeY;

    private HitPosition getRandomUnusedPositionForBonus(ArrayList<HitPosition> excludePositions) {
        while (true) {
            int xPos = this.rng.nextInt(this.gridSizeX);
            int yPos = this.rng.nextInt(this.gridSizeY);

            // There must be no boat there
            boolean isThereABoatOnThatPosition = false;

            for (Boat boat : allBoats) {
                try {
                    if (boat.isPositionOnShip(xPos, yPos)) {
                        isThereABoatOnThatPosition = true;
                        break;
                    }
                } catch (PositionNotSetException ex) {
                    // Should never happen
                }
            }

            if (isThereABoatOnThatPosition) {
                continue;
            }

            boolean excludeThisPositionBecauseThereAlreadyIsAMineThere = false;

            for (HitPosition pos : excludePositions) {
                if (pos.getX() == xPos && pos.getY() == yPos) {
                    excludeThisPositionBecauseThereAlreadyIsAMineThere = true;
                    break;
                }
            }

            if (excludeThisPositionBecauseThereAlreadyIsAMineThere) {
                continue;
            }

            return new HitPosition(xPos, yPos);
        }
    }

    public void addBonusses() throws BoatsNotPlacedException {

        if (allBoats.isEmpty()) {
            throw new BoatsNotPlacedException();
        }

        ArrayList<HitPosition> positionBonus = new ArrayList<>();

        for (int i = 0; i < numberOfMines; ++i) {
            // Warning: can fail in an infinite loop if there is no more space left on the grid
            HitPosition position = this.getRandomUnusedPositionForBonus(positionBonus);

            final int posX = position.getX();
            final int posY = position.getY();

            Mine mine = new Mine(posX, posY, this.minesRadius);

            if (!allMines.containsKey(posX)) {

                HashMap<Integer, Mine> toAdd = new HashMap<>();
                toAdd.put(posY, mine);

                allMines.put(posX, toAdd);
            } else {
                allMines.get(posX).put(i, mine);
            }

            positionBonus.add(position);
        }

        for (int i = 0; i < this.numberOfSpySatBonus; ++i) {
            // Warning: can fail in an infinite loop if there is no more space left on the grid
            HitPosition position = this.getRandomUnusedPositionForBonus(positionBonus);

            final int posX = position.getX();
            final int posY = position.getY();

            SpySat spySat = new SpySat(posX, posY);

            if (!allSpySat.containsKey(posX)) {

                HashMap<Integer, SpySat> toAdd = new HashMap<>();
                toAdd.put(posY, spySat);

                allSpySat.put(posX, toAdd);
            } else {
                allSpySat.get(posX).put(i, spySat);
            }

            positionBonus.add(position);
        }
    }

    public ArrayList<Boat> getAllBoats() {
        return allBoats;
    }

    public int getGridSizeX() {
        return gridSizeX;
    }

    public int getGridSizeY() {
        return gridSizeY;
    }

    public void print(PrintStream out) {

        String[][][] grid = new String[this.gridSizeX][this.gridSizeY][1];

        for (int i = 0; i < this.gridSizeX; ++i) {
            for (int j = 0; j < this.gridSizeY; ++j) {
                grid[i][j][0] = "  ";
            }
        }

        for (Boat boat : allBoats) {
            BoatPosition position = boat.getPosition();
            int posX = position.getX();
            int posY = position.getY();
            int length = position.getLength();
            NavalBattleProtocol.BOAT_ORIENTATION orientation = position.getOrientation();

            if (orientation == NavalBattleProtocol.BOAT_ORIENTATION.HORIZONTAL) {
                int endX = posX + length;

                for (int i = posX; i < endX; ++i) {
                    boolean isDamaged = boat.isDamagedAtBloc(i - posX);

                    grid[i][posY][0] = (isDamaged ? "X" : "O") + " ";
                    //grid[i][posY][1] = isDamaged ? "XX" : "OO";
                }
            } else {
                int endY = posY + length;

                for (int i = posY; i < endY; ++i) {
                    boolean isDamaged = boat.isDamagedAtBloc(i - posY);

                    grid[posX][i][0] = (isDamaged ? "X" : "O") + " ";
                    //grid[posY][i][1] = isDamaged ? "XX" : "OO";
                }
            }
        }
        
        for (HitPosition hit : this.missedFires)
        {
            grid[hit.getX()][hit.getY()][0] = "X" + " ";
        }

        for (Map.Entry<Integer, HashMap<Integer, Mine>> column : this.allMines.entrySet()) {
            final int posX = (int) column.getKey();

            for (Map.Entry<Integer, Mine> line : column.getValue().entrySet()) {
                final int posY = line.getKey();
                grid[posX][posY][0] = "M" + " ";
            }
        }

        for (Map.Entry<Integer, HashMap<Integer, SpySat>> column : this.allSpySat.entrySet()) {
            final int posX = (int) column.getKey();

            for (Map.Entry<Integer, SpySat> line : column.getValue().entrySet()) {
                final int posY = line.getKey();
                grid[posX][posY][0] = "S" + " ";
            }
        }

        final String ls = System.lineSeparator();

        for (int i = 0; i < this.gridSizeY; ++i) {
            for (int c = 0; c < 1; ++c) {
                for (int j = 0; j < this.gridSizeX; ++j) {
                    out.print(grid[j][i][c]);
                }
                out.print(ls);
            }
        }
        out.flush();
    }

    public PlayerGrid(Dimension dimension) {
        this.gridSizeX = (int) dimension.getWidth();
        this.gridSizeY = (int) dimension.getHeight();

        if (this.gridSizeX < 0 || this.gridSizeY < 0) {
            throw new IllegalArgumentException();
        }
    }

    public void positionBoats(ArrayList<Boat> boats) throws BoatsAreOverlapping, BoatIsOutsideGridException {
        if (BoatUtils.areBoatsOverlapping(new Dimension(gridSizeX, gridSizeY), boats)) {
            throw new BoatsAreOverlapping();
        }

        this.allBoats = boats;
    }

    public void fire(int x, int y) throws AlreadyFiredOnThisPositionException {
        if (x < 0 || x >= gridSizeX || y < 0 || y >= this.gridSizeY) {
            throw new IllegalArgumentException();
        }

        for (HitPosition hp : this.missedFires) {
            if (hp.getX() == x && hp.getY() == y) {
                throw new AlreadyFiredOnThisPositionException();
            }
        }
        
        boolean hasHitAShip = false;

        for (Boat boat : allBoats) {
            try {
                if (boat.isPositionOnShip(x, y)) {
                    NavalBattleProtocol.HIT_ON_BOAT hit = boat.fire(x, y);

                    if (hit == NavalBattleProtocol.HIT_ON_BOAT.ALREADY_FIRED_ON_THAT_POSITION) {
                        throw new AlreadyFiredOnThisPositionException();
                    } else if (hit == NavalBattleProtocol.HIT_ON_BOAT.HIT || hit == NavalBattleProtocol.HIT_ON_BOAT.SINKED) {
                        hasHitAShip = true;
                        break;
                    }
                }
            } catch (PositionNotSetException ex) {
                Logger.getLogger(PlayerGrid.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Check if we hit a bonus
        // TO-DO
        
        if (!hasHitAShip)
        {
            this.missedFires.add(new HitPosition(x, y));
        }
    }

}
