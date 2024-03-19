# Voyagers

Voyagers is a Java application designed to manage trips efficiently through a command-line interface.

## Introduction

Voyagers is a Java application designed to simplify trip management. It provides users with a command-line interface to manage their trips efficiently, including adding main trips, setting trip details, listing all trips, and more.

## Features

- **Add Main Trip**: Add a new main trip with specified details such as name, dates, location, and description.
- **Delete Main Trip**: Remove an existing main trip from the list.
- **Set Trip Details**: Modify trip details such as name, dates, location, and description for existing trips.
- **List All Trips**: View a comprehensive list of all trips along with their details.

## Usage

### Adding a Main Trip

To add a new main trip, use the `addmaintrip` command followed by the trip details.

```bash
addmaintrip /n <Name> /start <Start Date> /end <End Date> /location <Location> /d <Description>
```

### Setting Trip Details

You can modify the details of an existing trip using the following commands:

- `setname`: Set the name of a trip.
- `setdates`: Set the dates of a trip.
- `setlocation`: Set the location of a trip.
- `setdescription`: Set the description of a trip.

Example:

```bash
setname /old Europe Trip /new European Adventure
```

```bash
setdates /n Europe Trip /start 2024/05/01 /end 2024/07/31
```

### Deleting a Main Trip
To delete an existing main trip, use the `deletemaintrip` command followed by the trip name.

```bash
deletemaintrip /n Europe Trip
```
### Listing All Trips
To view a list of all trips along with their details, use the listall command.

```bash 
listall
```

### Enter "exit" to exit the application.

```bash 
exit
```