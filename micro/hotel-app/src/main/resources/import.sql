CREATE SEQUENCE hotel_sequence START WITH 1 INCREMENT BY 1;
INSERT INTO Hotel(id, travelorderid, nights) VALUES (NEXTVAL('hotel_sequence'),1,5);
INSERT INTO Hotel(id, travelorderid, nights) VALUES (NEXTVAL('hotel_sequence'),2,2);
INSERT INTO Hotel(id, travelorderid, nights) VALUES (NEXTVAL('hotel_sequence'),3,3);
INSERT INTO Hotel(id, travelorderid, nights) VALUES (NEXTVAL('hotel_sequence'),4,10);
INSERT INTO Hotel(id, travelorderid, nights) VALUES (NEXTVAL('hotel_sequence'),5,30);