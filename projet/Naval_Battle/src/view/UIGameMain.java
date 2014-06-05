package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.BoatType;
import view.actionListeners.ALGameMain;
import view.utils.ComponentMover;
import view.utils.PicManip;

public class UIGameMain extends JFrame
{
	private static final long serialVersionUID = -5839340289045647964L;
	
	private static final String UI_NAME = "ui_gameMain";
	private static final String SEND = "Envoyer";
	private static final String OPPONENT_FLEET = "Flotte de l'adversaire";
	private static final String MY_FLEET = "Ma flotte";
	private static final int CELL_NUMBER = 15;
	private static final int CELL_SIZE = 27;
	private static final int TOTAL_SIZE = CELL_NUMBER * CELL_SIZE;

	private MyGrid myGrid;
	private EnemyGrid enemyGrid;
	
	public UIGameMain() throws IOException
	{
		this.myGrid = new MyGrid();
		this.enemyGrid = new EnemyGrid();
		initUI();
	}
	
	private void initUI()
	{
		// frame settings
	    setResizable(false);
	    setUndecorated(true);
	    
	    // contents
	    JPanel pane = new JPanel();
        setContentPane(pane);
	    addComponents(pane);
	    
	    new ComponentMover(this, this.getContentPane());
	    
	    pack();
	    setLocationRelativeTo(null);
	}
	
