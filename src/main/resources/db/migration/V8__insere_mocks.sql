INSERT INTO T_ESG_USUARIO (ID_USUARIO, NOME, EMAIL, SENHA, ROLE, SETOR)
VALUES (USUARIO_SEQ.NEXTVAL, 'Administrador', 'admin@esgame.com', '$2a$10$pN7f3J61SlIwT/qP.rRGWeXZfNtn4Ejhv1LnY.Uv8WJI/WWAonHd2', 'ADMIN', 'FINANCEIRO');

INSERT INTO T_ESG_USUARIO (ID_USUARIO, NOME, EMAIL, SENHA, ROLE, SETOR)
VALUES (USUARIO_SEQ.NEXTVAL, 'Usuário Comum', 'usuario@esgame.com', '$2a$10$pN7f3J61SlIwT/qP.rRGWeXZfNtn4Ejhv1LnY.Uv8WJI/WWAonHd2', 'USER', 'RH');

INSERT INTO T_ESG_MISSAO (ID_MISSAO, NOME, DESCRICAO, DATA_INICIO, DATA_FIM, PONTOS_BASE, TIPO_MATERIAL)
VALUES (MISSAO_SEQ.NEXTVAL, 'Missão Reciclagem', 'Coleta de materiais recicláveis no setor de TI', TO_DATE('2025-05-01', 'YYYY-MM-DD'), TO_DATE('2025-05-31', 'YYYY-MM-DD'), 10, 'Papel');

INSERT INTO T_ESG_MATERIAL (NOME, UNIDADE, PONTOS_POR_UNIDADE)
VALUES ('Plástico', 'kg', 10);

INSERT INTO T_ESG_REPRESENTANTE (
    NOME,
    EMAIL,
    TELEFONE,
    DATA_CADASTRO
) VALUES (
    'João Silva',               -- Nome do representante
    'joao.silva@email.com',      -- Email do representante
    '11999998888',               -- Telefone
    SYSDATE                      -- Data de cadastro (data/hora atual do banco)
);