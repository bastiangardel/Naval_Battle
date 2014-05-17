package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import view.actionListeners.ALGameMain;
import view.utils.ComponentMover;

public class UIGameMain extends JFrame
{
	private static final long serialVersionUID = -5839340289045647964L;
	
	private static final String UI_NAME = "ui_gameMain";
	private static final String SEND = "Envoyer";
	private static final String OPPONENT_FLEET = "Flotte de l'adversaire";
	private static final String MY_FLEET = "Ma flotte";
	private static final int CELL_NUMBER = 16;
	private static final int CELL_SIZE = 20;

	private MyGrid myGrid;
	private EnemyGrid enemyGrid;
	
	public UIGameMain()
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

	class EnemyGrid extends GridArea
	{
		private static final long serialVersionUID = -6389260975774544829L;

		public EnemyGrid()
		{
			super(CELL_NUMBER, CELL_SIZE);
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
	
	class MyGrid extends GridArea
	{
		private static final long serialVersionUID = -1824922420293740009L;

		public MyGrid()
		{
			super(CELL_NUMBER, CELL_SIZE);
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
	
	class GridArea  extends JPanel
	{
		private static final long serialVersionUID = 4301030987169745017L;
		
		private int cellNumber;
		private int cellSize;
		private int totalSize;
		
		public GridArea(int cellNumber, int cellSize)
		{
			this.cellNumber = cellNumber;
			this.cellSize = cellSize;
			this.totalSize = cellNumber * cellSize;
			setOpaque(false);
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

