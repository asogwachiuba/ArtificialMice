import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Introduction extends JFrame implements ActionListener{
	
	JRadioButton  wild_type, pet_type, red, blue, white, gray, up, down, left, right;
	
	JButton play;
	
	String selected_type, selected_color, selected_direction;
	

	public static void main(String[] args) {
		new Introduction();
	}
	
	public Introduction () {
		this.setLayout(new GridLayout(4,1, 20,10));
		
		
		// Rat Type
		JPanel rat_type = new JPanel();
		rat_type.setBorder(BorderFactory.createTitledBorder("Select Rat Type"));
		
		wild_type = new JRadioButton("Wild Rat");
		pet_type = new JRadioButton("Pet Rat");
		
		ButtonGroup type_group = new ButtonGroup();
		type_group.add(wild_type);
		type_group.add(pet_type);
		
		rat_type.add(wild_type);
		rat_type.add(pet_type);
		
		this.add(rat_type);
		
		// Select Rat Color
		JPanel color_panel = new JPanel();
		color_panel.setBorder(BorderFactory.createTitledBorder("Select Rat Color"));
		
		ButtonGroup color_group = new ButtonGroup();
		red = new JRadioButton("Red");
		blue = new JRadioButton("Blue");
		white = new JRadioButton("White");
		gray = new JRadioButton("Gray");
		
		color_group.add(red);
		color_group.add(blue);
		color_group.add(white);
		color_group.add(gray);
		
		color_panel.add(red);
		color_panel.add(blue);
		color_panel.add(white);
		color_panel.add(gray);
		
		this.add(color_panel);
		
		// Select Rat Start Direction
		JPanel direction_panel = new JPanel();
		direction_panel.setBorder(BorderFactory.createTitledBorder("Select Rat Starting Direction"));
		
		ButtonGroup direction_group = new ButtonGroup();
		up = new JRadioButton("Up");
		down = new JRadioButton("Down");
		left = new JRadioButton("Left");
		right = new JRadioButton("Right");
		
		direction_group.add(up);
		direction_group.add(down);
		direction_group.add(left);
		direction_group.add(right);
		
		direction_panel.add(up);
		direction_panel.add(down);
		direction_panel.add(left);
		direction_panel.add(right);
		
		this.add(direction_panel);
		
		// play button
		 play = new JButton("Play");
		 JPanel buttonPanel = new JPanel();
		 buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));  // Padding around button panel
		 buttonPanel.add(play);
		 play.addActionListener(this);
		 this.add(buttonPanel);
		
		
		
		
		// Create frame
		this.setTitle("Select Your Mouse Character");
		this.setBounds(0, 0, 400, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == play) {
			if(!wild_type.isSelected() && !pet_type.isSelected()) {
				JOptionPane.showMessageDialog(this, "Select the rat type to play");
				return;
			}
			
			if(!red.isSelected() && !blue.isSelected() && !white.isSelected() && !gray.isSelected()) {
				JOptionPane.showMessageDialog(this, "Select the rat color to play");
				return;
			}
			
			if(!up.isSelected() && !down.isSelected() && !left.isSelected() && !right.isSelected()) {
				JOptionPane.showMessageDialog(this, "Select the rat start direction to play");
				return;
			}
			// This gets the selected values	
			selected_type = wild_type.isSelected() ? wild_type.getText() : pet_type.getText();
			selected_color = red.isSelected() ? red.getText() : white.isSelected() ? white.getText() : blue.isSelected() ? blue.getText() : gray.getText();
			selected_direction = up.isSelected() ? up.getText() : down.isSelected() ? down.getText() : left.isSelected() ? left.getText() : right.getText();
			new MainClass(selected_type, selected_color, selected_direction);
			this.dispose();
		}
		
		
	}

}
