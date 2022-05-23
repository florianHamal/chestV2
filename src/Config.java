import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
	private static File file;
	private static FileConfiguration customFile;
	
	public static void setup() {
		//file = new File(Bukkit.getServer().getPluginManager().getPlugin("ChestV2").getDataFolder()+"/ChestV2.yml");
		file = new File("C:\\Users\\flori\\Desktop\\chestTestServer\\plugins\\ChestV2.yml");
		if(!file.exists()) {
			try{
				file.createNewFile();
			}catch(IOException e) {
				//-----
			}
		}
		customFile= YamlConfiguration.loadConfiguration(file);
	}

	public static FileConfiguration getCustomFile() {
		return customFile;
	}
	
	public static void save() {
		try {
			customFile.save(file);
		}catch(IOException e) {
			System.out.println("�cFehler bim speichern");
		}
	}
	public static void reload() {
		customFile = YamlConfiguration.loadConfiguration(file);
	}
}
