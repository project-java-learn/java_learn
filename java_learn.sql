CREATE TABLE IF NOT EXISTS `Level` (
  `level_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `level` TEXT NOT NULL)

CREATE TABLE IF NOT EXISTS `DefinitionsAnswer` (
  `dd_answer_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `dd_question_id` INTEGER NOT NULL,
  `dd_answer` TEXT NOT NULL,
  `is_correct` INTEGER NOT NULL,
  FOREIGN KEY(`dd_question_id`) REFERENCES `DefinitionsQuestion`(`dd_question_id`)
  ON UPDATE NO ACTION ON DELETE CASCADE )

CREATE TABLE IF NOT EXISTS `DefinitionsQuestion` (
  `dd_question_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `level_id` INTEGER NOT NULL,
  `dd_question` TEXT NOT NULL,
  FOREIGN KEY(`level_id`) REFERENCES `Level`(`level_id`)
  ON UPDATE NO ACTION ON DELETE CASCADE )

CREATE TABLE IF NOT EXISTS `FillBlankAnswer` (
  `fb_answer_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `fb_question_id` INTEGER NOT NULL,
  `fb_answer` TEXT NOT NULL,
  `is_correct` INTEGER NOT NULL,
  FOREIGN KEY(`fb_question_id`) REFERENCES `FillBlankQuestion`(`fb_question_id`)
  ON UPDATE NO ACTION ON DELETE CASCADE )

CREATE TABLE IF NOT EXISTS `FillBlankQuestion` (
  `fb_question_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `level_id` INTEGER NOT NULL,
  FOREIGN KEY(`level_id`) REFERENCES `Level`(`level_id`)
  ON UPDATE NO ACTION ON DELETE CASCADE )

CREATE TABLE IF NOT EXISTS `HighlightAnswer` (
  `hl_answer_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `hl_question_id` INTEGER NOT NULL,
  `hl_answer` TEXT NOT NULL, `type` TEXT,
  FOREIGN KEY(`hl_question_id`) REFERENCES `HighlightQuestion`(`hl_question_id`)
  ON UPDATE NO ACTION ON DELETE CASCADE )

CREATE TABLE IF NOT EXISTS `HighlightQuestion` (
  `hl_question_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `level_id` INTEGER NOT NULL,
  `hl_question` TEXT NOT NULL,
  FOREIGN KEY(`level_id`) REFERENCES `Level`(`level_id`)
  ON UPDATE NO ACTION ON DELETE CASCADE )

CREATE TABLE IF NOT EXISTS `MultipleChoiceAnswer` (
  `mc_answer_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `mc_question_id` INTEGER NOT NULL,
  `mc_answer` TEXT NOT NULL,
  `is_correct` INTEGER NOT NULL,
  FOREIGN KEY(`mc_question_id`) REFERENCES `MultipleChoiceQuestion`(`mc_question_id`)
  ON UPDATE NO ACTION ON DELETE CASCADE )

CREATE TABLE IF NOT EXISTS `MultipleChoiceQuestion` (
  `mc_question_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `level_id` INTEGER NOT NULL,
  `mc_question` TEXT NOT NULL,
  FOREIGN KEY(`level_id`) REFERENCES `Level`(`level_id`)
  ON UPDATE NO ACTION ON DELETE CASCADE )",
