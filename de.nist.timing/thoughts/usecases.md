#### What are the use cases?

  - As a user, I want to create an account where I can
    - my user details (name, e-mail, etc.) 
    - specify the amount of working hours per day or week
    - the location for the official holidays etc.
    - my special leave types

  - As a user, I create working day entries which consist of
    - date and time (year, month, day, hour, minute)
    - type of entry (start, end, pause start, pause end)

  - As a user, I create whole-day entries, which consist of 
    - date (year, month, day)
    - type of whole-day entry (vacation, sick, business trip, fair, training, workshop, etc.)

  - As a user, I want to create range-entries, which consist of
    - start date (year, month, day)
    - end date (year, month, day)
    - type of absence (vacation, sickness, etc.)

  - As a user, I request an Overview, which provides
    - hour account balance
    - calendar with all active entries shown

  - As a user, I request an entry validation, which provides
    - all conflicting, missing, unreasonable entries to fix
 
#### What classes does our domain provide then?
  - Entry 
  - Overview
  - ValidationSummary