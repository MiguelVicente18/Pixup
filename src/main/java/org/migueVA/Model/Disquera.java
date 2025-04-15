package org.migueVA.Model;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString (callSuper = true)
@Entity
@Table(name ="TBL_DISQUERA")

public class Disquera extends Catalogo
{
    @Column (name = "DISQUERA", nullable = false)
    private String disquera;

}
