CREATE TABLE Seats (
    seat_id INT PRIMARY KEY AUTO_INCREMENT,
    seat_number INT
);

CREATE TABLE Events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    description TEXT
);


CREATE TABLE Tickets (
    ticket_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    seat_id INT,
    price DECIMAL(10, 2),
    buyer_name VARCHAR(255),
    status VARCHAR(50),
    booked_at  TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES Events(event_id),
    FOREIGN KEY (seat_id) REFERENCES Seats(seat_id)
);


CREATE TABLE Bookings (booking_id INT PRIMARY KEY AUTO_INCREMENT, user_id int, ticket_id INT,FOREIGN KEY (ticket_id) REFERENCES Tickets(ticket_id));