package com.myteam.chat.whenkafkaisable.kafkamainserver.config;


//@Configuration
//public class AdminKafkaProduceConfig {

   /* @Value("${spring.kafka.bootstrap-servers}")
    private String servers;*/

    //requestconsume을 프로듀싱하는애 정확히 말하자면은 각 채팅서버의 consumer의 create,del을 유도함.
   // @Bean
    /*public ProducerFactory<String,String> KafkaControlProducer(){
        Map<String,Object> config=new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,servers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }
    //@Bean
    public KafkaTemplate<String, String> adminKafkaTemplate(){
        return new KafkaTemplate<>(KafkaControlProducer());
    }
    //@Bean
    public AdminClient adminClient(KafkaAdmin kafkaAdmin) {
        return AdminClient.create(kafkaAdmin.getConfigurationProperties());

        //이거따로 yml에서 bootstrap server를 설정안해주면 defualtㄱ값으로 localhost:9092가들어간다.
    }*/
//}
