package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.utils.ComponentMover;

public class UIGameMain extends JFrame
{
	private static final long serialVersionUID = -5839340289045647964L;
	
	private static final String UI_NAME = "ui_gameMain";
	private static final int CELL_NUMBER = 10;
	private static final int CELL_SIZE = 30;

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
		JPanel boatsArea = new JPanel();
		boatsArea.setLayout(new BoxLayout(boatsArea, BoxLayout.Y_AXIS));
		
		boatsArea.add(myGrid);
		boatsArea.add(Box.createRigidArea(new Dimension(0, 10))); // space between grids
		boatsArea.add(enemyGrid);
		
		pane.add(boatsArea);
	}

	class EnemyGrid extends GridArea
	{
		private static final long serialVersionUID = -6389260975774544829L;

		public EnemyGrid()
		{
			super("Enemy Grid", CELL_NUMBER, CELL_SIZE);
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
			super("My Grid", CELL_NUMBER, CELL_SIZE);
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
}

