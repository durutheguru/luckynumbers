

INSERT INTO partner(id, name, description, time_added)
  VALUES(1, 'Burna Boy', 'African Giant', CURRENT_TIMESTAMP);


INSERT INTO campaign
  (time_added, name, description, partner_id, start_date, end_date, expected_winner_count, campaign_type, campaign_status)
values
  (NOW(), 'Party With Burna', 'Party Description', 1, '2020-01-01', '2020-03-05', 5, 'SINGLE', 'APPROVED');


INSERT INTO campaign
  (time_added, name, description, partner_id, start_date, end_date, expected_winner_count, campaign_type, campaign_status)
values
  (NOW(), 'BURNA Jamz', 'Jamz Description', 1, '2020-01-01', '2020-03-05', 5, 'SINGLE', 'APPROVED');


INSERT INTO stage_description
  (time_added, stage, winners_count, evaluation_time, campaign_id)
VALUES
  (NOW(), 1, 50, NOW(), 1);


INSERT INTO stage_description
  (time_added, stage, winners_count, evaluation_time, campaign_id)
VALUES
  (NOW(), 1, 50, NOW(), 2);

