package minduracing.TrackParts;

import static minduracing.MinduRacing.gap;

public class Straight extends TrackPart{
    public float length;
    public boolean vertical;
    public float pgap;

    public Straight(int topx, int topy, float length, boolean vertical){
        this(topx, topy, length, vertical, gap);
    }

    public Straight(int topx, int topy, float length, boolean vertical, float pgap){
        super(topx, topy);
        this.length = length;
        this.vertical = vertical;
        this.pgap = pgap;
    }

    public boolean erase(int x, int y){
        if (vertical){
            return verti(x, y, this.pgap);
        } else {
            return horiz(x, y, this.pgap);
        }
    }

    private boolean verti(int x, int y, float pgap){
        return !(x < topx  || x > topx + pgap  || y < topy || y > topy + length);
    }

    private boolean horiz(int x, int y, float pgap){
        return !(x < topx || x > topx + length || y < topy  || y > topy + pgap );
    }

    @Override
    public int[] WP() {
        int[] ret;
        if (vertical){
            ret = new int[]{topx + (int)Math.floor(pgap/2), topy + (int)Math.floor(length/2)};
        } else {
            ret = new int[]{topx + (int)Math.floor(length/2), topy + (int)Math.floor(pgap/2)};
        }
        return ret;
    }
}
