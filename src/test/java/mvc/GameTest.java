package mvc;

import mvc.model.Game;
import mvc.model.Level;
import mvc.model.Tile;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private static Tile[][] generateBoard(int rowTiles, int columnTiles) {
        Tile board[][] = new Tile[rowTiles][columnTiles];
        for (int y = 0; y < columnTiles; y++) {
            for (int x = 0; x < rowTiles; x++) {
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setMine(Math.random() < 0.1);
                board[x][y] = tile;
            }
        }
        return board;
    }

    @Test
    public void testIsOnBoard() {
        Game game = new Game();
        Level level = Level.EASY;
        game.setRowTiles(5);
        game.setColumnTiles(5);
        assertEquals(game.isOnBoard(4, 4, level), true);
        assertEquals(game.isOnBoard(0, 0, level), true);
        assertEquals(game.isOnBoard(5, 5, level), false);
    }

    @Test
    public void testIsSolved() {
        Game game = new Game();
        game.setRowTiles(10);
        game.setColumnTiles(10);
        Tile board[][] = generateBoard(game.getRowTiles(), game.getColumnTiles());
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
        Tile board[][] = generateBoard(5, 5);
        assertEquals(8, game.getNeighbors(board[2][2], board, level).size());
        assertFalse(7 == game.getNeighbors(board[2][2], board, level).size());
        assertEquals(3, game.getNeighbors(board[0][0], board, level).size());
        assertFalse(8 == game.getNeighbors(board[4][4], board, level).size());
    }
}
