create table t_gp_mottu_contato (
                                    status int not null check (status<=1),
                                    id_contato bigint identity not null,
                                    id_telefone bigint not null,
    nm_dono varchar(200) not null,
    primary key (id_contato)
);

create table t_gp_mottu_filial (
    id_contato bigint,
    id_endereco bigint,
    id_filial bigint identity not null,
    cnpj_filial varchar(14) not null,
    senha_filial varchar(200) not null,
    primary key (id_filial)
);

create table t_gp_mottu_perfil (
    id_perfil bigint identity not null,
    nm_perfil varchar(255) not null check (nm_perfil in ('ADMINISTRADOR','SUPERVISOR','OPERADOR','MOTOBOY','GERENTE','RH','TI','FINANCEIRO','COMERCIAL','AUDITOR')),
    primary key (id_perfil)
);

create table t_gp_mottu_secoes_filial (
    lado1 float(53) not null,
    lado2 float(53) not null,
    lado3 float(53) not null,
    lado4 float(53) not null,
    id_filial bigint,
    id_secao bigint identity not null,
    id_tipo_secao bigint,
    primary key (id_secao)
);

create table t_gp_mottu_usuario (
    id_filial bigint,
    id_perfil bigint,
    id_usuario bigint identity not null,
    nm_email varchar(200) not null,
    nm_usuario varchar(200) not null,
    senha varchar(200) not null,
    primary key (id_usuario)
);

create table t_gp_mottu_telefone (
    ddd varchar(3) not null,
    ddi varchar(3) not null,
    id_telefone bigint identity not null,
    numero varchar(10) not null,
    primary key (id_telefone)
);

create table t_gp_mottu_tipo_secao (
    id_tipo_secao bigint identity not null,
    nm_secao varchar(200) not null,
    primary key (id_tipo_secao)
);