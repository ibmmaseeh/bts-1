package com.ibm.bug.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.bug.entity.Bug;

public interface BugRepository extends MongoRepository<Bug, String> {

}
