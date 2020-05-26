package x.mvmn.gcppubsub.admin.gcppubsubadmin.web.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.pubsub.PubSubAdmin;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.pubsub.v1.PubsubMessage;

import io.swagger.annotations.Api;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.util.GcpUtil;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.GenericCollectionDto;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.MessageDto;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.MessageExtDto;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.SubscriptionDto;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.SubscriptionWithTopicDto;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.TopicDto;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.mapper.PubSubModelsMapper;

@RestController
@RequestMapping("/api/v1/pubsub")
@Api("PubSub API")
public class PubSubApiController {

	@Autowired
	private PubSubAdmin pubSubAdmin;

	@Autowired
	private PubSubTemplate template;

	@Autowired
	private PubSubModelsMapper pubSubModelsMapper;

	@Value("${spring.cloud.gcp.project-id}")
	private String gcpProjectId;

	@GetMapping("topics")
	public GenericCollectionDto<TopicDto> listTopics() {
		return new GenericCollectionDto<>(pubSubAdmin.listTopics().stream().map(pubSubModelsMapper::toDto).collect(Collectors.toList()));
	}

	@PostMapping("topics")
	public TopicDto createTopic(@Valid @RequestBody TopicDto topic) {
		return pubSubModelsMapper.toDto(pubSubAdmin.createTopic(topic.getName()));
	}

	@GetMapping("subscriptions")
	public GenericCollectionDto<SubscriptionWithTopicDto> listSubscriptions() {
		return new GenericCollectionDto<>(pubSubAdmin.listSubscriptions().stream()
				.map(sub -> SubscriptionWithTopicDto.of(pubSubModelsMapper.toDto(sub), sub.getTopic())).collect(Collectors.toList()));
	}

	@PostMapping("subscriptions")
	public SubscriptionDto createTopicSubscription(@Valid @RequestBody SubscriptionWithTopicDto subscription) {
		return pubSubModelsMapper.toDto(
				pubSubAdmin.createSubscription(subscription.getName(), GcpUtil.topicFullName(subscription.getTopicName(), gcpProjectId)));
	}

	@PostMapping("publish/{topicName}")
	public String publish(@PathVariable("topicName") String topicName, @Valid @RequestBody MessageDto message) {
		try {
			return template.publish(GcpUtil.topicFullName(topicName, gcpProjectId), message.getPayload(),
					message.getHeaders())
					.get();
		} catch (InterruptedException e) {
			Thread.interrupted();
			throw new RuntimeException("Interrupted while publishing message", e);
		} catch (ExecutionException e) {
			throw new RuntimeException("Failed to publish message", e.getCause());
		}
	}

	@PostMapping("receive/{subscriptionName}")
	public GenericCollectionDto<MessageExtDto> receive(@PathVariable("subscriptionName") String subscriptionName) {
		List<PubsubMessage> messages = template.pullAndAck(subscriptionName, 10, true);
		return new GenericCollectionDto<>(messages.stream().map(pubSubModelsMapper::toDto).collect(Collectors.toList()));
	}
}
