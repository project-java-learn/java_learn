package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.media.Image;
import android.support.annotation.NonNull;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Level.class,
            parentColumns = "level_id",
            childColumns = "level_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class FBImage {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "fb_image_id")
  private long fbImageId;

  @ColumnInfo(name = "level_id")
  private long levelId;

  @NonNull
  @ColumnInfo(name = "fb_image")
  private Image fbImage;

  public FBImage(long levelId, @NonNull Image fbImage) {
    this.levelId = levelId;
    this.fbImage = fbImage;
  }

  public long getFbImageId() {
    return fbImageId;
  }

  public void setFbImageId(long fbImageId) {
    this.fbImageId = fbImageId;
  }

  public long getLevelId() {
    return levelId;
  }

  public void setLevelId(long levelId) {
    this.levelId = levelId;
  }

  @NonNull
  public Image getFbImage() {
    return fbImage;
  }

  public void setFbImage(@NonNull Image fbImage) {
    this.fbImage = fbImage;
  }
}
