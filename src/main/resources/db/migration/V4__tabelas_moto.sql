create table t_gp_mottu_moto (
    id_moto bigint identity not null,
    id_secao bigint,
    id_tipo_moto bigint,
    identificador varchar(50) not null,
    status varchar(50) not null,
    condicoes_manutencao varchar(200) not null,
    primary key (id_moto)
);

create table t_gp_mottu_qrcode (
    id_filial bigint,
    id_moto bigint,
    id_qrcode bigint identity not null,
    vl_qrcode varchar(200) not null,
    primary key (id_qrcode)
);

create table t_gp_mottu_tipo_moto (
    id_tipo_moto bigint identity not null,
    nm_tipo varchar(40) not null,
    primary key (id_tipo_moto)
);