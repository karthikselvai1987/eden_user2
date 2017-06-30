package com.egen.management.service.model;

import java.util.List;

@Entity
@Table(name = "User", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class User {
    
    public User(String id, String firstName, String middleName, String lastName, Short age, String gender, Long phoneNumber,
	    String zipCode, List<String> errorMessages) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.middleName = middleName;
	this.lastName = lastName;
	this.age = age;
	this.gender = gender;
	this.phoneNumber = phoneNumber;
	this.zipCode = zipCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;
    
    @Column(name= "firstName", nullable = false)
    @NotNull(message="NotNull.FirstName.description")
    private String firstName;
    
    @Column(name = "middleName", nullable = true)
    private String middleName;
    
    @Column(name = "lastName", nullable = false)
    @NotNull(message="NotNull.LastName.description")
    private String lastName;
    
    @Column(name = "age", nullable = false)
    @NotNull(message="NotNull.Age.description")
    @Min(value=1, message =" Minimum.Age.description")
    private Short age;
    
    @Column(name = "gender", nullable = false)
    @NotNull(message="NotNull.Gender.description")
    private String gender;
    
    @Column(name = "phoneNumber", nullable = false)
    @NotNull(message="NotNull.Phone.description")
    @Min(value=1111111111L, message = "Maximum.PhoneNumber.description")
    @Max(value=9999999999L, message = "Maximum.PhoneNumber.description")
    private Long phoneNumber;
    
    @Column(name = "zipCode", nullable = true)
    private String zipCode;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    
    @Override
    public int hashCode() {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	if (!(object instanceof User)) {
	    return false;
	}
	User user = (User) object;
	if ((this.id == null && user.id != null) || (this.id != null && !this.id.equals(user.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "org.egen.mangement.model.User[ id=" + id + " ]";
    }

}
