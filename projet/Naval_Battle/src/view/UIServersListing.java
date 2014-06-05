package view;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import view.actionListeners.ALServerListing;
import view.customSwingComp.JButtonDesign;
import view.utils.ComponentMover;
import view.utils.JTableColumnsAdjuster;
import view.utils.PicManip;

public class UIServersListing extends JFrame
{
	private static final long serialVersionUID = -8379327763047478016L;
	
	private static final String UI_NAME = "ui_serversListing";
	private static final String SPECIFY_MANUALLY = "Spécifier manuellement";
	private static final String CONNECT = "Connecter";
	private static final String CANCEL = "Annuler";
	
	private static final String PASS_PROTECTED = "Protégé";
	private static final String NAME = "Nom du serveur";
	private static final String PLAYERS = "Joueurs";
	private static final String FREE_SPACE_AVAILABLE = "Libre";
	
	private static final String CHECK_ICON_PATH = "pictures/icons/check.gif";
	private static final String CROSS_ICON_PATH = "pictures/icons/cross.gif";	
	private static final String LOCK_ICON_PATH = "pictures/icons/lock.gif";
	
	private JTable ServListTable;
	
	public UIServersListing() throws IOException
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
		
		JPanel buttonManualContainer = new JPanel();
		JButtonDesign buttonManualSpecify = new JButtonDesign(SPECIFY_MANUALLY);
		buttonManualContainer.add(buttonManualSpecify);
		pane.add(buttonManualContainer);
		
        JPanel tablePane = new JPanel();
        
        ServListTable = new JTable(new ServTableModel());
        try
        {
			replaceBoolWithIcon((ServTableModel)ServListTable.getModel());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
        
        ServListTable.setFillsViewportHeight(true);
        ServListTable.getTableHeader().setReorderingAllowed(false);
        ServListTable.getTableHeader().setResizingAllowed(false);
        ServListTable.setRowSelectionAllowed(true);
        ServListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ServListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer renderString = (DefaultTableCellRenderer)ServListTable.getDefaultRenderer(String.class);
        renderString.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer renderInt = (DefaultTableCellRenderer)ServListTable.getDefaultRenderer(Integer.class);
        renderInt.setHorizontalAlignment(JLabel.CENTER);
        JTableColumnsAdjuster.adjustColumns(ServListTable);

        JScrollPane sp = new JScrollPane(ServListTable);
        Dimension dimTable = ServListTable.getPreferredSize();
        sp.setPreferredSize(new Dimension(dimTable.width + 3, dimTable.height + 23));

        tablePane.add(sp);
        
        pane.add(tablePane);
		
        JPanel buttonConnectContainer = new JPanel();
        JButtonDesign buttonConnect = new JButtonDesign(CONNECT);
        buttonConnectContainer.add(buttonConnect);
        pane.add(buttonConnectContainer);
        
        JPanel buttonCancelContainer = new JPanel();
        JButtonDesign buttonCancel = new JButtonDesign(CANCEL);
        buttonCancelContainer.add(buttonCancel);
        pane.add(buttonCancelContainer);
		
		buttonManualSpecify.setActionCommand("manual");
		buttonConnect.setActionCommand("connect");
		buttonCancel.setActionCommand("cancel");
		
		ActionListener al = new ALServerListing();
		buttonManualSpecify.addActionListener(al);
		buttonConnect.addActionListener(al);
		buttonCancel.addActionListener(al);
		
		pane.getRootPane().setDefaultButton(buttonConnect);
	}
	
    private class ServTableModel extends AbstractTableModel
    {
		private static final long serialVersionUID = -5985198864046684897L;
		
		private String[] columnNames = {PASS_PROTECTED,
										NAME,
                                        PLAYERS,
                                        FREE_SPACE_AVAILABLE};
        private Object[][] data =
        {
		    {new Boolean(true), "Luc's Server", new Integer(2), new Boolean(false)},
		    {new Boolean(false), "Hophop", new Integer(1), new Boolean(true)},
		    {new Boolean(false), "NoName", new Integer(0), new Boolean(true)},
		    {new Boolean(false), "NoName", new Integer(0), new Boolean(true)},
		    {new Boolean(false), "NoName", new Integer(0), new Boolean(true)},
		    {new Boolean(false), "NoName", new Integer(0), new Boolean(true)},
		    {new Boolean(false), "NoName", new Integer(0), new Boolean(true)},
		    {new Boolean(false), "NoName", new Integer(0), new Boolean(true)},
		    {new Boolean(false), "NoName", new Integer(0), new Boolean(true)},
		    {new Boolean(false), "NoName", new Integer(0), new Boolean(true)}
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
	
	private void replaceBoolWithIcon(ServTableModel table) throws IOException
	{
		BufferedImage buffCheckIcon = ImageIO.read(new File(CHECK_ICON_PATH));
		buffCheckIcon = PicManip.resizeImg(buffCheckIcon, 20, 16);
		ImageIcon checkIcon = new ImageIcon(buffCheckIcon);

		BufferedImage buffCrossIcon = ImageIO.read(new File(CROSS_ICON_PATH));
		buffCrossIcon = PicManip.resizeImg(buffCrossIcon, 16, 16);
		ImageIcon crossIcon = new ImageIcon(buffCrossIcon);
		
		BufferedImage buffLock = ImageIO.read(new File(LOCK_ICON_PATH));
		buffLock = PicManip.resizeImg(buffLock, 16, 16);
		ImageIcon lockIcon = new ImageIcon(buffLock);
		
		for (int i = 0; i < table.getRowCount(); ++i)
		{
			for (int j = 0; j < table.getColumnCount(); ++j)
			{
				if (table.getValueAt(i, j).getClass() == Boolean.class)
				{
					if (((Boolean)table.getValueAt(i, j)).booleanValue())
					{
						if (j == 0)
						{
							table.setValueAt(lockIcon, i, j);
						}
						else if (j == 3)
						{
							table.setValueAt(checkIcon, i, j);
						}
					}
					else if (j == 3)
					{
						table.setValueAt(crossIcon, i, j);
					}
				}
			}
		}
	}
}
