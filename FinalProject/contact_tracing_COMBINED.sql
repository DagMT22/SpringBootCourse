DROP TABLE IF EXISTS contacts;
DROP TABLE IF EXISTS cases;
DROP TABLE IF EXISTS individuals;
DROP TABLE IF EXISTS tests;
DROP TABLE IF EXISTS variants;


CREATE TABLE variants (
  variant_id VARCHAR(40) NOT NULL,
  variant_name VARCHAR(40) NOT NULL,
  disease_name VARCHAR(40) NOT NULL,
  infectious_time_range INT NOT NULL,
  PRIMARY KEY (variant_id)
);

CREATE TABLE tests (
  test_id VARCHAR(40) NOT NULL,
  test_name VARCHAR(40) NOT NULL,
  exposure_range_min INT NOT NULL,
  exposure_range_max INT NOT NULL,
  PRIMARY KEY (test_id)
);
CREATE TABLE individuals (
  personal_id INT NOT NULL AUTO_INCREMENT,
  full_name VARCHAR(40) NOT NULL,
  date_of_birth DATE NOT NULL,
  phone VARCHAR(20) NOT NULL,
  home_address VARCHAR(80) NOT NULL,
  county VARCHAR(40) NOT NULL,
  PRIMARY KEY (personal_id)
);

CREATE TABLE cases (
  case_id INT NOT NULL AUTO_INCREMENT,
  variant VARCHAR(40) NOT NULL,
  test_method VARCHAR(40) NOT NULL,
  patient_id INT NOT NULL,
  detected_date DATE NOT NULL,
  exposure_date DATE,
  exposure_location VARCHAR(80),
  notes VARCHAR(1024),
  PRIMARY KEY (case_id),
  FOREIGN KEY (variant)
  REFERENCES variants (variant_id),
  FOREIGN KEY (test_method)
  REFERENCES tests (test_id),
  FOREIGN KEY (patient_id)
  REFERENCES individuals (personal_id)
);


CREATE TABLE contacts (
  contact_id INT NOT NULL AUTO_INCREMENT,
  case_id INT NOT NULL,
  personal_id INT NOT NULL,
  contact_date DATE NOT NULL,
  location VARCHAR(80) NOT NULL,
  notes VARCHAR(1024),
  PRIMARY KEY (contact_id),
  FOREIGN KEY (case_id)
  REFERENCES cases (case_id),
  FOREIGN KEY (personal_id)
  REFERENCES individuals (personal_id)
);

-- variants
-- INSERT INTO variants (variant_id, variant_name, disease_name, infectious_time_range) VALUES ('','','',);
INSERT INTO variants (variant_id, variant_name, disease_name, infectious_time_range) VALUES ('SARS-CoV-2-DELTA','Delta','Covid-19',20);
INSERT INTO variants (variant_id, variant_name, disease_name, infectious_time_range) VALUES ('SARS-CoV-2-OMICRON','Omicron','Covid-19',20);
INSERT INTO variants (variant_id, variant_name, disease_name, infectious_time_range) VALUES ('Monkeypox-Zaire','Central African','Monkeypox',28);
INSERT INTO variants (variant_id, variant_name, disease_name, infectious_time_range) VALUES ('Monkeypox-SierraLeone','West African','Monkeypox',28);

-- tests
-- INSERT INTO tests (test_id, test_name, exposure_range_min, exposure_range_max) VALUES ('','',,);
INSERT INTO tests (test_id, test_name, exposure_range_min, exposure_range_max) VALUES ('Orthopox','Orthopox',14,56);
INSERT INTO tests (test_id, test_name, exposure_range_min, exposure_range_max) VALUES ('Covid-Rapid','Rapid Test',3,28);
INSERT INTO tests (test_id, test_name, exposure_range_min, exposure_range_max) VALUES ('Covid-RT-PCR','Lab PCR',2,28);
INSERT INTO tests (test_id, test_name, exposure_range_min, exposure_range_max) VALUES ('Covid-Antegen','Antegen Test',5,90);

