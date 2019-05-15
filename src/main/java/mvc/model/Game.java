package mvc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Segédosztály a játék működéséhez.
 */
public class Game {

    /**
     * A megjelenített csempék sorainak száma.
     */
    private static int rowTiles;

    /**
     * A megjelenített csempék oszlopainak száma.
     */
    private static int columnTiles;

    public Game() {
    }

    /**
     * Visszaadja a paraméterként kapott csempe szomszédait,
     * a paraméterként kapott mátrixból,
     * a paraméterként kapott nehészségi szintnek megfelelően.
     * @param tile - a paraméterként kapott csempe
     * @param board - a paraméterként kapott mátrix
     * @param level - a paraméterként kapott nehézségi szint
     * @return a paraméterként kapott csempe szomszédait tartalmazó {@link java.util.List} objektum
     */
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

    /**
     * Visszaadja, hogy a paraméterként kapott {@code row} és {@code column} koordináták rajta vannak e a táblán,
     * a paraméterként kapott nehészségi szintnek megfelelően.
     * @param row - a csempe row koordinátája
     * @param column - a csempe column koordinátája
     * @param level - egy {@link Level} objektum, ami nehézségi szint
     * @return {@code true}, ha a {@code row} és {@code column} rajta vannak a táblán,
     *         {@code false} egyébként
     */
    public boolean isOnBoard(int row, int column, Level level){
        return row >= 0 && row < level.rowTiles && column >= 0 && column < level.columnTiles;
    }

    /**
     * Visszaadja, hogy a paraméterként kapott {@link mvc.model.Tile} objektum
     * sikeresen meg lett oldva.
     * @param board - egy {@link mvc.model.Tile} objektum
     * @return {@code true}, ha sikeresen meg lett oldva
     *         {@code false} egyébként
     */
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


    /**
     * Visszaadja a megjelenített csempék sorainak számát.
     * @return a megjelenített csempék sorainak száma
     */
    public int getRowTiles() {
        return rowTiles;
    }

    /**
     * Beállítja a megjelenített csempék sorainak számát.
     * @param rowTiles - a megjelenített csempék sorainak száma
     */
    public void setRowTiles(int rowTiles) {
        this.rowTiles = rowTiles;
    }

    /**
     * Visszaadja a megjelenített csempék oszlopainak számát.
     * @return a megjelenített csempék oszlopainak száma
     */
    public int getColumnTiles() {
        return columnTiles;
    }

    /**
     * Beállítja a megjelenített csempék oszlopainak számát.
     * @param columnTiles - a megjelenített csempék oszlopainak száma
     */
    public void setColumnTiles(int columnTiles) {
        this.columnTiles = columnTiles;
    }
}
