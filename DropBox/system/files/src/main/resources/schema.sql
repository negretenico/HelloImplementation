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
