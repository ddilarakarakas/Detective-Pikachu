public class LowJump implements Jump{
    private final String type;
    public LowJump(){
        this.type = "low";
    }

    @Override
    public boolean jump(boolean jump,Player player) {
        if(jump){
            if(player.getCoordinate_y() != 480) {
                player.setCoordinate_y(player.getCoordinate_y() - player.getJump_y());
            }
            else{
                jump = false;
            }
        }
        else{
            if(player.getCoordinate_y() < 570) {
                player.setCoordinate_y(player.getCoordinate_y() + player.getJump_y());
            }
        }

        return jump;
    }
}
