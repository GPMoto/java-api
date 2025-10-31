CREATE TABLE t_gpMottu_token_push (
    id_token_push bigint identity,
    user_id bigint NOT NULL,
    token VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES t_gp_mottu_usuario(id_usuario),
    primary key (id_token_push)
);
