import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * It is the class created for Power Ups A, B, C images. It holds the coordinates.
 * It uploads different images according to the type and provides random generation.
 */
public class PowerUpsImage {
    private BufferedImage image;
    private int coordinate_x;
    private final int coordinate_y;
    private Random random;
    private int scroll_x;
    private String filePath;

    public PowerUpsImage(String type){
        filePath = type;
        AddImage();
        random = new Random();
        coordinate_y = 570;
        coordinate_x = 1110;
        scroll_x = 1110;
    }

    public PowerUpsImage(){
        random = new Random();
        coordinate_y = 570;
        coordinate_x = 1110;
        scroll_x = 1110;
    }

    public void setCoordinate_x(int coordinate_x) {
        this.coordinate_x = coordinate_x;
    }

    public int getCoordinate_x() {
        return coordinate_x;
    }

    public int getCoordinate_y() {
        return coordinate_y;
    }

    private void AddImage(){

        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException exc) {
            System.out.println("Error opening image file: "+exc.getMessage());
        }
    }

    public void scrollingPowerUps(int x, int scroll){
        coordinate_x -= 1;
        scroll_x -= 1;
        if(coordinate_x == 0)
            coordinate_x = x;
        if(scroll_x == 0)
            scroll_x = scroll;
    }

    public void paintPowerUps(Graphics graphic){
        graphic.drawImage(image,coordinate_x,coordinate_y,null);
    }
}