	private void addComponents(JPanel pane)
	{
		pane.setLayout(new BorderLayout());
		
		// contour
		pane.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		
		JPanel opponentContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel opponent = new JLabel(OPPONENT_FLEET);
		opponent.setPreferredSize(new Dimension(enemyGrid.getPreferredSize().width, 15));
		opponent.setHorizontalAlignment(SwingConstants.CENTER);
		opponentContainer.add(opponent);
		pane.add(opponentContainer, BorderLayout.NORTH);
		
		// boats
		JPanel boatsAreaContainer = new JPanel();
		JPanel boatsArea = new JPanel();
		boatsArea.setLayout(new BoxLayout(boatsArea, BoxLayout.Y_AXIS));
		
		boatsArea.add(enemyGrid);
		boatsArea.add(Box.createRigidArea(new Dimension(0, 20))); // space between grids
		boatsArea.add(myGrid);
		
		boatsAreaContainer.add(boatsArea);
		pane.add(boatsAreaContainer, BorderLayout.CENTER);
		
		JPanel myFleetContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel myFleet = new JLabel(MY_FLEET);
		myFleet.setPreferredSize(new Dimension(myGrid.getPreferredSize().width, 15));
		myFleet.setHorizontalAlignment(SwingConstants.CENTER);
		myFleetContainer.add(myFleet);
		pane.add(myFleetContainer, BorderLayout.SOUTH);
		
		// chat
		JPanel chatPaneContainer = new JPanel();
		JPanel chatPane = new JPanel(new BorderLayout());
		JTextArea chatText = new JTextArea("sgvfcac"
				+ "cacadff"
				+ "afd cfs"
				+ "avffavf"
				+ "jfbsfj avfa"
				+ "vfaffvdaf"
				+ "mafv safnm"
				+ "asfamsvf"
				+ " mvfmfv");
		chatText.setLineWrap(true);
		chatText.setEditable(false);
		JScrollPane chatTextPane = new JScrollPane(chatText,
										JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
										JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel inputPane = new JPanel(new BorderLayout());
		JTextField chatInputField = new JTextField();
		JButton buttonSend = new JButton(SEND);
		inputPane.add(chatInputField, BorderLayout.CENTER);
		inputPane.add(buttonSend, BorderLayout.EAST);

		chatPane.add(chatTextPane, BorderLayout.CENTER);
		chatPane.add(inputPane, BorderLayout.SOUTH);
		chatPane.setPreferredSize(new Dimension(300, boatsArea.getPreferredSize().height));
		chatPaneContainer.add(chatPane);
		
		pane.add(chatPaneContainer, BorderLayout.EAST);
		
		buttonSend.setActionCommand("send");
		
		ActionListener al = new ALGameMain();
		buttonSend.addActionListener(al);
		
		pane.getRootPane().setDefaultButton(buttonSend);
	}

	private class EnemyGrid extends GridArea
	{
		private static final long serialVersionUID = -6389260975774544829L;
		
		private int[][] playField;

//		public EnemyGrid(int[][] playField)
		public EnemyGrid()
		{
			super(CELL_NUMBER, CELL_SIZE);
			
//			this.playField = playField;
			this.playField = new int[CELL_NUMBER][CELL_NUMBER];
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
//			Graphics2D g2 = (Graphics2D)g;
//			int current;
//			for (int y=0; y<10; y++) for (int x=0; x<10; x++)
//			{
//				if (area[x][y]!=0)
//				{
//					current = area[x][y]/10;
//					if ((current/10)%10!=0)		//or could be written, ==1
//					{
//						if (current%10!=0) g2.drawImage(PlayingField.fire, 25*x, 25*y, this);
//						else g2.drawImage(PlayingField.splash, 25*x, 25*y, this);
//					}
//				}
//			}
//			if (cursorLocation!=null) g2.drawImage(PlayingField.target,
//						25*(int)cursorLocation.getX(), 25*(int)cursorLocation.getY(), this);
		}
	}
	
	private class MyGrid extends GridArea
	{
		private static final long serialVersionUID = -1824922420293740009L;
		
		private static final String DESTROYER_SHIP_PATH = "pictures/ships/destroyer.gif";		
		private static final String SUBMARINE_SHIP_PATH = "pictures/ships/submarine.gif";		
		private static final String CRUISER_SHIP_PATH = "pictures/ships/cruiser.gif";		
		private static final String AIRCRAFT_CARRIER_SHIP_PATH = "pictures/ships/aircraft_carrier.gif";
		private static final String MISS_PIC_PATH = "pictures/grid/miss.gif";
		private static final String HIT_PIC_PATH = "pictures/grid/hit.gif";
		
		private BufferedImage destroyerPic;
		private BufferedImage submarinePic;
		private BufferedImage cruiserPic;
		private BufferedImage aircraftCarrierPic;
		
		private BufferedImage missPic;
		private BufferedImage hitPic;
		
		
		private int[][] playField;
		
		// for placement
		private BoatType boat;

//		public MyGrid(int[][] playField)
		public MyGrid() throws IOException
		{
			super(CELL_NUMBER, CELL_SIZE);
			
			// pics
			destroyerPic = ImageIO.read(new File(DESTROYER_SHIP_PATH));
			submarinePic = ImageIO.read(new File(SUBMARINE_SHIP_PATH));
			cruiserPic = ImageIO.read(new File(CRUISER_SHIP_PATH));
			aircraftCarrierPic = ImageIO.read(new File(AIRCRAFT_CARRIER_SHIP_PATH));
			
			missPic = ImageIO.read(new File(MISS_PIC_PATH));
			hitPic = ImageIO.read(new File(HIT_PIC_PATH));
			
//			this.playField = playField;
			this.playField = new int[CELL_NUMBER][CELL_NUMBER];
			
			// pour test
			this.boat = BoatType.AIRCRAFT_CARRIER;
			super.horizontalPlacement = true;
			
			MouseHandler mh = new MouseHandler();
			addMouseMotionListener(mh);
			addMouseListener(mh);
		}

		public void setBoatToPlace(BoatType boat)
		{
			this.boat = boat;
		}
		
		private boolean validPlacement()
		{
			if (cursorLocation == null)
				return false;
			
			if (super.horizontalPlacement)
			{				
				if (((cursorLocation.x * CELL_SIZE) + (boat.getNbrcase() * CELL_SIZE)) > TOTAL_SIZE)
					return false;

				for (int i = 0; i < boat.getNbrcase(); ++i)
					if (playField[(int)((cursorLocation.getX() / CELL_SIZE) + i)][(int)(cursorLocation.getY() / CELL_SIZE)] != 0)
						return false;
			}
			else
			{
				if (((cursorLocation.y * CELL_SIZE) + (boat.getNbrcase() * CELL_SIZE)) > TOTAL_SIZE)
					return false;

				for (int i = 0; i < boat.getNbrcase(); ++i)
					if (playField[(int)(cursorLocation.getX() / CELL_SIZE)][(int)((cursorLocation.getY() / CELL_SIZE) + i)] != 0)
						return false;
			}
			
			return true;
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D)g;
			
			
			// ici il faut mettre un for sur le tableau de bateaux avec leurs emplacements
			g2.drawImage(aircraftCarrierPic, null, 29, 30);
			
			// ici il faut mettre un for sur le tableau des coups joués
			g2.drawImage(hitPic, null, 81, 27);
			g2.drawImage(missPic, null, 81, 81);
			
			if (validPlacement())
			{
				if (super.horizontalPlacement)
				{
					g2.fill3DRect(CELL_SIZE * (int)cursorLocation.getX(),
								CELL_SIZE * (int)cursorLocation.getY(),
								CELL_SIZE * boat.getNbrcase(),
								CELL_SIZE,
								false);
				}
				else
				{
					g2.fill3DRect(CELL_SIZE * (int)cursorLocation.getX(),
								CELL_SIZE * (int)cursorLocation.getY(),
								CELL_SIZE,
								CELL_SIZE * boat.getNbrcase(),
								false);
				}
			}
		}
		
		private class MouseHandler extends MouseAdapter
		{
			private Rectangle lastSelected = new Rectangle();
			
			public void mouseMoved(MouseEvent e)
			{
				int x = (int)(e.getPoint().getX() / CELL_SIZE);
				int y = (int)(e.getPoint().getY() / CELL_SIZE);
				
				if (x < CELL_NUMBER && y < CELL_NUMBER && cellsLocations[x][y] != lastSelected)
				{
					lastSelected = cellsLocations[x][y];
					cursorLocation = new Point(x, y);
					repaint();
				}
			}
			
			public void mouseClicked(MouseEvent e)
			{
				// click gauche
				if (e.getModifiers() == InputEvent.BUTTON1_MASK)
				{
				}
				// click droit
				else if (e.getModifiers() == InputEvent.BUTTON3_MASK)
				{
					horizontalPlacement = !horizontalPlacement;
					repaint();
				}
			}			
		}
	}
	
