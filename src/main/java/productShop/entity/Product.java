package productShop.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Data
@Entity
public class Product {
    @Id
    private String name;

    @Column
    private float price;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action")
    private Action action;
}
