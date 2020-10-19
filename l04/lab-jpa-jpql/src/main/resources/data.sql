INSERT INTO USER (ID, EMAIL, FIRST_NAME, LAST_NAME) VALUES(1, 'peter.muster@fhnw.ch', 'Peter', 'Muster');
INSERT INTO USER (ID, EMAIL, FIRST_NAME, LAST_NAME) VALUES(2, 'juerg.luthiger@fhnw.ch', 'Juerg', 'Luhthiger');

INSERT INTO MOVIE (ID, TITLE, RENTED, RELEASE_DATE) VALUES(1, 'Ex Machina', '1', '2017-07-14');
INSERT INTO MOVIE (ID, TITLE, RENTED, RELEASE_DATE) VALUES(2, 'Paddington', '0', '2017-04-28');
INSERT INTO MOVIE (ID, TITLE, RENTED, RELEASE_DATE) VALUES(3, 'Interstellar', '0', '2017-03-31');

INSERT INTO RENTAL (ID, RENTAL_DATE, RENTAL_DAYS, USER_ID, MOVIE_ID) VALUES(1, '2019-10-21', 7, 1, 1);
INSERT INTO RENTAL (ID, RENTAL_DATE, RENTAL_DAYS, USER_ID, MOVIE_ID) VALUES(2, '2019-10-21', 7, 1, 3);
