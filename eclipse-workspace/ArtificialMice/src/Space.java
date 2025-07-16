
public class Space {
  String[] [] grid ;
 final private int max_x_axis = 7;
  
final private int max_y_axis = 7;
 
 public Space() {
	 grid = new String[max_y_axis][max_x_axis];
	 initializeGrid();
 }
 
/**
 * @return the max_x_axis
 */
public int getMax_x_axis() {
	return max_x_axis;
}

/**
 * @return the max_y_axis
 */
public int getMax_y_axis() {
	return max_y_axis;
}

 
 public void setCellColor(int x_axis, int y_axis, String color ) {
	 grid[y_axis][x_axis] = color;
 }
 
 public String getCellColor(int x_axis, int y_axis) {
	 return grid[y_axis][x_axis];
 }
  
 private void initializeGrid() {
	  for(int row = 0; row < max_y_axis; row++) {
		  for(int column = 0; column < max_x_axis; column++) {
			  grid[row][column] = "gray";
		  }
	  }
  }
}
