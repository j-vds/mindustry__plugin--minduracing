package minduracing.TrackParts;

import static minduracing.MinduRacing.gap;

public class Straight {
    public static boolean verti(int x, int y, int topx, int topy, float length){
        return verti(x, y, topx, topy, length, gap);
    }

    public static boolean verti(int x, int y, int topx, int topy, float length, float pgap){
        return !(x < topx || x > topx + pgap || y < topy || y > topy + length);
    }

    public static boolean horiz(int x, int y, int topx, int topy, float length){
        return horiz(x, y, topx, topy, length, gap);
    }

    public static boolean horiz(int x, int y, int topx, int topy, float length, float pgap){
        return !(x < topx || x > topx + length || y < topy || y > topy + pgap);
    }
}
