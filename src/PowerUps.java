public abstract class PowerUps extends Points{
    public PowerUps(String path){
        super(path);
    }

    /**
     * Abstract function is getTotalPower_ups
     * @return total powerUps
     */
    public abstract int getTotalPower_ups();
    public abstract String getDescription();
}
