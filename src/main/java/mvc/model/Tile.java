package mvc.model;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;



public class Tile extends StackPane {

    private int x;
    private int y;
    private int value;
    private boolean isMine;
    private boolean isFlag;
    private boolean isRevealed;

    private Rectangle tile;
    private Text text;
    private ImageView picture;


    public Tile(){
    }
    public Tile(int x, int y, boolean isMine){
        this.x = x;
        this.y = y;
        this.isMine = isMine;
    }

    public Tile(int x, int y, boolean isMine, boolean isRevealed, int minesNear, boolean isFlagged) {
        super();
        this.x = x;
        this.y = y;
        this.isMine = isMine;
        this.isRevealed = isRevealed;
        this.value = minesNear;
        this.isFlag = isFlagged;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public Rectangle getTile() {
        return tile;
    }

    public void setTile(Rectangle tile) {
        this.tile = tile;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public ImageView getPicture() {
        return picture;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
    }


}
