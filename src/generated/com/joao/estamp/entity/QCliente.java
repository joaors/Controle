package com.controle.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCliente is a Querydsl query type for Cliente
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCliente extends EntityPathBase<Cliente> {

    private static final long serialVersionUID = -120203951L;

    public static final QCliente cliente = new QCliente("cliente");

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

    public final ListPath<EmpresaServico, QEmpresaServico> listPecaDivisaoEstoqueOriginal = this.<EmpresaServico, QEmpresaServico>createList("listPecaDivisaoEstoqueOriginal", EmpresaServico.class, QEmpresaServico.class, PathInits.DIRECT2);

    public final StringPath nomeFantasia = createString("nomeFantasia");

    public final NumberPath<java.math.BigDecimal> saldo = createNumber("saldo", java.math.BigDecimal.class);

    //inherited
    public final StringPath telFixo = _super.telFixo;

    public QCliente(String variable) {
        super(Cliente.class, forVariable(variable));
    }

    public QCliente(Path<? extends Cliente> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCliente(PathMetadata<?> metadata) {
        super(Cliente.class, metadata);
    }

}

