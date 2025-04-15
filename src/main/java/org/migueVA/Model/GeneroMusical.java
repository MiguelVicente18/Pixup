package org.migueVA.Model;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString (callSuper = true)
@Entity
@Table(name ="TBL_GENERO_MUSICAL")

public class GeneroMusical extends Catalogo
{
    @Column (name = "GENERO", nullable = false)
    private String genero;

}
