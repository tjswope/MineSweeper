import os
import pygame

class Cell:
    def __init__(self, x, y):
        self.x_coordinate = x
        self.y_coordinate = y
        self.neighbor_bomb_count = 0  # Number of bombs around this cell
        self.is_revealed = False      # Whether this cell has been revealed
        self.has_bomb = False         # Whether this cell contains a bomb
        self.image = None
        self.set_image(10)  # Default image (unrevealed cell)

    def has_bomb_method(self):
        return self.has_bomb

    def set_bomb(self):
        self.has_bomb = True

    def get_neighbor_bomb_count(self):
        return self.neighbor_bomb_count

    def set_neighbor_bomb_count(self, count):
        self.neighbor_bomb_count = count

    def reveal(self):
        self.is_revealed = True
        # Update the image based on the cell state
        if self.has_bomb:
            self.set_image(9)  # Bomb image
        else:
            self.set_image(self.neighbor_bomb_count)  # Use neighbor bomb count for the image

    def set_image(self, cell_type):
        # Construct the image path
        image_path = os.path.join("images", f"{cell_type}.png")
        try:
            #
            img = pygame.image.load(image_path).convert_alpha()
            # Scale the image to twice its original size (similar to Java’s scaling)
            width = img.get_width() * 2
            height = img.get_height() * 2
            self.image = pygame.transform.smoothscale(img, (width, height))
        except pygame.error as e:
            print(f"Error: Could not load image {image_path}: {e}")
            self.image = None

    def draw(self, surface):
        if self.image:
            surface.blit(self.image, (self.x_coordinate, self.y_coordinate))
