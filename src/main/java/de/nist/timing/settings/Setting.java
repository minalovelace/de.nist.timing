package de.nist.timing.settings;

public class Setting {
	private final SettingName settingName;
	private String setting;

	public Setting(SettingName settingName) {
		this.settingName = settingName;
	}

	public Setting(SettingName settingName, String setting) {
		this.settingName = settingName;
		this.setting = setting;
	}

	public SettingName getSettingName() {
		return this.settingName;
	}

	public String getSetting() {
		return setting;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}
}