package by.burov.classifiers.repository.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "security", name = "countries")
public class Country {

     @Id
     @GeneratedValue(generator = "UUID")
     @GenericGenerator(
             name = "UUID",
             strategy = "org.hibernate.id.UUIDGenerator")
     @Column(name = "uuid", updatable = false, nullable = false)
     private UUID uuid;

     @Column(name = "dt_create")
     private LocalDateTime dtCreate;

     @Column(name = "dt_update")
     private LocalDateTime dtUpdate;

     @Column(name = "title", unique = true, nullable = false)
     private String title;

     @Column(name = "description")
     private String description;

     public UUID getUuid() {
          return uuid;
     }

     public void setUuid(UUID uuid) {
          this.uuid = uuid;
     }

     public LocalDateTime getDtCreate() {
          return dtCreate;
     }

     public void setDtCreate(LocalDateTime dtCreate) {
          this.dtCreate = dtCreate;
     }

     public LocalDateTime getDtUpdate() {
          return dtUpdate;
     }

     public void setDtUpdate(LocalDateTime dtUpdate) {
          this.dtUpdate = dtUpdate;
     }

     public String getTitle() {
          return title;
     }

     public void setTitle(String title) {
          this.title = title;
     }

     public String getDescription() {
          return description;
     }

     public void setDescription(String description) {
          this.description = description;
     }
}
