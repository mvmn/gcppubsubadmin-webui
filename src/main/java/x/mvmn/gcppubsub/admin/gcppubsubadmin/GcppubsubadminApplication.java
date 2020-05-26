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
