

INSERT INTO partner(id, name, description, time_added)
  VALUES(2000, 'Burna Boy', 'African Giant', CURRENT_TIMESTAMP);


INSERT INTO campaign
  (id, time_added, name, description, partner_id, start_date, end_date, expected_winner_count, campaign_type, campaign_status)
values
  (2000, NOW(), 'Party With Burna', 'Party Description', 2000, '2020-01-01', '2020-03-05', 5, 'SINGLE', 'APPROVED');


INSERT INTO campaign
  (id, time_added, name, description, partner_id, start_date, end_date, expected_winner_count, campaign_type, campaign_status)
values
  (2001, NOW(), 'BURNA Jamz', 'Jamz Description', 2000, '2020-01-01', '2020-03-05', 5, 'SINGLE', 'APPROVED');


INSERT INTO stage_description
  (id, time_added, stage, winners_count, evaluation_time, campaign_id)
VALUES
  (2000, NOW(), 1, 50, NOW(), 2000);


INSERT INTO stage_description
  (id, time_added, stage, winners_count, evaluation_time, campaign_id)
VALUES
  (2001, NOW(), 1, 50, NOW(), 2001);

