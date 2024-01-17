package com.nook.TownRegistry.model.bulletinBoard;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
@Document(collection = "bulletinBoardPost")
public class BulletinBoardPost {
    // TODO: Make expiration time configurable
//    @Value("${townRegistry.bulletinBoard.time.until.expiration:5}")
//    private long expirationTime = 5;

    @Id
    private final String messageId;

    private final String townId;
    private final String citizenId;
    private final String message;
    private final String townName;
    private final String citizenName;


    @Indexed(name = "deleteAt", expireAfterSeconds=300)
    private final LocalDateTime createdAt;
}