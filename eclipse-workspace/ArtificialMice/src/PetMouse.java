import java.util.Arrays;

public class PetMouse extends AbstractMouse {
	
	

	public PetMouse(String mouse_color, int x_coordinate, int y_coordinate, String cell_color, Direction startDirection) {
		super(mouse_color, x_coordinate, y_coordinate, startDirection);
		super.cell_color = cell_color;
	}
	
	

	@Override
	public void nextMove(String current_cell_color) {
		if(current_cell_color == "gray") {
			turnRight();
			cell_color = this.color;
		} else {
			turnLeft();
			cell_color = "gray";
		}
		
	}
	
	

	@Override
	public String toString() {
		return "PetMouse \n[cell_color=" + cell_color + ", \ncurrent_position=" + Arrays.toString(current_position)
				+ ", \ndirection=" + direction + ", \ncolor=" + color + "]";
	}

}
