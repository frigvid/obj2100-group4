CREATE TABLE `plassering` (
  `id` integer PRIMARY KEY,
  `bygg` string NOT NULL,
  `floy` integer NOT NULL,
  `etasje` integer,
  `rom` integer
);

CREATE TABLE `inventar` (
  `sku` integer PRIMARY KEY COMMENT 'id',
  `beskrivelse` string,
  `innkjopsdato` datetime,
  `innkjopspris` real,
  `antall` integer,
  `forventetLevetid` smallint DEFAULT null,
  `kategori` integer,
  `plassering` integer,
  `kassert` integer DEFAULT null
);

CREATE TABLE `kassert` (
  `id` integer PRIMARY KEY,
  `dato` date,
  `tid` timestamp,
  `begrunnelse` id
);

CREATE TABLE `kassertType` (
  `id` integer PRIMARY KEY,
  `begrunnelse` string COMMENT 'solgt/kassert/på lager/annet'
);

CREATE TABLE `kategori` (
  `id` integer PRIMARY KEY,
  `type` integer NOT NULL,
  `kategori` string,
  PRIMARY KEY (`id`, `type`)
);

CREATE TABLE `kategoriType` (
  `id` integer PRIMARY KEY,
  `type` string COMMENT 'mobler/utsmykning/teknisk'
);

CREATE INDEX `kategori_index_0` ON `kategori` (`kategori`);

ALTER TABLE `inventar` ADD FOREIGN KEY (`kategori`) REFERENCES `kategori` (`id`);

ALTER TABLE `inventar` ADD FOREIGN KEY (`plassering`) REFERENCES `plassering` (`id`);

ALTER TABLE `inventar` ADD FOREIGN KEY (`kassert`) REFERENCES `kassert` (`id`);

ALTER TABLE `kassert` ADD FOREIGN KEY (`begrunnelse`) REFERENCES `kassertType` (`id`);

ALTER TABLE `kategori` ADD FOREIGN KEY (`type`) REFERENCES `kategoriType` (`id`);
