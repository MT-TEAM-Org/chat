package com.myteam.chat.currentChatDirectory.chatroom;

import com.myteam.chat.currentChatDirectory.chatroom.TopicManagementService;
import com.myteam.chat.currentChatDirectory.match.match.domain.MatchCategory;
import com.myteam.chat.currentChatDirectory.match.match.service.MatchReadService;
import com.myteam.chat.currentChatDirectory.service.ChatRoomReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatRoomSchedule {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String TOPIC_PREFIX = "/topic/chat.match-";
	private final TopicManagementService topicManagementService;
	private final MatchReadService matchReadService;;
	private final ChatRoomReadService chatRoomReadService;

	//매일 새벽 3시
	@Scheduled(cron = "0 0 3 * * *")
	public void chatRoomCron() {
		log.info("cron start");
		//채팅방 토픽 삭제
		chatRoomReadService.findChatRoomYesterDay().forEach(chatRoom -> {
			topicManagementService.deleteTopic(TOPIC_PREFIX + chatRoom.getId());
		});

		//채팅방 생성 오늘부터 이틀 뒤 까지
		LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
		LocalDateTime endOfTomorrow = startOfDay.plusDays(3);
		String startStr = startOfDay.format(DATE_TIME_FORMATTER);
		String endStr = endOfTomorrow.format(DATE_TIME_FORMATTER);

		for (MatchCategory category : MatchCategory.values()) {
			if (category.equals(MatchCategory.ESPORTS)) { //ESPORTS는 당일의 마지막경기 ID 기준으로 채팅방을 생성함 (길표님이랑 이야기됌)
				matchReadService.findLastEsportsMatchPerDay(startStr, endStr)
					.forEach(match -> {
						if (!chatRoomReadService.existsById(match.getId())) {
							topicManagementService.createTopic(match);
						}
					});
				continue;
			}
			matchReadService.findMatchesTodayAndTomorrow(startStr, endStr, category)
				.forEach(match -> {
					if (!chatRoomReadService.existsById(match.getId())) {
						topicManagementService.createTopic(match);
					}
				});
		}

	}
  }

