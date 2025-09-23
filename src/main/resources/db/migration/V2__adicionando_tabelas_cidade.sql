
create table t_gp_mottu_pais (
    id_pais bigint identity not null,
    nm_pais varchar(100) not null,
    primary key (id_pais)
);

create table t_gp_mottu_estado (
                                   id_estado bigint identity not null,
                                   id_pais bigint,
                                   nm_estado varchar(100) not null,
                                   primary key (id_estado)
);


create table t_gp_mottu_cidade (
                                   id_cidade bigint identity not null,
                                   id_estado bigint,
                                   nm_cidade varchar(200) not null,
                                   primary key (id_cidade)
);

create table t_gp_mottu_endereco (
                                     nr_logradouro int not null check (nr_logradouro<=99999),
                                     cep varchar(8) not null,
                                     id_cidade bigint,
                                     id_endereco bigint identity not null,
                                     nm_logradouro varchar(200) not null,
                                     primary key (id_endereco)
);