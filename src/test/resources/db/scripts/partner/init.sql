
INSERT INTO partner(id, name, description, time_added)
  VALUES(1, 'Burna Boy', 'African Giant', CURRENT_TIMESTAMP);


INSERT INTO partner(id, name, description, time_added)
  VALUES(2, 'Dangote', 'Still dey find money', CURRENT_TIMESTAMP);


INSERT INTO partner_user(id, partner_id, name, email, username, password, time_added)
  VALUES(1, 1, 'Burna''s Manager', 'burna_manager@yahoo.co.uk', 'burna_manager', 'password', CURRENT_TIMESTAMP);


INSERT INTO partner_user(id, partner_id, name, email, username, password, time_added)
  VALUES(2, 2, 'Dangote''s Assistant', 'dangote@dangote.com', 'dangote_assistant', 'password', CURRENT_TIMESTAMP);




