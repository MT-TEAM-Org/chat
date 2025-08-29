package com.myteam.chat.currentChatDirectory.match.match.service;


import com.myteam.chat.currentChatDirectory.match.match.domain.Match;
import com.myteam.chat.currentChatDirectory.match.match.domain.MatchCategory;
import com.myteam.chat.currentChatDirectory.match.match.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchReadService {

	private final MatchRepository matchRepository;

	public List<Match> findMatchesTodayAndTomorrow(String startOfDay, String endOfTomorrow,
                                                   MatchCategory matchCategory) {
		return matchRepository.findMatchesTodayAndTomorrow(startOfDay, endOfTomorrow, matchCategory.name());
	}

	public List<Match> findLastEsportsMatchPerDay(String startOfDay, String endOfTomorrow) {
		return matchRepository.findLastEsportsMatchPerDay(startOfDay, endOfTomorrow);
	}

}
