package pckg.su.domains;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Admin on 31.03.2019.
 */
@Data
@Entity
@Table(name = "unit_of_measure")
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return uom;
    }

    public void setDescription(String description) {
        this.uom = description;
    }
}
