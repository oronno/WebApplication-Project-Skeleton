package com.oneous.webapp.persistance.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Abdullah Al Mamun Oronno (www.oneous.com)
 */

@MappedSuperclass
public abstract class Persistent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(updatable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updated;

    @Version
    private int version;

    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date d) {
        created = d;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date d) {
        updated = d;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persistent that = (Persistent) o;

        if (version != that.version) return false;
        if (created != null ? !created.equals(that.getCreated()) : that.getCreated() != null) return false;
        if (id != null ? !id.equals(that.id) : that.getId() != null) return false;
        if (updated != null ? !updated.equals(that.getUpdated()) : that.getUpdated() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        result = 31 * result + version;
        return result;
    }
}
