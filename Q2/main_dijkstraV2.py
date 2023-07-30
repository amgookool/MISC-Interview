"""The implmentation of Dijkstra's algorithm for the Flat Game Board Problem
- The board is represented as a grid of 0s and 1s and 2s
- 0s represent the empty cells
- 1s represent the walls
- 2s represent the goal (Point B)
- The ball can move in four directions: up, down, left, right
- The ball can move only to the empty cells
- The ball cannot move to the walls
- The ball cannot move outside the board
- The ball can move to the goal
- The ball can move only one cell at a time
- The ball cannot move diagonally
"""
import heapq


def is_valid_move(board: [[int]], x: int, y: int) -> bool:
    """This function checks if the move is valid or not

    Args:
        board ([[int]]): The grid of the game
        x (int): The specific row position
        y (int): The specific column position

    Returns:
        boolean: Return true if the move is valid, otherwise return false
    """
    # Get the number of rows and columns
    rows, cols = len(board), len(board[0])
    # Return true if x and y are in the range of the board and the position is not a wall
    return 0 <= x < rows and 0 <= y < cols and board[x][y] != 1


def get_neighbors(board: [[int]], x: int, y: int, prev_direction: tuple) -> [[int]]:
    # The directions the ball can move
    # Left -> (0, 1) Right -> (0, -1) Up -> (1, 0) Down -> (-1, 0)
    directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]
    # Initialize the neighbors list
    neighbors = []
    # Loop through the directions
    for dx, dy in directions:
        # Get the neighbor position
        nx, ny = x + dx, y + dy
        # Check if the previous direction is None
        if prev_direction is None:
            # Check if the move is valid
            if is_valid_move(board, nx, ny):
                # Add the neighbor to the neighbors list
                neighbors.append((nx, ny))
            # Continue to the next direction
            continue


        # Check if the move is valid and the neighbor is not the previous position and the neighbor is not the opposite direction
        if is_valid_move(board, nx, ny) and (dx, dy) != (prev_direction[0] * -1, prev_direction[1] * -1):
            # Add the neighbor to the neighbors list
            neighbors.append((nx, ny))
    # Return the neighbors list
    return neighbors


def dijkstras(board: [[int]], start: tuple, end: tuple) -> list:
    # Get the rows and cols of board
    rows, cols = len(board), len(board[0])
    # Create distance list that contains the distance of each position
    distance = [[float("inf") for _ in range(cols)] for _ in range(rows)]
    # Set the start position distance to 0
    distance[start[0]][start[1]] = 0
    # Create the priority queue that contains the position and the distance
    queue = [(0, start[0], start[1])]
    # Create the parent map that contains the parent of each position
    parent_map = {}

    # Loop until the queue is empty
    while queue:
        # Get the current position and distance
        dist, x, y = heapq.heappop(queue)

        # Check if the current position is the end position
        if (x, y) == end:
            path = []
            # Loop until the current position is the start position
            while (x, y) in parent_map:
                # Add the current position to the path
                path.insert(0, (x, y))
                # Get the parent position
                x, y = parent_map[(x, y)]
            # Add the start position to the path
            path.insert(0, (x, y))
            return path

        # Check if the current distance is greater than the distance of the current position
        if dist > distance[x][y]:
            continue

        # Initialize the previous direction
        prev_direction = None

        # Loop through the neighbors of the current position
        for nx, ny in get_neighbors(board=board, x=x, y=y, prev_direction=prev_direction):
            # Get the new distance if the neighbor is not a wall otherwise the distance is the same
            new_dist = dist + 1 if board[nx][ny] != 2 else dist
            # Check if the new distance is less than the distance of the neighbor
            if new_dist < distance[nx][ny]:
                # Update the distance of the neighbor
                distance[nx][ny] = new_dist
                # Add the neighbor to the queue
                heapq.heappush(queue, (new_dist, nx, ny))
                # Update the parent of the neighbor
                parent_map[(nx, ny)] = (x, y)
                # Update the previous direction
                prev_direction = (nx - x, ny - y)

    # Return None if the ball cannot move to the end position
    return None


def display_board(board: [[int]], path=None, start=None, end=None):
    # Check if the path is None
    if path is None:
        path = []

    for i, row in enumerate(board):
        for j, cell in enumerate(row):
            if (i, j) in path and (i, j) != start and (i, j) != end:
                print("*", end=" ")
            elif cell == 0:
                print("-", end=" ")
            elif cell == 1:
                print("#", end=" ")
            elif cell == 2:
                print("A", end=" ")
            elif cell == 3:
                print("B", end=" ")
        print()


if __name__ == "__main__":
    # Test case 1
    # board1 = [
    #     [2, 0, 1, 0, 0],
    #     [0, 0, 1, 1, 0],
    #     [0, 1, 0, 1, 0],
    #     [0, 0, 0, 1, 0],
    #     [0, 0, 0, 0, 3]
    # ]
    # start1 = (0, 0)
    # end1 = (4, 4)
    # print("Test case 1:")
    # display_board(board1)
    # shortest_path = bfs(board1, start1, end1)
    # print("Shortest Path:", shortest_path)
    # print("Shortest path length:", len(shortest_path) - 1)

    # Test case 2
    board2 = [
        [3, 0, 1, 0, 0],
        [0, 0, 0, 1, 0],
        [0, 1, 0, 1, 0],
        [0, 0, 0, 1, 0],
        [0, 0, 0, 2, 0]
    ]
    start2 = (4, 3)
    end2 = (0, 0)
    print("\nTest case 2:")
    display_board(board2)
    shortest_path = dijkstras(board2, start2, end2)
    print("Shortest path:", shortest_path)
    print("Shortest path length:", len(shortest_path) - 1)
    display_board(board2, shortest_path, start=start2, end=end2)
