package org.migueVA.Model;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString (callSuper = true)
@Entity
@Table(name ="TBL_MUNICIPIO")

public class Municipio extends Catalogo
{
    @Column (name = "MUNICIPIO", nullable = false)
    private String municipio;

    @ManyToOne
    @JoinColumn (name = "TBL_ESTADO_ID")
    private Estado estado;
}
