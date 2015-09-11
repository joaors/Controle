package com.controle.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFilial is a Querydsl query type for Filial
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFilial extends EntityPathBase<Filial> {

    private static final long serialVersionUID = -1998877836L;

    public static final QFilial filial = new QFilial("filial");

    public final QEmpresa _super = new QEmpresa(this);

    //inherited
    public final BooleanPath ativo = _super.ativo;

    //inherited
    public final StringPath celular = _super.celular;

    //inherited
    public final StringPath cep = _super.cep;

    //inherited
    public final StringPath cidade = _super.cidade;

    public final StringPath cnpj = createString("cnpj");

    //inherited
    public final StringPath descricao = _super.descricao;

    //inherited
    public final StringPath dsbairro = _super.dsbairro;

    //inherited
    public final StringPath endereco = _super.endereco;

    //inherited
    public final NumberPath<Integer> id = _super.id;

    public final StringPath inscEstadual = createString("inscEstadual");

    public final StringPath nomeFantasia = createString("nomeFantasia");

    public final NumberPath<Integer> osfim = createNumber("osfim", Integer.class);

    public final NumberPath<Integer> osinicio = createNumber("osinicio", Integer.class);

    //inherited
    public final StringPath telFixo = _super.telFixo;

    public QFilial(String variable) {
        super(Filial.class, forVariable(variable));
    }

    public QFilial(Path<? extends Filial> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFilial(PathMetadata<?> metadata) {
        super(Filial.class, metadata);
    }

}

