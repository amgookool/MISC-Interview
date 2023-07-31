# Flat Game Board with Rolling Ball

You are required to design a program that finds the shortest path from **point A** to **point B** on a _**flat**_ game board.
The board will have walls placed at runtime.

The ball on the board can only roll **orthogonally**, and once it starts moving, it cannot stop until it encounters a wall.

You task is to implement an algorithm that efficiently calculates and displays the shortest path between points A and B under these constraints.

## Requirements Clarification

**Board Representation Sizing**: A flat game board indicates a 2-dimensional array (matrix) which consists of rows and columns. Are the number of rows and columns equal (square matrix) or are they different?

_Note_: Knowing the board size can help optimize the memory usage and algorithm performance. 

**Input of Program**: How will the program receive input for the board and the positions of points A and B? Will it be through a GUI, a file or command-line arguments?

**Wall Placement**: How are walls represented on the board? Are they placed at specific coordinates or defined as areas on the board? Are there any restrictions on wall placements? (eg. minimum distance between points A and B)

**Valid Moves**: The orthogonal movement of the ball means that the ball only moves left, right, up and down NOT diagonally... correct?

**Multiple Paths**: Should the algorithm find all possible shortest paths from A to B, or is it sufficient to find just one of them?

## Approach

1. **Board Initialization**: Create data structure to represent the game board and initialize it with the given walls and positions of Points A and B.
2. **Graph Construction**: Convert the game board into a graph representation, where each cell is a node, and there is an edge between neighboring (adjacent) cells if the ball can move between them. 
3. **Shortest Path Algorithm**: Use a suitable shortest path algorithm to fuind the minimum distance between point A and point B on the constructed graph. Algorithms I know are _Breadth-First Search_ and *Dijkstra's* algorithms.
4. **Path Tracing**: Once the shortest path is found, trace back the path from point B to point A using the information obtained during the shortest path algorithm execution. This will give the sequence of moves ewquired to reach point B from point A.
5. **Display**: Depending on the input method and program requirements, display the game board with the calculated shortest path, highlighting the cells that are part of the path. 
6. **Error Handling**: Implement appropiate error handling to deal with scenarios like invalid input, unreachable points, or unpathable areas.
7. **Optimization**: Depending on the contraints, consider possible optimizations like early stopping in the pathfinding algorithm if it is known that only one shortest path is needed.
8. **Testing**: Test the program with various board sizes, wall configurations, and point positions to ensure its correctness and efficiency. 


## Implementation (Breadth First Search)



## Implementation (Dijkstra's Algorithm Approach)

### Understanding the problem

The problem is to find the shortest path from a starting point (A) to an ending point (B) on a game board that may have walls (indicated by '#'). 
The ball can only move in one direction until it encounters a wall, and then it can choose to move left, right, up, or down from that point. 
The goal is to find the shortest path for the ball to reach the ending point while avoiding walls.

### Representing the ball
We will represent the game board as a 2D grid. Each cell in the grid will have one of the following values:

- 'A' to represent the starting point.
- 'B' to represent the ending point.
- '-' to represent an empty path.
- '#' to represent a wall.
- '*' to represent the ball's current position during the algorithm execution

### Dijkstra's ALgorithm
Dijkstra's algorithm is a graph traversal algorithm that helps find the shortest path from a starting node to all other nodes in a weighted graph. 
In our case, we will treat the game board as a graph, where each cell is a node, and the cost to move from one cell to an adjacent cell is 1, except when moving to a wall cell or revisiting a cell.

### Initialization
- We initialize the distance array to store the shortest distance from the starting point to each cell on the board. 
  - All distances are set to infinity except the starting point, which is set to 0.
- We use a priority queue to process cells based on their distances from the starting point. 
  - Cells with smaller distances are processed first.


### Processing Cells
- Start with the starting point and add it to the priority queue with distance 0. 
- While the priority queue is not empty, do the following:
    - Pop the cell with the smallest distance from the priority queue.
    - If the popped cell is the ending point, we found the shortest path and can reconstruct it using a parent_map.
    - Otherwise, consider all neighboring cells that are not walls or already visited.
    - For each valid neighbor, update its distance if a shorter path is found, and add it to the priority queue.

### Reconstructing the path
Once we find the ending point, we can use the parent_map to reconstruct the shortest path from the starting point to the ending point. We start from the ending point and follow the parent pointers back to the starting point, adding each cell to the path in reverse order.

### Returning the result
Return the shortest path as a list of cell coordinates representing the ball's movement from the starting point to the ending point.


### Handling Ball Movement
During the algorithm execution, whenever the ball moves to a new cell, we update that cell's value in the board to '*' to visualize ball movement during the path reconstruction.
