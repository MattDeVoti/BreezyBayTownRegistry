package com.nook.TownRegistry.service;

import com.nook.TownRegistry.exception.BadRequestException;
import com.nook.TownRegistry.model.bulletinBoard.BulletinBoardPost;
import com.nook.TownRegistry.repository.BulletinBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class BulletinBoardService {

    private final CitizenService citizenService;
    private final TownService townService;
    private final BulletinBoardRepository bulletinBoardRepository;


    public BulletinBoardPost create(String townId, String citizenId, String message) {
        BulletinBoardPost bulletinBoardPost = buildPost(townId, citizenId, message);
        bulletinBoardPost = bulletinBoardRepository.save(bulletinBoardPost);
        return bulletinBoardPost;
    }

    public List<BulletinBoardPost> get(String townId, String citizenId){
        validation(townId, citizenId);
        List<BulletinBoardPost> bulletinBoardPosts = bulletinBoardRepository.findByTownIdAndCitizenId(townId, citizenId);
        return  bulletinBoardPosts;
    }

    public BulletinBoardPost buildPost(String townId, String citizenId, String message){
        String townName = townService.get(townId).getName();
        String citizenName = citizenService.get(townId, citizenId).getName();
        validation(townId, citizenId, message, townName, citizenName);

        message = messageBuilder(townName, citizenName,message);
        BulletinBoardPost bulletinBoardPost = new BulletinBoardPost(townId, citizenId, message, townName, citizenName);
        bulletinBoardPost.populateCreatedAt();
        return bulletinBoardPost;
    }

    public String messageBuilder(String townName, String citizenName, String message){
        String header = "Dear citizens of " + townName + ",\n";
        String footer = "\n-" + citizenName;

        return (header + message + footer);
    }

//    public Date currentDateTime(){
////        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        Date now  = new Date(System.currentTimeMillis());
//        return now;
//    }

    public void validation(String townId, String citizenId, String message, String townName, String citizenName){
        if(message.length()>1000){
            throw new BadRequestException("Bulletin message too long (cannot be longer than 1000 characters)");
        }
        if(message.isBlank()){
            throw new BadRequestException("No message contained in post");
        }
        if(townId.isBlank()){
            throw new BadRequestException("townId cannot be null, town with this id may not exist");
        }
        if(citizenId.isBlank()){
            throw new BadRequestException("residentId cannot be null, citizen with this id may not exist");
        }
        if(townName.isBlank()){
            throw new BadRequestException("townName cannot be null");
        }
        if(citizenName.isBlank()){
            throw new BadRequestException("citizenName cannot be null");
        }
    }

    public void validation(String townId, String citizenId){
        if(citizenId.isBlank()){
            throw new BadRequestException("residentId cannot be null");
        }
        if(townId.isBlank()){
            throw new BadRequestException("townId cannot be null");
        }
    }
}
