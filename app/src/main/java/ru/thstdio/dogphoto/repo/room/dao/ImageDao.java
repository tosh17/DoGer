package ru.thstdio.dogphoto.repo.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import ru.thstdio.dogphoto.repo.room.entity.RoomImage;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ImageDao {
    @Insert(onConflict = REPLACE)
    void insert(RoomImage users);

    @Query("SELECT * FROM image_cache WHERE sha1 = :sha1")
    RoomImage contain(String sha1);
}
