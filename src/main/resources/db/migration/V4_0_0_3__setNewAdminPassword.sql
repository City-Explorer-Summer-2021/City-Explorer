UPDATE usr
SET password = '$2a$08$Ib2hN.pB6/0ggLk802LJueQyhehhq6GrdrqbI8vZixvSOkNjyzdSO'
WHERE id = (SELECT id FROM usr LIMIT 1);