package de.schulte.smartbar.backoffice.tables;

import de.schulte.smartbar.backoffice.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@jakarta.persistence.Table(name = "sbo_table", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "name" })
})
public class Table extends BaseEntity {

    @NotNull
    private String name;
    @NotNull
    private Integer seatCount;
    @NotNull
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
