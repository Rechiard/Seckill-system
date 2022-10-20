package e.commerce.haowu.frame.rabbitmq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Object msg) {
        log.info("发送信息：" + msg);
        //发送消息给队列queue
        rabbitTemplate.convertAndSend("fanoutExchange", "", msg);
    }

    //发送秒杀信息
    public void sendSeckillMessage(String message) {
        log.info("发送信息:" + message);
        rabbitTemplate.convertAndSend("seckillExchange", "seckill.message", message);
    }


}
