CREATE TABLE Performers (
    performer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Venues (
    venue_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
    -- Additional venue details
);

CREATE TABLE SeatMaps (
    seat_map_id INT PRIMARY KEY AUTO_INCREMENT,
    venue_id INT,
    layout TEXT,  -- JSON, XML, etc.
    FOREIGN KEY (venue_id) REFERENCES Venues(venue_id)
);

CREATE TABLE Seats (
    seat_id INT PRIMARY KEY AUTO_INCREMENT,
    venue_id INT,
    row_number INT,
    seat_number INT,
    seat_type VARCHAR(50),
    FOREIGN KEY (venue_id) REFERENCES Venues(venue_id)
);

CREATE TABLE Events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    date DATE NOT NULL,
    venue_id INT,
    performer_id INT,
    FOREIGN KEY (venue_id) REFERENCES Venues(venue_id),
    FOREIGN KEY (performer_id) REFERENCES Performers(performer_id)
);

CREATE TABLE Tickets (
    ticket_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    seat_id INT,
    price DECIMAL(10, 2),
    buyer_name VARCHAR(255),
    status VARCHAR(50),
    FOREIGN KEY (event_id) REFERENCES Events(event_id),
    FOREIGN KEY (seat_id) REFERENCES Seats(seat_id)
);
