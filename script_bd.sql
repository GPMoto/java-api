USE master;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'unique_id_telefone')
ALTER TABLE t_gp_mottu_cidade DROP CONSTRAINT unique_id_telefone;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'unique_cep')
ALTER TABLE t_gp_mottu_contato DROP CONSTRAINT unique_cep;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'idx_filial_contato')
ALTER TABLE t_gp_mottu_endereco DROP CONSTRAINT idx_filial_contato;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'idx_filial_endereco')
ALTER TABLE t_gp_mottu_estado DROP CONSTRAINT idx_filial_endereco;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'unique_cnpj')
ALTER TABLE t_gp_mottu_filial DROP CONSTRAINT unique_cnpj;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'unique_identificador')
ALTER TABLE t_gp_mottu_filial DROP CONSTRAINT unique_identificador;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'idx_qrcode_moto')
ALTER TABLE t_gp_mottu_moto DROP CONSTRAINT idx_qrcode_moto;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'unique_email')
ALTER TABLE t_gp_mottu_moto DROP CONSTRAINT unique_email;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_cidade_estado')
ALTER TABLE t_gp_mottu_qrcode DROP CONSTRAINT fk_cidade_estado;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_contato_telefone')
ALTER TABLE t_gp_mottu_qrcode DROP CONSTRAINT fk_contato_telefone;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_endereco_cidade')
ALTER TABLE t_gp_mottu_secoes_filial DROP CONSTRAINT fk_endereco_cidade;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_estado_pais')
ALTER TABLE t_gp_mottu_secoes_filial DROP CONSTRAINT fk_estado_pais;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_filial_contato')
ALTER TABLE t_gp_mottu_usuario DROP CONSTRAINT fk_filial_contato;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_filial_endereco')
ALTER TABLE t_gp_mottu_usuario DROP CONSTRAINT fk_filial_endereco;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_moto_filial')
ALTER TABLE t_gp_mottu_usuario DROP CONSTRAINT fk_moto_filial;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_moto_tipo_moto')
ALTER TABLE t_gp_mottu_usuario DROP CONSTRAINT fk_moto_tipo_moto;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_qrcode_filial')
ALTER TABLE t_gp_mottu_usuario DROP CONSTRAINT fk_qrcode_filial;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_qrcode_moto')
ALTER TABLE t_gp_mottu_usuario DROP CONSTRAINT fk_qrcode_moto;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_secoes_filial_filial')
ALTER TABLE t_gp_mottu_usuario DROP CONSTRAINT fk_secoes_filial_filial;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_secoes_filial_tipo_secao')
ALTER TABLE t_gp_mottu_usuario DROP CONSTRAINT fk_secoes_filial_tipo_secao;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_usuario_filial')
ALTER TABLE t_gp_mottu_usuario DROP CONSTRAINT fk_usuario_filial;

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_usuario_perfil')
ALTER TABLE t_gp_mottu_usuario DROP CONSTRAINT fk_usuario_perfil;


-- Drop tables
IF OBJECT_ID('t_gp_mottu_cidade', 'U') IS NOT NULL DROP TABLE t_gp_mottu_cidade;
IF OBJECT_ID('t_gp_mottu_contato', 'U') IS NOT NULL DROP TABLE t_gp_mottu_contato;
IF OBJECT_ID('t_gp_mottu_endereco', 'U') IS NOT NULL DROP TABLE t_gp_mottu_endereco;
IF OBJECT_ID('t_gp_mottu_estado', 'U') IS NOT NULL DROP TABLE t_gp_mottu_estado;
IF OBJECT_ID('t_gp_mottu_filial', 'U') IS NOT NULL DROP TABLE t_gp_mottu_filial;
IF OBJECT_ID('t_gp_mottu_moto', 'U') IS NOT NULL DROP TABLE t_gp_mottu_moto;
IF OBJECT_ID('t_gp_mottu_pais', 'U') IS NOT NULL DROP TABLE t_gp_mottu_pais;
IF OBJECT_ID('t_gp_mottu_perfil', 'U') IS NOT NULL DROP TABLE t_gp_mottu_perfil;
IF OBJECT_ID('t_gp_mottu_qrcode', 'U') IS NOT NULL DROP TABLE t_gp_mottu_qrcode;
IF OBJECT_ID('t_gp_mottu_secoes_filial', 'U') IS NOT NULL DROP TABLE t_gp_mottu_secoes_filial;
IF OBJECT_ID('t_gp_mottu_telefone', 'U') IS NOT NULL DROP TABLE t_gp_mottu_telefone;
IF OBJECT_ID('t_gp_mottu_tipo_moto', 'U') IS NOT NULL DROP TABLE t_gp_mottu_tipo_moto;
IF OBJECT_ID('t_gp_mottu_tipo_secao', 'U') IS NOT NULL DROP TABLE t_gp_mottu_tipo_secao;
IF OBJECT_ID('t_gp_mottu_usuario', 'U') IS NOT NULL DROP TABLE t_gp_mottu_usuario;


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


