CREATE TABLE nivelAcesso(
id SERIAL NOT NULL UNIQUE,
sigla VARCHAR(3) NOT NULL UNIQUE,
descricao VARCHAR(255),
PRIMARY KEY(id)
);


    
CREATE TABLE usuarios(
id SERIAL NOT NULL UNIQUE,
nome VARCHAR(255) NOT NULL,
usuario VARCHAR(255) NOT NULL UNIQUE,
senha VARCHAR(32) NOT NULL,
nivel INTEGER NOT NULL,
bloqueado BOOLEAN NOT NULL,
ativo BOOLEAN NOT NULL,
tentativas INTEGER CHECK(tentativas <= 3),
criado TIMESTAMP NOT NULL,
editado TIMESTAMP,
PRIMARY KEY(id),
FOREIGN KEY (nivel) REFERENCES nivelAcesso(id)
);
