package com.myteam.chat.match.match.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myteam.chat.match.match.domain.Match;
import com.myteam.chat.match.match.domain.MatchCategory;
import com.myteam.chat.match.match.repository.MatchRepository;

import lombok.RequiredArgsConstructor;

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
