package mvc.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class GameTest {

    private static Tile[][] generateBoard(int rowTiles, int columnTiles) {
        Tile[][] board = new Tile[rowTiles][columnTiles];
        for (int y = 0; y < columnTiles; y++) {
            for (int x = 0; x < rowTiles; x++) {
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                board[x][y] = tile;
            }
        }
        return board;
    }

    public void setMines(Level level, Tile[][] board){

        Random row = new Random();
        Random column = new Random();
        int mineIndexRow;
        int minIndexColumn;
        int i = 0;
        while (i < level.mines) {
            mineIndexRow = row.nextInt(level.rowTiles);
            minIndexColumn = column.nextInt(level.columnTiles);
            if (board[mineIndexRow][minIndexColumn].isMine()) {
                continue;
            }
            i++;
            board[mineIndexRow][minIndexColumn].setMine(true);
        }
    }

    @Test
    public void testGame(){
        Game game = new Game();
        assertNotNull(game);
    }

    @Test
    public void testIsOnBoard() {
        Game game = new Game();
        Level level = Level.EASY;
        game.setRowTiles(level.rowTiles);
        game.setColumnTiles(level.columnTiles);
        assertTrue(game.isOnBoard(level.rowTiles-1, level.columnTiles-1, level));
        assertTrue(game.isOnBoard(0, 0, level));
        assertFalse(game.isOnBoard(level.rowTiles, level.columnTiles, level));
    }

    @Test
    public void testIsSolved() {
        Game game = new Game();
        game.setRowTiles(10);
        game.setColumnTiles(10);
        Tile[][] board = generateBoard(game.getRowTiles(), game.getColumnTiles());
        assertFalse(game.isSolved(board));
        for (int i = 0; i < game.getRowTiles(); i++) {
            for (int j = 0; j < game.getColumnTiles(); j++) {
                if (board[i][j].isMine()) {
                    board[i][j].setFlag(true);
                    continue;
                }
                if (!board[i][j].isRevealed()) {
                    board[i][j].setRevealed(true);
                }
            }
        }
        assertTrue(game.isSolved(board));
    }

    @Test
    public void testGetNeighbors() {
        Game game = new Game();
        Level level = Level.EASY;
        game.setRowTiles(level.rowTiles);
        game.setColumnTiles(level.columnTiles);
        Tile[][] board = generateBoard(level.rowTiles, level.columnTiles);
        assertEquals(8, game.getNeighbors(board[2][2], board, level).size());
        assertNotEquals(7, game.getNeighbors(board[2][2], board, level).size());
        assertEquals(3, game.getNeighbors(board[0][0], board, level).size());
        assertNotEquals(8, game.getNeighbors(board[4][4], board, level).size());
    }

    @Test
    public void testMines(){
        Game game = new Game();
        Level level = Level.HARD;
        game.setRowTiles(level.rowTiles);
        game.setColumnTiles(level.columnTiles);
        Tile[][] board = generateBoard(level.rowTiles,level.columnTiles);
        setMines(level, board);
        int mines = 0;
        for(int i=0; i< level.rowTiles; i++) {
            for(int j=0; j<level.columnTiles; j++) {
                if (board[i][j].isMine())
                    mines++;
            }
        }
        assertEquals(mines, level.mines);
    }
}
