package com.orangemask;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class App
{
    private static final String TOPIC = "auto-orders";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main( String[] args ) {
        System.out.println("Consumer launch");

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "T-DealerShip");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(TOPIC));


        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

                for (ConsumerRecord<String, String> record : records) {

                    System.out.println(" Прилетело из Kafka: " + record.value());

                    String[] message = record.value().split(":");

                    int carPrice = Integer.parseInt(message[1]);
                    int downPaymentAmount = Integer.parseInt(message[2]);
                    if(downPaymentAmount > carPrice){
                        System.out.println("Rejection: the down payment exceeds the purchase price.");
                    }else if(downPaymentAmount == carPrice){
                        System.out.println("The purchase was made without the use of credit funds.");
                    }else{
                        int creditBody = carPrice - downPaymentAmount;
                        double totalDebt = creditBody * 1.2;
                        double monthlyPayment = totalDebt / 36;
                        System.out.println(monthlyPayment);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            consumer.close();
            System.out.println("Project finished.");
        }
    }
}
