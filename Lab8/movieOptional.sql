use movie;

Create table movies (id varchar(20) PRIMARY KEY, title varchar(255), release_date varchar(100), duration int, score float);

Create table genres (id varchar(20) PRIMARY KEY, name varchar(30));

Create table movieGenre (idMovie varchar(20), idGen varchar(20), 
                         FOREIGN KEY (idMovie) REFERENCES movies(id),
                         FOREIGN KEY (idGen) REFERENCES genres(id),
			 			 PRIMARY KEY (idMovie, idGen)
                        );
                        
Create table actors (idMovie varchar(20) not null,
                     full_name varchar(255),
                     FOREIGN KEY (idMovie) REFERENCES movies(id)
                    );

Create table directors (idMovie varchar(20) not null,
                        full_name varchar(255),
                       	FOREIGN KEY (idMovie) REFERENCES movies(id)
                       );


delete from moviegenre;
delete from directors;
delete from actors;
delete from movies;
delete from genres;