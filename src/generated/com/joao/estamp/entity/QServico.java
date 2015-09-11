package com.controle.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QServico is a Querydsl query type for Servico
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QServico extends EntityPathBase<Servico> {

    private static final long serialVersionUID = 1003361814L;

    public static final QServico servico = new QServico("servico");

    public final StringPath descricao = createString("descricao");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> valor = createNumber("valor", java.math.BigDecimal.class);

    public QServico(String variable) {
        super(Servico.class, forVariable(variable));
    }

    public QServico(Path<? extends Servico> path) {
        super(path.getType(), path.getMetadata());
    }

    public QServico(PathMetadata<?> metadata) {
        super(Servico.class, metadata);
    }

}

