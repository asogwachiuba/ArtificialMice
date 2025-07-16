
/**
 * This defines the various direction of movement available
 * */
enum Direction {
	left, right, up, down,
}

public abstract class AbstractMouse {
	
	 public String printArray(Object[] array) {
	        String str = "[ ";
	        for (int i = 0; i < array.length; i++) {
	            str += array[i] + ", ";
	        }
	        return str + "]";
	    }

	    public String printArray(int[] array) {
	        String str = "[ ";
	        for (int i = 0; i < array.length; i++) {
	            str += array[i] + ", ";
	        }
	        return str + "]";
	    }
 /**
  * This represents the location of the mouse on the 
  * space grid
  * */
 int[] current_position = new int [2];
 /**
  * This indicates the mouse next direction
  * */
 Direction direction;
 
 /**
  * This indicates the colour of the mouse
  * */
 String color;
 
 protected String cell_color;
 
 String getCellColor() {
		return cell_color;
	}
 
 /**
  * This defines the next movement of the mouse
  * */
 public abstract void nextMove(String current_cell_color);
 
 public void turnRight () {
	 System.out.println("Current direction is " + direction);
	 switch(direction) {
	case down:
		System.out.println("The down case was selected"); 
		direction = Direction.left;
		break;
	case left:
		System.out.println("The left case was selected");
		direction = Direction.up;
		break;
	case right:
		System.out.println("The right case was selected");
		direction = Direction.down;
		break;
	case up:
		System.out.println("The up case was selected");
		direction = Direction.right;
		break;
	default:
		System.out.println("The default case was selected");
		break;
	 
	 }
	 System.out.println("New turn right direction is " + direction);
 }
 
 public void turnLeft () {
	 System.out.println("Current direction is " + direction);
	 switch(direction) {
	case down:
		direction = Direction.right;
		break;
	case left:
		direction = Direction.down;
		break;
	case right:
		direction = Direction.up;
		break;
	case up:
		direction = Direction.left;
		break;
	default:
		break;
	 
	 }
	 
	 System.out.println("New turn left direction is " + direction);
 }
 
 public void move() {
	    System.out.println("Current position is " + printArray(current_position));
	    switch (direction) {
	        case down:
	            // If its at the bottom already, it goes to the top
	            current_position[0] = (current_position[0] + 1) % 7;
	            break;
	        case left:
	            // If its at the left border, wrap around to the right border
	            current_position[1] = (current_position[1] - 1 + 7) % 7;
	            break;
	        case right:
	            // If its at the right border, wrap around to the left border
	            current_position[1] = (current_position[1] + 1) % 7;
	            break;
	        case up:
	            // If its at the top border, wrap around to the bottom
	            current_position[0] = (current_position[0] - 1 + 7) % 7;
	            break;
	    }
	    System.out.println("New position is " + printArray(current_position));
	}
 
 public void move(Direction direction) {
	    System.out.println("Current position is " + printArray(current_position));
	    switch (direction) {
	        case down:
	        	// If its at the right border, wrap around to the left border
	            current_position[1] = (current_position[1] + 1) % 7;
	            break;
	        case left:
	        	// If its at the top border, wrap around to the bottom
	            current_position[0] = (current_position[0] - 1 + 7) % 7;
	            break;
	        case right:
	           // If its at the bottom already, it goes to the top
	            current_position[0] = (current_position[0] + 1) % 7;
	            break;
	        case up:
	            // If its at the left border, wrap around to the right border
	            current_position[1] = (current_position[1] - 1 + 7) % 7;
	            break;
	    }
	    System.out.println("New position is " + printArray(current_position));
	}

 
 public String toString() {
	 return this.getClass().getName() + "( cell(" + current_position[0] + "," 
     + current_position[1] + ")," + direction.name() + "," + color;
 }
 
 public AbstractMouse( String color, int x_coordinate, int y_coordinate, Direction direction) {
	this.color = color;
	current_position[0] = y_coordinate;
	current_position[1] = x_coordinate;
	this.direction = direction;
 }
}




















