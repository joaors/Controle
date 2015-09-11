package com.controle.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFornecedor is a Querydsl query type for Fornecedor
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFornecedor extends EntityPathBase<Fornecedor> {

    private static final long serialVersionUID = 1214611118L;

    public static final QFornecedor fornecedor = new QFornecedor("fornecedor");

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

    public final NumberPath<java.math.BigDecimal> saldo = createNumber("saldo", java.math.BigDecimal.class);

    //inherited
    public final StringPath telFixo = _super.telFixo;

    public QFornecedor(String variable) {
        super(Fornecedor.class, forVariable(variable));
    }

    public QFornecedor(Path<? extends Fornecedor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFornecedor(PathMetadata<?> metadata) {
        super(Fornecedor.class, metadata);
    }

}

