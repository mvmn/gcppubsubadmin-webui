package x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionWithTopicDto extends SubscriptionDto {

	private String topicName;

	public static SubscriptionWithTopicDto of(SubscriptionDto subDto, String topicName) {
		SubscriptionWithTopicDto result = new SubscriptionWithTopicDto();

		result.setName(subDto.getName());
		result.setTopicName(topicName);

		return result;
	}
}
