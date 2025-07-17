
package hibernate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "line_1",nullable = false)
    private String line1;
    
    @Column(name = "line_2",nullable = false)
    private String line2;
    
    @Column(name = "postal_code",nullable = false)
    private String postalCode;
    
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address() {
    }

    
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    
    public String getLine1() {
        return line1;
    }

    
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    
    public String getLine2() {
        return line2;
    }

    
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    
    public City getCity() {
        return city;
    }

    
    public void setCity(City city) {
        this.city = city;
    }

    
    public String getPostalCode() {
        return postalCode;
    }

    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    
    public User getUser() {
        return user;
    }

    
    public void setUser(User user) {
        this.user = user;
    }
}
