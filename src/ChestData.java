import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChestData {
    private ChestType currentChestType;
    private ArrayList<ChestType> chestTypes = new ArrayList<ChestType>();

    public ChestData (){
        Config.setup();
    }
    public void loadData() {
        chestTypes.clear();
        int chestTypesLength = Config.getCustomFile().getInt("chestTypesLength");
        String chestTypeName;
        int chestTypeRandomNumber,chestTypeSize;
        ChestType chestType;
        double x,y,z;
        World world;
        for (int i = 0;i<chestTypesLength;i++){
            chestTypeName = Config.getCustomFile().getString("chestTypeName"+i);
            chestTypeSize = Config.getCustomFile().getInt("chestTypeSize"+i);
            chestTypeRandomNumber = Config.getCustomFile().getInt("chestTypeRandomNumber"+i);
            chestType = new ChestType(chestTypeName,chestTypeSize,chestTypeRandomNumber);
            for (int j = 0; j<chestTypeSize;j++){
                chestType.getInventory().setItem(j,Config.getCustomFile().getItemStack("chestType"+i+"item"+j));
            }
            for (int j = 0;i<Config.getCustomFile().getInt("chestAmount"+i);j++){
                world = Bukkit.getWorld(Config.getCustomFile().getString("chestType"+i+"chest"+j+"world"));
                x = Config.getCustomFile().getDouble("chestType"+i+"chest"+j+"x");
                y = Config.getCustomFile().getDouble("chestType"+i+"chest"+j+"y");
                z = Config.getCustomFile().getDouble("chestType"+i+"chest"+j+"z");
                chestType.getChests().add(new Location(world,x,y,z,0,0));
            }
            chestTypes.add(chestType);
        }

    }
    public void saveData(){
        System.out.println("savingChestData");
        Config.getCustomFile().set("chestTypesLength", chestTypes.size());
        for (int i = 0;i<chestTypes.size();i++){
            System.out.println("test1");
            Config.getCustomFile().set("chestTypeName"+i,chestTypes.get(i).getName());
            Config.getCustomFile().set("chestTypeRandomNumber"+i,chestTypes.get(i).getRandomNumber());
            Config.getCustomFile().set("chestTypeSize"+i,chestTypes.get(i).getInventory().getSize());
            for (int j = 0;j<chestTypes.get(i).getInventory().getSize();j++){
                Config.getCustomFile().set("chestType"+i+"item"+j,chestTypes.get(i).getInventory().getItem(i));
                System.out.println("test2");
            }
            Config.getCustomFile().set("chestAmount"+i,chestTypes.get(i).getChests().size());
            for (int j = 0; j<chestTypes.get(i).getChests().size();j++){
                Config.getCustomFile().set("chestType"+i+"chest"+j+"world",chestTypes.get(i).getChests().get(j).getWorld().getName());
                Config.getCustomFile().set("chestType"+i+"chest"+j+"x",chestTypes.get(i).getChests().get(j).getX());
                Config.getCustomFile().set("chestType"+i+"chest"+j+"y",chestTypes.get(i).getChests().get(j).getY());
                Config.getCustomFile().set("chestType"+i+"chest"+j+"z",chestTypes.get(i).getChests().get(j).getZ());
                System.out.println("test3");
            }
        }

    }


    public void addChestTypes(ChestType chestType){
        chestTypes.add(chestType);
    }
    public void chestTypeRmove(String name){
        ChestType chestType = findChestType(name);
        if (chestType!=null){
            chestTypes.remove(chestType);
        }
    }

    public void chestTypeAdd(String name,int infSize,int randomNumber){
        ChestType chestType = findChestType(name);
        if (chestType!=null){
            chestType.setRandomNumber(randomNumber);
            chestType.getInventory().setMaxStackSize(infSize);
            return;
        }
        chestTypes.add(new ChestType(name,infSize,randomNumber));
    }

    public ChestType findChesttypeForChest(Location location){
        for (ChestType chestType:chestTypes){
            if (chestType.getChests().contains(location)){
                return chestType;
            }
        }
        return null;
    }

    public void tryChestRemove(Chest chest) throws Exception {
        for (ChestType chestType: chestTypes){
            for (Location location:chestType.getChests()){
                if (chestType.getChests().contains(chest.getLocation())){
                    chestType.getChests().remove(location);
                    return;
                }
            }
        }
    }
    public void setCurrentChestType(ChestType chestType){
        currentChestType = chestType;

    }
    public ChestType findChestType(String name){
        for (ChestType chestType:chestTypes){
            if (chestType.getName().equals(name)){
                return chestType;
            }
        }
        return null;
    }

    public ChestType getCurrentChestType() {
        return currentChestType;
    }

    public ArrayList<ChestType> getChestTypes() {
        return chestTypes;
    }
    public void fillAll(){
        for (ChestType chestType:chestTypes){
            chestType.fillAll();
        }
    }

}
