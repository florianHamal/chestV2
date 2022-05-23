import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Random;

public class ChestType {
    private String name;
    private Inventory inventory;
    private int randomNumber;

    public ArrayList<Location> chests= new ArrayList<Location>();

    public ChestType(String name,int invSize,int randomNumber){
        this.name = name;
        this.inventory = Bukkit.createInventory(null,invSize,name);
        this.randomNumber = randomNumber;
    }

    public void openInv(Player player){
        player.openInventory(inventory);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public ArrayList<Location> getChests() {
        return chests;
    }
    public void fillAll() {
        for (Location location : chests) {
            BlockState state = location.getBlock().getState();
            if (state instanceof Chest) {
                Chest chest = (Chest) state;
                Inventory inv = chest.getBlockInventory();
                fill(inv);
            }
        }
    }

    public void fill(Inventory inventoryToFill){
        Random random = new Random();
        for (int i = 0;i<inventoryToFill.getSize();i++){
            if (random.nextInt()%randomNumber==0){

                inventoryToFill.setItem(i,inventory.getItem(random.nextInt(inventory.getSize())));
            }
        }
    }
}
