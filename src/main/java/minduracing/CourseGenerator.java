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
        super(size, size);
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


    public void buildWP(Tile[][] tiles, int x, int y){
        Waypoints[] wps = Waypoints.values();
        for(int dx = 0; x < 2; dx++){
            for(int dy = 0; y < 2; dy++){
                tiles[x + dx][y + dy].setBlock(wps[2 * dx + dy].wall, wps[2 * dx + dy].team);
            }
        }
    }
}
