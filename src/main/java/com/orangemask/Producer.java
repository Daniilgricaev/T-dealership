package com.orangemask;

import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
@Slf4j
public class Producer {

    private static final String TOPIC = "auto-orders";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args)throws Exception {
        log.info("Producer launch");
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        log.info("Send cars to Kafka");
        Scanner scan = new Scanner(System.in);
        while(true){
            log.info("Enter car name : ");
            String carName = scan.nextLine();
            if(carName.equalsIgnoreCase("exit")){
                break;
            }
            log.info("Enter price :");
            String priceStr = scan.nextLine();
            log.info("Enter down payment amount : ");
            String downPaymentAmountStr = scan.nextLine();
            int price = Integer.parseInt(priceStr);
            int downPaymentAmount = Integer.parseInt(downPaymentAmountStr);

            String message = carName + ':' + price + ':' + downPaymentAmount;
            producer.send(new ProducerRecord<>(TOPIC, message)).get();
            log.info("Пакет отправлен в Т-Банк!");
        }
        producer.close();
    }
}
