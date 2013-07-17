package org.IMC.Files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationYML{

	private static File file = new File("plugins/Tutorial_NetzwerkPlugin/", "Configuration.yml");
	private static FileConfiguration fc = YamlConfiguration.loadConfiguration(file);

	public static FileConfiguration getConfig(){
		return fc;
	}
	
	public static void save() {
		try {
			fc.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addDefault(String path, Object value) {
		if (!ConfigurationYML.fc.isSet(path)) {
			ConfigurationYML.fc.set(path, value);
		}
		ConfigurationYML.save();
	}
	
}
