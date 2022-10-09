package me.taison.sectors;

import org.bukkit.plugin.java.JavaPlugin;

public final class Sectors extends JavaPlugin {

    private final static Thread thread = new Thread(new RedisPubSubSystem()::subscribeChannel);

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new WorldBorderBoundsEvent(), this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "changesector:main");
        saveDefaultConfig();

        thread.start();
    }



    @Override
    public void onDisable() {
        thread.stop();
    }
}
