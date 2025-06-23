CREATE TABLE `superpoderes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `superpoder` VARCHAR(50) NOT NULL,
  `descricao` VARCHAR(250) NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_superpoder` (`superpoder`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `herois` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(120) NOT NULL,
  `nome_heroi` VARCHAR(120) NOT NULL,
  `data_nascimento` DATE NULL,
  `altura` DOUBLE NOT NULL,
  `peso` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_nome_heroi` (`nome_heroi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `heroissuperpoderes` (
  `heroi_id` INT NOT NULL,
  `superpoder_id` INT NOT NULL,
  PRIMARY KEY (`heroi_id`, `superpoder_id`),
  CONSTRAINT `fk_herois_superpoderes_heroi`
    FOREIGN KEY (`heroi_id`)
    REFERENCES `herois` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_herois_superpoderes_superpoder`
    FOREIGN KEY (`superpoder_id`)
    REFERENCES `superpoderes` (`id`)
    ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;