package minduracing;

import arc.*;
import arc.util.*;

import mindustry.Vars;
import mindustry.core.GameState.*;
import mindustry.entities.type.Player;
import mindustry.game.EventType.*;
import mindustry.game.*;
import mindustry.gen.Call;
import mindustry.plugin.*;
import mindustry.world.Tile;

import static arc.util.Log.info;
import static mindustry.Vars.*;
import static minduracing.MinduRacing.*;

public class RacingMod extends Plugin{
    private final Rules rules = new Rules();

    private CourseGenerator generator;


    @Override
    public void init(){
        info("minduracing plugin loaded...");
        rules.tags.put("minduracing", "true");
        rules.infiniteResources = true;
        rules.canGameOver = false;
        rules.playerDamageMultiplier = 0f;

        //Todo: check for min 2 players and have a countdown (~ 10 seconds)
        //if game is already running -> spectator mode
        Events.on(PlayerJoin.class, event -> {

        });


        Events.on(Trigger.update, () -> {
            if(!active()) return;
        });


        netServer.assigner = (player, players) -> dead;
    }


    @Override
    public void registerServerCommands(CommandHandler handler){
        handler.register("minduracing", "Begin hosting with the Bomberman gamemode.", args -> {
            logic.reset();
            Log.info("Generating map...");
            world.loadGenerator(generator = new CourseGenerator());
            info("Map generated.");
            state.rules = rules.copy();
            logic.play();
            netServer.openServer();
        });
    }

    @Override
    public void registerClientCommands(CommandHandler handler){
        handler.<Player>register("wp", "<x> <y>", "place a wp", (args, player) -> {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            buildWP(x, y, player);
        });
    }

    public boolean active(){
        return state.rules.tags.getBool("minduracing") && !state.is(State.menu);
    }

    public void buildWP(int x, int y, Player player){
        Waypoints[] wps = Waypoints.values();
        for(int dx = 0; x < 2; dx++){
            for(int dy = 0; y < 2; dy++){
                Call.onConstructFinish(world.getTiles()[x + dx][y + dy], wps[2*dx+dy].wall, player.id, (byte)0, dead, true);
            }
        }
    }
}
