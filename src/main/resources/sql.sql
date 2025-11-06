CREATE TABLE `volunteer`
(
    `Id`            INT         NOT NULL,
    `Name` VARCHAR(50) NOT NULL,
    `CityId`       INT     NOT NULL,
    `DateOfBirth`   DATE    NOT NULL,
    `Gender`        BIT     NOT NULL,
    PRIMARY KEY (`Id`),
    FOREIGN KEY (`CityId`) REFERENCES city(`CityId`)
);
