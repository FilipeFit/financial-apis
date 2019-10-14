package com.financial.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Builder
@Table(name = "cidade")
public class Cidade {

    @EqualsAndHashCode.Include
    @Id
    private Long codigo;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "codigo_estado")
    private Estado estado;
}
