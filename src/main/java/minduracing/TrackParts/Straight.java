package minduracing.TrackParts;

import static minduracing.MinduRacing.gap;

public class Straight extends TrackPart{
    public static int edgeGap = 1;
    public float length;
    public boolean vertical;

    public Straight(int topx, int topy, float length, boolean vertical){
        super(topx, topy);
        this.length = length;
        this.vertical = vertical;
    }

    public boolean verti(int x, int y){
        return verti(x, y, gap);
    }

    public boolean verti(int x, int y, float pgap){
        return !(x < topx + edgeGap || x > topx + pgap - edgeGap || y < topy || y > topy + length);
    }

    public boolean horiz(int x, int y){
        return horiz(x, y, gap);
    }

    public boolean horiz(int x, int y, float pgap){
        return !(x < topx || x > topx + length || y < topy + edgeGap || y > topy + pgap - edgeGap);
    }

    @Override
    public int[] WP() {
        int[] ret;
        if (vertical){
            ret = new int[]{topx + gap/2, topy + (int)Math.floor(length/2)};
        } else {
            ret = new int[]{topx + (int)Math.floor(length/2), topy +  gap/2};
        }
        return ret;
    }
}
