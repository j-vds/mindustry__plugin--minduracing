package minduracing;

import arc.math.*;
import arc.struct.*;
import arc.util.*;
import mindustry.maps.*;
import mindustry.maps.generators.*;
import mindustry.world.*;

import static mindustry.Vars.*;
import static minduracing.MinduRacing.*;


// class that generates the map
public class CourseGenerator extends Generator {
    public Pallete pallete =  Pallete.cavern;


    CourseGenerator() {
        super(width, height);
    }

    @Override
    public void generate(Tile[][] tiles) {

        // init tiles (1x1)
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(x, y);
                tiles[x][y].setFloor(pallete.floor);
            }
        }


        world.setMap(new Map(StringMap.of("name", mapname)));
    }
}
