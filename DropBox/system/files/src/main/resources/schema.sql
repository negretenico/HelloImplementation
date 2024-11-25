CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name TEXT
);

CREATE TABLE FileMetadata (
    file_id INT AUTO_INCREMENT PRIMARY KEY,
    extension TEXT,
    file_size INT,
    name TEXT,
    uploadedBy INT,
    status TEXT,
    s3URL TEXT,
    FOREIGN KEY (uploadedBy) REFERENCES Users(user_id)
);

CREATE TABLE FileShares (
    id INT AUTO_INCREMENT PRIMARY KEY,
    file_id INT,
    user_id INT,
    shared_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (file_id) REFERENCES FileMetadata(file_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    UNIQUE (file_id, user_id) -- Ensure no duplicate file-user mappings
);