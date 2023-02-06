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
    cpfFuncionario varchar(11) NOT NULL,
    dataEmprestimo TIMESTAMP NOT NULL,
    dataDevolucao TIMESTAMP NULL,
    status int NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT emprestimos_FK_funcionario FOREIGN KEY (idFuncionario) REFERENCES pi_funcionarios (id)
);

CREATE TABLE IF NOT EXISTS pi_itensemprestimo (
    id int AUTO_INCREMENT,
    idEmprestimo int NOT NULL,
    idFerramenta int NOT NULL,
    quantidade int NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT itensemprestimo_FK_emprestimo FOREIGN KEY (idEmprestimo) REFERENCES pi_emprestimos (id),
    CONSTRAINT itensemprestimo_FK_ferramenta FOREIGN KEY (idFerramenta) REFERENCES pi_ferramentas (id)
);