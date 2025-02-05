import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GraphicsPanel extends JPanel implements MouseListener{
    private Cell[][] cells; // 2D array of Cell objects
    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int NUM_BOMBS = 10;

    public GraphicsPanel() {
    	setPreferredSize(new Dimension(ROWS * 30, COLS * 30)); 
        this.addMouseListener(this);
        this.cells = new Cell[ROWS][COLS];

        // Initialize the Cell objects
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cells[row][col] = new Cell(col * 30, row * 30); // Assuming 32x32 cell size
            }
        }

        // Randomly place bombs and calculate neighbor bomb counts
        placeBombs(10); // Place 10 bombs
        calculateNeighborBombCounts();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw each cell
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                cells[row][col].draw(g, this);
            }
        }
    }

    public void revealCell(int row, int col) {
        if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
            Cell cell = cells[row][col];
            if (!cell.isRevealed()) {
                cell.reveal();
                repaint();

                // If the cell has no neighboring bombs, reveal surrounding cells
                if (cell.getNeighborBombCount() == 0 && !cell.hasBomb()) {
                    revealSurroundingCells(row, col);
                }
            }
        }
    }

    private void revealSurroundingCells(int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) 	
                	revealCell(row + i, col + j);
            }
        }
    }

    private void placeBombs(int bombCount) {

    }

    private void calculateNeighborBombCounts() {
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                int count = countNeighborBombs(row, col);
                cells[row][col].setNeighborBombCount(count);
            }
        }
    }

    private int countNeighborBombs(int row, int col) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighborRow = row + i;
                int neighborCol = col + j;

                if (neighborRow >= 0 && neighborRow < ROWS && neighborCol >= 0 && neighborCol < COLS) {
                    if (cells[neighborRow][neighborCol].hasBomb()) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("Left button clicked");
            revealCell(e.getY()/30, e.getX()/30);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            System.out.println("Right button clicked");
        }
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
