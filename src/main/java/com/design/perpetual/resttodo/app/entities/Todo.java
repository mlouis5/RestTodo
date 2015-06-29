/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.resttodo.app.entities;

import com.design.perpetual.resttodo.app.Mergeable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author MacDerson
 */
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@Entity
@Table(name = "todo", catalog = "household", schema = "household")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Todo.findAll", query = "SELECT t FROM Todo t"),
    @NamedQuery(name = "Todo.findById", query = "SELECT t FROM Todo t WHERE t.id = :id"),
    @NamedQuery(name = "Todo.findByType", query = "SELECT t FROM Todo t WHERE t.type = :type"),
    @NamedQuery(name = "Todo.findByRecurrence", query = "SELECT t FROM Todo t WHERE t.recurrence = :recurrence"),
    @NamedQuery(name = "Todo.findByCreatedOn", query = "SELECT t FROM Todo t WHERE t.createdOn = :createdOn"),
    @NamedQuery(name = "Todo.findByValue", query = "SELECT t FROM Todo t WHERE t.value = :value"),
    @NamedQuery(name = "Todo.findByDueBy", query = "SELECT t FROM Todo t WHERE t.dueBy = :dueBy"),
    @NamedQuery(name = "Todo.findByIsComplete", query = "SELECT t FROM Todo t WHERE t.isComplete = :isComplete")})
public class Todo implements Serializable, Mergeable<Todo> {
    @Size(max = 128)
    @Column(name = "send_to", length = 128)
    private String sendTo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "priority", nullable = false, length = 4)
    private String priority;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "type", nullable = false, length = 4)
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "recurrence", nullable = false, length = 15)
    private String recurrence;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "value", nullable = false, length = 2147483647)
    private String value;
    @Column(name = "due_by")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueBy;
    @Column(name = "is_complete")
    private Boolean isComplete;
    @JoinColumn(name = "created_by", referencedColumnName = "email", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    private HouseholdMember createdBy;

    public Todo() {
    }

    public Todo(Integer id) {
        this.id = id;
    }

    public Todo(Integer id, String type, String recurrence, Date createdOn, String value) {
        this.id = id;
        this.type = type;
        this.recurrence = recurrence;
        this.createdOn = createdOn;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDueBy() {
        return dueBy;
    }

    public void setDueBy(Date dueBy) {
        this.dueBy = dueBy;
    }

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    public HouseholdMember getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(HouseholdMember createdBy) {
        this.createdBy = createdBy;
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
        if (!(object instanceof Todo)) {
            return false;
        }
        Todo other = (Todo) object;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "com.design.perpetual.resttodo.app.entities.Todo[ id=" + id + " ]";
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    @Override
    public boolean merge(Todo t) {
        boolean merged = false;
        if (Objects.nonNull(t)) {
            Field[] thisFields = getFields(this);
            Field[] paramFields = getFields(t);
            for (Field f : thisFields) {
                f.setAccessible(true);
                Column col = f.getAnnotation(Column.class);
                if (Objects.nonNull(col)) {
                    for(Field pf : paramFields){
                        pf.setAccessible(true);
                        if(f.getName().equals(pf.getName())){
                            try {
                                Object thisVal = f.get(this);
                                Object parVal = pf.get(t);
                                if(!Objects.equals(thisVal, parVal)){
                                    f.set(this, parVal);
                                    if(!merged){
                                        merged = !merged;
                                    }
                                }
                            } catch (IllegalArgumentException | IllegalAccessException ex) {
                                Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }
        return merged;
    }

    private Field[] getFields(Todo t) {
        Class<?> cls = t.getClass();
        Field[] fields = cls.getDeclaredFields();
        Field[] supFields = cls.getSuperclass().getDeclaredFields();
        return ArrayUtils.addAll(fields, supFields);       
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }
    
    public String[] modifiedValue(){
        switch(this.type){
            case "Todo":
            case "Note":{
                return new String[]{this.value};
            }
            case "List":{
                return this.value.split(",\\s*");
            }
            default: return new String[]{};
        }
    }

}
