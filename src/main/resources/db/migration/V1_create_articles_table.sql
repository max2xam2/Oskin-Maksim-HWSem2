CREATE TABLE articles (
    article_id BIGINT PRIMARY KEY,
    article_title VARCHAR(255) NOT NULL,
    article_url VARCHAR(255) NOT NULL,
    category_id BIGINT NOT NULL,
    website_id BIGINT NOT NULL
);