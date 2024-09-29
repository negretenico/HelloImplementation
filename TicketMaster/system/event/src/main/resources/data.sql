-- Create Performers
INSERT INTO Performers (name) VALUES ('The Weekend');
INSERT INTO Performers (name) VALUES ('Dave Chappelle');
INSERT INTO Performers (name) VALUES ('Taylor Swift');
INSERT INTO Performers (name) VALUES ('LA Lakers');
INSERT INTO Performers (name) VALUES ('TBD - Super Bowl Halftime Show');

-- Create Venues
INSERT INTO Venues (name, location) VALUES ('SoFi Stadium', 'Inglewood, CA');
INSERT INTO Venues (name, location) VALUES ('Madison Square Garden', 'New York, NY');
INSERT INTO Venues (name, location) VALUES ('Red Rocks Amphitheatre', 'Morrison, CO');
INSERT INTO Venues (name, location) VALUES ('Staples Center', 'Los Angeles, CA');
INSERT INTO Venues (name, location) VALUES ('Grant Park', 'Chicago, IL');

-- Create Seat Maps
INSERT INTO SeatMaps (venue_id, layout) VALUES (1, '{"sections": {"A": 100, "B": 200, "C": 300}}');
INSERT INTO SeatMaps (venue_id, layout) VALUES (2, '{"sections": {"1": 50, "2": 100, "3": 150}}');
INSERT INTO SeatMaps (venue_id, layout) VALUES (3, '{"sections": {"Front": 50, "Middle": 100, "Back": 200}}');
INSERT INTO SeatMaps (venue_id, layout) VALUES (4, '{"sections": {"Lower": 100, "Upper": 200}}');
INSERT INTO SeatMaps (venue_id, layout) VALUES (5, '{"sections": {"General Admission": 1000}}');

-- Create Seats
-- SoFi Stadium (venue_id = 1)
INSERT INTO Seats (venue_id, row_number, seat_number, seat_type) VALUES (1, 1, 1, 'VIP');
INSERT INTO Seats (venue_id, row_number, seat_number, seat_type) VALUES (1, 1, 2, 'Regular');
-- Madison Square Garden (venue_id = 2)
INSERT INTO Seats (venue_id, row_number, seat_number, seat_type) VALUES (2, 1, 1, 'VIP');
INSERT INTO Seats (venue_id, row_number, seat_number, seat_type) VALUES (2, 1, 2, 'Regular');
-- Red Rocks Amphitheatre (venue_id = 3)
INSERT INTO Seats (venue_id, row_number, seat_number, seat_type) VALUES (3, 1, 1, 'Regular');
INSERT INTO Seats (venue_id, row_number, seat_number, seat_type) VALUES (3, 1, 2, 'Regular');
-- Staples Center (venue_id = 4)
INSERT INTO Seats (venue_id, row_number, seat_number, seat_type) VALUES (4, 1, 1, 'VIP');
INSERT INTO Seats (venue_id, row_number, seat_number, seat_type) VALUES (4, 1, 2, 'Regular');
-- Grant Park (venue_id = 5) - General Admission, so no specific seats

-- Create Events
INSERT INTO Events (name, description, date, venue_id, performer_id) VALUES 
('Super Bowl 2024', 'The biggest football event of the year!', '2024-02-11', 1, 5),
('Taylor Swift Concert', 'Live performance by Taylor Swift.', '2024-07-19', 3, 3),
('Dave Chappelle Comedy Show', 'A night of comedy with Dave Chappelle.', '2024-09-05', 2, 2),
('Lollapalooza Festival', 'Annual music festival with various artists.', '2024-08-01', 5, 3),
('NBA Finals Game 7', 'The final game of the NBA season.', '2024-06-15', 4, 4);