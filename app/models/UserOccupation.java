package models;

import javax.persistence.*;
import play.db.ebean.*;

@Entity public class UserOccupation extends Model {
    // autogenerated
    @Id public Long id; public Long getId() {return this.id;} public UserOccupation setId(Long id) {this.id = id; return this;}

    // has one User
    public String userUsername; public String getUserUsername() {return this.userUsername;} public UserOccupation setUserUsername(String userUsername) {this.userUsername = userUsername; return this;}
    public User findUser() {return User.find.ref(userUsername);}

    // has one Occupation
    public Integer occupationId; public Integer getOccupationId() {return this.occupationId;} public UserOccupation setOccupationId(Integer occupationId) {this.occupationId = occupationId; return this;}
    public Occupation findOccupation() {return Occupation.find.ref(occupationId);}
    
	public static UserOccupation create(String userUsername, Integer occupationId) {
        return (new UserOccupation()).setUserUsername(userUsername).setOccupationId(occupationId).saveGet();
    }
	public UserOccupation saveGet() {this.save(); return this;}
    public static Finder<Long, UserOccupation> find = new Finder<Long, UserOccupation>(Long.class, UserOccupation.class);
}