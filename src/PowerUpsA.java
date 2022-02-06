public class PowerUpsA extends PowerUps{
    Points points;

    /**
     * It holds the object of the abstract class from which it is derived. Required for Decorator pattern.
     * @param points Points abstract class object
     */
    public PowerUpsA(Points points){
        super("images/powerupsA.png");
        this.points = points;

    }

    @Override
    public int getTotalPoints() {
        return points.getTotalPoints()*2;
    }

    @Override
    public int getTotalPower_ups() {
        return 2*points.getTotalPower_ups();
    }

    @Override
    public String getDescription() {
        return points.getDescription() + " x2 ";
    }

}
