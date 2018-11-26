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
public class MCImage {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "mc_image_id")
  private long mcImageId;

  @ColumnInfo(name = "level_id")
  private long levelId;

  @NonNull
  @ColumnInfo(name = "mc_image")
  private Image mcImage;

  public MCImage (@NonNull Image mcImage, long levelId) {
    this.levelId = levelId;
    this.mcImage = mcImage;
  }

  public long getMcImageId() {
    return mcImageId;
  }

  public void setMcImageId(long mcImageId) {
    this.mcImageId = mcImageId;
  }

  public long getLevelId() {
    return levelId;
  }

  public void setLevelId(long levelId) {
    this.levelId = levelId;
  }

  @NonNull
  public Image getMcImage() {
    return mcImage;
  }

  public void setMcImage(@NonNull Image mcImage) {
    this.mcImage = mcImage;
  }
}
