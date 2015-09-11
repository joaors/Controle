package com.controle.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVale is a Querydsl query type for Vale
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVale extends EntityPathBase<Vale> {

    private static final long serialVersionUID = -82057875L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVale vale = new QVale("vale");

    public final DateTimePath<java.util.Date> data = createDateTime("data", java.util.Date.class);

    public final BooleanPath descontado = createBoolean("descontado");

    public final QFilial filial;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> valor = createNumber("valor", java.math.BigDecimal.class);

    public QVale(String variable) {
        this(Vale.class, forVariable(variable), INITS);
    }

    public QVale(Path<? extends Vale> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVale(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVale(PathMetadata<?> metadata, PathInits inits) {
        this(Vale.class, metadata, inits);
    }

    public QVale(Class<? extends Vale> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.filial = inits.isInitialized("filial") ? new QFilial(forProperty("filial")) : null;
    }

}

