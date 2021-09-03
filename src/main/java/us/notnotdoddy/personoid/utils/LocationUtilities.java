package us.notnotdoddy.personoid.utils;

import me.definedoddy.fluidapi.FluidUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import us.notnotdoddy.personoid.npc.PersonoidNPC;

public class LocationUtilities {

    public static Location getRandomLoc(PersonoidNPC npc) {
        Location loc = npc.getLivingEntity().getLocation().clone();
        double x = loc.getX() + FluidUtils.random(-20, 20);
        double z = loc.getZ() + FluidUtils.random(-20, 20);
        double y = npc.getLivingEntity().getLocation().getWorld().getHighestBlockYAt((int) x, (int) z) + 1;
        return new Location(npc.getLivingEntity().getLocation().getWorld(), x, y, z);
    }

    public static boolean withinMargin(Location firstLocation, Location secondLocation, double margin){
        Location firstClone = firstLocation.clone();
        Location secondClone = secondLocation.clone();
        firstClone.setY(1000);
        secondClone.setY(1000);
        if (firstClone.getWorld().getUID().equals(secondClone.getWorld().getUID())){
            return firstClone.distance(secondClone) <= margin;
        }
        else {
            return false;
        }
    }

    public static Player getClosestPlayer(Location loc) {
        Player closestPlayer = null;
        double closestDistance = 0;
        for (Player player : loc.getWorld().getPlayers()) {
            double distance = player.getLocation().distance(loc);
            if (closestPlayer == null || distance < closestDistance && player.getGameMode() != GameMode.SPECTATOR) {
                closestDistance = distance;
                closestPlayer = player;
            }
        }
        return closestPlayer;
    }
}
