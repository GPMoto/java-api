

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
