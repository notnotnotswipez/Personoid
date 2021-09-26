package us.notnotdoddy.personoid.utils;

import me.definedoddy.fluidapi.utils.JavaUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;
import us.notnotdoddy.personoid.npc.NPCHandler;
import us.notnotdoddy.personoid.npc.PersonoidNPC;

import java.util.ArrayList;
import java.util.List;

public class LocationUtils {

    static List<BlockFace> blockFaces = new ArrayList<>();

    static {
        blockFaces.add(BlockFace.NORTH);
        blockFaces.add(BlockFace.SOUTH);
        blockFaces.add(BlockFace.EAST);
        blockFaces.add(BlockFace.WEST);
    }

    public static Location getRandomLoc(PersonoidNPC npc) {
        Location loc = npc.getEntity().getLocation().clone();
        double x = loc.getX() + JavaUtils.random(-20, 20);
        double z = loc.getZ() + JavaUtils.random(-20, 20);
        double y = npc.getEntity().getLocation().getWorld().getHighestBlockYAt((int) x, (int) z) + 1;
        return new Location(npc.getEntity().getLocation().getWorld(), x, y, z);
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

    public static Location getNearestValidLocation(Location origin){
        Location standable = null;
        for (BlockFace blockFace : blockFaces){
            if (getFirstLowest(origin.getBlock().getRelative(blockFace).getLocation(), 5) != null){
                standable = getFirstLowest(origin.getBlock().getRelative(blockFace).getLocation(), 5);
            }
        }
        return standable;
    }

    private static Location getFirstLowest(Location starting, int maxSize){
        Block currentBlock = starting.getBlock();
        int currentLoops = 0;
        while (currentBlock.getRelative(BlockFace.DOWN).getType().isAir() && currentLoops < maxSize){
            currentLoops++;
            currentBlock = currentBlock.getRelative(BlockFace.DOWN);
        }
        if (currentBlock.getRelative(BlockFace.DOWN).getType().isAir()){
            return null;
        }
        else {
            return currentBlock.getLocation();
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

    public static PersonoidNPC getClosestNPC(Location loc) {
        PersonoidNPC closestNPC = null;
        double closestDistance = 0;
        for (PersonoidNPC npc : NPCHandler.getNPCs().values()) {
            if (npc.getEntity().getWorld() == loc.getWorld()) {
                double distance = npc.getEntity().getLocation().distance(loc);
                if (closestNPC == null || distance < closestDistance) {
                    closestDistance = distance;
                    closestNPC = npc;
                }
            }
        }
        return closestNPC;
    }

    public static Block getBlockInFront(Location location, int distance) {
        BlockIterator blocks = new BlockIterator(location, 1, distance);
        Block lastNonSolidBlock = null;
        while (blocks.hasNext()) {
            Block block = blocks.next();
            if (block.getType().isSolid()) {
                return lastNonSolidBlock;
            } else {
                lastNonSolidBlock = block;
            }
        }
        return null;
    }
}