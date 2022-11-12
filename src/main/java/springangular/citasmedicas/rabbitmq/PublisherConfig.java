package springangular.citasmedicas.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfig {

    @Bean
    public Queue queueDiagnostico() {
        return new Queue("citasmedicas-diagnostico", true);
    }
}
