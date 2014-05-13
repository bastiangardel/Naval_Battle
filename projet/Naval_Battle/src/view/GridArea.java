package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GridArea  extends JPanel
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
