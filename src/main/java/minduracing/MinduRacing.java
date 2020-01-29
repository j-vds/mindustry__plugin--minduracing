package minduracing;

import mindustry.content.*;
import mindustry.game.Schematic;
import mindustry.game.Schematics;
import mindustry.game.Team;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.defense.Wall;

public class MinduRacing {
    public static int laps = 3;
    public static int size = 100;

    public static Team dead = Team.crux;
    public static Team[] teams = {Team.blue, Team.green, Team.purple, Team.crux};

    //public static String w_B64 ="bXNjaAB4nE2M3QqAIBSDp3kRVPQkPdQhhAR/DnZCevskSdrNvm0waGgFEylYjIVuTi6KwbQnZpu3Qt5jEScU3RVanOVIuaeVPZ3/GTD4VK8B9ZJuXmmo1LoHNN4aKQ==";
    public static String mapname = "oval v1";


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

    //waypoints
    public enum Waypoints{
        blue(Team.blue, Blocks.titaniumWall),
        green(Team.green, Blocks.plastaniumWall),
        purple(Team.purple, Blocks.thoriumWall),
        crux(Team.crux, Blocks.copperWall);

        public final Team team;
        public final Wall wall;

        //constructor
        Waypoints(Team team, Block wall){
            this.team = team;
            this.wall = (Wall)wall;
        }


    }
}
