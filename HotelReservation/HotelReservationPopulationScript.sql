USE HotelReservation;

INSERT INTO AddOns
(AddOnID, AddOnName)
	VALUES
    (1, "RoomService"),
    (2, "Movie"),
    (3, "Drinks");

INSERT INTO BillingStatements
(BillingStatementID, BillingRate, BillingTax)
	VALUES
    (1, 1032.38, 23.32),
    (2, 565.35, 11.29),
    (3, 725.29, 14.98);
    
INSERT INTO BillingStatementsAddOns
(BillingStatementID, AddOnID)
	VALUES
    (1, 3),
    (2, 2),
    (3, 2);
    
INSERT INTO Amenities
(AmenityID, AmenityName)
	VALUES
    (1, "Fridge"),
    (2, "SpaBath"),
    (3, "Toaster");
    
INSERT INTO Customers
(CustomerID, CustomerFirstName, CustomerLastName, Phone, Email)
	VALUES
    (1, "Bob", "Johnson", 7012093456, "bob.johnson@sun.com"),
    (2, "Jane", "Smith", 4813429829, "jane@gmail.com"),
    (3, "John", "Jackson", 5369872345, "j.jackson@oracle.com");

INSERT INTO Reservations
(ReservationID, CustomerID, StartDate, EndDate)
	VALUES
	(1, 3, "2018-12-02", "2018-12-05"),
    (2, 2, "2018-12-07", "2018-12-09"),
    (3, 1, "2018-12-08", "2018-12-20");

INSERT INTO Rooms
(RoomID, ReservationID, BillingStatementID, RoomNumber, 
	RoomFloor, RoomOccupancy, RoomType)
	VALUES
	(1, 2, 3, 202, 2, 10, "Single"),
    (2, 2, 1, 105, 1, 5, "Single"),
    (3, 1, 2, 104, 1, 2, "Double");
    
INSERT INTO RoomRates
(RoomRateID, RoomID, RateRangeName, RateRangeStartDate, RateRangeEndDate)
	VALUES
	(1, 2, "Common", "2018-01-03", "2018-11-30"),
    (2, 1, "Peak", "2018-12-01", "2018-12-31"),
    (3, 1, "Holiday", "2018-01-01", "2018-01-02");
    
INSERT INTO Guests
(GuestID, ReservationID, GuestFirstName, GuestLastName, GuestAge)
	VALUES
    (1, 3, "Sally", "Smith", 21),
    (2, 1, "Samantha", "Sanders", 34),
    (3, 3, "Sam", "Sampson", 18);
    
INSERT INTO PromotionCodes
(PromotionCodeID, StartDate, EndDate, DiscountName, DiscountValue)
	VALUES
    (1, "2018-01-01", "2018-01-31", "NewYearSale", 14.99),
    (2, "2018-06-01", "2018-06-30", "SummerMadness", 9.99),
    (3, "2018-12-01", "2018-12-31", "ChristmasSale", 2.50);
    
INSERT INTO RoomsAmenities
(AmenityID, RoomID)
	VALUES
    (1, 1),
    (1, 2),
    (3, 2);
    
INSERT INTO ReservationsPromotionCodes
(ReservationID, PromotionCodeID)
	VALUES
    (2, 3),
    (3, 3),
    (1, 1);

