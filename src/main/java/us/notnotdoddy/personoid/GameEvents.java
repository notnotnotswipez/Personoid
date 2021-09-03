package us.notnotdoddy.personoid;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import us.notnotdoddy.personoid.npc.PersonoidNPC;
import us.notnotdoddy.personoid.npc.PersonoidNPCHandler;
import us.notnotdoddy.personoid.utils.PlayerInfo;

public class GameEvents implements Listener {
    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        for (PersonoidNPC npc : PersonoidNPCHandler.getNPCs().values()) {
            if (!npc.players.containsKey(player.getUniqueId())) {
                npc.players.put(player.getUniqueId(), new PlayerInfo());
            }
        }
    }
}