-- individuals
-- INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('','','','','');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Sue Ali','1986-09-22','555-555-0001','8583 Stonybrook St. San Jose, CA 95123','Santa Clara');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Najma Newton','1980-04-26','555-555-0002','131 Rockaway Ave. Ontario, CA 91762','San Bernardino');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Frances Horn','1979-02-15','555-555-0003','38 Courtland Road Baldwin Park, CA 91706','Los Angeles');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Iga Luna','1961-07-21','555-555-0004','7207 Lexington Ave. San Francisco, CA 94110','San Francisco');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Lester Hanna','1990-08-18','555-555-0005','9984 Somerset Rd. Corona, CA 92882','Riverside');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Amy-Louise Moreno','1973-05-05','555-555-0006','61 Wintergreen Drive Bellflower, CA 90706','Los Angeles');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Augustus Markham','1988-11-13','555-555-0007','23 Hickory Ave. Davis, CA 95616','Yolo');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Nimra Lacey','1968-12-07','555-555-0008','8504 Williams St. Lake Forest, CA 92630','Orange');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Amirah Yoder','1967-09-27','555-555-0009','9550 Homestead Ave. Long Beach, CA 90813','Los Angeles');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Cleo Povey','1985-07-18','555-555-0010','500 Sherman St. Napa, CA 94558','Napa');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Harrison Foster','1967-12-16','555-555-0011','9929 Arnold Street Los Angeles, CA 90066','Los Angeles');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Deen Howard','1979-11-19','555-555-0012','42 North Old York St. Ontario, CA 91761','San Bernardino');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Saarah Lopez','1960-12-19','555-555-0013','757 E. Smith Ave. Anaheim, CA 92804','Orange');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Samia Atkins','1983-05-01','555-555-0014','778 Addison Avenue Merced, CA 95340','Merced');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Murphy Wolf','1999-06-08','555-555-0015','8307 Leeton Ridge Street Cupertino, CA 95014','Santa Clara');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Arnold Salinas','1965-12-04','555-555-0016','18 3rd Street Tracy, CA 95376','San Joaquin');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Daisy Cullen','1960-12-13','555-555-0017','55 Homestead Dr. La Puente, CA 91744','Los Angeles');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Shakeel Burns','1976-12-13','555-555-0018','513 Brookside Dr. Santa Ana, CA 92704','Orange');
INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Grady Correa','1989-03-03','555-555-0019','396 Vine Street Paramount, CA 90723','Los Angeles');
-- INSERT INTO individuals (full_name, date_of_birth, phone, home_address, county) VALUES ('Elsie Bloggs','1987-03-03','555-555-0020','226 Sunbeam Ave. Oxnard, CA 93033','Ventura');

-- list individuals
-- SELECT * FROM individuals;

-- cases
-- INSERT INTO cases (variant, test_method, patient_id, detected_date, exposure_date, exposure_location, notes) VALUES ('','','','','','','');
INSERT INTO cases (variant, test_method, patient_id, detected_date, exposure_date, exposure_location, notes) VALUES ('SARS-CoV-2-OMICRON','Covid-RT-PCR',1,'2022-05-01','2022-04-20','85 S Second St, San Jose, CA 95113','test note');
-- INSERT INTO cases (variant, test_method, patient_id, detected_date, exposure_date, exposure_location, notes) VALUES ('SARS-CoV-2-DELTA','Covid-Rapid',20,'2022-04-01','2022-03-25','200 N Grand Ave, Los Angeles, CA 90012','');

-- list cases
-- SELECT * FROM cases;

-- contacts
-- INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (,,'','','');
INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (1,2,'2022-04-20','85 S Second St, San Jose, CA 95113','Test Note');
INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (1,3,'2022-04-21','85 S Second St, San Jose, CA 95113','');
INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (1,4,'2022-04-22','85 S Second St, San Jose, CA 95113','');
INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (1,5,'2022-04-23','85 S Second St, San Jose, CA 95113','');
INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (1,6,'2022-04-24','85 S Second St, San Jose, CA 95113','');
INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (1,7,'2022-04-29','6898 Raleigh Rd, San Jose, CA 95123','');
INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (1,8,'2022-04-29','6898 Raleigh Rd, San Jose, CA 95123','');
INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (1,9,'2022-04-29','6898 Raleigh Rd, San Jose, CA 95123','');
-- INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (1,10,'2022-04-29','6898 Raleigh Rd, San Jose, CA 95123','');
-- INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (2,11,'2022-03-25','200 N Grand Ave, Los Angeles, CA 90012','');
-- INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (2,12,'2022-03-25','200 N Grand Ave, Los Angeles, CA 90012','');
-- INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (2,13,'2022-03-25','200 N Grand Ave, Los Angeles, CA 90012','');
-- INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (2,14,'2022-03-25','200 N Grand Ave, Los Angeles, CA 90012','');
-- INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (2,15,'2022-03-25','200 N Grand Ave, Los Angeles, CA 90012','');
-- INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (2,16,'2022-03-30','5621 N Figueroa St, Los Angeles, CA 90042','');
-- INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (2,17,'2022-03-30','5621 N Figueroa St, Los Angeles, CA 90042','');
-- INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (2,18,'2022-03-30','5621 N Figueroa St, Los Angeles, CA 90042','');
-- INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES (2,19,'2022-03-30','5621 N Figueroa St, Los Angeles, CA 90042','');

-- list contacts
-- SELECT * FROM contacts;
