package minduracing.TrackParts;

import java.util.function.DoubleUnaryOperator;

import static minduracing.MinduRacing.*;

public class Corner extends TrackPart{
    public static float innerRadius = 20f;
    public static float outerRadius = innerRadius + gap;
    private static float edgeGap = 1f;

    public int pcase;
    public float IR;
    public float OR;

    public Corner(int topx, int topy, int pcase){
        this(topx, topy, pcase, innerRadius, outerRadius);
    }

    public Corner(int topx, int topy, int pcase, float IR, float OR){
        super(topx, topy);
        this.pcase = pcase;
        this.IR = IR;
        this.OR = OR;
    }

    public static boolean cornerTop(int x, int y, int topx, int topy, int pcase){
        return cornerTop(x, y, topx, topy, pcase, innerRadius, outerRadius);
    }
    /* True == blank */
    //topx en topy zijn coordinaten van de links onderhoek
    public static boolean cornerTop(int x, int y, int topx, int topy, int pcase, float IR, float OR){
        float mx, my;
        switch (pcase){
            //   ---
            //  |
            case 1:
                mx = topx + OR;
                my = topy;
                if (x > mx || x < mx - OR || y < my || y > my + OR) return false;
                break;
            //  ---
            //    |
            case 2:
                mx = topx;
                my = topy;
                if (x < mx || x > mx + OR || y < my || y > my + OR) return false;
                break;
            //     |
            //  ---
            case 3:
                mx = topx;
                my = topy + OR;
                if (x < mx || x > mx + OR || y > my || y < my - OR) return false;
                break;
            //  |
            //  ---
            case 4:
                mx = topx + OR;
                my = topy + OR;
                if (x < mx - OR || x > mx || y < my - OR || y > my) return false;
                break;
            default:
                return false;
        }

        if (inCircle(OR-edgeGap, mx, my, x, y) && !inCircle(IR, mx, my, x, y)){
            return true;
        } else {
            return false;
        }
    }
}
