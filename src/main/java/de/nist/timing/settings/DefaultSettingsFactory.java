package de.nist.timing.settings;

public class DefaultSettingsFactory {
	public static Settings create() {
		Settings settings = new Settings();
		settings.setSetting(new Setting(SettingName.APP_SETTINGS_FILE_LOCATION, "./"));
		settings.setSetting(new Setting(SettingName.COLOR_BORDER, "\\cellcolor{gray!40}"));
		settings.setSetting(new Setting(SettingName.COLOR_COMMENT, "\\cellcolor{yellow!60}"));
		settings.setSetting(new Setting(SettingName.COLOR_CORNER, "\\cellcolor{gray!60}"));
		settings.setSetting(new Setting(SettingName.FEDERAL_STATE, "BW"));
		settings.setSetting(new Setting(SettingName.PDFLATEX_FILE_LOCATION, "/Library/TeX/texbin/pdflatex"));
		settings.setSetting(new Setting(SettingName.PLANNED_WORKING_TIME, "480"));
		settings.setSetting(new Setting(SettingName.REGEX_FOR_FILENAME, "^\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d[T]\\d\\d[_]\\d\\d[_]\\d\\d[.][t][x][t]$"));
		settings.setSetting(new Setting(SettingName.WAIT_FOR_PDFLATEX_SEC, "60"));
		settings.setSetting(new Setting(SettingName.WH_CAL_DATA_FOLDER, "WHCalData"));
		
		return settings;
	}
}