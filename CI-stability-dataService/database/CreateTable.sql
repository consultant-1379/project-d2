create database `ci_stability_database`;

USE ci_stability_database;

CREATE TABLE all_jobs(
	job_id INT NOT NULL AUTO_INCREMENT,
	job_name VARCHAR(100) UNIQUE,
	PRIMARY KEY(job_id));

CREATE TABLE all_builds(
	build_id INT NOT NULL AUTO_INCREMENT,
	job_id INT,
	build_num INT,
	timestamp VARCHAR(100),
	status VARCHAR(100),
	PRIMARY KEY(build_id));

ALTER TABLE all_builds add UNIQUE unique_key(job_id, build_num);