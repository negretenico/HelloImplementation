CREATE TABLE Rider (
    rider_id INT AUTO_INCREMENT PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE PaymentMethod (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    rider_id INT,
    method_type TEXT NOT NULL,
    details TEXT NOT NULL,
    FOREIGN KEY (rider_id) REFERENCES Rider(rider_id)
);

CREATE TABLE Driver (
    driver_id INT AUTO_INCREMENT PRIMARY KEY,
    name TEXT NOT NULL,
    availability BOOLEAN NOT NULL DEFAULT TRUE
);


CREATE TABLE Fare (
    fare_id INT AUTO_INCREMENT PRIMARY KEY,
    price_estimate FLOAT NOT NULL,
    time_estimate INT,
    distance_estimate FLOAT
);

CREATE TABLE Location (
    location_id INT AUTO_INCREMENT PRIMARY KEY,
    latitude FLOAT NOT NULL,
    longitude FLOAT NOT NULL,
    name TEXT
);

CREATE TABLE Ride (
    ride_id INT AUTO_INCREMENT PRIMARY KEY,
    fare_id INT,
    pickup_location INT,
    dropoff_location INT,
    rider_id INT,
    driver_id INT,
    status TEXT NOT NULL, -- PENDING, IN_PROGRESS, COMPLETED, CANCELLED
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    completed_at TIMESTAMP,
    FOREIGN KEY (fare_id) REFERENCES Fare(fare_id),
    FOREIGN KEY (rider_id) REFERENCES Rider(rider_id),
    FOREIGN KEY (driver_id) REFERENCES Driver(driver_id),
    FOREIGN KEY (pickup_location) REFERENCES Location(location_id),
    FOREIGN KEY (dropoff_location) REFERENCES Location(location_id)
);
