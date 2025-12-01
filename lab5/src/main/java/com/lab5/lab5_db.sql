CREATE TABLE Notebook
(
    id            INT PRIMARY KEY,
    manufacturer  VARCHAR(100) NOT NULL,
    notebook_name VARCHAR(100) NOT NULL,
    pages         INT          NOT NULL,
    cover_type    VARCHAR(20)  NOT NULL,
    country       VARCHAR(100) NOT NULL,
    circulation   INT          NOT NULL,
    page_style    VARCHAR(20)  NOT NULL
);