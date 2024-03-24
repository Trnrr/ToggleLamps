package trnrr.toggleablelamps;

import turniplabs.halplibe.util.ConfigUpdater;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

public class Config {
	public static ConfigUpdater updater = ConfigUpdater.fromProperties();
	private static final Toml properties = new Toml("Toggleable Lamps TOML Config");
	public static TomlConfigHandler cfg;
	static {
		properties.addCategory("Item IDs")
			.addEntry("StartingID", 8888);

		cfg = new TomlConfigHandler(updater, ToggleableLamps.MOD_ID, properties);
	}
}
