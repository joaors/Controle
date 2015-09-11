package com.controle.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTeste is a Querydsl query type for Teste
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTeste extends EntityPathBase<Teste> {

    private static final long serialVersionUID = 1749452586L;

    public static final QTeste teste = new QTeste("teste");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QTeste(String variable) {
        super(Teste.class, forVariable(variable));
    }

    public QTeste(Path<? extends Teste> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeste(PathMetadata<?> metadata) {
        super(Teste.class, metadata);
    }

}