alter table t_gp_mottu_contato
    add constraint unique_id_telefone unique (id_telefone);

alter table t_gp_mottu_endereco
    add constraint unique_cep unique (cep);

create unique nonclustered index idx_filial_contato
       on t_gp_mottu_filial (id_contato) where id_contato is not null;

    create unique nonclustered index idx_filial_endereco
       on t_gp_mottu_filial (id_endereco) where id_endereco is not null;

alter table t_gp_mottu_filial
    add constraint unique_cnpj unique (cnpj_filial);

alter table t_gp_mottu_moto
    add constraint unique_identificador unique (identificador);

create unique nonclustered index idx_qrcode_moto
       on t_gp_mottu_qrcode (id_moto) where id_moto is not null;

alter table t_gp_mottu_usuario
    add constraint unique_email unique (nm_email);

alter table t_gp_mottu_cidade
    add constraint fk_cidade_estado
        foreign key (id_estado)
            references t_gp_mottu_estado;

alter table t_gp_mottu_contato
    add constraint fk_contato_telefone
        foreign key (id_telefone)
            references t_gp_mottu_telefone;

alter table t_gp_mottu_endereco
    add constraint fk_endereco_cidade
        foreign key (id_cidade)
            references t_gp_mottu_cidade;

alter table t_gp_mottu_estado
    add constraint fk_estado_pais
        foreign key (id_pais)
            references t_gp_mottu_pais;

alter table t_gp_mottu_filial
    add constraint fk_filial_contato
        foreign key (id_contato)
            references t_gp_mottu_contato;

alter table t_gp_mottu_filial
    add constraint fk_filial_endereco
        foreign key (id_endereco)
            references t_gp_mottu_endereco;

alter table t_gp_mottu_moto
    add constraint fk_moto_filial
        foreign key (id_secao)
            references t_gp_mottu_secoes_filial;

alter table t_gp_mottu_moto
    add constraint fk_moto_tipo_moto
        foreign key (id_tipo_moto)
            references t_gp_mottu_tipo_moto;

alter table t_gp_mottu_qrcode
    add constraint fk_qrcode_filial
        foreign key (id_filial)
            references t_gp_mottu_filial;

alter table t_gp_mottu_qrcode
    add constraint fk_qrcode_moto
        foreign key (id_moto)
            references t_gp_mottu_moto;

alter table t_gp_mottu_secoes_filial
    add constraint fk_secoes_filial_filial
        foreign key (id_filial)
            references t_gp_mottu_filial;

alter table t_gp_mottu_secoes_filial
    add constraint fk_secoes_filial_tipo_secao
        foreign key (id_tipo_secao)
            references t_gp_mottu_tipo_secao;

alter table t_gp_mottu_usuario
    add constraint fk_usuario_filial
        foreign key (id_filial)
            references t_gp_mottu_filial;

