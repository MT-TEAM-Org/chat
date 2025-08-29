package com.myteam.chat.whenkafkaisable.kafkamainserver.match.match.repository;


//@Repository
//public interface MatchRepository extends JpaRepository<Match, Long> {
//	@Query(value = "SELECT * FROM p_match m WHERE m.start_time >= :startOfDay AND m.start_time <= :endOfTomorrow AND m.category = :matchCategory", nativeQuery = true)
//	List<Match> findMatchesTodayAndTomorrow(@Param("startOfDay") String startOfDay,
//		@Param("endOfTomorrow") String endOfTomorrow,
//		@Param("matchCategory") String matchCategory);
//
//	@Query(value =
//		"WITH RankedMatches AS (" +
//			"  SELECT m.*, ROW_NUMBER() OVER (PARTITION BY DATE(m.start_time) ORDER BY m.start_time DESC) AS rn " +
//			"  FROM p_match m " +
//			"  WHERE m.start_time >= :startOfDay AND m.start_time <= :endOfTomorrow " +
//			"    AND m.category = 'ESPORTS'" +
//			") " +
//			"SELECT * FROM RankedMatches WHERE rn = 1", nativeQuery = true)
//	List<Match> findLastEsportsMatchPerDay(@Param("startOfDay") String startOfDay,
//		@Param("endOfTomorrow") String endOfTomorrow);
//}
