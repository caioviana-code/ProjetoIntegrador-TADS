CREATE TABLE IF NOT EXISTS pi_ferramentas (
    id int AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    estoque int NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pi_funcionarios (
    id int AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    cpf varchar(11) NOT NULL unique,
    telefone varchar(10) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pi_emprestimos (
    id int AUTO_INCREMENT,
    idFuncionario int NOT NULL,
    idFerramenta int NOT NULL,
    quantidade int NOT NULL,
    dataEmprestimo TIMESTAMP NOT NULL,
    dataDevolucao TIMESTAMP NULL,
    status int NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT emprestimos_FK_funcionario FOREIGN KEY (idFuncionario) REFERENCES pi_funcionarios (id),
    CONSTRAINT emprestimos_FK_ferramenta FOREIGN KEY (idFerramenta) REFERENCES pi_ferramentas (id)
);

CREATE TABLE IF NOT EXISTS pi_usuarios (
    id int AUTO_INCREMENT,
    login varchar(30) NOT NULL,
    senha varchar(30) NOT NULL,
    email varchar(100) NOT NULL,
    PRIMARY KEY (id)
);