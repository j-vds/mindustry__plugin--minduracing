package minduracing;

import mindustry.content.*;
import mindustry.world.*;
import mindustry.world.blocks.*;

public class MinduRacing {

    //floor
    public enum Pallete{
        cavern(Blocks.darksand, Blocks.duneRocks),
        desert(Blocks.sand    , Blocks.saltRocks);

        public final Floor floor;
        public final StaticWall wall;

        Pallete(Block floor, Block wall){
            this.floor = (Floor)floor;
            this.wall = (StaticWall)wall;
        }
    }
}
