package org.opensource.climone.entities;

import org.hibernate.annotations.Index;
import org.opensource.climone.util.UIDGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class IdentificableEntity implements Serializable {

	private static final long serialVersionUID = 1330300089555997723L;

	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="ENT_GEN")
    @TableGenerator(name="ENT_GEN")
	protected Integer id;

	@Column(nullable = false, length = 128)
	protected String uid;

    @Basic(optional = false)
    @Index(name = "index_dateCreated")
    private Date dateCreated;

    @Basic(optional = false)
    @Index(name = "index_lastModified")
    private Date lastModified;

    @Version
    @Column(nullable = false)
    private int version = 0;

	public IdentificableEntity() {
		uid = UIDGenerator.generateUID();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof IdentificableEntity)) {
			return false;
		}
		IdentificableEntity other = (IdentificableEntity) obj;
		if (uid == null) {
			if (other.uid != null) {
				return false;
			}
		} else if (!uid.equals(other.uid)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Entity [id=" + id + ", uid=" + uid + "]";
	}
}
