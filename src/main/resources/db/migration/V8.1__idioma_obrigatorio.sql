-- -- 3. Adicionar constraint de validação (antes de tornar NOT NULL)
ALTER TABLE t_gp_mottu_usuario
ADD CONSTRAINT ck_idiom
    CHECK (idiom IN ('PTBR', 'ES', 'EN'));
GO

-- 4. Tornar a coluna obrigatória (NOT NULL)
ALTER TABLE t_gp_mottu_usuario
ALTER COLUMN idiom VARCHAR(10) NOT NULL;
GO
