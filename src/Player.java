import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player{
    private int coordinate_x;
    private int coordinate_y;
    private final int jump_y;
    private BufferedImage image_player;
    private String jump_type;
    private Jump jump_;
    private Player player;
    private int life;
    private BufferedImage image_life;
    private int life_pre;

    public Player(){
        addImagePlayer();
        setCoordinate_x(140);
        setCoordinate_y(570);
        setJump_tip("low");
        this.jump_y = 1;
        life = 3;
        life_pre = 3;
    }

    public int getLife_pre() {
        return life_pre;
    }

    public void setLife_pre(int life_pre) {
        this.life_pre = life_pre;
    }

    public int getJump_y() {
        return jump_y;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public String getJump_tip() {
        return jump_type;
    }

    public void setJump_tip(String jump_type) {
        this.jump_type = jump_type;
    }

    public int getCoordinate_x(){
        return coordinate_x;
    }

    public int getCoordinate_y(){
        return coordinate_y;
    }

    public void setCoordinate_x(int coordinate_x) {
        this.coordinate_x = coordinate_x;
    }

    public void setCoordinate_y(int coordinate_y) {
        this.coordinate_y = coordinate_y;
    }

    /**
     * When player is jumping, paint new location
     * @param g Graphic
     */
    public void PaintJumpPlayer(Graphics g){
        g.drawImage(image_player,coordinate_x,coordinate_y,null);
    }

    public boolean tick(boolean jump){
        if(jump_type.equals("low")){
            jump_ = new LowJump();
        }
        else if(jump_type.equals("high")){
            jump_ = new HighJump();
        }
        return jump_.jump(jump,player);
    }

    /**
     * Update Life Images
     * @param graphic graphics
     */
    public void UpdateLifeImages(Graphics graphic){
        for(int i=0;i<getLife();i++){
            if(i==0)
                graphic.drawImage(image_life,i+5,0,null);
            else if(i == 1)
                graphic.drawImage(image_life,i+50,0,null);
            else
                graphic.drawImage(image_life,i+98,0,null);
        }
    }

    public void PaintPlayer(Graphics graphic){
        graphic.drawImage(image_player,coordinate_x,coordinate_y,null);
    }


    private void addImagePlayer(){
        try {
            image_player = ImageIO.read(new File("images/pikachu_standing.png"));
            image_life = ImageIO.read(new File("images/heart.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file: "+exc.getMessage());
        }
    }


}
