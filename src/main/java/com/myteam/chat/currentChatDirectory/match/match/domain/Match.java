package com.myteam.chat.currentChatDirectory.match.match.domain;


import com.myteam.chat.currentChatDirectory.domain.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "p_match")
public class Match extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "home_team_id")
	private Team homeTeam;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "away_team_id")
	private Team awayTeam;

	private String place;

	@Column(name = "league_name")
	private String leagueName;

	@Enumerated(EnumType.STRING)
	private MatchCategory category;

	@Column(name = "start_time")
	private LocalDateTime startTime;

	@Column(name = "end_time")
	private LocalDateTime endTime;
	@Builder
	public Match(Long id, Team homeTeam, Team awayTeam, String place, String leagueName, MatchCategory category, LocalDateTime startTime, LocalDateTime endTime) {
		this.id = id;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.place = place;
		this.leagueName = leagueName;
		this.category = category;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
