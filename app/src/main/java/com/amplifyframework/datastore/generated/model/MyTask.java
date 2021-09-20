package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the MyTask type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "MyTasks")
@Index(name = "byTask", fields = {"teamID"})
public final class MyTask implements Model {
  public static final QueryField ID = field("MyTask", "id");
  public static final QueryField TITLE = field("MyTask", "title");
  public static final QueryField LAT = field("MyTask", "lat");
  public static final QueryField LON = field("MyTask", "lon");
  public static final QueryField BODY = field("MyTask", "body");
  public static final QueryField STATE = field("MyTask", "state");
  public static final QueryField TEAM = field("MyTask", "teamID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String") String lat;
  private final @ModelField(targetType="String") String lon;
  private final @ModelField(targetType="String") String body;
  private final @ModelField(targetType="String") String state;
  private final @ModelField(targetType="Team", isRequired = true) @BelongsTo(targetName = "teamID", type = Team.class) Team team;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getLat() {
      return lat;
  }
  
  public String getLon() {
      return lon;
  }
  
  public String getBody() {
      return body;
  }
  
  public String getState() {
      return state;
  }
  
  public Team getTeam() {
      return team;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private MyTask(String id, String title, String lat, String lon, String body, String state, Team team) {
    this.id = id;
    this.title = title;
    this.lat = lat;
    this.lon = lon;
    this.body = body;
    this.state = state;
    this.team = team;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      MyTask myTask = (MyTask) obj;
      return ObjectsCompat.equals(getId(), myTask.getId()) &&
              ObjectsCompat.equals(getTitle(), myTask.getTitle()) &&
              ObjectsCompat.equals(getLat(), myTask.getLat()) &&
              ObjectsCompat.equals(getLon(), myTask.getLon()) &&
              ObjectsCompat.equals(getBody(), myTask.getBody()) &&
              ObjectsCompat.equals(getState(), myTask.getState()) &&
              ObjectsCompat.equals(getTeam(), myTask.getTeam()) &&
              ObjectsCompat.equals(getCreatedAt(), myTask.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), myTask.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTitle())
      .append(getLat())
      .append(getLon())
      .append(getBody())
      .append(getState())
      .append(getTeam())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("MyTask {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("lat=" + String.valueOf(getLat()) + ", ")
      .append("lon=" + String.valueOf(getLon()) + ", ")
      .append("body=" + String.valueOf(getBody()) + ", ")
      .append("state=" + String.valueOf(getState()) + ", ")
      .append("team=" + String.valueOf(getTeam()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TitleStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static MyTask justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new MyTask(
      id,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      title,
      lat,
      lon,
      body,
      state,
      team);
  }
  public interface TitleStep {
    TeamStep title(String title);
  }
  

  public interface TeamStep {
    BuildStep team(Team team);
  }
  

  public interface BuildStep {
    MyTask build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep lat(String lat);
    BuildStep lon(String lon);
    BuildStep body(String body);
    BuildStep state(String state);
  }
  

  public static class Builder implements TitleStep, TeamStep, BuildStep {
    private String id;
    private String title;
    private Team team;
    private String lat;
    private String lon;
    private String body;
    private String state;
    @Override
     public MyTask build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new MyTask(
          id,
          title,
          lat,
          lon,
          body,
          state,
          team);
    }
    
    @Override
     public TeamStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public BuildStep team(Team team) {
        Objects.requireNonNull(team);
        this.team = team;
        return this;
    }
    
    @Override
     public BuildStep lat(String lat) {
        this.lat = lat;
        return this;
    }
    
    @Override
     public BuildStep lon(String lon) {
        this.lon = lon;
        return this;
    }
    
    @Override
     public BuildStep body(String body) {
        this.body = body;
        return this;
    }
    
    @Override
     public BuildStep state(String state) {
        this.state = state;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String title, String lat, String lon, String body, String state, Team team) {
      super.id(id);
      super.title(title)
        .team(team)
        .lat(lat)
        .lon(lon)
        .body(body)
        .state(state);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder team(Team team) {
      return (CopyOfBuilder) super.team(team);
    }
    
    @Override
     public CopyOfBuilder lat(String lat) {
      return (CopyOfBuilder) super.lat(lat);
    }
    
    @Override
     public CopyOfBuilder lon(String lon) {
      return (CopyOfBuilder) super.lon(lon);
    }
    
    @Override
     public CopyOfBuilder body(String body) {
      return (CopyOfBuilder) super.body(body);
    }
    
    @Override
     public CopyOfBuilder state(String state) {
      return (CopyOfBuilder) super.state(state);
    }
  }
  
}
