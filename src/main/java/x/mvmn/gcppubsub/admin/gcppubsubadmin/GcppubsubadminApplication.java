package x.mvmn.gcppubsub.admin.gcppubsubadmin;

import java.awt.Desktop;
import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GcppubsubadminApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(GcppubsubadminApplication.class, args);
		// PubSubAdmin adm = app.getBean(PubSubAdmin.class);
		// adm.listTopics().stream().map(topic -> "Topic: " + topic.getName()).forEach(System.out::println);
		// adm.listSubscriptions().stream().map(subscription -> "Subscription: " + subscription.getName()).forEach(System.out::println);
		// PubSubTemplate template = app.getBean(PubSubTemplate.class);
		// List<AcknowledgeablePubsubMessage> msgs = template.pull("projects/test/subscriptions/Test3.mvmn-gcppubsub-http-source.Test3", 100,
		// false);
		// msgs.stream().map(msg -> msg.getAckId() + ": " + msg.getPubsubMessage().getData().toString(StandardCharsets.UTF_8))
		// .forEach(System.out::println);
		//
		// template.publish("projects/test/topics/Test3.mvmn-gcppubsub-http-source", "Test");
		// System.out.println("done");

		try {
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				Desktop.getDesktop()
						.browse(new URI("http://127.0.0.1:" + Integer.parseInt(app.getEnvironment().getProperty("local.server.port"))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
