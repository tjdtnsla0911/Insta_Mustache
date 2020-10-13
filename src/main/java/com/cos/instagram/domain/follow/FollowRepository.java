package com.cos.instagram.domain.follow;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer>{
	
	
	@Query(value = "SELECT count(*) FROM follow WHERE toUserId = ?1", nativeQuery = true)
	int mCountByFollower(int toUserId);
	
	@Query(value = "SELECT count(*) FROM follow WHERE fromUserId = ?1", nativeQuery = true)
	int mCountByFollowing(int fromUserId); 
	
	@Query(value = "SELECT count(*) FROM follow WHERE fromUserId = ?1 AND toUserId = ?2", nativeQuery = true)
	int mFollowState(int loginUserId, int pageUserId);
	
	// 수정,삭제,추가시에는 모디파이 어노테이션 필요 @Modifying
	// 수정,삭제,추가시에 return값으로 변경된 행의 개수를 받는다.
	//내가만든 쿼리
	@Modifying
	@Query(value="select * from follow where fromuserId = ?1 and  toUserid = ?2",nativeQuery = true)
	Follow loginFollow(int loginUserId,int pageUserId);
		//
	@Modifying
	@Query(value = "INSERT INTO follow(fromUserId, toUserId) VALUES(?1, ?2)", nativeQuery = true)
	int mFollow(int loginUserId, int pageUserId);
	
	@Modifying
	@Query(value = "DELETE FROM follow WHERE fromUserId = ?1 AND toUserId = ?2", nativeQuery = true)
	int mUnFollow(int loginUserId, int pageUserId);
	
	List<Follow> findByFromUserId(Integer userid);
}
