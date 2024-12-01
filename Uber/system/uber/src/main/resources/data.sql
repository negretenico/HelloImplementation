-- Inserting sample data into the Rider table
INSERT INTO Rider (name) VALUES
('Alice Johnson'),
('Bob Smith'),
('Charlie Brown'),
('Diana Prince'),
('Ethan Hunt'),
('Fiona Gallagher'),
('George Clooney'),
('Hannah Montana'),
('Ian Malcolm'),
('Julia Roberts');

-- Inserting sample data into the Driver table
INSERT INTO Driver (name, availability) VALUES
('Kevin Spacey', TRUE),
('Liam Neeson', TRUE),
('Maggie Smith', FALSE),
('Nicolas Cage', TRUE),
('Olivia Wilde', TRUE),
('Paul Rudd', FALSE),
('Quentin Tarantino', TRUE),
('Rita Ora', TRUE),
('Samuel L. Jackson', FALSE),
('Tina Fey', TRUE);

-- Inserting sample data into the Location table
INSERT INTO Location (latitude, longitude, name) VALUES
(40.7128, -74.0060, 'New York, NY'),
(34.0522, -118.2437, 'Los Angeles, CA'),
(41.8781, -87.6298, 'Chicago, IL'),
(29.7604, -95.3698, 'Houston, TX'),
(33.4484, -112.0740, 'Phoenix, AZ'),
(39.7392, -104.9903, 'Denver, CO'),
(32.7157, -117.1611, 'San Diego, CA'),
(47.6062, -122.3321, 'Seattle, WA'),
(38.9072, -77.0369, 'Washington, D.C.'),
(51.5074, -0.1278, 'London, UK');

-- Inserting sample data into the Fare table
INSERT INTO Fare (price_estimate, time_estimate, distance_estimate) VALUES
(25.50, 15, 10.0),
(35.00, 20, 15.0),
(45.75, 25, 20.0),
(55.00, 30, 25.0),
(65.25, 35, 30.0),
(75.00, 40, 35.0),
(85.50, 45, 40.0),
(95.00, 50, 45.0),
(105.25, 55, 50.0),
(115.00, 60, 55.0);

-- Inserting sample data into the Ride table
INSERT INTO Ride (fare_id, pickup_location, dropoff_location, rider_id, driver_id, status, requested_at) VALUES
(1, 1, 2, 1, 1, 'PENDING', '2024-12-01 07:00:00'),
(2, 2, 3, 2, 2, 'IN_PROGRESS', '2024-12-01 07:15:00'),
(3, 3, 4, 3, 4, 'COMPLETED', '2024-12-01 07:30:00'),
(4, 4, 5, 4, 5, 'CANCELLED', '2024-12-01 07:45:00'),
(5, 5, 6, 5, 6, 'PENDING', '2024-12-01 08:00:00'),
(6, 6, 7, 6, 8, 'IN_PROGRESS', '2024-12-01 08:15:00'),
(7, 7, 8, 7, 9, 'COMPLETED', '2024-12-01 08:30:00'),
(8, 8, 9, 8, 10, 'CANCELLED', '2024-12-01 08:45:00'),
(9, 9, 10, 9, 1, 'PENDING', '2024-12-01 09:00:00'),
(10, 10, 1, 10, 2, 'IN_PROGRESS', '2024-12-01 09:15:00');
