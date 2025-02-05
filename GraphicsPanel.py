import random
import pygame
from Cell import Cell


class GraphicsPanel:
    ROWS = 10
    COLS = 10
    CELL_SIZE = 30  # This is used to calculate where cells are drawn
    NUM_BOMBS = 10

    def __init__(self):
        self.width = self.COLS * self.CELL_SIZE
        self.height = self.ROWS * self.CELL_SIZE

        # Create a 2D array of Cell objects
        self.cells = [
            [Cell(col * self.CELL_SIZE, row * self.CELL_SIZE) for col in range(self.COLS)]
            for row in range(self.ROWS)
        ]

        self.place_bombs(self.NUM_BOMBS)
        self.calculate_neighbor_bomb_counts()

    def place_bombs(self, bomb_count):
        self.cells[0][0].set_bomb()

    def calculate_neighbor_bomb_counts(self):
        for row in range(self.ROWS):
            for col in range(self.COLS):
                count = self.count_neighbor_bombs(row, col)
                self.cells[row][col].set_neighbor_bomb_count(count)

    def count_neighbor_bombs(self, row, col):
        count = 0
        # Loop over all neighboring cells (including diagonals)
        for i in range(-1, 2):
            for j in range(-1, 2):
                neighbor_row = row + i
                neighbor_col = col + j
                if 0 <= neighbor_row < self.ROWS and 0 <= neighbor_col < self.COLS:
                    if self.cells[neighbor_row][neighbor_col].has_bomb:
                        count += 1
        return count

    def reveal_cell(self, row, col):
        if 0 <= row < self.ROWS and 0 <= col < self.COLS:
            cell = self.cells[row][col]
            if not cell.is_revealed:
                cell.reveal()
                # If this cell has no neighboring bombs and isn’t a bomb, reveal surrounding cells
                if cell.get_neighbor_bomb_count() == 0 and not cell.has_bomb:
                    self.reveal_surrounding_cells(row, col)

    def reveal_surrounding_cells(self, row, col):
        for i in range(-1, 2):
            for j in range(-1, 2):
                # Skip the current cell
                if i != 0 or j != 0:
                    self.reveal_cell(row + i, col + j)

    def draw(self, surface):
        # Draw each cell onto the provided surface
        for row in self.cells:
            for cell in row:
                cell.draw(surface)

    def handle_mouse_event(self, event):
        if event.type == pygame.MOUSEBUTTONDOWN:
            x, y = event.pos
            col = x // self.CELL_SIZE
            row = y // self.CELL_SIZE
            if event.button == 1:  # Left click
                print("Left button clicked")
                self.reveal_cell(row, col)
            elif event.button == 3:  # Right click
                print("Right button clicked")
