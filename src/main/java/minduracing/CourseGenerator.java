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

        //TODO: load as a class or from a file
        parts.with(
                new Corner( 0, height/2, 1),
                new Straight((int)Math.floor(Corner.outerRadius), height-gap, width - 2*Corner.outerRadius, false),
                new Corner((int)Math.floor(width - Corner.outerRadius), height/2, 2),
                new Corner((int)Math.floor(width - Corner.outerRadius), 0, 3),
                new Straight((int)Math.floor(Corner.outerRadius), 0, width - 2*Corner.outerRadius, false),
                new Corner(0, 0, 4)
        );

        checkpoints = new int[parts.size + 1][2];

        for (int i=0; i<parts.size; i++){
            checkpoints[i] = parts.get(i).WP();
            for (int x = 0; x < width; x++){
                for (int y = 0; y < height; y++){
                //with Array<>().foreach or each method would be better
                    if (parts.get(i).erase(x, y)){
                        tiles[x][y].setBlock(Blocks.air);
                    }
                }
            }
        }

        checkpoints[parts.size] = getSpawn();

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
