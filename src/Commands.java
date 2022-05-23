import com.sun.org.apache.bcel.internal.generic.SWITCH;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    ChestData chestData = new ChestData();
    public Commands(ChestData chestData){
        this.chestData = chestData;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (player.hasPermission("chest.edit")){
            switch (strings[0]){
                case"addType":
                    chestData.getChestTypes().add(new ChestType(strings[1],Integer.parseInt(strings[2]),Integer.parseInt(strings[3])));
                    break;
                case "removeType":
                    chestData.chestTypeRmove(strings[1]);
                    break;
                case "select":
                    chestData.setCurrentChestType(chestData.findChestType(strings[1]));
                    break;
                case "info":
                    player.sendMessage("addType <name> <size> <random amount>|create chest type\n" +
                                          "select <chestTypeName>               |select chesttype used when place chest (null for none)\n" +
                                          "removeType <chestType>               |deleteChestType\n" +
                                          "");
                    break;
                case "open":
                    player.openInventory(chestData.getCurrentChestType().getInventory());
                    break;
                case "save":
                    chestData.saveData();
                    break;
                case "fillAll":
                    chestData.fillAll();
                    break;
                case "showTypes":
                    player.sendMessage(chestData.getChestTypes().toString());
                    break;
                case "showAll":
                    player.sendMessage(chestData.toString());
                    break;
                default:
                    player.sendMessage("/chest help for info");
                    break;
            }
        }



        return false;
    }
}
