package models;

import javax.persistence.*;
import play.db.ebean.*;

@Entity public class CreditCardAccount extends Model {
	// autogenerated
    @Id public Long id; public Long getId() {return this.id;} public CreditCardAccount setId(Long id) {this.id = id; return this;}
    
    // has one User
    public String userUsername; public String getUserUsername() {return this.userUsername;} public CreditCardAccount setUserUsername(String userUsername) {this.userUsername = userUsername; return this;}
    public User findUser() {return User.find.ref(userUsername);}
    
    // has one FinancialInstitution 
    public Integer financialInstitutionId; public Integer getFinancialInstitutionId() {return this.financialInstitutionId;} public CreditCardAccount setFinancialInstitutionId(Integer financialInstitutionId) {this.financialInstitutionId = financialInstitutionId; return this;}
    public FinancialInstitution findFinancialInstitution() {return FinancialInstitution.find.ref(financialInstitutionId);}
    
    public String ofxUser; public String getOfxUser() {return this.ofxUser;} public CreditCardAccount setOfxUser(String ofxUser) {this.ofxUser = ofxUser; return this;}
    public String ofxPassword; public String getOfxPassword() {return this.ofxPassword;} public CreditCardAccount setOfxPassword(String ofxPassword) {this.ofxPassword = ofxPassword; return this;}
    public String ccNumber; public String getCcNumber() {return this.ccNumber;} public CreditCardAccount setCcNumber(String ccNumber) {this.ccNumber = ccNumber; return this;}
    
    public static CreditCardAccount create(String userUsername, Integer financialInstitutionId, String ofxUser, String ofxPassword, String ccNumber) {
        return (new CreditCardAccount()).setUserUsername(userUsername).setFinancialInstitutionId(financialInstitutionId).setOfxUser(ofxUser).setOfxPassword(ofxPassword).setCcNumber(ccNumber).saveGet();
    }
    public CreditCardAccount saveGet() {this.save(); return this;}
    public static Finder<Long, CreditCardAccount> find = new Finder<Long, CreditCardAccount>(Long.class, CreditCardAccount.class);
}