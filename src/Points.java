public abstract class Points {
    private int totalPower_ups = 1;
    String description = " x1 ";
    private PowerUpsImage imagePower;

    /**
     * Points Abstract Class Constructor
     */
    public Points(){
        imagePower = new PowerUpsImage();
    }

    /**
     * Points Abstract Class Constructor with image path
     * @param path image path
     */
    public Points(String path){
        imagePower = new PowerUpsImage(path);
    }
    public PowerUpsImage getImagePower() {
        return imagePower;
    }

    public String getDescription() {
        return description;
    }

    public int getTotalPower_ups(){
        return totalPower_ups;
    }
    public abstract int getTotalPoints();

}
