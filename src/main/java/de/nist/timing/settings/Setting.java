package de.nist.timing.settings;

public class Setting<T> {

	private final SettingName settingName;
	private T setting;
	
	public Setting(SettingName settingName) {
		this.settingName = settingName;
	}

	public Setting(SettingName settingName, T setting) {
		this.settingName = settingName;
	}
	
	public SettingName getSettingName() {
		return this.settingName;
	}

	public T getSetting() {
		return setting;
	}

	public void setSetting(T setting) {
		this.setting = setting;
	}
}
