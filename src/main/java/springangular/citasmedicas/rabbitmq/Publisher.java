package springangular.citasmedicas.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springangular.citasmedicas.entityDto.CitaDTO;
import springangular.citasmedicas.entityDto.DiagnosticoDTO;
import springangular.citasmedicas.entityDto.MedicoDTO;
import springangular.citasmedicas.entityDto.PacienteDTO;

@Component
@EnableRabbit
public class Publisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDiagnostico(DiagnosticoDTO message) {
        rabbitTemplate.convertAndSend("citasmedicas-diagnostico", message);
    }

}
