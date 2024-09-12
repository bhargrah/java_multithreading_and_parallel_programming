# Joins

- Inner Join: Only matched rows.
    - An inner join returns only the rows that have matching values in both tables. If there is no match, the row is not
      included in the result set.
      ```
      SELECT Employees.Name, Departments.DeptName
      FROM Employees
      INNER JOIN Departments
      ON Employees.DeptID = Departments.DeptID;
      
      -- Only rows with matching DeptID in both tables are included.
      ```

- Left Join: All rows from the left table, with matched rows from the right.
    - A left join returns all rows from the left table and the matched rows from the right table. If no match is found,
      NULL values are returned for columns from the right table.
        ```
        SELECT Employees.Name, Departments.DeptName
        FROM Employees
        LEFT JOIN Departments
        ON Employees.DeptID = Departments.DeptID;
      
        -- Here, all employees are returned, even if they don't belong to any department.
        ```

- Right Join: All rows from the right table, with matched rows from the left.
    - A right join is the opposite of a left join; it returns all rows from the right table and the matched rows from
      the left table. If no match is found, NULL values are returned for columns from the left table.
        ```
        SELECT Employees.Name, Departments.DeptName
        FROM Employees
        RIGHT JOIN Departments
        ON Employees.DeptID = Departments.DeptID;
      
        -- Here, all departments are returned, even if they don't have any employees.
        ```

- Full Join: All rows from both tables, with matched rows combined.
    - A full join returns all rows when there is a match in either the left or right table. If there is no match, NULL
      values are returned for columns where there is no match.
         ```
         SELECT Employees.Name, Departments.DeptName
         FROM Employees
         FULL OUTER JOIN Departments
         ON Employees.DeptID = Departments.DeptID;
       
         -- This join returns all records when there is a match in either table.
         ```
- Cross Join: All possible combinations of rows.
    - A full join returns all rows when there is a match in either the left or right table. If there is no match,
      NULL
      values are returned for columns where there is no match.
     ```
      SELECT Employees.Name, Departments.DeptName
      FROM Employees
      CROSS JOIN Departments;
  
      -- This join returns every possible pairing of rows from Employees and Departments.
     ```

- Self Join: A table joined with itself.
    - A self join is a regular join, but the table is joined with itself. It’s often used to find related data within
      the same table.
     ```
    SELECT E1.Name AS Employee, E2.Name AS Manager
    FROM Employees E1
    JOIN Employees E2
    ON E1.ManagerID = E2.EmployeeID;

    -- This join helps find the relationships between employees and their managers within the same table.
     ```