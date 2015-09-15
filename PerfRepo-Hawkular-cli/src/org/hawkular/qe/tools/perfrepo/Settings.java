package org.hawkular.qe.tools.perfrepo;

import java.util.ArrayList;
import java.util.Map;

public class Settings {

	// Test Execution Tags
	public ArrayList<String> tags;
	// Test Execution Parameters
	public Map<String, String> parameters;
	// Test Execution Metrics
	public ArrayList<MetricGlue> metrics;
	public Map<String, String> perfrepo;
	public Map<String, ArrayList<String>> attachments;

	public void printSettings() {
		printList(tags, "tags");
		printList(metrics, "metrics");
		printHashMap(attachments, "attachments");
		printHashMap(parameters, "parameters");
		printHashMap(perfrepo, "perfrepo");
	}

	public void printList(ArrayList<?> l, String label) {
		PerfRepoClientWrapper.logger.info(label + ":");
		String out = "";
		for (Object value : l) {
			if (value.getClass() == String.class)
				out += "\n\t" + value;
			if (value.getClass() == MetricGlue.class) {
				MetricGlue mg = (MetricGlue) value;
				out += "\n\tRemoteName: " + mg.remoteName + "\tCSVColumnName: "
						+ mg.CSVColumnName;
			}
		}
		PerfRepoClientWrapper.logger.info(out);
	}

	@SuppressWarnings("unchecked")
	// for line: for (String s : (ArrayList<String>) value) {
	public void printHashMap(Map<String, ?> hm, String label) {
		String out = "";
		PerfRepoClientWrapper.logger.info(label + ":");
		for (String name : hm.keySet()) {
			String key = name.toString();
			Object value = hm.get(name).toString();
			if (value.getClass() == String.class)
				out += "\n\t" + key + ": " + value + "";
			if (value.getClass() == ArrayList.class) {
				out += "\n\t" + key + ":";
				for (String s : (ArrayList<String>) value) {
					out += "\n\t\t" + s;
				}
			}
		}
		PerfRepoClientWrapper.logger.info(out);
	}

	// *.yml file data getters

	public String getTestUId() {
		return perfrepo.get("testUID");
	}

	public String getTestExecutionName() {
		return perfrepo.get("testExecturionName");
	}

	public String getDelimiter() {
		return perfrepo.get("csvFileDelimiter");
	}

	public String getCsvFilePath() {
		return perfrepo.get("csvFilePath");
	}

	// Static getters

	public static String getHost() {
		return System.getProperty("host");
	}

	public static String getUrl() {
		return (System.getProperty("url") == null) ? "" : System
				.getProperty("url");
	}

	public static String getBasicHash() {
		return System.getProperty("basicHash");
	}

	public static String getSettingsFile() {
		return System.getProperty("settingsFile");
	}

}
