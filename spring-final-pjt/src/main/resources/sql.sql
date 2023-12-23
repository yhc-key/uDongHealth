DROP DATABASE IF EXISTS udong_health;
CREATE DATABASE udong_health DEFAULT CHARACTER SET utf8mb4;

USE udong_health;

CREATE TABLE if not exists `place` (
	place_id INT AUTO_INCREMENT,
    hashtag VARCHAR(20),
    cnt INT DEFAULT 0,
    place_name VARCHAR(20) NOT NULL,
    eq_year VARCHAR(10),
    address VARCHAR(50) NOT NULL,
	address_gu VARCHAR(20),
    address_dong VARCHAR(20),
    address_detail VARCHAR(50),
    eq_kind VARCHAR(500),
    eq_broken VARCHAR(50),
    eq_fix VARCHAR(10),
    latitude DOUBLE,
    longitude DOUBLE,
    dept VARCHAR(50),
    dept_phone VARCHAR(50),
    update_date DATE,
    PRIMARY KEY(place_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE if not exists `user` (
	user_id INT AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(50) UNIQUE,
    password VARCHAR(20) NOT NULL,
    favorites VARCHAR(20),
    reg_date DATETIME,
    login_date DATETIME,
    is_retired VARCHAR(1) DEFAULT 'N',
    PRIMARY KEY(user_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;


CREATE TABLE if not exists `review` (
	review_id INT AUTO_INCREMENT,
    user_id INT NOT NULL,
    place_id INT NOT NULL,
    writer VARCHAR(20) NOT NULL,
    view_cnt INT default 0,
    content TEXT NOT NULL,
    PRIMARY KEY(review_id),
    FOREIGN KEY (user_id) REFERENCES `user` (user_id),
    FOREIGN KEY (place_id) REFERENCES `place` (place_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;


CREATE TABLE if not exists `favorites` (
	favorites_id INT AUTO_INCREMENT,
    user_id INT NOT NULL,
    place_id INT NOT NULL,
    PRIMARY KEY(favorites_id),
	FOREIGN KEY (user_id) REFERENCES `user` (user_id),
    FOREIGN KEY (place_id) REFERENCES `place` (place_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;


SELECT ROUND((6371 * ACOS(COS(RADIANS(37.5013068)) * COS(RADIANS(latitude)) * COS(RADIANS(longitude)-RADIANS(127.0396597)) + SIN(RADIANS(37.5013068))*SIN(RADIANS(latitude)))),3) AS distance, *
FROM place
ORDER BY (6371
		*ACOS(COS(RADIANS(37.5013068))
    	*COS(RADIANS(latitude))
        *COS(radians(longitude)-RADIANS(127.0396597))
        +SIN(RADIANS(37.5013068))*SIN(RADIANS(latitude))))
LIMIT 4;


INSERT INTO user(name, email, password, favorites, reg_date)
VALUES('정유경','ssafy1@gmail.com','1234',null,now()),
('조용환','ssafy2@gmail.com','1234',null,now());

INSERT INTO review(user_id, place_id, writer, content)
VALUES(1,1,'정유경','여기 완전 추천이에용'),
(2,1,'조용환','운동하기 아주 좋네요');

commit;

SELECT * FROM place;
SELECT * FROM user;
SELECT * FROM review;
SELECT * FROM favorites;