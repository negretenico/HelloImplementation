-- Inserting data into Seats table
INSERT INTO Seats (seat_number)
VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9), (10);

-- Inserting data into Events table
INSERT INTO Events (name, description)
VALUES
('Concert A', 'A live concert featuring famous artists.'),
('Play B', 'A classical play performance.'),
('Comedy Show C', 'A stand-up comedy event with well-known comedians.');

-- Inserting data into Tickets table
INSERT INTO Tickets (event_id, seat_id, price, buyer_name, status,booked_at)
VALUES
(1, 1, 100.00, 'John Doe', 'PENDED', '2024-09-08 10:00:00'),
(2, 3, 75.50, 'Robert Brown', 'PENDED', '2024-09-08 10:05:00'),

-- These tickets are confirmed or available and should NOT be deleted
(1, 2, 100.00, 'Jane Smith', 'CONFIRMED', NULL),
(2, 4, 75.50, 'Emily Davis', 'AVAILABLE', NULL),
(3, 5, 50.00, 'Michael Johnson', 'CONFIRMED', NULL),
(3, 6, 50.00, 'Sarah Wilson', 'AVAILABLE', NULL),

-- Optional: A booked ticket that is NOT yet expired and should NOT be deleted
(1, 7, 100.00, 'Alice Green', 'PENDED', '2024-09-08 10:20:00');
