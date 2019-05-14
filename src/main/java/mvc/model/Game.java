package mvc.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static int rowTiles;
    private static int columnTiles;

    public Game() {
    }

    public List<Tile> getNeighbors(Tile tile, Tile[][] board, Level level) {
        List<Tile> neighbours = new ArrayList<>();

        int[] points = new int[] {
                -1, -1,
                -1, 0,
                -1, 1,
                0, -1,
                0, 1,
                1, -1,
                1, 0,
                1, 1
        };

        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];

            int neighbourX = tile.getX() + dx;
            int neighbourY = tile.getY() + dy;

            if (isOnBoard(neighbourX, neighbourY, level)) {
                neighbours.add(board[neighbourX][neighbourY]);
            }
        }

        return neighbours;
    }

    public boolean isOnBoard(int row, int column, Level level){
        if(row >= 0 && row < level.rowTiles && column >= 0 && column < level.columnTiles) {
            return true;
        }
        return false;
    }

    public boolean isSolved(Tile[][] board){
        int revealed = 0;
        int mines = 0;
        for (int i = 0; i < rowTiles; i++) {
            for (int j = 0; j < columnTiles; j++) {
                if (board[i][j].isRevealed()) {
                    revealed++;
                }
                if (board[i][j].isMine() && board[i][j].isFlag()) {
                    mines++;
                }
            }
        }
        return rowTiles * columnTiles - revealed == mines;
    }

    public int getRowTiles() {
        return rowTiles;
    }

    public void setRowTiles(int rowTiles) {
        this.rowTiles = rowTiles;
    }

    public int getColumnTiles() {
        return columnTiles;
    }

    public void setColumnTiles(int columnTiles) {
        this.columnTiles = columnTiles;
    }
}
