package com.myteam.chat.whenkafkaisable.kafkamainserver;



//@RestController
//@RequiredArgsConstructor
//@Slf4j
//@Transactional
//public class TestChatRoomCreate {

  //  private final RedisChatRoomService redisChatRoomService;
    //private final KafkaAdmin kafkaAdmin;
  //  private final ApplicationEventPublisher publisher;
 //   private final KafkaTemplate<String,String> adminKafkaTemplate;
  //  private static final String TOPIC_PREFIX = "topic_chat.match-";
  //  @GetMapping("/test")
   // public String createSetting(){

     //       try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
                //redisChatRoomService.openRoom(1L);
         //       NewTopic topic = TopicBuilder
           //             .name(TOPIC_PREFIX+1L)
             //           .partitions(3)
               //         .build();
              //  adminClient.createTopics(Collections.singletonList(topic)).all().get();
              //  log.info("Topic '{}' created successfully with {} partitions and replication factor {}.",
                 //       topic.name(),topic.numPartitions(),1);
             //  ConsumerCreateEvent consumerControlEvent=ConsumerCreateEvent
                     //   .builder()
                  //      .topic(TOPIC_PREFIX+1L)
                  //      .build();
             //   adminKafkaTemplate.send("create",TOPIC_PREFIX+1L);
          //      return "ok";
          //  } catch (ExecutionException | InterruptedException e) {
             //   log.error("Failed to create topic about match {}", e.getMessage());
              //  Thread.currentThread().interrupt(); // 인터럽트 복원
                /*if(chatRoom!=null) {
                    chatRoomService.eraseChatRoom(chatRoom.getId());
                }*/
         //   }
         //   return "ok";
   // }
   // @GetMapping("/del")
   // public String deltest(){

       // try (AdminClient adminClient =
            //         AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
         //   adminClient.deleteTopics(Collections.singletonList(TOPIC_PREFIX+1L)).all().get();
         //   adminKafkaTemplate.send("del",TOPIC_PREFIX+1L);
         //   log.info("delete scuccess");
      //  } catch (Exception e) {
          //  log.error("Failed to delete topic {}",e.getMessage());
          //  Thread.currentThread().interrupt(); // 인터럽트 복원
       // }
      //  return "ok";
   // }
//}
