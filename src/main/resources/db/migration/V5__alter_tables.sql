
alter table if exists t_gp_mottu_cidade
    add constraint FKn0c2rm0wlm1x83uo8k91pfeiy
    foreign key (id_estado)
    references t_gp_mottu_estado;

alter table if exists t_gp_mottu_contato
    add constraint FK6ln1g62h95fr1mer3182iyu7s
    foreign key (id_telefone)
    references t_gp_mottu_telefone;

alter table if exists t_gp_mottu_endereco
    add constraint FKd0k457tjrbsgfjbv0rguqvvlh
    foreign key (id_cidade)
    references t_gp_mottu_cidade;

alter table if exists t_gp_mottu_estado
    add constraint FK318583hta83ah6tw6jwbi29xl
    foreign key (id_pais)
    references t_gp_mottu_pais;

alter table if exists t_gp_mottu_filial
    add constraint FKirj4dfxk02k7y73eobui58gk6
    foreign key (id_contato)
    references t_gp_mottu_contato;

alter table if exists t_gp_mottu_filial
    add constraint FK6ggv5pafxnkja30914uy9msuk
    foreign key (id_endereco)
    references t_gp_mottu_endereco;

alter table if exists t_gp_mottu_moto
    add constraint FKfj91oxndb5ja9r8r3h9rsq3uj
    foreign key (id_secao)
    references t_gp_mottu_secoes_filial;

alter table if exists t_gp_mottu_moto
    add constraint FKgetdjg42kkbfmo0fiygbenbo7
    foreign key (id_tipo_moto)
    references t_gp_mottu_tipo_moto;

alter table if exists t_gp_mottu_qrcode
    add constraint FKg7ih1f1hpbh0842gn9byeou2l
    foreign key (id_filial)
    references t_gp_mottu_filial;

alter table if exists t_gp_mottu_qrcode
    add constraint FKfnfd3weesmrb893ro7ws4v8l5
    foreign key (id_moto)
    references t_gp_mottu_moto;

alter table if exists t_gp_mottu_secoes_filial
    add constraint FKbaac61mb4bwwxnsefictwv1qa
    foreign key (id_filial)
    references t_gp_mottu_filial;

alter table if exists t_gp_mottu_secoes_filial
    add constraint FKm97wo6c4hg9nffy25yg3kaaid
    foreign key (id_tipo_secao)
    references t_gp_mottu_tipo_secao;

alter table if exists t_gp_mottu_usuario
    add constraint FKp6b9tjnjgbipjatqey31wxjs6
    foreign key (id_filial)
    references t_gp_mottu_filial;

alter table if exists t_gp_mottu_usuario
    add constraint FK5tksjicfcljksrneuvisppeev
    foreign key (id_perfil)
    references t_gp_mottu_perfil;