DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS unit_of_measure;
CREATE TABLE category (
                               id INT AUTO_INCREMENT  PRIMARY KEY,
                               description VARCHAR(250) NOT NULL
);
CREATE TABLE unit_of_measure(
                                id INT AUTO_INCREMENT  PRIMARY KEY,
                                uom VARCHAR(250) NOT NULL
);