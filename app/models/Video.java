package models;

import java.text.*;
import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.singularsys.jep.Jep;
import com.singularsys.jep.standard.StandardVariableTable;

// todo: limit duration to 1 minute? infinite duration will prevent payment
// todo: may not upload videos if committed balance is negative
// todo: add limit of money spent?
@Entity public class Video extends Model {
	// autogenerated
	@Id public Long id; public Long getId() {return this.id;} public Video setId(Long id) {this.id = id; return this;}
    
	// has one User
    @Id public String userUsername; public String getUserUsername() {return this.userUsername;} public Video setUserUsername(String userUsername) {this.userUsername = userUsername; return this;}
    public User findUser() {return User.find.ref(userUsername);}

    // has many WatchingVideo
    public List<WatchingVideo> findWatchingVideos() {return WatchingVideo.find.where().eq("videoId", id).findList();}
    
    // has many WatchedVideo
    public List<WatchedVideo> findCreditCardAccounts() {return WatchedVideo.find.where().eq("videoId", id).findList();}
    
    public String title = "Untitled"; public String getTitle() {return this.title;} public Video setTitle(String title) {this.title = title; return this;}
    public String description = "No description"; public String getDescription() {return this.description;} public Video setDescription(String description) {this.description = description; return this;}
    public Integer duration = 0; public Integer getDuration() {return this.duration;} public Video setDuration(Integer duration) {this.duration = duration; return this;}
    public String payFormula = ""; public String getPayFormula() {return this.payFormula;} public Video setPayFormula(String payFormula) {this.payFormula = payFormula; return this;}
    
    public static Video create(String userUsername, String title, String description, Integer duration, String payFormula) {
        return (new Video()).setUserUsername(userUsername).setTitle(title).setDescription(description).setDuration(duration).setPayFormula(payFormula).saveGet();
    }
    public Video saveGet() {this.save(); return this;}
    public static Finder<Long, Video> find = new Finder<Long, Video>(Long.class, Video.class);
    
    public Long getPayout(User user) {
        try {
        	ConsumerProfile consumerProfile = ConsumerProfile.find.ref(user.getUsername());
            Jep jep = new Jep();
            jep.setComponent(new StandardVariableTable(jep.getVariableFactory()));
            jep.addVariable("a7", (double) (consumerProfile.getA7() / 100L));
            jep.addVariable("a30", (double) (consumerProfile.getA30() / 100L));
            jep.addVariable("a365", (double) (consumerProfile.getA365() / 100L));
            jep.addVariable("f7", (double) consumerProfile.getF7());
            jep.addVariable("f30", (double) consumerProfile.getF30());
            jep.addVariable("f365", (double) consumerProfile.getF365());
            jep.parse(payFormula); // todo: catch invalid parse, and check pay formula at time of video creation
            return (long) Math.ceil(Double.parseDouble(jep.evaluate().toString()) * 100.0);
        }
        catch (Exception e) {e.printStackTrace();}
        return 0L;
    }
}