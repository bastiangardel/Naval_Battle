package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import view.actionListeners.ALServerStats;
import view.customSwingComp.JButtonDesign;
import view.utils.ComponentMover;
import view.utils.JTableColumnsAdjuster;
import view.utils.PicManip;

public class UIServerStats extends JFrame
{
	private static final long serialVersionUID = 3562034473955614333L;
	
	private static final String UI_NAME = "ui_serverStats";
	private static final String BACK = "Retour";
	
	private static final String LOCAL_IP_ADRESS = "Adresse IP locale: ";
	private static final String EXTERNAL_IP_ADRESS = "Adresse IP externe: ";
	private static final String PASSWORD = "Mot de passe: ";
	private static final String CURRENT_GAME = "Partie en cours: ";
	
	private static final String HISTORY = "Historique: ";
	private static final String ICON = " ";
	private static final String WINNER = "Vainqueur";
	private static final String LOSER = "Perdant";
	private static final String DATE = "Date de la partie";
	
	private static final String WINNER_ICON_PATH = "pictures/icons/winner.gif";
	
	private JLabel locAdrIpField;
	private JLabel extAdrIpField;
	private JLabel passField;
	private JLabel playerOne;
	private JLabel playerTwo;
	private JTable historyTable;
	
	public UIServerStats() throws IOException
	{
		initUI();
	}
	
	private void initUI()
	{
		// frame settings
	    setResizable(false);
	    setUndecorated(true);
	    
	    // contents
//	    JLabel pane = getBG();
	    JPanel pane = new JPanel();
        setContentPane(pane);
	    addComponents(pane);
        
		new ComponentMover(this, this.getContentPane());
        
        pack();
	    setLocationRelativeTo(null);
	}
	
	private void addComponents(JPanel pane)
	{
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		// contour
		pane.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		
		JPanel buttonBackContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButtonDesign buttonBack = new JButtonDesign(BACK);
		buttonBackContainer.add(buttonBack);
		buttonBackContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
		pane.add(buttonBackContainer);
		
		JPanel statsPanelContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel statsPanel = new JPanel(new GridLayout(5, 2));
        
        JLabel locAdrIpText = new JLabel(LOCAL_IP_ADRESS);
        locAdrIpText.setAlignmentX(LEFT_ALIGNMENT);
        locAdrIpField = new JLabel("192.168.bla.bla");
        JLabel extAdrIpText = new JLabel(EXTERNAL_IP_ADRESS);
        extAdrIpField = new JLabel("182.58.bla.bla");
        JLabel passText = new JLabel(PASSWORD);
        passField = new JLabel("<aucun>");
        JLabel currentGame = new JLabel(CURRENT_GAME);
        playerOne = new JLabel("Luc");
        JLabel blank = new JLabel("");
        playerTwo = new JLabel("Marian");
		
        statsPanel.add(locAdrIpText);
        statsPanel.add(locAdrIpField);
        statsPanel.add(extAdrIpText);
        statsPanel.add(extAdrIpField);
        statsPanel.add(passText);
        statsPanel.add(passField);
        statsPanel.add(currentGame);
        statsPanel.add(playerOne);
        statsPanel.add(blank);
        statsPanel.add(playerTwo);
        
        statsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsPanelContainer.add(statsPanel);
        statsPanelContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        pane.add(statsPanelContainer);
		
        JPanel historyPaneContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel historyPane = new JPanel();
        historyPane.setLayout(new BoxLayout(historyPane, BoxLayout.Y_AXIS));
        
        JLabel historyLabel = new JLabel(HISTORY);
        historyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        historyPane.add(historyLabel);
        
        JPanel historyTablePane = new JPanel();
        historyTable = new JTable(new HistoricTableModel());
        try
        {
			setWinnerIcon((HistoricTableModel)historyTable.getModel());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
        historyTable.setFillsViewportHeight(true);
        historyTable.setEnabled(false);
        historyTable.setShowGrid(false);
        JTableColumnsAdjuster.adjustColumns(historyTable);
        historyTable.setRowHeight(20);

        historyTable.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        historyTablePane.add(historyTable);
        historyTablePane.setAlignmentX(Component.LEFT_ALIGNMENT);
        historyPane.add(historyTablePane);
        historyPaneContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        historyPaneContainer.add(historyPane);
        pane.add(historyPaneContainer);
        
		buttonBack.setActionCommand("back");
		
		ActionListener al = new ALServerStats();
		buttonBack.addActionListener(al);
		
		pane.getRootPane().setDefaultButton(buttonBack);
	}
	
    private class HistoricTableModel extends AbstractTableModel
    {
		private static final long serialVersionUID = -8909549809875743360L;

		private String[] columnNames = {ICON,
										WINNER,
										LOSER,
                                        DATE};
        private Object[][] data =
        {
		    {"", "Luc", "Paul", "dateblablabla1"},
		    {"", "Marian", "Adrien", "dateblablabla2"},
		    {"", "Sophie", "Adrien", "dateblablabla3"},
		    {"", "Sophie", "Adrien", "dateblablabla3"},
		    {"", "Sophie", "Adrien", "dateblablabla3"},
		    {"", "Sophie", "Adrien", "dateblablabla3"},
		    {"", "Sophie", "Adrien", "dateblablabla3"},
        };

        public int getColumnCount()
        {
            return columnNames.length;
        }

        public int getRowCount()
        {
            return data.length;
        }

        public String getColumnName(int col)
        {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col)
        {
            return data[row][col];
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col)
        {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
        
        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
    }
    
	private void setWinnerIcon(HistoricTableModel table) throws IOException
	{
		BufferedImage buffWin = ImageIO.read(new File(WINNER_ICON_PATH));
		buffWin = PicManip.resizeImg(buffWin, 16, 16);
		ImageIcon winIcon = new ImageIcon(buffWin);
		
		for (int i = 0; i < table.getRowCount(); ++i)
		{
			table.setValueAt(winIcon, i, 0);
		}
	}
}