	private class GridArea  extends JPanel
	{
		private static final long serialVersionUID = 4301030987169745017L;
		
		private int cellNumber;
		private int cellSize;
		private int totalSize;
		protected Rectangle cellsLocations[][] = new Rectangle[CELL_NUMBER][CELL_NUMBER];
		
		protected boolean horizontalPlacement;
		protected Point cursorLocation;
		
		public GridArea(int cellNumber, int cellSize)
		{
			this.cellNumber = cellNumber;
			this.cellSize = cellSize;
			this.totalSize = cellNumber * cellSize;
			setOpaque(false);
			
			for (int x = 0; x < CELL_NUMBER; ++x)
			{
				for (int y = 0; y < CELL_NUMBER; ++y)
				{
					cellsLocations[x][y] = new Rectangle(x * CELL_SIZE,
														 y * CELL_SIZE,
															CELL_SIZE,
															CELL_SIZE);
				}
			}
		}

		public Dimension getPreferredSize()
		{
			return new Dimension(totalSize + 1, totalSize + 1);
		}
		
		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D)g;

			GradientPaint gp =
					new GradientPaint(0.0f, 0.0f, new Color(40,200,140),
					   (float)(totalSize), (float)(totalSize), new Color(40,180,210));
			
			g2.setPaint(gp);
			g2.fillRect(0, 0, totalSize, totalSize);

			// bordures
			g2.setColor(new Color(0,100,90));
			
			for (int i = 1; i < cellNumber; ++i)
			{
				g2.drawLine(i * cellSize, 0, i * cellSize, totalSize);
				g2.drawLine(0, i * cellSize, totalSize, i * cellSize);
			}
			g2.setColor(Color.black);
			g2.draw3DRect(0, 0, totalSize, totalSize, false);
		}
	}
}

