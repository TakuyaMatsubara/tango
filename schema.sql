
/* Drop Tables */

DROP TABLE IF EXISTS group_id;
DROP TABLE IF EXISTS history;
DROP TABLE IF EXISTS question;




/* Create Tables */

CREATE TABLE group_id
(
	question_group_id varchar,
	group_id varchar
);

CREATE TABLE history
(
	history_id varchar,
	history varchar,
	days varchar,
	toral_points int
) ;


CREATE TABLE question
(
	question_id varchar NOT NULL,
	question_name varchar NOT NULL,
	answer boolean NOT NULL,
	correct_answer boolean NOT NULL,
	points int NOT NULL
) ;



