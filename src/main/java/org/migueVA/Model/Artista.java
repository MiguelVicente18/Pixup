package org.migueVA.Model;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString (callSuper = true)
@Entity
@Table(name ="TBL_ARTISTA")

public class Artista extends Catalogo
{
    @Column (name = "ARTISTA", nullable = false)
    private String artista;

}
