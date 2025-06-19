CREATE TABLE categories (
    category_id BIGINT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
);
