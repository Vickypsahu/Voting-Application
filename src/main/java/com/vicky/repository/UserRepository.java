package com.vicky.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vicky.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUsername(String username);
	
	//@Query("SELECT vote, vote_count FROM (SELECT  vote AS vote,  COUNT(vote) AS vote_count, ROW_NUMBER() OVER (ORDER BY vote) AS row_num FROM User GROUP BY vote)WHERE row_num <= 4")
	//public List<Object[]> getCount();

	//@Cacheable(cacheNames = "my-non-cached-query", key = "#vote")
	//public List<User> findByVote(String vote);
	
	//@Query("select vote,count(vote) from User group by vote order By vote")
	//public List<User> getCount();

}
