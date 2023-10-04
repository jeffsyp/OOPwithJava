# OOPwithJava

This is a simple software to manage an event calendar, which is used to schedule the events held at different campus locations listed below.

HLL114, Hill Center, Busch ARC103, Allison Road Classroom, Busch BE_AUD, Beck Hall, Livingston TIL232, Tillett Hall, Livingston AB2225, Academic Building, College Avenue MU302, Murray Hall, College Avenue

The software allows the users to book a room for an event or cancel an event on the calendar. The software is able to display the event calendar sorted by event date/time, by campus/building/room, or by the hosting department. Let’s assume the event calendar only opens to five departments: CS (computer science), EE (electrical engineering), ITI (information technology and informatics), MATH (mathematics) and BAIT (business analytics and information technology).

The software is an interactive system where the users enter command lines on the console/terminal, and the system immediately generates responses and output the results on the console/terminal. That is, when the user hits the enter key, the system reads the data entered, process the data, and immediately output the results. A command line always begins with a command and followed by additional data tokens delimited by one or more spaces. Commands are in uppercase letter(s) and case-sensitive, which means the commands in lowercase letters are invalid. Below is a list of commands.

• A command; to add an event to the event calendar. To book a timeslot on the calendar, an event shall include the event date, timeslot, location, contact and the duration of the event. Below is an example of a command line for adding an event to the calendar. You can assume that the user will always enter enough data tokens in the order shown below.

A 2/29/2024 afternoon HLL114 CS cs@rutgers.edu 60

The above command line starts with the A command, followed by a future event date, timeslot, location, contact and duration in minutes. The date shall be given in mm/dd/yyyy format, and the date should be within 6 months from today’s date. All the campus locations provide three timeslots daily: 10:30am, 2:00pm and 6:30pm. A timeslot is entered as either “morning”, “afternoon”, or “evening”. A location is entered in acronym listed, for example, HIL114. A contact of an event includes the hosting department and an email address. A department name is entered in acronym listed, for example, CS. The email address must have the domain name @rutgers.edu. The event duration is a positive integer representing the number of minutes. The duration is at least 30 minutes and at most 120 minutes. The command is case-sensitive as mentioned above, however, all data tokens in the same command line are NOT case-sensitive. This software checks the following conditions before adding an event to the calendar.

An event is not a valid calendar date.
An event date is not a future date.
An event date is more than 6 months away from today’s date.
An invalid timeslot.
A location that is not one of the six locations listed.
A department name that is not one of the five departments listed.
An invalid email address containing the wrong format or wrong domain name.
Conflict of schedule - an event with the same date/timeslot/location is already on the calendar.
• R command, to cancel an event and remove the specified event from the calendar, for example,

R 12/22/2023 MORNING HLL114

You must check if the date is valid. Refer to the sample output for the messages to display.

• P command, to display the event calendar on the console/terminal, with the current order in the array.

• PE command, to display the event calendar on the console/terminal, sorted by the event date and timeslot of the timeslot. That is, if two events have the same date, display the events in the order of the timeslots.

• PC command, to display the event calendar on the console/terminal, sorted by campus and building/room. That is, if two events are held on the same campus, display the events in the order of building/room number.

• PD command, to display the event calendar on the console/terminal, sorted by the department in the contact. If two events have the same hosting department, the order doesn’t matter.

• Q command, to stop the execution of the software and display " Event Organizer terminated."
