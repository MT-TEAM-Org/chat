//package com.myteam.chat.batch.chatroom;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.myteam.chat.chat.service.ChatRoomReadService;
//import com.myteam.chat.chat.service.ChatRoomService;
//import com.myteam.chat.kafka.service.TopicManagementService;
//import com.myteam.chat.match.match.domain.Match;
//import com.myteam.chat.match.match.domain.MatchCategory;
//import com.myteam.chat.match.match.service.MatchReadService;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class ChatRoomSchedule {
//
//	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//	private final TopicManagementService topicManagementService;
//	private final MatchReadService matchReadService;
//	private final ChatRoomService chatRoomService;
//	private final ChatRoomReadService chatRoomReadService;
//
//	//매일 새벽 3시
//	@Scheduled(cron = "0 0 3 * * *")
//	public void chatRoomCron() {
//		//채팅방 토픽 삭제
//		chatRoomReadService.findChatRoomYesterDay().forEach(chatRoom -> {
//			topicManagementService.deleteTopic("room-" + chatRoom.getId());
//		});
//
//		//채팅방 생성 오늘부터 이틀 뒤 까지
//		LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
//		LocalDateTime endOfTomorrow = startOfDay.plusDays(3);
//		String startStr = startOfDay.format(DATE_TIME_FORMATTER);
//		String endStr = endOfTomorrow.format(DATE_TIME_FORMATTER);
//
//		for (MatchCategory category : MatchCategory.values()) {
//			if (category.equals(MatchCategory.ESPORTS)) { //ESPORTS는 당일의 마지막경기 ID 기준으로 채팅방을 생성함 (길표님이랑 이야기됌)
//				matchReadService.findLastEsportsMatchPerDay(startStr, endStr)
//					.forEach(match -> {
//						if (!chatRoomReadService.existsById(match.getId())) {
//							chatRoomService.createChatRoom(match);
//						}
//					});
//				continue;
//			}
//
//			matchReadService.findMatchesTodayAndTomorrow(startStr, endStr, category)
//				.forEach(match -> {
//					if (!chatRoomReadService.existsById(match.getId())) {
//						chatRoomService.createChatRoom(match);
//					}
//				});
//		}
//
//	}
//}
