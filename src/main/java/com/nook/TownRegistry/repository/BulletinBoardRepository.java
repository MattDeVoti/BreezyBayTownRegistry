package com.nook.TownRegistry.repository;

import com.nook.TownRegistry.model.bulletinBoard.BulletinBoardPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BulletinBoardRepository extends MongoRepository<BulletinBoardPost, String> {
    List<BulletinBoardPost> findByTownIdAndCitizenId(String townId, String citizenId);
}
