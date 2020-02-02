INSERT INTO nivelAcesso(sigla, descricao)
VALUES ('ADM', 'ADMINISTRADOR, ACESSO FULL'),
    ('BAS', 'NIVEL BASICO'),
    ('INT', 'NIVEL INTERMEDIARIO');
	
INSERT INTO usuarios(nome, usuario, senha, nivel, criado, bloqueado, ativo)
VALUES ('Administrador', 'admin', MD5('123456'), 1, CURRENT_TIMESTAMP,FALSE,TRUE);
