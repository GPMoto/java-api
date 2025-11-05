-- 1. Verificar se a coluna não existe antes de adicionar
IF NOT EXISTS (SELECT * FROM sys.columns
               WHERE object_id = OBJECT_ID(N't_gp_mottu_usuario')
               AND name = 'idiom')
BEGIN
    ALTER TABLE t_gp_mottu_usuario
        ADD idiom VARCHAR(10) NULL;
END
GO

-- 2. Popular valores padrão para registros existentes
UPDATE t_gp_mottu_usuario
SET idiom = 'PTBR'
WHERE idiom IS NULL OR idiom = '';
GO

-- -- 3. Adicionar constraint de validação (antes de tornar NOT NULL)
-- ALTER TABLE t_gp_mottu_usuario
-- ADD CONSTRAINT ck_idiom
--     CHECK (idiom IN ('PTBR', 'ES', 'EN'));
-- GO

-- -- 4. Tornar a coluna obrigatória (NOT NULL)
-- ALTER TABLE t_gp_mottu_usuario
-- ALTER COLUMN idiom VARCHAR(10) NOT NULL;
-- GO
