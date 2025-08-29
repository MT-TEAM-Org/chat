package com.myteam.chat.whenkafkaisable.kafkamainserver.event;


import lombok.extern.slf4j.Slf4j;


//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class AdminKafkaEventHandler {


//    private final KafkaAdmin kafkaAdmin;
    //@Qualifier("adminKafkaTemplate")
  //  private final KafkaTemplate<String,String> adminKafkaTemplate;
    /*@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void ConsumerDelControl(ConsumerDelEvent consumerDelEvent){
        try (AdminClient adminClient =
                     AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            adminClient.deleteTopics(Collections.singletonList(consumerDelEvent.getTopic())).all().get();
            log.info("Topic '{}' deleted successfully.",consumerDelEvent.getTopic());
            adminKafkaTemplate.send("del",consumerDelEvent.getTopic());
        } catch (ExecutionException | InterruptedException e) {
            log.error("Failed to delete topic '{}': {}", consumerDelEvent.getTopic(), e.getMessage());
            Thread.currentThread().interrupt(); // 인터럽트 복원
        }
    }*/
    /*@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void ConsumerCreateControl(ConsumerCreateEvent consumerCreateEvent){
        log.info("create event:{}",consumerCreateEvent.getTopic());
        adminKafkaTemplate.send("create",consumerCreateEvent.getTopic());
    }*/

//}
