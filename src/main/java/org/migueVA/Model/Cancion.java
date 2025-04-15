package org.migueVA.Model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString (callSuper = true)
@Entity
@Table(name ="TBL_CANCION")

public class Cancion extends Catalogo
{
    @Column (name = "TITULOCANCION")
    private String tituloCancion;

    @Column (name = "DURACION")
    private LocalTime duracion;

    @ManyToOne
    @JoinColumn(name = "TBL_DISCO_ID")
    private Disco disco;

}
