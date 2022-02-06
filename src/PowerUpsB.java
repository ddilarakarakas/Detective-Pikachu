public class PowerUpsB extends PowerUps{
    Points points;
    /**
     * It holds the object of the abstract class from which it is derived. Required for Decorator pattern.
     * @param points Points abstract class object
     */
    public PowerUpsB(Points points){
        super("images/powerupsB.png");
        this.points = points;

    }

    @Override
    public int getTotalPoints() {
        return 5*points.getTotalPoints();
    }

    @Override
    public int getTotalPower_ups() {
        return 5*points.getTotalPower_ups();
    }

    @Override
    public String getDescription() {
        return points.getDescription() + " x5 ";
    }
}
