package x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto;

import java.time.Instant;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MessageExtDto extends MessageDto {

	private String messageId;
	private String orderingKey;
	private Instant publishTime;
}
