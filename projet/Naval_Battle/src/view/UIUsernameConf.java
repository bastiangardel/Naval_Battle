package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.actionListeners.ALUsernameConf;
import view.customSwingComp.JButtonDesign;
//import view.customSwingComp.JFrameWithBGimg;
import view.utils.ComponentMover;
import view.utils.JTextFieldLimit;

public class UIUsernameConf extends JFrame
{
	private static final long serialVersionUID = -5809380707006840995L;
	
	private static final String UI_NAME = "ui_usernameconf";
	private static final String NEW_NICKNAME = "Nouveau pseudo: ";
	private static final String MODIFY = "Modifier";
	private static final String CANCEL = "Annuler";
	
	public UIUsernameConf() throws IOException {
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
		
		JPanel firstLine = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel nicknameText = new JLabel(NEW_NICKNAME);
		JTextField nicknameField = new JTextField(20);
		nicknameField.setDocument(new JTextFieldLimit(20));
		nicknameText.setLabelFor(nicknameField);
		firstLine.add(nicknameText);
		firstLine.add(nicknameField);
		
		JPanel secondLine = new JPanel();
		JButtonDesign buttonModify = new JButtonDesign(MODIFY);
		JButtonDesign buttonCancel= new JButtonDesign(CANCEL);
		secondLine.add(buttonModify);
		secondLine.add(buttonCancel);
		
		firstLine.setAlignmentX(Component.LEFT_ALIGNMENT);
		secondLine.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		pane.add(firstLine);
		pane.add(secondLine);

		buttonModify.setActionCommand("modify");
		buttonCancel.setActionCommand("cancel");
		
		ActionListener al = new ALUsernameConf();
		buttonModify.addActionListener(al);
		buttonCancel.addActionListener(al);
		
		pane.getRootPane().setDefaultButton(buttonModify);
	}
}
