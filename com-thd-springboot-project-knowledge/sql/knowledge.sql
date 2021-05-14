CREATE TABLE `knowledge_info` (
  `knowledge_id` VARCHAR(64) NOT NULL,
  `doc_type` VARCHAR(64) NULL,
  `title` VARCHAR(256) NULL,
  `desc` VARCHAR(512) NULL,
  `content` MEDIUMTEXT NULL,
  `create_time` DATETIME NULL,
  `modify_time` DATETIME NULL,
  `create_by` VARCHAR(64) NULL,
  `modify_by` VARCHAR(64) NULL,
  `is_deleted` INT NULL,
  PRIMARY KEY (`knowledge_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `knowledge_dic_classify` (
  `classify_id` VARCHAR(64) NOT NULL,
  `parent_tree_code` VARCHAR(256) NULL,
  `classify_name` VARCHAR(64) NULL,
  `classify_desc` VARCHAR(256) NULL,
  `classify_order` INT NULL,
  `create_time` DATETIME NULL,
  `modify_time` DATETIME NULL,
  `create_by` VARCHAR(64) NULL,
  `modify_by` VARCHAR(64) NULL,
  `is_deleted` INT NULL,
  PRIMARY KEY (`classify_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `knowledge_rela_info_classify` (
  `id` INT NOT NULL,
  `knowledge_id` VARCHAR(64) NULL,
  `classify_id` VARCHAR(64) NULL,
  PRIMARY KEY (`id`));

