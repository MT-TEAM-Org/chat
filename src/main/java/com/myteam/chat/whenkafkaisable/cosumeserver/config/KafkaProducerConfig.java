package com.myteam.chat.whenkafkaisable.cosumeserver.config;


//@Configuration
//public class KafkaProducerConfig {

   /*@Value("${spring.kafka.bootstrap-servers}")
    private  String Kafka_Main_Sever;


    //chatresponse 객체를 produceing 애임
    @Bean
    public ProducerFactory<String,String> kafkaProducer(){
        Map<String,Object> config=new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,Kafka_Main_Sever);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }
   // @Bean(name = "kafkaConsumeTemplate")
    //@Primary
    public KafkaTemplate<String,String> kafkaConsumeTemplate(){
        return new KafkaTemplate<>(kafkaProducer());
    }*/
//}
