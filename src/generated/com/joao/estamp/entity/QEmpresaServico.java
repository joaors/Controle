package com.controle.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEmpresaServico is a Querydsl query type for EmpresaServico
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmpresaServico extends EntityPathBase<EmpresaServico> {

    private static final long serialVersionUID = 485831359L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmpresaServico empresaServico = new QEmpresaServico("empresaServico");

    public final QEmpresa empresa;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QServico servico;

    public final NumberPath<java.math.BigDecimal> valorUnitario = createNumber("valorUnitario", java.math.BigDecimal.class);

    public QEmpresaServico(String variable) {
        this(EmpresaServico.class, forVariable(variable), INITS);
    }

    public QEmpresaServico(Path<? extends EmpresaServico> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmpresaServico(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmpresaServico(PathMetadata<?> metadata, PathInits inits) {
        this(EmpresaServico.class, metadata, inits);
    }

    public QEmpresaServico(Class<? extends EmpresaServico> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.empresa = inits.isInitialized("empresa") ? new QEmpresa(forProperty("empresa")) : null;
        this.servico = inits.isInitialized("servico") ? new QServico(forProperty("servico")) : null;
    }

}

