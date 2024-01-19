package com.nook.TownRegistry.controller;

import com.nook.TownRegistry.model.bulletinBoard.BulletinBoardPost;
import com.nook.TownRegistry.service.BulletinBoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nook/{townId}/{citizenId}/bulletinBoard")
public class BulletinBoardController {

    private final BulletinBoardService bulletinBoardService;

    @RequestMapping(method = RequestMethod.POST, value="createMessage/{messageId}")
    @Operation(summary = "Create new message", description = "Creates a new message for the bulletin board")
    public BulletinBoardPost createBulletinPost(@PathVariable String townId, @PathVariable String citizenId, @PathVariable String messageId, @RequestBody String message){
        return bulletinBoardService.create(townId, citizenId, messageId, message);
    }

    @RequestMapping(method = RequestMethod.GET, value="getMessages")
    @Operation(summary = "Retrieve messages by citizen", description = "Retrieve a citizen's bulletin board messages by townId and citizenId")
    public List<BulletinBoardPost> getBulletinPostsByCitizen(@PathVariable String townId, @PathVariable String citizenId){
        return bulletinBoardService.getByCitizen(townId, citizenId);
    }

    @RequestMapping(method = RequestMethod.GET, value="getMessage/{messageId}")
    @Operation(summary = "Retrieve message by Id", description = "Retrieve a bulletin board message by Id")
    public BulletinBoardPost getBulletinPostsById(@PathVariable String messageId){
        return bulletinBoardService.getById(messageId);
    }
}
