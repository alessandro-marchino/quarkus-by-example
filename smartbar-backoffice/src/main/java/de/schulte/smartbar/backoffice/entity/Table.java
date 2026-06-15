package de.schulte.smartbar.backoffice.entity;

import jakarta.persistence.Entity;

@Entity
@jakarta.persistence.Table(name = "sbo_table")
public class Table extends BaseEntity {

    private String name;
    private Integer seatCount;
    private Boolean active;
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the seatCount
     */
    public Integer getSeatCount() {
        return seatCount;
    }
    /**
     * @param seatCount the seatCount to set
     */
    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }
    /**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }
    /**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

}
