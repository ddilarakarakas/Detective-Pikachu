public class PowerUpsC extends PowerUps{
    Points points;
    /**
     * It holds the object of the abstract class from which it is derived. Required for Decorator pattern.
     * @param points Points abstract class object
     */
    public PowerUpsC(Points points){
        super("images/powerupsC.png");
        this.points = points;

    }

    @Override
    public int getTotalPoints() {
        return 10*points.getTotalPoints();
    }

    @Override
    public int getTotalPower_ups() {
        return 10*points.getTotalPower_ups();
    }

    @Override
    public String getDescription() {
        return points.getDescription() + " x10 ";
    }
}
