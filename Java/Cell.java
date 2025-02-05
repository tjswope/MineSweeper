import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Cell {
    private ImageIcon image;
    private int x_coordinate;
    private int y_coordinate;
    private int neighborBombCount; // Number of bombs around this cell
    private boolean isRevealed;    // Whether this cell has been revealed
    private boolean hasBomb;       // Whether this cell contains a bomb

    public Cell(int x, int y) {
        this.x_coordinate = x;
        this.y_coordinate = y;
        this.neighborBombCount = 0;
        this.isRevealed = false;
        this.hasBomb = false;
        setImage(10); // Default image (unrevealed cell)
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    public void setBomb() {
        this.hasBomb = true;
    }

    public int getNeighborBombCount() {
        return neighborBombCount;
    }

    public void setNeighborBombCount(int count) {
        this.neighborBombCount = count;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void reveal() {
        this.isRevealed = true;

        // Update the image based on the state
        if (hasBomb) {
            setImage(9); // Bomb image
        } else {
            setImage(neighborBombCount); // Use neighbor bomb count for the image
        }
    }

    public void setImage(int cellType) {
        // Change the image based on the cell type
        String imagePath = "images/" + cellType + ".png";
        ClassLoader cldr = this.getClass().getClassLoader();
        URL imageURL = cldr.getResource(imagePath);

        if (imageURL != null) {
            image = new ImageIcon(imageURL);
        } else {
            System.out.println("Error: Could not load image " + imagePath);
        }
    }


    
    public void draw(Graphics g, Component c) {
        if (image != null) {
            // Get the Image from ImageIcon
            Image img = image.getImage();

            // Scale the image to twice its original size
            int width = img.getWidth(null) * 2;
            int height = img.getHeight(null) * 2;
            
            // Draw the scaled image
            Image scaledImage = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);
            resizedIcon.paintIcon(c, g, x_coordinate, y_coordinate);
        }
    }
    

}
