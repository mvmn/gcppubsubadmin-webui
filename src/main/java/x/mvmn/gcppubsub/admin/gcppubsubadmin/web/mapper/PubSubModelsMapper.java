package x.mvmn.gcppubsub.admin.gcppubsubadmin.web.mapper;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.Subscription;
import com.google.pubsub.v1.Topic;

import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.MessageExtDto;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.SubscriptionDto;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.TopicDto;

@Mapper(componentModel = "spring")
public abstract class PubSubModelsMapper {

	public abstract TopicDto toDto(Topic topic);

	public abstract SubscriptionDto toDto(Subscription topic);

	@Mapping(target = "headers", source = "attributesMap")
	@Mapping(target = "payload", source = "data")
	public abstract MessageExtDto toDto(PubsubMessage message);

	protected String map(ByteString byteString) {
		return byteString != null ? byteString.toString(StandardCharsets.UTF_8) : null;
	}

	protected Instant map(Timestamp timestamp) {
		return timestamp != null ? Instant.ofEpochSecond(timestamp.getSeconds()) : null;
	}
}
