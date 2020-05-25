package x.mvmn.gcppubsub.admin.gcppubsubadmin.util;

public class GcpUtil {

	public static String topicFullName(String topicShortName, String gcpProjectId) {
		return String.format("projects/%s/topics/%s", gcpProjectId, topicShortName);
	}
}
