import javafx.print.PageLayout;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ChestRandomizer extends JavaPlugin {
    private ChestData chestData = new ChestData();
    static private ChestRandomizer plugin;

    @Override
    public void onEnable(){
        getCommand("chest").setExecutor(new Commands(chestData));
        PluginManager pluginManager =  Bukkit.getPluginManager();
        pluginManager.registerEvents(new Listeners(chestData),this);
        plugin = this;
    }

    public static ChestRandomizer getPlugin(){
        return plugin;
    }




    public static void main(String[] args) {

    }
}
