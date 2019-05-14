package mvc.model;


public class Level {

    public static Level EASY = new Level("Könnyű",0,5,5,1, 200,200);
    public static Level MEDIUM = new Level("Közepes",1,10,10,10,400,400);
    public static Level HARD = new Level("Nehéz",2,20,20,40,800,800);

    public String name;
    public int id;
    public int rowTiles;
    public int columnTiles;
    public int mines;
    public int WIDTH;
    public int HEIGHT;


    public Level(String name,int id, int rowTiles, int columnTiles, int mines, int WIDTH, int HEIGHT){
        this.id = id;
        this.name = name;
        this.rowTiles = rowTiles;
        this.columnTiles = columnTiles;
        this.mines = mines;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    @Override
    public String toString() {
        return "Level{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", rowTiles=" + rowTiles +
                ", columnTiles=" + columnTiles +
                ", mines=" + mines +
                ", WIDTH=" + WIDTH +
                ", HEIGHT=" + HEIGHT +
                '}';
    }
}