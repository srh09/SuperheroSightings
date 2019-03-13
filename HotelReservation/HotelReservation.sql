DROP DATABASE IF EXISTS HotelReservation;
CREATE DATABASE IF NOT EXISTS HotelReservation;

USE HotelReservation;

CREATE TABLE IF NOT EXISTS Reservations
(
	ReservationID INT(11) NOT NULL auto_increment,
    CustomerID INT(11) NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    PRIMARY KEY(ReservationID)
);

CREATE TABLE IF NOT EXISTS Rooms
(
	RoomID INT(11) NOT NULL auto_increment,
    ReservationID INT(11) NOT NULL,
    BillingStatementID INT(11) NOT NULL,
    RoomNumber INT(3) NOT NULL,
    RoomFloor INT(2) NOT NULL,
    RoomOccupancy INT(3) NOT NULL,
    RoomType VARCHAR(45) NOT NULL,
    PRIMARY KEY(RoomID)
);

CREATE TABLE IF NOT EXISTS RoomRates
(
	RoomRateID INT(11) NOT NULL auto_increment,
    RoomID INT(11) NOT NULL,
    RateRangeName VARCHAR(45) NOT NULL,
    RateRangeStartDate DATE NOT NULL,
    RateRangeEndDate DATE NOT NULL,
    PRIMARY KEY(RoomRateID)
);

CREATE TABLE IF NOT EXISTS Amenities
(
	AmenityID INT(11) NOT NULL auto_increment,
    AmenityName VARCHAR(45) NOT NULL,
    PRIMARY KEY(AmenityID)
);

CREATE TABLE IF NOT EXISTS RoomsAmenities
(
	AmenityID INT(11) NOT NULL,
    RoomID INT(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS BillingStatements
(
	BillingStatementID INT(11) NOT NULL auto_increment,
    BillingRate DECIMAL(10,2) NOT NULL,
    BillingTax DECIMAL (10,2) NOT NULL,
    PRIMARY KEY(BillingStatementID)
);

CREATE TABLE IF NOT EXISTS AddOns
(
	AddOnID INT(11) NOT NULL auto_increment,
    AddOnName VARCHAR(45) NOT NULL,
    PRIMARY KEY(AddOnID)
);

CREATE TABLE IF NOT EXISTS BillingStatementsAddOns
(
	BillingStatementID INT(11) NOT NULL,
    AddOnID INT(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS Customers
(
	CustomerID INT(11) NOT NULL auto_increment,
    CustomerFirstName VARCHAR(45) NOT NULL,
    CustomerLastName VARCHAR(45) NOT NULL,
	Phone BIGINT(10) NOT NULL,
    Email VARCHAR(45) NOT NULL,
    PRIMARY KEY(CustomerID)
);

CREATE TABLE IF NOT EXISTS Guests
(
	GuestID INT(11) NOT NULL auto_increment,
    ReservationID INT(11) NOT NULL,
    GuestFirstName VARCHAR(45) NOT NULL,
    GuestLastName VARCHAR(45) NOT NULL,
    GuestAge INT(3) NOT NULL,
    PRIMARY KEY(GuestID)
);

CREATE TABLE IF NOT EXISTS PromotionCodes
(
	PromotionCodeID INT(11) NOT NULL auto_increment,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    DiscountName VARCHAR(45) NOT NULL,
    DiscountValue DECIMAL(5,2) NOT NULL,
    PRIMARY KEY(PromotionCodeID)
);

CREATE TABLE IF NOT EXISTS ReservationsPromotionCodes
(
	ReservationID INT(11) NOT NULL,
    PromotionCodeID INT(11) NOT NULL
);

ALTER TABLE Reservations ADD FOREIGN KEY (CustomerID)
REFERENCES Customers(CustomerID);

ALTER TABLE Rooms ADD FOREIGN KEY (ReservationID)
REFERENCES Reservations(ReservationID);

ALTER TABLE Rooms ADD FOREIGN KEY (BillingStatementID)
REFERENCES BillingStatements(BillingStatementID);

ALTER TABLE RoomRates ADD FOREIGN KEY (RoomID)
REFERENCES Rooms(RoomID);

ALTER TABLE RoomsAmenities ADD FOREIGN KEY (AmenityID)
REFERENCES Amenities(AmenityID);

ALTER TABLE RoomsAmenities ADD FOREIGN KEY (RoomID)
REFERENCES Rooms(RoomID);

ALTER TABLE BillingStatementsAddOns ADD FOREIGN KEY (BillingStatementID)
REFERENCES BillingStatements(BillingStatementID);

ALTER TABLE BillingStatementsAddOns ADD FOREIGN KEY (AddOnID)
REFERENCES AddOns(AddOnID);

ALTER TABLE Guests ADD FOREIGN KEY (ReservationID)
REFERENCES Reservations(ReservationID);

ALTER TABLE ReservationsPromotionCodes ADD FOREIGN KEY (ReservationID)
REFERENCES Reservations(ReservationID);

ALTER TABLE ReservationsPromotionCodes ADD FOREIGN KEY (PromotionCodeID)
REFERENCES PromotionCodes(PromotionCodeID);
	