USE HotelReservation;

-- Must Keep track of all room information
SELECT * 
FROM Rooms;

-- Lets say RoomNumber with Reservation StartDate and EndDate where the ID is 2

SELECT RoomNumber, Reservations.StartDate, Reservations.EndDate
FROM Rooms
INNER JOIN Reservations
ON Rooms.ReservationID = Reservations.ReservationID
WHERE Rooms.ReservationID = 2;


-- Affiliate a customer name with their Reservation

SELECT Customers.CustomerFirstName, Customers.CustomerLastName, StartDate, EndDate
FROM Reservations
INNER JOIN Customers
ON Customers.CustomerID = Reservations.CustomerID;

-- Customer booking multiple Rooms on same reservation

SELECT Rooms.RoomNumber, Reservations.ReservationID, CustomerFirstName
FROM Customers
INNER JOIN Reservations
ON Customers.customerID = Reservations.customerID
INNER JOIN Rooms
ON Rooms.ReservationID = Reservations.ReservationID
WHERE Reservations.ReservationID = 2;

-- Allow Tracking of RoomRates, AddOns, Billing and allow for PromotionCodes

SELECT * 
FROM RoomRates;

SELECT * 
FROM AddOns;

SELECT * 
FROM BillingStatements;

SELECT * 
FROM PromotionCodes;

