package mvc.model;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Egy csempét reprezentáló osztály.
 */

public class Tile extends StackPane {

    /**
     * A csempe x koordinátája.
     */
    private int x;

    /**
     * A csempe y koordinátája.
     */
    private int y;

    /**
     * Az csempe értéke.
     */
    private int value;

    /**
     * A csempe akna-e.
     */
    private boolean isMine;

    /**
     * A csempe zászlóval van e jelölve.
     */
    private boolean isFlag;

    /**
     * A csempe megjelenítésre került e.
     */
    private boolean isRevealed;

    /**
     * A csempe reprezentálására szolgáló {@link Rectangle} objektum.
     */
    private Rectangle tile;

    /**
     * A csempe értékének megjelenítésére szolgáló {@link Text} objektum.
     */
    private Text text;

    /**
     * A csempe grafikus {@link ImageView} képe.
     */
    private ImageView picture;


    /**
     * Paraméter nélküli konstruktor egy Tile objektum létrehozásához.
     */
    public Tile(){
    }

    /**
     * Visszaadja a csempe x koordinátáját.
     * @return a csempe x koordinátája
     */
    public int getX() {
        return x;
    }

    /**
     * Beállítja a csempe x koordinátáját.
     * @param x - a csempe x koordinátája
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Viiszaadja a csempe y koordinátáját.
     * @return a csempe y koordinátája
     */
    public int getY() {
        return y;
    }

    /**
     * Beállítja a csempe y koordinátáját.
     * @param y - a csempe y koordinátája
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Visszaadja a csempe értékét.
     * @return a csempe értéke
     */
    public int getValue() {
        return value;
    }

    /**
     * Beállítja a csempe értékét.
     * @param value - a csempe értéke
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Visszaadja, hogy a csempe akna-e.
     * @return a csempe akna-e
     */
    public boolean isMine() {
        return isMine;
    }

    /**
     * Beállítja, hogy a csempe akna-e.
     * @param mine - a csempe akna-e
     */
    public void setMine(boolean mine) {
        isMine = mine;
    }

    /**
     * Visszaadja, hogy a csempe zászlóval van e jelölve.
     * @return a csempe zászlóval van e jelölve
     */
    public boolean isFlag() {
        return isFlag;
    }

    /**
     * Beállítja, hogy a csempe zászlóval van e jelölve.
     * @param flag - a csempe zászlóval van e jelölve
     */
    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    /**
     * Visszaadja, hogy a csempe megjelenítésre került e.
     * @return a csempe megjelenítésre került e
     */
    public boolean isRevealed() {
        return isRevealed;
    }

    /**
     * Beállítja, hogy a csempe megjelenítésre került e.
     * @param revealed - a csempe megjelenítésre került e
     */
    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    /**
     * Visszaadja a csempe reprezentálására szolgáló
     * {@link Rectangle} objektumot.
     * @return a csempe reprezentálására szolgáló
     *          {@link Rectangle} objektum
     */
    public Rectangle getTile() {
        return tile;
    }

    /**
     * Beállítja a csempe reprezentálására szolgáló
     * {@link Rectangle} objektumot.
     * @param tile - a csempe reprezentálására szolgáló
     *              {@link Rectangle} objektum
     */
    public void setTile(Rectangle tile) {
        this.tile = tile;
    }

    /**
     * Visszaadja a csempe értékének megjelenítésére szolgáló
     * {@link Text} objektumot.
     * @return a csempe értékének megjelenítésére szolgáló
     *          {@link Text} objektum
     */
    public Text getText() {
        return text;
    }

    /**
     * Beállítja a csempe értékének megjelenítésére szolgáló
     * {@link Text} objektumot.
     * @param text - a csempe értékének megjelenítésére szolgáló
     *             {@link Text} objektum
     */
    public void setText(Text text) {
        this.text = text;
    }

    /**
     * Visszaadja a csempe grafikus {@link ImageView} képét.
     * @return a csempe grafikus {@link ImageView} képe
     */
    public ImageView getPicture() {
        return picture;
    }

    /**
     * Beállítja a csempe grafikus {@link ImageView} képét.
     * @param picture - a csempe grafikus {@link ImageView} képe
     */
    public void setPicture(ImageView picture) {
        this.picture = picture;
    }


}
