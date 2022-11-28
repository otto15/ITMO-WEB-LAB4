CREATE TABLE IF NOT EXISTS s335109.app_users (
    id SERIAL,
    name VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS s335109.hit_checks (
    id SERIAL,
    user_id integer NOT NULL,
    x DOUBLE PRECISION NOT NULL,
    y DOUBLE PRECISION NOT NULL,
    r DOUBLE PRECISION NOT NULL,
    calling_date TIMESTAMP WITH TIME ZONE NOT NULL,
    execution_time BIGINT NOT NULL,
    hit_status BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES s335109.app_users(id)
);