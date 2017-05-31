CREATE DATABASE `bd_biblioteca` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE TABLE `aluno` (
  `idAluno` int(11) NOT NULL,
  `nomeAluno` varchar(100) NOT NULL,
  PRIMARY KEY (`idAluno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `livro` (
  `idLivro` int(11) NOT NULL,
  `tituloLivro` varchar(45) NOT NULL,
  `autorLivro` varchar(45) NOT NULL,
  `quantidadeEstoque` int(11) NOT NULL,
  PRIMARY KEY (`idLivro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `locacao` (
  `idLocacao` int(11) NOT NULL AUTO_INCREMENT,
  `dataRetirada` date NOT NULL,
  `dataDevolucao` date DEFAULT NULL,
  `aluno_idAluno` int(11) NOT NULL,
  `livro_idLivro` int(11) NOT NULL,
  PRIMARY KEY (`idLocacao`,`aluno_idAluno`,`livro_idLivro`),
  KEY `fk_locacao_aluno_idx` (`aluno_idAluno`),
  KEY `fk_locacao_livro1_idx` (`livro_idLivro`),
  CONSTRAINT `fk_locacao_aluno` FOREIGN KEY (`aluno_idAluno`) REFERENCES `aluno` (`idAluno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_locacao_livro1` FOREIGN KEY (`livro_idLivro`) REFERENCES `livro` (`idLivro`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


