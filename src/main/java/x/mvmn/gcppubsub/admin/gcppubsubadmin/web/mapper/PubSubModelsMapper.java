package x.mvmn.gcppubsub.admin.gcppubsubadmin.web.mapper;

import org.mapstruct.Mapper;

import com.google.pubsub.v1.Subscription;
import com.google.pubsub.v1.Topic;

import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.SubscriptionDto;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.TopicDto;

@Mapper(componentModel = "spring")
public interface PubSubModelsMapper {

	TopicDto toDto(Topic topic);

	SubscriptionDto toDto(Subscription topic);
}
