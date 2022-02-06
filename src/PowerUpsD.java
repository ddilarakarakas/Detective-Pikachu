import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class PowerUpsD {
    private BufferedImage image;
    private int coordinate_x;
    private int coordinate_y;
    private int scroll_x;
    private Random random;

    public PowerUpsD(){
        AddImage();
        random = new Random();
        coordinate_y = 570;
        coordinate_x = random.nextInt(1110)+1110;
        scroll_x = 1110;
    }

    public int getCoordinate_y() {
        return coordinate_y;
    }

    public int getCoordinate_x() {
        return coordinate_x;
    }

    /**
     * Scroll Power Ups D left
     * @param x coordinate x
     * @param scroll scroll coordinate x
     */
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

    private void AddImage(){
        try {
            image = ImageIO.read(new File("images/powerupsD.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file: "+exc.getMessage());
        }
    }
}
