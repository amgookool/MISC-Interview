"""The implmentation of BFS algorithm for the Flat Game Board Problem
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

from collections import deque


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


def get_neighbors(x: int, y: int) -> [[int]]:
    """This function returns the neighbors of the current position

    Args:
        x (int): The specific row position
        y (int): The specific column position

    Returns:
        [[int]]: Return the neighbors of the current position
    """
    # Return the neighbors of the current position
    # the left cell (x-1, y)
    # the right cell (x+1, y)
    # the upper cell (x, y-1)
    # the lower cell (x, y+1)
    return [(x-1, y), (x+1, y), (x, y-1), (x, y+1)]


def bfs(board: [[int]], start: tuple, end: tuple) -> int:
    # Create queue that contains the start position and the distance
    # queue = deque([(start[0], start[1], 0)])
    queue = deque([(start[0], start[1])])
    # Create visited set that contains the visited positions
    visited = set()
    # Create parent map that contains the parent of each position
    parent_map = {}

    # As long as the queue is not empty
    while queue:
        # Get the current position and the distance
        x, y= queue.popleft()
        # Add the current position to the visited set
        visited.add((x, y))

        # # Check if the current position is the end position
        # if (x, y) == end:
        #     return steps

        # Check if the current position is the end position
        if (x, y) == end:
            # Create path list that contains the path from the start position to the end position
            path = []
            # Add the end position to the path list
            while (x, y) in parent_map:
                # Add the current position to the path list
                path.insert(0, (x, y))
                # Get the parent of the current position
                x, y = parent_map[(x, y)]
            # Add the start position to the path list
            path.insert(0, start)
            return path

        # Iterate over the neighbors of the current position
        for nx, ny in get_neighbors(x, y):
            # Check if the neighbor is valid and not visited
            if is_valid_move(board, nx, ny) and (nx, ny) not in visited:
                # If the ball can move to the neighbor, add the neighbor to the queue
                queue.append((nx, ny))
                # Add the neighbor to the visited set
                visited.add((nx, ny))
                parent_map[(nx, ny)] = (x, y)

    # Return -1 if the ball cannot move to the end position
    return None


def display_board(board: [[int]], path=None):
    # Check if the path is None
    if path is None:
        path = []

    for i, row in enumerate(board):
        for j, cell in enumerate(row):
            if (i, j) in path:
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
    shortest_path = bfs(board2, start2, end2)
    print("Shortest path:", shortest_path)
    print("Shortest path length:", len(shortest_path) - 1)
