package de.nist.timing.settings;

public class LaTeXSettings {
	public static Integer WAIT_FOR_PDFLATEX_SEC = 60;
	public static String PDFLATEX_FILE_LOCATION = "/Library/TeX/texbin/pdflatex";
	public static String REGEX_FOR_FILENAME = "^\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d[T]\\d\\d[_]\\d\\d[_]\\d\\d[.][t][x][t]$";
	
	/* Colors */
	public static String COLOR_BORDER = "\\cellcolor{gray!40}";
	public static String COLOR_COMMENT = "\\cellcolor{yellow!60}";
	public static String COLOR_CORNER = "\\cellcolor{gray!60}";
}