CREATE SEQUENCE travelorder_sequence START WITH 1 INCREMENT BY 1;
INSERT INTO TravelOrder(id) VALUES (NEXTVAL('travelorder_sequence'));
INSERT INTO TravelOrder(id) VALUES (NEXTVAL('travelorder_sequence'));
INSERT INTO TravelOrder(id) VALUES (NEXTVAL('travelorder_sequence'));
INSERT INTO TravelOrder(id) VALUES (NEXTVAL('travelorder_sequence'));
INSERT INTO TravelOrder(id) VALUES (NEXTVAL('travelorder_sequence'));

CREATE SEQUENCE flight_sequence START WITH 1 INCREMENT BY 1;
INSERT INTO Flight(id, travelorderid, fromAirport, toAirport) VALUES (NEXTVAL('flight_sequence'),1,'GRU','MCO');
INSERT INTO Flight(id, travelorderid, fromAirport, toAirport) VALUES (NEXTVAL('flight_sequence'),2,'GRU','JFK');
INSERT INTO Flight(id, travelorderid, fromAirport, toAirport) VALUES (NEXTVAL('flight_sequence'),3,'GRU','ATL');
INSERT INTO Flight(id, travelorderid, fromAirport, toAirport) VALUES (NEXTVAL('flight_sequence'),4,'GRU','MEX');
INSERT INTO Flight(id, travelorderid, fromAirport, toAirport) VALUES (NEXTVAL('flight_sequence'),5,'GRU','MCO');

CREATE SEQUENCE hotel_sequence START WITH 1 INCREMENT BY 1;
INSERT INTO Hotel(id, travelorderid, nights) VALUES (NEXTVAL('hotel_sequence'),1,5);
INSERT INTO Hotel(id, travelorderid, nights) VALUES (NEXTVAL('hotel_sequence'),2,2);
INSERT INTO Hotel(id, travelorderid, nights) VALUES (NEXTVAL('hotel_sequence'),3,3);
INSERT INTO Hotel(id, travelorderid, nights) VALUES (NEXTVAL('hotel_sequence'),4,10);
INSERT INTO Hotel(id, travelorderid, nights) VALUES (NEXTVAL('hotel_sequence'),5,30);