alter table t_gp_mottu_usuario
    add constraint fk_usuario_perfil
        foreign key (id_perfil)
            references t_gp_mottu_perfil;

 INSERT INTO T_GP_MOTTU_PAIS (NM_PAIS) VALUES ('Brasil');
 INSERT INTO T_GP_MOTTU_PAIS (NM_PAIS) VALUES ('Argentina');
 INSERT INTO T_GP_MOTTU_PAIS (NM_PAIS) VALUES ('Uruguai');
 INSERT INTO T_GP_MOTTU_PAIS (NM_PAIS) VALUES ('Chile');
 INSERT INTO T_GP_MOTTU_PAIS (NM_PAIS) VALUES ('Paraguai');
 INSERT INTO T_GP_MOTTU_PAIS (NM_PAIS) VALUES ('Colômbia');
 INSERT INTO T_GP_MOTTU_PAIS (NM_PAIS) VALUES ('Peru');
 INSERT INTO T_GP_MOTTU_PAIS (NM_PAIS) VALUES ('México');
 INSERT INTO T_GP_MOTTU_PAIS (NM_PAIS) VALUES ('Espanha');
 INSERT INTO T_GP_MOTTU_PAIS (NM_PAIS) VALUES ('Portugal');
 INSERT INTO T_GP_MOTTU_TIPO_SECAO (NM_SECAO) VALUES ('Carga Leve');
 INSERT INTO T_GP_MOTTU_TIPO_SECAO (NM_SECAO) VALUES ('Carga Média');
 INSERT INTO T_GP_MOTTU_TIPO_SECAO (NM_SECAO) VALUES ('Carga Pesada');
 INSERT INTO T_GP_MOTTU_TIPO_SECAO (NM_SECAO) VALUES ('Produtos Refrigerados');
 INSERT INTO T_GP_MOTTU_TIPO_SECAO (NM_SECAO) VALUES ('Frágeis');
 INSERT INTO T_GP_MOTTU_TIPO_SECAO (NM_SECAO) VALUES ('Químicos');
 INSERT INTO T_GP_MOTTU_TIPO_SECAO (NM_SECAO) VALUES ('Documentos');
 INSERT INTO T_GP_MOTTU_TIPO_SECAO (NM_SECAO) VALUES ('Expressa');
 INSERT INTO T_GP_MOTTU_TIPO_SECAO (NM_SECAO) VALUES ('Longo Alcance');
 INSERT INTO T_GP_MOTTU_TIPO_SECAO (NM_SECAO) VALUES ('Zona de Risco');
 INSERT INTO T_GP_MOTTU_TIPO_MOTO (NM_TIPO) VALUES ('Scooter');
 INSERT INTO T_GP_MOTTU_TIPO_MOTO (NM_TIPO) VALUES ('Custom');
 INSERT INTO T_GP_MOTTU_TIPO_MOTO (NM_TIPO) VALUES ('Naked');
 INSERT INTO T_GP_MOTTU_TIPO_MOTO (NM_TIPO) VALUES ('Trail');
 INSERT INTO T_GP_MOTTU_TIPO_MOTO (NM_TIPO) VALUES ('Sport');
 INSERT INTO T_GP_MOTTU_TIPO_MOTO (NM_TIPO) VALUES ('Off-road');
 INSERT INTO T_GP_MOTTU_TIPO_MOTO (NM_TIPO) VALUES ('Touring');
 INSERT INTO T_GP_MOTTU_TIPO_MOTO (NM_TIPO) VALUES ('Street');
 INSERT INTO T_GP_MOTTU_TIPO_MOTO (NM_TIPO) VALUES ('Elétrica');
 INSERT INTO T_GP_MOTTU_TIPO_MOTO (NM_TIPO) VALUES ('Retrô');
 INSERT INTO T_GP_MOTTU_PERFIL (NM_PERFIL) VALUES ('ADMINISTRADOR');
 INSERT INTO T_GP_MOTTU_PERFIL (NM_PERFIL) VALUES ('SUPERVISOR');
 INSERT INTO T_GP_MOTTU_PERFIL (NM_PERFIL) VALUES ('OPERADOR');
 INSERT INTO T_GP_MOTTU_PERFIL (NM_PERFIL) VALUES ('MOTOBOY');
 INSERT INTO T_GP_MOTTU_PERFIL (NM_PERFIL) VALUES ('GERENTE');
 INSERT INTO T_GP_MOTTU_PERFIL (NM_PERFIL) VALUES ('RH');
 INSERT INTO T_GP_MOTTU_PERFIL (NM_PERFIL) VALUES ('TI');
 INSERT INTO T_GP_MOTTU_PERFIL (NM_PERFIL) VALUES ('FINANCEIRO');
 INSERT INTO T_GP_MOTTU_PERFIL (NM_PERFIL) VALUES ('COMERCIAL');
 INSERT INTO T_GP_MOTTU_PERFIL (NM_PERFIL) VALUES ('AUDITOR');
 INSERT INTO T_GP_MOTTU_TELEFONE (DDI, DDD, NUMERO) VALUES ('+55', '11', '912345678');
 INSERT INTO T_GP_MOTTU_TELEFONE (DDI, DDD, NUMERO) VALUES ('+55', '19', '998877665');
 INSERT INTO T_GP_MOTTU_TELEFONE (DDI, DDD, NUMERO) VALUES ('+55', '31', '987654321');
 INSERT INTO T_GP_MOTTU_TELEFONE (DDI, DDD, NUMERO) VALUES ('+55', '21', '976543210');
 INSERT INTO T_GP_MOTTU_TELEFONE (DDI, DDD, NUMERO) VALUES ('+55', '71', '965432109');
 INSERT INTO T_GP_MOTTU_TELEFONE (DDI, DDD, NUMERO) VALUES ('+55', '41', '954321098');
 INSERT INTO T_GP_MOTTU_TELEFONE (DDI, DDD, NUMERO) VALUES ('+55', '51', '943210987');
 INSERT INTO T_GP_MOTTU_TELEFONE (DDI, DDD, NUMERO) VALUES ('+55', '81', '932109876');
 INSERT INTO T_GP_MOTTU_TELEFONE (DDI, DDD, NUMERO) VALUES ('+55', '85', '921098765');
 INSERT INTO T_GP_MOTTU_TELEFONE (DDI, DDD, NUMERO) VALUES ('+55', '61', '910987654');
 INSERT INTO T_GP_MOTTU_ESTADO (NM_ESTADO, ID_PAIS) VALUES ('São Paulo', 1);
 INSERT INTO T_GP_MOTTU_ESTADO (NM_ESTADO, ID_PAIS) VALUES ('Minas Gerais', 1);
 INSERT INTO T_GP_MOTTU_ESTADO (NM_ESTADO, ID_PAIS) VALUES ('Rio de Janeiro', 1);
 INSERT INTO T_GP_MOTTU_ESTADO (NM_ESTADO, ID_PAIS) VALUES ('Bahia', 1);
 INSERT INTO T_GP_MOTTU_ESTADO (NM_ESTADO, ID_PAIS) VALUES ('Paraná', 1);
 INSERT INTO T_GP_MOTTU_ESTADO (NM_ESTADO, ID_PAIS) VALUES ('Santa Catarina', 1);
 INSERT INTO T_GP_MOTTU_ESTADO (NM_ESTADO, ID_PAIS) VALUES ('Rio Grande do Sul', 1);
 INSERT INTO T_GP_MOTTU_ESTADO (NM_ESTADO, ID_PAIS) VALUES ('Pernambuco', 1);
 INSERT INTO T_GP_MOTTU_ESTADO (NM_ESTADO, ID_PAIS) VALUES ('Ceará', 1);
 INSERT INTO T_GP_MOTTU_ESTADO (NM_ESTADO, ID_PAIS) VALUES ('Distrito Federal', 1);
 INSERT INTO T_GP_MOTTU_CIDADE (NM_CIDADE, ID_ESTADO) VALUES ('São Paulo', 1);
 INSERT INTO T_GP_MOTTU_CIDADE (NM_CIDADE, ID_ESTADO) VALUES ('Campinas', 1);
 INSERT INTO T_GP_MOTTU_CIDADE (NM_CIDADE, ID_ESTADO) VALUES ('Belo Horizonte', 2);
 INSERT INTO T_GP_MOTTU_CIDADE (NM_CIDADE, ID_ESTADO) VALUES ('Rio de Janeiro', 3);
 INSERT INTO T_GP_MOTTU_CIDADE (NM_CIDADE, ID_ESTADO) VALUES ('Salvador', 4);
 INSERT INTO T_GP_MOTTU_CIDADE (NM_CIDADE, ID_ESTADO) VALUES ('Curitiba', 5);
 INSERT INTO T_GP_MOTTU_CIDADE (NM_CIDADE, ID_ESTADO) VALUES ('Porto Alegre', 7);
 INSERT INTO T_GP_MOTTU_CIDADE (NM_CIDADE, ID_ESTADO) VALUES ('Recife', 8);
 INSERT INTO T_GP_MOTTU_CIDADE (NM_CIDADE, ID_ESTADO) VALUES ('Fortaleza', 9);
 INSERT INTO T_GP_MOTTU_CIDADE (NM_CIDADE, ID_ESTADO) VALUES ('Brasília', 10);
 INSERT INTO T_GP_MOTTU_ENDERECO (ID_CIDADE, CEP, NM_LOGRADOURO, NR_LOGRADOURO) VALUES (1, '01001000', 'Av. Paulista', 1000);
 INSERT INTO T_GP_MOTTU_ENDERECO (ID_CIDADE, CEP, NM_LOGRADOURO, NR_LOGRADOURO) VALUES (2, '13015000', 'Rua Barão de Jaguara', 123);
 INSERT INTO T_GP_MOTTU_ENDERECO (ID_CIDADE, CEP, NM_LOGRADOURO, NR_LOGRADOURO) VALUES (3, '30140071', 'Av. Afonso Pena', 500);
 INSERT INTO T_GP_MOTTU_ENDERECO (ID_CIDADE, CEP, NM_LOGRADOURO, NR_LOGRADOURO) VALUES (4, '20040002', 'Rua da Carioca', 200);
 INSERT INTO T_GP_MOTTU_ENDERECO (ID_CIDADE, CEP, NM_LOGRADOURO, NR_LOGRADOURO) VALUES (5, '40000000', 'Av. Sete de Setembro', 333);
 INSERT INTO T_GP_MOTTU_ENDERECO (ID_CIDADE, CEP, NM_LOGRADOURO, NR_LOGRADOURO) VALUES (6, '80010000', 'Rua XV de Novembro', 90);
 INSERT INTO T_GP_MOTTU_ENDERECO (ID_CIDADE, CEP, NM_LOGRADOURO, NR_LOGRADOURO) VALUES (7, '90010000', 'Av. Borges de Medeiros', 600);
 INSERT INTO T_GP_MOTTU_ENDERECO (ID_CIDADE, CEP, NM_LOGRADOURO, NR_LOGRADOURO) VALUES (8, '50000000', 'Av. Conde da Boa Vista', 720);
 INSERT INTO T_GP_MOTTU_ENDERECO (ID_CIDADE, CEP, NM_LOGRADOURO, NR_LOGRADOURO) VALUES (9, '60000000', 'Av. Beira Mar', 450);
 INSERT INTO T_GP_MOTTU_ENDERECO (ID_CIDADE, CEP, NM_LOGRADOURO, NR_LOGRADOURO) VALUES (10, '70000000', 'Esplanada dos Ministérios', 1);
 INSERT INTO T_GP_MOTTU_CONTATO (NM_DONO, STATUS, ID_TELEFONE) VALUES ('João Silva', 1, 1);
 INSERT INTO T_GP_MOTTU_CONTATO (NM_DONO, STATUS, ID_TELEFONE) VALUES ('Maria Souza', 1, 2);
 INSERT INTO T_GP_MOTTU_CONTATO (NM_DONO, STATUS, ID_TELEFONE) VALUES ('Carlos Lima', 1, 3);
 INSERT INTO T_GP_MOTTU_CONTATO (NM_DONO, STATUS, ID_TELEFONE) VALUES ('Ana Paula', 1, 4);
 INSERT INTO T_GP_MOTTU_CONTATO (NM_DONO, STATUS, ID_TELEFONE) VALUES ('Pedro Rocha', 1, 5);
 INSERT INTO T_GP_MOTTU_CONTATO (NM_DONO, STATUS, ID_TELEFONE) VALUES ('Juliana Alves', 1, 6);
 INSERT INTO T_GP_MOTTU_CONTATO (NM_DONO, STATUS, ID_TELEFONE) VALUES ('Rafael Costa', 1, 7);
 INSERT INTO T_GP_MOTTU_CONTATO (NM_DONO, STATUS, ID_TELEFONE) VALUES ('Fernanda Dias', 1, 8);
 INSERT INTO T_GP_MOTTU_CONTATO (NM_DONO, STATUS, ID_TELEFONE) VALUES ('Lucas Martins', 1, 9);
 INSERT INTO T_GP_MOTTU_CONTATO (NM_DONO, STATUS, ID_TELEFONE) VALUES ('Patrícia Melo', 1, 10);
 INSERT INTO T_GP_MOTTU_FILIAL (CNPJ_FILIAL, SENHA_FILIAL, ID_ENDERECO, ID_CONTATO) VALUES ('12345678000100', 'senha123', 1, 1);
 INSERT INTO T_GP_MOTTU_FILIAL (CNPJ_FILIAL, SENHA_FILIAL, ID_ENDERECO, ID_CONTATO) VALUES ('12345678000200', 'senha456', 2, 2);
 INSERT INTO T_GP_MOTTU_FILIAL (CNPJ_FILIAL, SENHA_FILIAL, ID_ENDERECO, ID_CONTATO) VALUES ('12345678000300', 'senha789', 3, 3);
 INSERT INTO T_GP_MOTTU_FILIAL (CNPJ_FILIAL, SENHA_FILIAL, ID_ENDERECO, ID_CONTATO) VALUES ('12345678000400', 'senhaabc', 4, 4);
 INSERT INTO T_GP_MOTTU_FILIAL (CNPJ_FILIAL, SENHA_FILIAL, ID_ENDERECO, ID_CONTATO) VALUES ('12345678000500', 'senhadef', 5, 5);
 INSERT INTO T_GP_MOTTU_FILIAL (CNPJ_FILIAL, SENHA_FILIAL, ID_ENDERECO, ID_CONTATO) VALUES ('12345678000600', 'senhaghi', 6, 6);
 INSERT INTO T_GP_MOTTU_FILIAL (CNPJ_FILIAL, SENHA_FILIAL, ID_ENDERECO, ID_CONTATO) VALUES ('12345678000700', 'senhajkl', 7, 7);
 INSERT INTO T_GP_MOTTU_FILIAL (CNPJ_FILIAL, SENHA_FILIAL, ID_ENDERECO, ID_CONTATO) VALUES ('12345678000800', 'senhamno', 8, 8);
 INSERT INTO T_GP_MOTTU_FILIAL (CNPJ_FILIAL, SENHA_FILIAL, ID_ENDERECO, ID_CONTATO) VALUES ('12345678000900', 'senhapqr', 9, 9);
 INSERT INTO T_GP_MOTTU_FILIAL (CNPJ_FILIAL, SENHA_FILIAL, ID_ENDERECO, ID_CONTATO) VALUES ('12345678001000', 'senhastu', 10, 10);
 INSERT INTO T_GP_MOTTU_SECOES_FILIAL (LADO1, LADO2, LADO3, LADO4, ID_TIPO_SECAO, ID_FILIAL) VALUES (2.0, 2.0, 2.0, 2.0, 1, 1);
 INSERT INTO T_GP_MOTTU_SECOES_FILIAL (LADO1, LADO2, LADO3, LADO4, ID_TIPO_SECAO, ID_FILIAL) VALUES (3.0, 3.0, 3.0, 3.0, 2, 2);
 INSERT INTO T_GP_MOTTU_SECOES_FILIAL (LADO1, LADO2, LADO3, LADO4, ID_TIPO_SECAO, ID_FILIAL) VALUES (4.0, 4.0, 4.0, 4.0, 3, 3);
 INSERT INTO T_GP_MOTTU_SECOES_FILIAL (LADO1, LADO2, LADO3, LADO4, ID_TIPO_SECAO, ID_FILIAL) VALUES (5.0, 5.0, 5.0, 5.0, 4, 4);
 INSERT INTO T_GP_MOTTU_SECOES_FILIAL (LADO1, LADO2, LADO3, LADO4, ID_TIPO_SECAO, ID_FILIAL) VALUES (6.0, 6.0, 6.0, 6.0, 5, 5);
 INSERT INTO T_GP_MOTTU_SECOES_FILIAL (LADO1, LADO2, LADO3, LADO4, ID_TIPO_SECAO, ID_FILIAL) VALUES (7.0, 7.0, 7.0, 7.0, 6, 6);
 INSERT INTO T_GP_MOTTU_SECOES_FILIAL (LADO1, LADO2, LADO3, LADO4, ID_TIPO_SECAO, ID_FILIAL) VALUES (8.0, 8.0, 8.0, 8.0, 7, 7);
 INSERT INTO T_GP_MOTTU_SECOES_FILIAL (LADO1, LADO2, LADO3, LADO4, ID_TIPO_SECAO, ID_FILIAL) VALUES (9.0, 9.0, 9.0, 9.0, 8, 8);
 INSERT INTO T_GP_MOTTU_SECOES_FILIAL (LADO1, LADO2, LADO3, LADO4, ID_TIPO_SECAO, ID_FILIAL) VALUES (10.0, 10.0, 10.0, 10.0, 9, 9);
 INSERT INTO T_GP_MOTTU_SECOES_FILIAL (LADO1, LADO2, LADO3, LADO4, ID_TIPO_SECAO, ID_FILIAL) VALUES (11.0, 11.0, 11.0, 11.0, 10, 10);
 INSERT INTO T_GP_MOTTU_MOTO (STATUS, CONDICOES_MANUTENCAO, IDENTIFICADOR, ID_TIPO_MOTO, ID_SECAO) VALUES ('Revisada', 'Revisada', 'ABC1234', 1, 1);
 INSERT INTO T_GP_MOTTU_MOTO (STATUS, CONDICOES_MANUTENCAO, IDENTIFICADOR, ID_TIPO_MOTO, ID_SECAO) VALUES ('Troca de óleo', 'Troca de óleo', 'XYZ5678', 2, 1);
 INSERT INTO T_GP_MOTTU_MOTO (STATUS, CONDICOES_MANUTENCAO, IDENTIFICADOR, ID_TIPO_MOTO, ID_SECAO) VALUES ('Completa', 'Completa', 'DEF9012', 3, 3);
 INSERT INTO T_GP_MOTTU_MOTO (STATUS, CONDICOES_MANUTENCAO, IDENTIFICADOR, ID_TIPO_MOTO, ID_SECAO) VALUES ('Freios novos', 'Freios novos', 'GHI3456', 4, 4);
 INSERT INTO T_GP_MOTTU_MOTO (STATUS, CONDICOES_MANUTENCAO, IDENTIFICADOR, ID_TIPO_MOTO, ID_SECAO) VALUES ('Sem manutenção', 'Sem manutenção', 'JKL7890', 5, 5);
 INSERT INTO T_GP_MOTTU_MOTO (STATUS, CONDICOES_MANUTENCAO, IDENTIFICADOR, ID_TIPO_MOTO, ID_SECAO) VALUES ('Pneus novos', 'Pneus novos', 'MNO2345', 6, 6);
 INSERT INTO T_GP_MOTTU_MOTO (STATUS, CONDICOES_MANUTENCAO, IDENTIFICADOR, ID_TIPO_MOTO, ID_SECAO) VALUES ('Revisada', 'Revisada', 'PQR6789', 7, 7);
 INSERT INTO T_GP_MOTTU_MOTO (STATUS, CONDICOES_MANUTENCAO, IDENTIFICADOR, ID_TIPO_MOTO, ID_SECAO) VALUES ('Motor retificado', 'Motor retificado', 'STU1234', 8, 8);
 INSERT INTO T_GP_MOTTU_MOTO (STATUS, CONDICOES_MANUTENCAO, IDENTIFICADOR, ID_TIPO_MOTO, ID_SECAO) VALUES ('Completa', 'Completa', 'VWX5678', 9, 9);
 INSERT INTO T_GP_MOTTU_MOTO (STATUS, CONDICOES_MANUTENCAO, IDENTIFICADOR, ID_TIPO_MOTO, ID_SECAO) VALUES ('Pronta entrega', 'Pronta entrega', 'YZA9012', 10, 10);
 INSERT INTO T_GP_MOTTU_USUARIO (NM_USUARIO, NM_EMAIL, SENHA, ID_FILIAL, ID_PERFIL) VALUES ('Admin SP', 'admin.sp@email.com', '$2a$12$KJGTTLkR3a8ZysrmpDc6Mu6k.Kr4E3O6BwMBovRygeoqd40lbsFhe', 1, 1);
 INSERT INTO T_GP_MOTTU_USUARIO (NM_USUARIO, NM_EMAIL, SENHA, ID_FILIAL, ID_PERFIL) VALUES ('Admin Campinas', 'admin.campinas@email.com', '$2a$10$wQw2w2wQw2w2wQw2w2wQwOeQw2w2wQw2w2wQw2w2wQw2w2wQw2w2', 2, 2);
 INSERT INTO T_GP_MOTTU_USUARIO (NM_USUARIO, NM_EMAIL, SENHA, ID_FILIAL, ID_PERFIL) VALUES ('Admin BH', 'admin.bh@email.com', '$2a$10$wQw2w2wQw2w2wQw2w2wQwOeQw2w2wQw2w2wQw2w2wQw2w2wQw2w2', 3, 3);
 INSERT INTO T_GP_MOTTU_USUARIO (NM_USUARIO, NM_EMAIL, SENHA, ID_FILIAL, ID_PERFIL) VALUES ('Admin RJ', 'admin.rj@email.com', '$2a$10$wQw2w2wQw2w2wQw2w2wQwOeQw2w2wQw2w2wQw2w2wQw2w2wQw2w2', 4, 4);
 INSERT INTO T_GP_MOTTU_USUARIO (NM_USUARIO, NM_EMAIL, SENHA, ID_FILIAL, ID_PERFIL) VALUES ('Admin BA', 'admin.ba@email.com', '$2a$10$wQw2w2wQw2w2wQw2w2wQwOeQw2w2wQw2w2wQw2w2wQw2w2wQw2w2', 5, 5);
 INSERT INTO T_GP_MOTTU_USUARIO (NM_USUARIO, NM_EMAIL, SENHA, ID_FILIAL, ID_PERFIL) VALUES ('Admin PR', 'admin.pr@email.com', '$2a$10$wQw2w2wQw2w2wQw2w2wQwOeQw2w2wQw2w2wQw2w2wQw2w2wQw2w2', 6, 6);
 INSERT INTO T_GP_MOTTU_USUARIO (NM_USUARIO, NM_EMAIL, SENHA, ID_FILIAL, ID_PERFIL) VALUES ('Admin RS', 'admin.rs@email.com', '$2a$10$wQw2w2wQw2w2wQw2w2wQwOeQw2w2wQw2w2wQw2w2wQw2w2wQw2w2', 7, 7);
 INSERT INTO T_GP_MOTTU_USUARIO (NM_USUARIO, NM_EMAIL, SENHA, ID_FILIAL, ID_PERFIL) VALUES ('Admin PE', 'admin.pe@email.com', '$2a$10$wQw2w2wQw2w2wQw2w2wQwOeQw2w2wQw2w2wQw2w2wQw2w2wQw2w2', 8, 8);
 INSERT INTO T_GP_MOTTU_USUARIO (NM_USUARIO, NM_EMAIL, SENHA, ID_FILIAL, ID_PERFIL) VALUES ('Admin CE', 'admin.ce@email.com', '$2a$10$wQw2w2wQw2w2wQw2w2wQwOeQw2w2wQw2w2wQw2w2wQw2w2wQw2w2', 9, 9);
 INSERT INTO T_GP_MOTTU_USUARIO (NM_USUARIO, NM_EMAIL, SENHA, ID_FILIAL, ID_PERFIL) VALUES ('Admin DF', 'admin.df@email.com', '$2a$10$wQw2w2wQw2w2wQw2w2wQwOeQw2w2wQw2w2wQw2w2wQw2w2wQw2w2', 10, 10);
 INSERT INTO T_GP_MOTTU_QRCODE (VL_QRCODE, ID_MOTO, ID_FILIAL) VALUES ('QR-MOTO-001', 1, 1);
 INSERT INTO T_GP_MOTTU_QRCODE (VL_QRCODE, ID_MOTO, ID_FILIAL) VALUES ('QR-MOTO-002', 2, 1);
 INSERT INTO T_GP_MOTTU_QRCODE (VL_QRCODE, ID_MOTO, ID_FILIAL) VALUES ('QR-MOTO-003', 3, 3);
 INSERT INTO T_GP_MOTTU_QRCODE (VL_QRCODE, ID_MOTO, ID_FILIAL) VALUES ('QR-MOTO-004', 4, 4);
 INSERT INTO T_GP_MOTTU_QRCODE (VL_QRCODE, ID_MOTO, ID_FILIAL) VALUES ('QR-MOTO-005', 5, 5);
 INSERT INTO T_GP_MOTTU_QRCODE (VL_QRCODE, ID_MOTO, ID_FILIAL) VALUES ('QR-MOTO-006', 6, 6);
 INSERT INTO T_GP_MOTTU_QRCODE (VL_QRCODE, ID_MOTO, ID_FILIAL) VALUES ('QR-MOTO-007', 7, 7);
 INSERT INTO T_GP_MOTTU_QRCODE (VL_QRCODE, ID_MOTO, ID_FILIAL) VALUES ('QR-MOTO-008', 8, 8);
 INSERT INTO T_GP_MOTTU_QRCODE (VL_QRCODE, ID_MOTO, ID_FILIAL) VALUES ('QR-MOTO-009', 9, 9);
 INSERT INTO T_GP_MOTTU_QRCODE (VL_QRCODE, ID_MOTO, ID_FILIAL) VALUES ('QR-MOTO-010', 10, 10);