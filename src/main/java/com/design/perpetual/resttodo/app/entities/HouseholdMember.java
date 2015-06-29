/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.resttodo.app.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MacDerson
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@Entity
@Table(name = "household_member", catalog = "household", schema = "household")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HouseholdMember.findAll", query = "SELECT h FROM HouseholdMember h"),
    @NamedQuery(name = "HouseholdMember.findByEmail", query = "SELECT h FROM HouseholdMember h WHERE h.email = :email"),
    @NamedQuery(name = "HouseholdMember.findByFname", query = "SELECT h FROM HouseholdMember h WHERE h.fname = :fname"),
    @NamedQuery(name = "HouseholdMember.findByLname", query = "SELECT h FROM HouseholdMember h WHERE h.lname = :lname")})
public class HouseholdMember implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "pin", nullable = false, length = 32)
    private String pin;
    private static final long serialVersionUID = 1L;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "email", nullable = false, length = 128)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 77)
    @Column(name = "fname", nullable = false, length = 77)
    private String fname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 77)
    @Column(name = "lname", nullable = false, length = 77)
    private String lname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy", fetch = FetchType.EAGER)
    private List<Todo> todoList;

    public HouseholdMember() {
    }

    public HouseholdMember(String email) {
        this.email = email;
    }

    public HouseholdMember(String email, String fname, String lname) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @XmlTransient
    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HouseholdMember)) {
            return false;
        }
        HouseholdMember other = (HouseholdMember) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.design.perpetual.resttodo.app.entities.HouseholdMember[ email=" + email + " ]";
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    
}
