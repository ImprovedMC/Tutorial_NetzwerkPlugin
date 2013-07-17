package org.IMC.Main;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_6_R2.MinecraftServer;

import org.IMC.Events.JoinEvent;
import org.IMC.Events.QuitEvent;
import org.IMC.Files.ConfigurationYML;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin{

	@Override
	public void onDisable() {
		
	}
	
	@Override
	public void onLoad() {
		
	}
	
	@Override
	public void onEnable() {
		ArrayList<String> motds = new ArrayList<String>();
		motds.add("Der Server nutzt das NetzwerkPlugin von JayZee");
		motds.add("Das ist ein sich aktualisirender MOTD!");
		ConfigurationYML.addDefault("Server.JoinMessage", "&6Player %PLAYER% ist Online!");
		ConfigurationYML.addDefault("Server.QuitMessage", "&6Player &PLAYER% ist Offline!");
		ConfigurationYML.addDefault("Server.Motds", motds);
		PluginManager pm = Bukkit.getServer().getPluginManager();
		
		pm.registerEvents(new JoinEvent(this), this);
		pm.registerEvents(new QuitEvent(this), this);
	
		List<String> listMOTDS = ConfigurationYML.getConfig().getStringList("Server.Motds");
		final String[] loadMOTDS = {};
		
		int i = 0;
		
		for(String motd : listMOTDS){
			loadMOTDS[i] = motd;
			i++;
		}
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			int j = 0;
			@Override
			public void run() {
				if(j <= loadMOTDS.length){
					MinecraftServer.getServer().setMotd(ChatColor.translateAlternateColorCodes('&', loadMOTDS[j]));
					j++;
				} else {
					j = 0;
					MinecraftServer.getServer().setMotd(ChatColor.translateAlternateColorCodes('&', loadMOTDS[j]));
					j++;
				}
			}
		}, 20L * 3, 20L * 3);
	}
	
}
