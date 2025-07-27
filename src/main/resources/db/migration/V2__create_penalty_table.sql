CREATE TABLE penalty (
    id SERIAL PRIMARY KEY,
    bill_id SERIAL NOT NULL,
    fine NUMERIC(10, 2) NOT NULL,
    interest NUMERIC(10, 6) NOT NULL,
    adjusted_value NUMERIC(10, 2) NOT NULL,
    daysLate NUMERIC NOT NULL,
    CONSTRAINT fk_penalty_bill FOREIGN KEY (bill_id) REFERENCES bill(id) ON DELETE CASCADE
);