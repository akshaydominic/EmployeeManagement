# EmployeeManagement

1. Employee registration page \
  a. Fields: first_name, last_name, salary, department, position, email_address,
     contact_number, picture(option for selecting a file). All fields are mandatory.

2. Employees listing page.\
   a. List the employees by their names & pictures.\
   b. Users should be able to do the following operations from this page i.e.
edit & delete the employee.

Create 2 rest api’s for applying filter & pagination:\
  1. Filter api will fetch the list of employees by either department or position or
     pagination_limit. Send department, position, pagination_limit as request body.\
  2. Pagination api will fetch the N number of records. Send the number of
     records (N) as input as a request parameter.\
