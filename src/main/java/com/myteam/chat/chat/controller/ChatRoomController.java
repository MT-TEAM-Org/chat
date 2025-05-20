package com.myteam.chat.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myteam.chat.chat.controller.request.ChatRoomSaveRequest;
import com.myteam.chat.chat.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatRoom")
public class ChatRoomController {

	private final ChatRoomService chatRoomService;

	@PostMapping
	public ResponseEntity<Long> createChatRoom(@RequestBody ChatRoomSaveRequest chatRoomSaveRequest) {
		return ResponseEntity.ok(chatRoomService.createChatRoom(chatRoomSaveRequest.getRoomId()));
	}
}
