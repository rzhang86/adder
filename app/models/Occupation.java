package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

@Entity public class Occupation extends Model {
	@Id public Integer id; public Integer getId() {return this.id;} public Occupation setId(Integer id) {this.id = id; return this;}

	// has many UserOccupation
	public List<UserOccupation> findUserOccupations() {return UserOccupation.find.where().eq("occupationId", id).findList();}
	
	public String name; public String getName() {return this.name;} public Occupation setName(String name) {this.name = name; return this;}
    
	public static Occupation create(Integer id, String name) {
        return (new Occupation()).setId(id).setName(name).saveGet();
    }
	public Occupation saveGet() {this.save(); return this;}
    public static Finder<Integer, Occupation> find = new Finder<Integer, Occupation>(Integer.class, Occupation.class);
}