insert into BOOK(id, title, author, publisher) values (1, 'The Girl Who Lived', 'Christopher Greyson', 'Manning');
insert into BOOK(id, title, author, publisher) values (2, 'Red Mountain', 'Boo Walker', 'Manning');
insert into BOOK(id, title, author, publisher) values (3, 'Where the Crawdads Sing', 'Delia Owens', 'Manning');

insert into REVIEW(id, book_id, name, email, content) values (1, 1, 'Joe', 'jj@gmail.com', 'Terrible writing.');
insert into REVIEW(id, book_id, name, email, content) values (2, 1, 'Arthur', 'atrain@gmail.com', 'Amazing.');
insert into REVIEW(id, book_id, name, email, content) values (3, 2, 'Jill', 'the realjj@gmail.com', 'Would recommend!');
insert into REVIEW(id, book_id, name, email, content) values (4, 3, 'Tyler', 'tyler@gmail.com', 'Great stuff!');