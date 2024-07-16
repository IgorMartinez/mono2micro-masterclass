CREATE SEQUENCE flight_sequence START WITH 1 INCREMENT BY 1;
INSERT INTO Flight(id, travelorderid, fromAirport, toAirport) VALUES (NEXTVAL('flight_sequence'),1,'GRU','MCO');
INSERT INTO Flight(id, travelorderid, fromAirport, toAirport) VALUES (NEXTVAL('flight_sequence'),2,'GRU','JFK');
INSERT INTO Flight(id, travelorderid, fromAirport, toAirport) VALUES (NEXTVAL('flight_sequence'),3,'GRU','ATL');
INSERT INTO Flight(id, travelorderid, fromAirport, toAirport) VALUES (NEXTVAL('flight_sequence'),4,'GRU','MEX');
INSERT INTO Flight(id, travelorderid, fromAirport, toAirport) VALUES (NEXTVAL('flight_sequence'),5,'GRU','MCO');