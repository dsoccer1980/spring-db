DROP TABLE IF EXISTS Account;

CREATE TABLE Account(
                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                     userid INT NOT NULL,
                     amount INT NOT NULL,
                     currency VARCHAR(4) NOT NULL
);
CREATE UNIQUE INDEX account_userid_currency ON Account (userid, currency);
