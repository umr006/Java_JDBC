DROP SCHEMA IF EXISTS s21_chat;
CREATE SCHEMA IF NOT EXISTS s21_chat;

CREATE TABLE s21_chat.user (
	id SERIAL PRIMARY KEY,
	login varchar(32) NOT NULL UNIQUE,
	passwd varchar(64) NOT null
);

CREATE TABLE s21_chat.chatroom (
	id serial PRIMARY KEY,
	chatroom_name varchar(50) NOT NULL UNIQUE,
	chatroom_owner_id INT NOT NULL,
	FOREIGN KEY (chatroom_owner_id) REFERENCES s21_chat.user(id)
);

CREATE TABLE s21_chat.message (
	id serial PRIMARY KEY,
	message_author INT NOT NULL,
	room_id INT NOT NULL,
	message_text varchar(255),
	date_time timestamp DEFAULT current_timestamp,
	FOREIGN KEY (message_author) REFERENCES s21_chat.user(id),
	FOREIGN KEY (room_id) REFERENCES s21_chat.chatroom(id)
);

CREATE TABLE  s21_chat.list_of_created_rooms (
	Id serial PRIMARY KEY,
	chatroom_owner_id INT,
	chatroom_id INT,
	chatroom_name varchar(50),
	FOREIGN KEY (chatroom_owner_id) REFERENCES s21_chat.USER(id),
	FOREIGN KEY (chatroom_id) REFERENCES s21_chat.chatroom(id),
	FOREIGN KEY (chatroom_name) REFERENCES s21_chat.chatroom(chatroom_name)
);