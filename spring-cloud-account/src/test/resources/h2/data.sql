INSERT INTO account (id, username, password) VALUES ('2333333', 'aaaa', 'bbbb');
INSERT INTO account (id, username, password, age) VALUES (REPLACE((SELECT uuid()), '-', ''), 'aaaa1', 'bbbb', 10);
INSERT INTO account (id, username, password, age) VALUES (REPLACE((SELECT uuid()), '-', ''), 'aaaa2', 'bbbb', 9);
INSERT INTO account (id, username, password) VALUES (REPLACE((SELECT uuid()), '-', ''), 'aaaa3', 'bbbb');
INSERT INTO account (id, username, password) VALUES (REPLACE((SELECT uuid()), '-', ''), 'aaaa4', 'bbbb');
INSERT INTO account (id, username, password) VALUES (REPLACE((SELECT uuid()), '-', ''), 'aaaa5', 'bbbb');
INSERT INTO account (id, username, password) VALUES (REPLACE((SELECT uuid()), '-', ''), 'aaaa6', 'bbbb');