package de.nist.timing.settings;

import java.util.HashMap;

/*
 * This class holds the user specific settings like the federal state he or she lives in or the amount of minutes to work on a normal working day.
 */
public class Settings<T> {
	private HashMap<SettingName, T> settings;

	public HashMap<SettingName, T> getSettings() {
		return settings;
	}

	public void setSetting(Setting<T> setting) {
		this.settings.put(setting.getSettingName(), setting.getSetting());
	}

	public T getSetting(SettingName settingName) {
		return this.settings.get(settingName);
	}
}