-- cannot use "user" as table name because it is reserved
CREATE TABLE im_user (
  id                  SERIAL PRIMARY KEY,
  modified            TIMESTAMP,
  created             TIMESTAMP NOT NULL           DEFAULT now(),

  first_name          TEXT      NOT NULL,
  last_name           TEXT      NOT NULL,
  title               TEXT      NOT NULL,
  email               TEXT,

  CONSTRAINT user_already_exists UNIQUE (first_name, last_name)
);
