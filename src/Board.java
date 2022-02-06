import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Board extends JPanel implements ActionListener, KeyListener {
    private final int BOARD_W = 1110;
    private final int BOARD_H = 840;
    private final int DELAY = 5;
    private Graphics graphics;
    private BufferedImage image;
    private final Player player;
    private Timer timer;
    private int x = 0;
    private int scroll_x = 2220;
    private boolean backGround = false;
    private static boolean jump = false;
    private JTextArea textArea;
    private String[] textArray;
    private MonstersAndObstacles monster;
    private MonstersAndObstacles obstacle;
    private Random rand;
    private Points points;
    private PowerUpsD powerUpsD;
    private int total_point;
    private boolean jump_point = false;
    private boolean pointControlNew = true;

    /**
     * Board Class Constructor
     */
    public Board() {
        setPreferredSize(new Dimension(BOARD_W,BOARD_H));
        setBackground(Color.blue);
        graphics = null;
        image = null;
        player = new Player();
        rand = new Random();
        player.setPlayer(player);
        timer = new Timer(DELAY, this);
        timer.start();
        addCityBackground();
        TextInit();
        this.add(textArea);
        textArray = new String[12];
        for(int i=0;i<12;i++){
            textArray[i] = "\n";
        }
        obstacle = new MonstersAndObstacles("obstacle");
        obstacle.setCoordinate_x(rand.nextInt(500)+1110);
        monster = new MonstersAndObstacles("monster");
        monster.setCoordinate_x(rand.nextInt(100)+1110);
        powerUpsD = new PowerUpsD();
        points = new PlayerPoint();
        total_point = 0;


    }

    /**
     * JTextArea Initialize
     */
    private void TextInit(){
        textArea = new JTextArea();
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.white);
        textArea.setEnabled(false);
        textArea.setVisible(true);
    }

    /**
     * Makes the elements of the string array into a one string.
     * @return String
     */
    private String TextCombined(){
        String s = " ";
        if(textArray[0] != null){
            for(int i=0;i<12;i++){
                s = s + textArray[i] + "\n ";
            }
        }
        return s;
    }

    /**
     * Swing constantly calls this function to refresh the window continuously.
     * @param g Graphics
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.graphics = g;
        textArea.setBounds(0, 640, BOARD_W, 240);
        paintBackground();
        player.PaintPlayer(graphics);
        player.PaintJumpPlayer(graphics);
        if (TextCombined() != null) {
            textArea.setText(TextCombined());
        }
        NewMonster();
        if(monster != null){
            monster.paintMonsterObstacle(graphics);
        }
        NewObstacle();
        if(obstacle != null){
            obstacle.paintMonsterObstacle(graphics);
        }
        player.UpdateLifeImages(graphics);
        if(points != null){
            points.getImagePower().paintPowerUps(graphics);
        }
        makePowerUps();
        makePowerUpsD();
        if(powerUpsD != null){
            powerUpsD.paintPowerUps(graphics);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Swing constantly calls this function to update the window's background work
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (backGround) {
            scrollingBackground();
            if(monster != null)
                monster.scrollingMonsterObstacle(0,2220);
            if(obstacle != null)
                obstacle.scrollingMonsterObstacle(0,2220);
            if(points != null)
                points.getImagePower().scrollingPowerUps(0,2220);
            if(powerUpsD != null)
                powerUpsD.scrollingPowerUps(0,2220);
        }
        jump = player.tick(jump);
        UpdatePlayerLife();
        UpdateJumpType();
        UpdatePoint();
        repaint();
    }

    /**
     * Random new monster generation
     */
    private void NewMonster(){
        if(monster.getCoordinate_x() <= 0){
            monster = new MonstersAndObstacles("monster",(rand.nextInt(1110)+1110));
        }
    }

    /**
     * Random new obstacle generation
     */
    private void NewObstacle(){
        if(obstacle.getCoordinate_x() <= 0){
            obstacle = new MonstersAndObstacles("obstacle",(rand.nextInt(1100)+1110));
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Receives the input when any key is pressed on the keyboard
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D){
            backGround = true;
        }
        else if(key == KeyEvent.VK_SPACE){
            jump = true;
            jump_point = true;
        }
    }

    /**
     * When the key pressed on the keyboard is released, it receives input.
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D){
            backGround = false;
        }
    }

    /**
     * Add a city image to the background
     */
    private void addCityBackground(){
        try {
            image = ImageIO.read(new File("images/city.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file: "+exc.getMessage());
        }
    }

    /**
     * Move the background left when the player walks
     */
    private void scrollingBackground(){
        x -= 2;
        scroll_x -= 2;
        if(x == -2220)
            x = 2220;
        if(scroll_x == -2220)
            scroll_x = 2220;
    }

    /**
     * Paint background to the window
     */
    private void paintBackground(){
        graphics.drawImage(image,x,0,null);
        graphics.drawImage(image,scroll_x,0,null);
    }

    /**
     * Each time the player hits an obstacle or monster, it decreases by one health.
     */
    private void UpdatePlayerLife(){
        if(monster.getCoordinate_y() == player.getCoordinate_y() && monster.getCoordinate_x() == player.getCoordinate_x()){
            player.setLife_pre(player.getLife());
            player.setLife(player.getLife()-1);
            UpdateTextArea("Player's life : " +player.getLife());
        }
        if(obstacle.getCoordinate_y() == player.getCoordinate_y() && obstacle.getCoordinate_x() == player.getCoordinate_x()){
            player.setLife_pre(player.getLife());
            player.setLife(player.getLife()-1);
            UpdateTextArea("Player's life : " +player.getLife());
        }
    }

    /**
     * New Power Ups (A,B,C) Generation Randomly
     */
    private void makePowerUps(){
        if (points != null) {
            if(player.getCoordinate_y() == points.getImagePower().getCoordinate_y() && player.getCoordinate_x() == points.getImagePower().getCoordinate_x()){
                switch (rand.nextInt(3) + 1) {
                    case 1 -> points = new PowerUpsA(points);
                    case 2 -> points = new PowerUpsB(points);
                    case 3 -> points = new PowerUpsC(points);
                    default -> {
                    }
                }
                pointControlNew = true;
            }
            else{
                if(points.getImagePower().getCoordinate_x() <= 0){
                    points.getImagePower().setCoordinate_x(1150);
                    pointControlNew = false;
                }
            }
        }
    }
    /**
     * New Power Up (D) Generation Randomly
     */
    private void makePowerUpsD(){
        if(powerUpsD.getCoordinate_x() <= 0){
            powerUpsD = new PowerUpsD();
        }
    }

    /**
     * When the player catches Power Ups D, the player jump type becomes the opposite type
     */
    private void UpdateJumpType(){
        if(powerUpsD.getCoordinate_y() == player.getCoordinate_y() && powerUpsD.getCoordinate_x() == player.getCoordinate_x()){
            if(player.getJump_tip().equals("high"))
                player.setJump_tip("low");
            else
                player.setJump_tip("high");
            powerUpsD = new PowerUpsD();
            UpdateTextArea("Player's jump type : " + player.getJump_tip());
        }
    }

    /**
     * Update Total Point
     */
    private void UpdatePoint(){
        if(jump_point && !jump){
            if(player.getLife() == player.getLife_pre()){
                if (pointControlNew) {
                    total_point += points.getTotalPower_ups();
                    UpdateTextArea("Player's total points : " + total_point);
                    jump_point = false;
                }
            }
            else{
                player.setLife_pre(player.getLife());
                jump_point = false;
            }

        }
    }

    /**
     * Update Text Array for Text Area
     * @param mess New Message
     */
    private void UpdateTextArea(String mess){
        int index = -1;
        if(textArray[0].equals("\n")){
            textArray[0] = mess;
        }
        else{
            for(int i=0;i<12;i++){
                if(textArray[i].equals("\n")){
                    index = i;
                    break;
                }
            }
            if(index == -1){
                for(int i=1;i<12;i++){
                    textArray[i-1] = textArray[i];
                }
                textArray[11] = mess;
            }
            else{
                textArray[index] = mess;
            }
        }
    }
}
