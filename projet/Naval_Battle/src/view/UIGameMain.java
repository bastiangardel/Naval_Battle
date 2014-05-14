package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.utils.ComponentMover;

public class UIGameMain extends JFrame
{
	private static final long serialVersionUID = -5839340289045647964L;
	
	private static final String UI_NAME = "ui_gameMain";
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
	
	class GridArea  extends JPanel
	{
		private static final long serialVersionUID = 4301030987169745017L;
		
		private String title;
		private int cellNumber;
		private int cellSize;
		private int totalSize;
		
		public GridArea(String title, int cellNumber, int cellSize)
		{
			this.title = title;
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

