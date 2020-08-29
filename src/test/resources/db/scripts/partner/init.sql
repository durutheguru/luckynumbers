
INSERT INTO partner(id, name, description, time_added)
  VALUES(10000, 'Burna Boy', 'African Giant', CURRENT_TIMESTAMP);


INSERT INTO partner(id, name, description, time_added)
  VALUES(20000, 'Dangote', 'Still dey find money', CURRENT_TIMESTAMP);


INSERT INTO partner_user(id, partner_id, name, email, username, password, time_added)
  VALUES(10000, 10000, 'Burna''s Manager', 'burna_manager@yahoo.co.uk', 'burna_manager', 'password', CURRENT_TIMESTAMP);


INSERT INTO partner_user(id, partner_id, name, email, username, password, time_added)
  VALUES(20000, 20000, 'Dangote''s Assistant', 'dangote@dangote.com', 'dangote_assistant', 'password', CURRENT_TIMESTAMP);




