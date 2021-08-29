package us.notnotdoddy.personoid.npc;

import me.definedoddy.fluidapi.FluidUtils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Location;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PersonoidNPCHandler {
    public static final NPCRegistry registry = CitizensAPI.getNPCRegistry();
    private static final Map<NPC, PersonoidNPC> NPCs = new HashMap<>();

    public static Map<NPC, PersonoidNPC> getNPCs() {
        return NPCs;
    }

    public static PersonoidNPC create(String name, Location loc) {
        return new PersonoidNPC(name).spawn(loc);
    }

    public static String getRandomName() {
        InputStream stream = FluidPlugin.getPlugin().getClass().getResourceAsStream("/names.txt");
        File file = new File("names.txt");
        try {
            FileUtils.copyInputStreamToFile(stream, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FluidUtils.randomLineFromFile(file);
    }
}
