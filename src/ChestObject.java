import org.bukkit.Location;

public class ChestObject {
    Location location;
    ChestType chestType;
    boolean hasbeenFilled = false;

    public ChestObject(Location location,ChestType chestType) {
        this.location = location;
        this.chestType = chestType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ChestType getChestType() {
        return chestType;
    }

    public void setChestType(ChestType chestType) {
        this.chestType = chestType;
    }

    public boolean isHasbeenFilled() {
        return hasbeenFilled;
    }

    public void setHasbeenFilled(boolean hasbeenFilled) {
        this.hasbeenFilled = hasbeenFilled;
    }
}
