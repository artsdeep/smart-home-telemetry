CREATE TABLE IF NOT EXISTS Telemetry (
                           id SERIAL PRIMARY KEY,
                           device_id INT NOT NULL,
                           metric_name VARCHAR(100) NOT NULL,
                           metric_value FLOAT NOT NULL,
                           create_at TIMESTAMP NOT NULL
);
CREATE TABLE IF NOT EXISTS Alert (
                       alert_id SERIAL PRIMARY KEY,
                       device_id INT NOT NULL,
                       alert_type VARCHAR(100) NOT NULL,
                       resolved BOOLEAN NOT NULL DEFAULT FALSE
);
