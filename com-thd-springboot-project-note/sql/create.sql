CREATE TABLE `note` (
  `note_id` varchar(64) NOT NULL,
  `classify` varchar(64) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `content` mediumtext,
  `expire_date` date DEFAULT NULL,
  `alarm_days` int(11) DEFAULT NULL,
  `todo_level` int(11) DEFAULT NULL,
  `todo_status` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `is_deleted` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
