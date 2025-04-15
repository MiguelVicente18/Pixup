package org.migueVA.Model;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString (callSuper = true)
@Entity
@Table(name ="TBL_ESTADO")

public class Estado extends Catalogo
{
    @Column (name = "ESTADO", nullable = false)
    private String estado;
}
