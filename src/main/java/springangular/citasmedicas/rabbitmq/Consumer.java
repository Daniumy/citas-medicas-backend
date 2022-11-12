package springangular.citasmedicas.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import springangular.citasmedicas.entityDto.CitaDTO;
import springangular.citasmedicas.entityDto.DiagnosticoDTO;
import springangular.citasmedicas.entityDto.MedicoDTO;
import springangular.citasmedicas.entityDto.PacienteDTO;
import springangular.citasmedicas.mongoModel.DiagnosticoMongo;
import springangular.citasmedicas.service.*;

import java.util.concurrent.CountDownLatch;

@Component
public class Consumer {

    @Autowired
    DiagnosticoMongoService diagnosticoMongoService;

    @RabbitListener(queues = "citasmedicas-diagnostico")
    public void receiveDiagnosticoMessage(@Payload DiagnosticoDTO message) {
        System.out.println("Received <" + message.toString() + ">");
        diagnosticoMongoService.add(message);
        makeSlow();
    }


    private void makeSlow() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
