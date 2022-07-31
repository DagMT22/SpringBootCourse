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