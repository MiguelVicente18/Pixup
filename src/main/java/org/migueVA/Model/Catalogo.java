package org.migueVA.Model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Catalogo implements Serializable{
    @Id
    @Column (name = "ID")
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    protected Integer id;
}
