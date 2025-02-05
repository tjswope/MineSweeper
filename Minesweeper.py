import pygame
from GraphicsPanel import GraphicsPanel

def main():
    # Initialize Pygame
    pygame.init()

    # Create the game panel
    panel = GraphicsPanel()

    # Set up the display window using the panel's dimensions
    screen = pygame.display.set_mode((panel.width, panel.height))
    pygame.display.set_caption("MineSweeper")
    clock = pygame.time.Clock()

    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
            else:
                panel.handle_mouse_event(event)

        # Fill the background (white) and draw the cells
        screen.fill((255, 255, 255))
        panel.draw(screen)
        pygame.display.flip()

        clock.tick(60)  # Limit the frame rate to 60 FPS

    pygame.quit()

if __name__ == '__main__':
    main()
