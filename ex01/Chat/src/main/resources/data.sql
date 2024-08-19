
INSERT INTO s21_chat."user" (login, passwd)
VALUES
	('user1', 'passwd'),
	('user2', 'passwd'),
	('user3', 'passwd'),
	('user4', 'passwd'),
	('user5', 'passwd'),
	('user6', 'passwd');

INSERT INTO s21_chat.chatroom (chatroom_name, chatroom_owner_id)
VALUES
	('room1', 1),
	('room2', 2),
	('room3', 3),
	('room4', 4),
	('room5', 5),
	('room6', 6);

INSERT INTO s21_chat.message(message_author, room_id, message_text)
VALUES
	(1, 1, 'Hello, everybody'),
	(1, 1, 'My name is User1'),
	(3, 1, 'Hello, everybody! My name is User3! Nice to meet you, User1'),
	(1, 1, 'Nice to meet you, User3');

INSERT INTO s21_chat.list_of_created_rooms(chatroom_owner_id, chatroom_id, chatroom_name)
VALUES
	(1, 1, 'room1'),
	(2, 2, 'room2'),
	(3, 3, 'room3'),
	(4, 4, 'room4'),
	(5, 5, 'room5'),
	(6, 6, 'room6');
