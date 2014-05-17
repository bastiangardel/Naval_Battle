package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import view.actionListeners.ALServerCreation;
import view.customSwingComp.JButtonDesign;
import view.utils.ComponentMover;
import view.utils.JTextFieldLimit;

public class UIServerCreation extends JFrame implements ItemListener 
{
	private static final long serialVersionUID = 2270003163430296836L;

	private static final String UI_NAME = "ui_serverCreation";
	private static final String MODE = "Mode: ";
	private static final String BONUS = "Bonus: ";
	private static final String NAME = "Nom: ";
	private static final String PASSWORD = "Mot de Passe: ";
	private static final String VS_AI = "vs IA";
	private static final String TWO_PLAYERS = "2 joueurs";
	private static final String SPY_SATELLITE = "Satellite espion";
	private static final String MINES = "Mines";
	private static final String MAX_CHAR = "Max. 20 caractères";
	private static final String WITH_PASS = "Avec mot de passe";
	private static final String CREATE = "Créer";
	private static final String CANCEL = "Annuler";
	
	private JCheckBox addPassCheck;
	private JPanel passArea;
	
	public UIServerCreation() throws IOException
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
	    JPanel left = new JPanel();
	    left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
	    left.setPreferredSize(new Dimension(83, 142));
	    JPanel right = new JPanel();
	    right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
	    JPanel top = new JPanel();
	    passArea = new JPanel();
	    JPanel buttons = new JPanel();

	    top.add(left);
        top.add(right);
        
        pane.add(top);
        pane.add(passArea);
        pane.add(buttons);
        
        passArea.setVisible(false);
        
		// contour
		pane.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

		// left
		JLabel modeText = new JLabel(MODE);
		modeText.setAlignmentX(Component.RIGHT_ALIGNMENT);
		left.add(modeText);
		
		left.add(Box.createRigidArea(new Dimension(0, 8))); // empty space
		
		JLabel bonusText = new JLabel(BONUS);
		bonusText.setAlignmentX(Component.RIGHT_ALIGNMENT);
		left.add(bonusText);
		
		left.add(Box.createRigidArea(new Dimension(0, 40))); // empty space
		
		JLabel nameText = new JLabel(NAME);
		nameText.setAlignmentX(Component.RIGHT_ALIGNMENT);
		left.add(nameText);

		// right
		JRadioButton buttonVsAI = new JRadioButton(VS_AI);
		JRadioButton buttonTwoPlayers = new JRadioButton(TWO_PLAYERS);
		ButtonGroup modeGroup = new ButtonGroup();
		modeGroup.add(buttonVsAI);
		modeGroup.add(buttonTwoPlayers);
		JPanel modeButtons = new JPanel();
		modeButtons.setLayout(new BoxLayout(modeButtons, BoxLayout.X_AXIS));
		modeButtons.add(buttonVsAI);
		modeButtons.add(buttonTwoPlayers);
		modeButtons.setAlignmentX(Component.LEFT_ALIGNMENT);
		right.add(modeButtons);

		JPanel checkBoxContainer = new JPanel();
		checkBoxContainer.setLayout(new BoxLayout(checkBoxContainer, BoxLayout.Y_AXIS));
		JCheckBox checkSpySat = new JCheckBox(SPY_SATELLITE);
		checkBoxContainer.add(checkSpySat);
		JCheckBox checkMines = new JCheckBox(MINES);
		checkBoxContainer.add(checkMines);
		checkBoxContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
		right.add(checkBoxContainer);

		right.add(Box.createRigidArea(new Dimension(0, 10))); // empty space
		
		JPanel nameFieldContainer = new JPanel();
		nameFieldContainer.setLayout(new BoxLayout(nameFieldContainer, BoxLayout.Y_AXIS));
		JTextField nameField = new JTextField(20);
		nameField.setDocument(new JTextFieldLimit(20));
		JLabel maxChar = new JLabel(MAX_CHAR);
		maxChar.setFont(new Font(UIManager.getDefaults().getFont("TabbedPane.font").getFontName(), Font.PLAIN, 10));

		nameFieldContainer.add(nameField);
		nameFieldContainer.add(maxChar);
		nameFieldContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
		right.add(nameFieldContainer);

		right.add(Box.createRigidArea(new Dimension(0, 10))); // empty space
		
		addPassCheck = new JCheckBox(WITH_PASS);
		addPassCheck.setAlignmentX(Component.LEFT_ALIGNMENT);
		right.add(addPassCheck);
		
		// pass area
		JLabel passText = new JLabel(PASSWORD);
		passArea.add(passText);
		JPasswordField passField = new JPasswordField(20);
		passField.setDocument(new JTextFieldLimit(20));
		passField.setAlignmentX(Component.LEFT_ALIGNMENT);
		passArea.add(passField);
		
		// buttons
		JButtonDesign buttonCreate = new JButtonDesign(CREATE);
		JButtonDesign buttonCancel = new JButtonDesign(CANCEL);
        buttons.add(buttonCreate);
        buttons.add(buttonCancel);

		// Listeners
		addPassCheck.addItemListener(this);
		buttonCreate.setActionCommand("create");
		buttonCancel.setActionCommand("cancel");
		
		ActionListener al = new ALServerCreation();
		buttonCreate.addActionListener(al);
		buttonCancel.addActionListener(al);
		
		pane.getRootPane().setDefaultButton(buttonCreate);
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		Object source = e.getItemSelectable();

		if (source == addPassCheck)
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
			{
				this.passArea.setVisible(true);
				this.pack();
			}
			else
			{
				this.passArea.setVisible(false);
				this.pack();
			}
		}
	}
}
