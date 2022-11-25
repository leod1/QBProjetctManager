package fr.leod1.qbprojectmanager.ultils;


import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Qbloc {
    private String world;

    private Double x;

    private Double y;

    private Double z;

    private float Yaw;

    private float Pitch;

    public Qbloc(String world, Double x, Double y, Double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.Yaw = yaw;
        this.Pitch = pitch;
    }

    public String getWorld() {
        return this.world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public Double getX() {
        return this.x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return this.y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return this.z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public float getYaw() {
        return this.Yaw;
    }

    public void setYaw(float yaw) {
        this.Yaw = yaw;
    }

    public float getPitch() {
        return this.Pitch;
    }

    public void setPitch(float pitch) {
        this.Pitch = pitch;
    }

    public Location getLocation(){
        Location loc = new Location(Bukkit.getWorld(this.world),this.x,this.y,this.z,this.Yaw,this.Pitch);
        return loc;
    }
}
