package com.nook.TownRegistry.model.bulletinBoard;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
@Document(collection = "bulletinBoardPost")
public class BulletinBoardPost {
    private final String townId;
    private final String citizenId;
    private final String message;
    private final String townName;
    private final String citizenName;

    // TODO: delete messages from mongodb after a set amount of time
    @Field
    @Indexed(name="createdAtIndex", expireAfter="5d")
    private Date createdAt;

    public void populateCreatedAt(){
        this.createdAt = Date.from(Instant.now());
    }
}
