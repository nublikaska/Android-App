package com.example.denis.holodos.modules;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Model implements Serializable {

//    private static final long serialVersionUID = 1L;
//
   private long id;
//
//    private boolean archived;
//
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    private LocalDateTime creationDateTime = LocalDateTime.now();
//
//    private LocalDateTime archiveDateTime;
//
    public Model() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
//
//    public boolean isArchived() {
//        return archived;
//    }
//
//    public void setArchived(boolean archived) {
//        this.archived = archived;
//    }
//
//    public LocalDateTime getCreationDateTime() {
//        return creationDateTime;
//    }
//
//    public void setCreationDateTime(LocalDateTime creationDateTime) {
//        this.creationDateTime = creationDateTime;
//    }
//
//    public LocalDateTime getArchiveDateTime() {
//        return archiveDateTime;
//    }
//
//    public void setArchiveDateTime(LocalDateTime archiveDateTime) {
//        this.archiveDateTime = archiveDateTime;
//    }
}
