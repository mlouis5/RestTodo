INSERT INTO household.todo(
            id, type, recurrence, created_by, created_on, value, due_by, 
            is_complete)
    VALUES (3, 'List', 'Bi-Weekly', 'macdersonlouis@gmail.com', now(), 'Milk, Eggs, Bread, PBJ', now(), 
            false);



INSERT INTO household.todo VALUES (9, 'Todo', 'Semi-Monthly', 'mac.louis@live.com', '2015-06-03 12:09:31.194', 'buy printing paper', 'HIGH', NULL, false,  false);
INSERT INTO household.todo VALUES (8, 'Todo', 'Semi-Monthly', 'samira.f.davis@gmail.com', '2015-06-03 12:09:31.194', 'buy printing paper', 'LOW', NULL, true, false);
INSERT INTO household.todo VALUES (7, 'List', 'Bi-Weekly', 'fatima1davis@aol.com', '2015-06-03 12:09:31.194', 'bread, milk, juice, cereal, vegetables', 'HIGH', NULL, false, true);
INSERT INTO household.todo VALUES (10, 'Todo', 'One-Time', 'macdersonlouis@gmail.com', '2015-06-11 16:42:15.862', 'adding todo #2', 'LOW', '2015-06-12 00:00:00', false, false);
INSERT INTO household.todo VALUES (11, 'Todo', 'One-Time', 'macdersonlouis@gmail.com', '2015-06-11 16:54:20.554', 'new todo #3', 'HIGH', '2015-06-11 00:00:00', false, false);
INSERT INTO household.todo VALUES (12, 'Todo', 'One-Time', 'macdersonlouis@gmail.com', '2015-06-11 17:25:24.324', 'test #4', 'LOW', '2015-06-12 00:00:00', false, false);
INSERT INTO household.todo VALUES (13, 'Todo', 'One-Time', 'macdersonlouis@gmail.com', '2015-06-11 19:38:48.117', 'test #5', 'LOW', '2015-06-18 00:00:00', true, false);
INSERT INTO household.todo VALUES (2, 'Note', 'Daily', 'macdersonlouis@gmail.com', '2015-06-03 12:09:31.194', 'plan party for Fatima', 'HIGH', '2015-06-03 12:09:31.194', true, false);