package com.controle.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEmpresa is a Querydsl query type for Empresa
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmpresa extends EntityPathBase<Empresa> {

    private static final long serialVersionUID = 1690275808L;

    public static final QEmpresa empresa = new QEmpresa("empresa");

    public final BooleanPath ativo = createBoolean("ativo");

    public final StringPath celular = createString("celular");

    public final StringPath cep = createString("cep");

    public final StringPath cidade = createString("cidade");

    public final StringPath descricao = createString("descricao");

    public final StringPath dsbairro = createString("dsbairro");

    public final StringPath endereco = createString("endereco");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath telFixo = createString("telFixo");

    public QEmpresa(String variable) {
        super(Empresa.class, forVariable(variable));
    }

    public QEmpresa(Path<? extends Empresa> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmpresa(PathMetadata<?> metadata) {
        super(Empresa.class, metadata);
    }

}

