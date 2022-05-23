import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Listeners implements Listener {
    ChestData chestData = new ChestData();

    public Listeners(ChestData chestData){
        this.chestData = chestData;
    }

   /* @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if (event.hasBlock()){
            if (event.getClickedBlock().getType()== Material.CHEST){
                chestData.findChesttypeForChest(event.getClickedBlock().getLocation());
                ///proof
                //this is nonsense
            }
        }
    }*/

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event){
        if (event.getBlock().getType()==Material.CHEST){
            if (chestData.getCurrentChestType()!=null){
                chestData.getCurrentChestType().getChests().add(event.getBlock().getLocation());
                event.getPlayer().sendMessage("Chest has been saved");
            }
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if (event.getBlock().getType()==Material.CHEST){
            ChestType chestType = chestData.findChesttypeForChest(event.getBlock().getLocation());
            if (chestType!=null){
                chestType.getChests().remove(event.getBlock().getLocation());
                event.getPlayer().sendMessage("chest has been deleted");
            }
        }
    }
}
