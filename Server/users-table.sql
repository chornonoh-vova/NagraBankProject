CREATE TABLE `users` (
  `user_id` int(16) NOT NULL,
  `balance` double DEFAULT NULL,
  `pin` varchar(45) NOT NULL,
  `birthdate` date NOT NULL,
  `user_login` varchar(45) NOT NULL,
  `secret_question` varchar(45) NOT NULL,
  `secret_answer` varchar(45) NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_login_UNIQUE` (`user_login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Users of the bank'