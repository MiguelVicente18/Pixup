package org.migueVA.Model;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString (callSuper = true)
@Entity
@Table(name ="TBL_COLONIA")

public class Colonia extends Catalogo
{
    @Column (name = "COLONIA", nullable = false)
    private String colonia;

    @Column (name = "CP", nullable = false)
    private String cp;

    @ManyToOne
    @JoinColumn (name = "TBL_MUNICIPIO_ID")
    private Municipio municipio;
}
