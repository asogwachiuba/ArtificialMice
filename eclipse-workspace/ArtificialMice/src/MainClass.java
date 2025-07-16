import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.util.Random;

import java.awt.event.ActionListener;
import javax.swing.*;


public class MainClass extends JFrame implements ActionListener{
	
	private JLabel[] cells;
	private final int cell_length = 60;
	private AbstractMouse mouse;
	Space space = new Space();
	JButton up, down, left, right;
	JLabel rat_label;
	
	Color paintColor(String color) {
		switch(color) {
		case "red":
			return Color.RED;
		case "gray":
			return Color.GRAY;
		case "white":
			return Color.WHITE;
		case "blue":
			return Color.BLUE;
		default:
			return Color.PINK;
		}
	}
	
	String ratImagePath(String color) {
		switch(color) {
		case "red":
			return "/Users/user/eclipse-workspace/ArtificialMice/src/red_rat.jpg";
		case "gray":
			return "/Users/user/eclipse-workspace/ArtificialMice/src/gray_rat.jpg";
		case "white":
			return "/Users/user/eclipse-workspace/ArtificialMice/src/white_rate.jpg";
		case "blue":
			return "/Users/user/eclipse-workspace/ArtificialMice/src/blue_rat.jpg";
		default:
			return "/Users/user/eclipse-workspace/ArtificialMice/src/white_rate.jpg";
		}
	}
	
	
	MainClass(String type, String color, String direction) {
		
		final Random random = new Random();
		int petmouse_x_cordinate = random.nextInt(space.getMax_x_axis());
		int petmouse_y_cordinate = random.nextInt(space.getMax_y_axis());
		int wildmouse_x_cordinate = random.nextInt(space.getMax_x_axis());
		int wildmouse_y_cordinate = random.nextInt(space.getMax_y_axis());
		PetMouse pet_mouse = new PetMouse(color, petmouse_x_cordinate, petmouse_y_cordinate, space.getCellColor(petmouse_x_cordinate, petmouse_y_cordinate) , Direction.down);
		WildMouse wild_mouse = new WildMouse(color, wildmouse_x_cordinate, wildmouse_y_cordinate, space.getCellColor(wildmouse_x_cordinate, wildmouse_y_cordinate) , Direction.right);
		this.mouse =  type.equals("Wild Rat") ? wild_mouse : pet_mouse;
		
		this.setLayout(new BorderLayout());
		
		// Space UI
		JPanel space_panel = new JPanel();
		space_panel.setLayout(new GridLayout(space.getMax_y_axis(),space.getMax_x_axis()));
		int total_cells = space.getMax_y_axis() * space.getMax_x_axis();
		cells = new JLabel[total_cells];
		int current_cell_index = 0;
		for(int i = 0; i < space.getMax_y_axis(); i++) {
			for(int j = 0; j < space.getMax_x_axis(); j++) {
				JLabel label = new JLabel();
				label = new JLabel();
				label.setOpaque(true);
				label.setBackground(paintColor(space.getCellColor(i, j))); 
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				label.setBounds(j*cell_length, i*cell_length, cell_length, cell_length);
				cells[current_cell_index] = label;
				space_panel.add(cells[current_cell_index]);
				current_cell_index++;
			}
		}
		
		// mouse UI
		ImageIcon rat_icon = new ImageIcon(ratImagePath(mouse.color));
		Image rat_image = rat_icon.getImage().getScaledInstance(cell_length, cell_length, Image.SCALE_SMOOTH);
		rat_icon = new ImageIcon(rat_image);
		rat_label = new JLabel(rat_icon);
		rat_label.setOpaque(true);
		rat_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("The mouse was clicked");
				// Get the cell color to know what the next move will be then 
				String cell_color = space.getCellColor(mouse.current_position[1], mouse.current_position[0]);
				mouse.nextMove(cell_color);
				// update the space cell with its new color 
				space.setCellColor(mouse.current_position[1], mouse.current_position[0], mouse.getCellColor());
				// update the cell UI
				int cell_index = twoDToOneDIndex(mouse.current_position[1], mouse.current_position[0], space.getMax_x_axis());
				cells[cell_index].setBackground(paintColor(mouse.getCellColor()));
				System.out.println("The cell index am painting is in: \n2-D is [ " + mouse.current_position[0] + " , " + mouse.current_position[1] + " ]");
				cells[cell_index].repaint();
				
				mouse.move();
				
				// update the rat UI
				rat_label.setBounds(mouse.current_position[0] * cell_length, mouse.current_position[1] * cell_length, cell_length, cell_length);
			}
		});		
		
		
		// Game panel
		JLayeredPane gamepanel = new JLayeredPane();
		space_panel.setBounds(0, 0, space.getMax_x_axis() * cell_length, space.getMax_y_axis() * cell_length);
		System.out.println( "Mouse Info: " + this.mouse);
		// Position the rat image label based on the mouse's coordinates
		rat_label.setBounds(this.mouse.current_position[0] * cell_length, this.mouse.current_position[1] * cell_length, cell_length, cell_length);
		gamepanel.add(space_panel, JLayeredPane.DEFAULT_LAYER); // Background layer for the grid
		gamepanel.add(rat_label, JLayeredPane.PALETTE_LAYER); // On top of the background layout
		
		
		// Adding the game panel to the frame
		this.add(gamepanel, BorderLayout.CENTER);
		
		// Game controls and Rat state
		JPanel game_control_panel = new JPanel();
		game_control_panel.setLayout(new GridLayout(1,2));
		
		// Display mouse state
        JLabel mouse_info = new JLabel(this.mouse.toString());
        mouse_info.setHorizontalAlignment(SwingConstants.CENTER);
        game_control_panel.add(mouse_info, BorderLayout.CENTER);

		
		// Display keypad 
        JPanel key_panel = new JPanel(new GridLayout(3, 3));
        key_panel.setPreferredSize(new Dimension(200, 200));
        up = new JButton("Up");
        up.addActionListener(this);
        down = new JButton("Down");
        down.addActionListener(this);
        left = new JButton("Left");
        left.addActionListener(this);
        right = new JButton("Right");
        right.addActionListener(this);
        key_panel.add(new JLabel()); // Empty placeholder
        key_panel.add(up);
        key_panel.add(new JLabel()); // Empty placeholder
        key_panel.add(left);
        key_panel.add(new JLabel()); // Centre empty placeholder
        key_panel.add(right);
        key_panel.add(new JLabel()); // Empty placeholder
        key_panel.add(down);
        key_panel.add(new JLabel()); // Empty placeholder
		
        game_control_panel.add(key_panel, BorderLayout.SOUTH);
        this.add(game_control_panel, BorderLayout.SOUTH);
		
		// Make the frame visible
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Mouse Game");
		this.setBounds(0, 0, space.getMax_x_axis() * cell_length, (space.getMax_y_axis() * cell_length)+300);
		this.setVisible(true);
	}
	
	  public int twoDToOneDIndex(int row, int col, int numCols) {
	        return row * numCols + col;
	    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == up) {
			mouse.move(Direction.up);
			// update the rat UI
			System.out.println("Clicked Up");
			rat_label.setBounds(mouse.current_position[0] * cell_length, mouse.current_position[1] * cell_length, cell_length, cell_length);
		} 
		if(e.getSource() == down) {
			mouse.move(Direction.down);
			// update the rat UI
			System.out.println("Clicked Down");
			rat_label.setBounds(mouse.current_position[0] * cell_length, mouse.current_position[1] * cell_length, cell_length, cell_length);
		} 
		if(e.getSource() == left) {
			mouse.move(Direction.left);
			// update the rat UI
			System.out.println("Clicked Left");
			rat_label.setBounds(mouse.current_position[0] * cell_length, mouse.current_position[1] * cell_length, cell_length, cell_length);
		}
		if(e.getSource() == right) {
			mouse.move(Direction.right);
			// update the rat UI
			System.out.println("Clicked Right");
			rat_label.setBounds(mouse.current_position[0] * cell_length, mouse.current_position[1] * cell_length, cell_length, cell_length);
		} 
	}

}
