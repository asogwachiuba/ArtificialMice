import java.util.Arrays;

public class WildMouse extends AbstractMouse{
	
	private boolean is_tired = false;
	

	public WildMouse(String mouse_color, int x_coordinate, int y_coordinate,String cell_color, Direction startDirection) {
		super(mouse_color, x_coordinate, y_coordinate,startDirection);
		this.cell_color = cell_color;
	}

	/**
	 * @return the cell_color
	 */
	public String getCell_color() {
		return cell_color;
	}
	
	

	@Override
	public String toString() {
		return "WildMouse \n[is_tired=" + is_tired + ", \ncell_color=" + cell_color + ", \ncurrent_position="
				+ Arrays.toString(current_position) + ", \ndirection=" + direction + ", \ncolor=" + color + "]";
	}

	@Override
	public void nextMove(String current_cell_color) {
		// is tired and gray
		if(is_tired && current_cell_color == "gray") {
			cell_color = color;
			is_tired = false;
		} 
		// is tired and not gray
		else if(is_tired && current_cell_color != "gray") {
			cell_color = "gray";
			is_tired = false;
		} 
		// not tired and gray
		else if(!is_tired && current_cell_color == "gray") {
			turnRight();
			cell_color = color;
			is_tired = true;
		} 
		// not tired and not gray
		else if (!is_tired && current_cell_color != "gray") {
			turnLeft();
			cell_color = "gray";
			is_tired = true;
		}
		
	}

}
