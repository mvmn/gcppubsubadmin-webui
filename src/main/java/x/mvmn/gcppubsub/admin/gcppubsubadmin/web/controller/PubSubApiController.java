package x.mvmn.gcppubsub.admin.gcppubsubadmin.web.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.pubsub.PubSubAdmin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.util.GcpUtil;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.GenericCollectionDto;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.SubscriptionDto;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.TopicDto;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.exception.NotFoundException;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.mapper.PubSubModelsMapper;

@RestController
@RequestMapping("/api/v1/pubsub")
@Api("PubSub API")
public class PubSubApiController {

	@Autowired
	private PubSubAdmin pubSubAdmin;

	@Autowired
	private PubSubModelsMapper pubSubModelsMapper;

	@Value("${spring.cloud.gcp.project-id}")
	private String gcpProjectId;

	@GetMapping("/topics")
	public GenericCollectionDto<TopicDto> listTopics() {
		return new GenericCollectionDto<>(pubSubAdmin.listTopics().stream().map(pubSubModelsMapper::toDto).collect(Collectors.toList()));
	}

	@GetMapping("/topics/{topicName}")
	public TopicDto getTopic(@PathVariable String topicName) {
		String topicFullName = GcpUtil.topicFullName(topicName, gcpProjectId);
		return pubSubAdmin.listTopics().stream().filter(topic -> topic.getName().equals(topicFullName)).map(pubSubModelsMapper::toDto)
				.findAny().orElseThrow(() -> new NotFoundException("No topic found with name " + topicName));
	}

	@PostMapping("/topics")
	public TopicDto createTopic(@Valid TopicDto topic) {
		return pubSubModelsMapper.toDto(pubSubAdmin.createTopic(topic.getName()));
	}

	@GetMapping("/topics/{topicName}/subscriptions")
	public GenericCollectionDto<SubscriptionDto> listSubscriptions(@PathVariable String topicName) {
		String topicFullName = GcpUtil.topicFullName(topicName, gcpProjectId);
		return new GenericCollectionDto<>(pubSubAdmin.listSubscriptions().stream().filter(sub -> sub.getTopic().equals(topicFullName))
				.map(pubSubModelsMapper::toDto).collect(Collectors.toList()));
	}
}
