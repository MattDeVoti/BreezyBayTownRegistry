package com.nook.TownRegistry.controller;

import com.nook.TownRegistry.model.bulletinBoard.BulletinBoardPost;
import com.nook.TownRegistry.service.BulletinBoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nook/{townId}/bulletinBoard")
public class BulletinBoardController {

    private final BulletinBoardService bulletinBoardService;

    @RequestMapping(method = RequestMethod.POST, value="createMessage/{citizenId}")
    @Operation(summary = "Create new message", description = "Creates a new message for the bulletin board")
    public BulletinBoardPost createBulletinPost(@PathVariable String townId, @PathVariable String citizenId, @RequestBody String message){
        return bulletinBoardService.create(townId, citizenId, message);
    }

    @RequestMapping(method = RequestMethod.GET, value="getMessages/{citizenId}")
    @Operation(summary = "Retrieve message", description = "Retrieve a bulletin board message.")
    public List<BulletinBoardPost> getBulletinPost(@PathVariable String townId, @PathVariable String citizenId){
        return bulletinBoardService.get(townId, citizenId);
    }
}
