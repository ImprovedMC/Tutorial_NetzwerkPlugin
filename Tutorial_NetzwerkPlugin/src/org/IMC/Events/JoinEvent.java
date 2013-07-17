package org.IMC.Events;

import net.minecraft.server.v1_6_R2.EntityHuman;
import net.minecraft.server.v1_6_R2.Packet20NamedEntitySpawn;

import org.IMC.Files.ConfigurationYML;
import org.IMC.Main.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_6_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

	@SuppressWarnings("unused")
	private MainClass main;

	public JoinEvent(MainClass main){
		this.main = main;
	}
	
	@EventHandler
	public void executeEvent(PlayerJoinEvent e){
		Player p = e.getPlayer();
		String name = "";
		if(p.isOp())
			name = "&c" + p.getName();
		else
			name = "&9" + p.getName();
		String joinMessage = ChatColor.translateAlternateColorCodes('&', ConfigurationYML.getConfig().getString("Server.JoinMessage").replaceAll("%PALYER%", name));
		e.setJoinMessage(joinMessage);
		CraftPlayer cp = (CraftPlayer)p;
		EntityHuman eh = (EntityHuman) p;
		Packet20NamedEntitySpawn pnes = new Packet20NamedEntitySpawn(eh);
		cp.getHandle().listName = ChatColor.translateAlternateColorCodes('&', name);
		for(Player target : Bukkit.getOnlinePlayers()){
			CraftPlayer targetcp = (CraftPlayer) target;
			targetcp.getHandle().playerConnection.sendPacket(pnes);
		}
	}
	
}
