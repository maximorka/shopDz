package productShop.entity;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Data;

@Data
@Table(name = "action_product")
@Entity
public class Action {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column
    private int rule;

    @Column
    private float price;
}
