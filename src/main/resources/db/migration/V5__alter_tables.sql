
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