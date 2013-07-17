package org.IMC.Events;

import org.IMC.Files.ConfigurationYML;
import org.IMC.Main.MainClass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

	@SuppressWarnings("unused")
	private MainClass main;

	public QuitEvent(MainClass main){
		this.main = main;
	}
	
	@EventHandler
	public void executeEvent(PlayerQuitEvent e){
		Player p = e.getPlayer();
		String name = "";
		if(p.isOp())
			name = "&c" + p.getName();
		else
			name = "&9" + p.getName();
		String quitMessage = ChatColor.translateAlternateColorCodes('&', ConfigurationYML.getConfig().getString("Server.QuitMessage").replaceAll("%PALYER%", name));
		
		e.setQuitMessage(quitMessage);
	}
	
}
