package minduracing.TrackParts;

// abstract ?
public class TrackPart {
    public int topx;
    public int topy;

    public TrackPart(int topx, int topy){
        this.topx = topx;
        this.topy = topy;
    }

    public boolean erase(int x, int y){
        return false;
    }

    public int[] WP(){
        return null;
    }

}
