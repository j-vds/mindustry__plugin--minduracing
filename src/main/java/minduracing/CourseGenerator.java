package minduracing;

import arc.math.*;
import arc.struct.*;
import arc.util.*;
import minduracing.TrackParts.Corner;
import minduracing.TrackParts.Straight;
import minduracing.TrackParts.TrackPart;
import mindustry.content.Blocks;
import mindustry.gen.Call;
import mindustry.maps.*;
import mindustry.maps.generators.*;
import mindustry.world.*;

import static mindustry.Vars.*;
import static minduracing.MinduRacing.*;


// class that generates the map
public class CourseGenerator extends Generator {
    public Pallete pallete =  Pallete.cavern;
    public int[][] checkpoints;
    private Array<TrackPart> parts = new Array<>();


    CourseGenerator() {
        //size depends on map ofc
        super(2*size, size);
    }

    @Override
    public void generate(Tile[][] tiles) {

        // init tiles (1x1)
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(x, y);
                if (0 <= x && x <= 10 && Math.abs(y - height/2) < 2){
                    tiles[x][y].setFloor(pallete.finish);
                } else {
                    tiles[x][y].setFloor(pallete.floor);
                }
                //make all blocks WALL
                tiles[x][y].setBlock(pallete.wall, dead);
            }
        }

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                if (Corner.cornerTop(x, y, 0, 0, 4)) tiles[x][y].setBlock(Blocks.air);
                if (Corner.cornerTop(x, y, 0, height/2, 1)) tiles[x][y].setBlock(Blocks.air);
                //straights
                if (Straight.horiz(x, y, (int)Math.floor(Corner.outerRadius), 0, width - 2*Corner.outerRadius)) tiles[x][y].setBlock(Blocks.air);
                if (Straight.horiz(x, y, (int)Math.floor(Corner.outerRadius), height-gap, width - 2*Corner.outerRadius)) tiles[x][y].setBlock(Blocks.air);

                if (Corner.cornerTop(x, y, (int)Math.floor(width - Corner.outerRadius), height/2, 2)) tiles[x][y].setBlock(Blocks.air);
                if (Corner.cornerTop(x, y, (int)Math.floor(width - Corner.outerRadius), 0, 3)) tiles[x][y].setBlock(Blocks.air);
            }
        }

        //populate checkpoints for now ~ mittle of a part + star
        //iterate track parts


        world.setMap(new Map(StringMap.of("name", mapname)));
    }


    public void buildWP(Tile[][] tiles, int x, int y){
        Waypoints[] wps = Waypoints.values();
        for(int dx = 0; dx < 2; dx++){
            for(int dy = 0; dy < 2; dy++){
                Call.onConstructFinish(world.tile(x + dx,y + dy), wps[2*dx+dy].wall, player.id, (byte)0, dead, true);
            }
        }
    }

    public int[] getSpawn(){
        int[] spawns = {5, (int)Math.floor(height/2)};
        return spawns;
    }
}
