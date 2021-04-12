use movie;

Create table movies (id int PRIMARY KEY, title varchar(255), release_date date, duration float, score float);

Create table genres (id int PRIMARY KEY, name varchar(255));

Create table movieGenre (idMovie int, idGen int, 
                         FOREIGN KEY (idMovie) REFERENCES movies(id),
                         FOREIGN KEY (idGen) REFERENCES genres(id),
			 PRIMARY KEY (idMovie, idGen)
                        );
