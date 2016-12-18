/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment_system;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alexey
 */
@Entity
//@XmlRootElement
@Table(name = "OPERATIONS")
@NamedQueries({
    @NamedQuery(name = "Operations.findAll", query = "SELECT o FROM Operations o"),
    @NamedQuery(name = "Operations.findById", query = "SELECT o FROM Operations o WHERE o.id = :id"),
    @NamedQuery(name = "Operations.findByDatetime", query = "SELECT o FROM Operations o WHERE o.datetime = :datetime"),
    @NamedQuery(name = "Operations.findByCount", query = "SELECT o FROM Operations o WHERE o.count = :count"),
    @NamedQuery(name = "Operations.findByIntention", query = "SELECT o FROM Operations o WHERE o.intention = :intention"),
    @NamedQuery(name = "Operations.history", query = "SELECT o FROM Operations o WHERE o.receiver = :user OR o.seller = :user")})
public class Operations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COUNT")
    private float count;
    @Size(max = 100)
    @Column(name = "INTENTION")
    private String intention;
    @JoinColumn(name = "RECEIVER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Accounts receiver;
    @JoinColumn(name = "SELLER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Accounts seller;
    
    public Operations() {
    }

    public Operations(Integer id) {
        this.id = id;
    }

    public Operations(Integer id, Date datetime, float count) {
        this.id = id;
        this.datetime = datetime;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }

    public Accounts getReceiver() {
        return receiver;
    }

    public void setReceiver(Accounts receiver) {
        this.receiver = receiver;
    }

    public Accounts getSeller() {
        return seller;
    }

    public void setSeller(Accounts seller) {
        this.seller = seller;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operations)) {
            return false;
        }
        Operations other = (Operations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "payment_system.Operations[ id=" + id + " ]";
    }
}
