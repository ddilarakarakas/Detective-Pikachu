import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * It is the class made for monsters and obstacles.
 */
public class MonstersAndObstacles {
    private BufferedImage image_monster;
    private BufferedImage image_obstacle;
    private int coordinate_x;
    private int coordinate_y;
    private int scroll_x;
    private String type;


    public MonstersAndObstacles(String type){
        addImageMonster();
        setCoordinate_x(1110);
        setCoordinate_y(570);
        setScroll_x(1110);
        setType(type);
    }

    public MonstersAndObstacles(String type,int x){
        addImageMonster();
        setCoordinate_x(x);
        setCoordinate_y(570);
        setScroll_x(1110);
        setType(type);
    }



    public void setType(String type) {
        this.type = type;
    }

    public void setCoordinate_y(int coordinate_y) {
        this.coordinate_y = coordinate_y;
    }

    public void setCoordinate_x(int coordinate_x) {
        this.coordinate_x = coordinate_x;
    }

    public void setScroll_x(int scroll_x) {
        this.scroll_x = scroll_x;
    }


    public int getCoordinate_x() {
        return coordinate_x;
    }


    public int getCoordinate_y() {
        return coordinate_y;
    }

    /**
     * Monster and obstacle image
     */
    private void addImageMonster(){
        try {
            image_monster = ImageIO.read(new File("images/monster.png"));
            image_obstacle = ImageIO.read(new File("images/obstacle.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file: "+exc.getMessage());
        }
    }

    /**
     * Monster and obstacle move left
     * @param x coordinate x
     * @param scroll scroll coordinate x
     */
    public void scrollingMonsterObstacle(int x, int scroll){
        coordinate_x -= 1;
        scroll_x -= 1;
        if(coordinate_x == 0)
            coordinate_x = x;
        if(scroll_x == 0)
            scroll_x = scroll;
    }

    public void paintMonsterObstacle(Graphics graphic){
        if(type.equals("monster")){
            graphic.drawImage(image_monster,coordinate_x,coordinate_y,null);
        }
        else if(type.equals("obstacle")){
            graphic.drawImage(image_obstacle,coordinate_x,coordinate_y,null);
        }
    }
}
