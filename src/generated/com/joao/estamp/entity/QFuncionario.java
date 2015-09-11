package com.controle.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFuncionario is a Querydsl query type for Funcionario
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFuncionario extends EntityPathBase<Funcionario> {

    private static final long serialVersionUID = -1630213710L;

    public static final QFuncionario funcionario = new QFuncionario("funcionario");

    public final QEmpresa _super = new QEmpresa(this);

    //inherited
    public final BooleanPath ativo = _super.ativo;

    //inherited
    public final StringPath celular = _super.celular;

    //inherited
    public final StringPath cep = _super.cep;

    //inherited
    public final StringPath cidade = _super.cidade;

    //inherited
    public final StringPath descricao = _super.descricao;

    //inherited
    public final StringPath dsbairro = _super.dsbairro;

    //inherited
    public final StringPath endereco = _super.endereco;

    //inherited
    public final NumberPath<Integer> id = _super.id;

    //inherited
    public final StringPath telFixo = _super.telFixo;

    public QFuncionario(String variable) {
        super(Funcionario.class, forVariable(variable));
    }

    public QFuncionario(Path<? extends Funcionario> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFuncionario(PathMetadata<?> metadata) {
        super(Funcionario.class, metadata);
    }

}

