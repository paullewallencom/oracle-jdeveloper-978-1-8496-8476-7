
CREATE OR REPLACE PACKAGE test_pkg
AS
/******************************************************************************
   NAME:       TEST_PKG
   PURPOSE:

   REVISIONS:
   Ver        Date        Author              Description
   ---------  ----------  ---------------     ---------------------------------
   1.0        05/12/11    Nick Haralabidis    Original
******************************************************************************/
   PROCEDURE test_proc_in (employeeid IN NUMBER);

   PROCEDURE test_proc_in_out (employeeid IN OUT NUMBER);

   FUNCTION test_func_in (managerid IN NUMBER)
      RETURN VARCHAR2;

   FUNCTION test_func_in_out (managerlastname IN OUT VARCHAR2)
      RETURN VARCHAR2;
END test_pkg;
/


CREATE OR REPLACE PACKAGE BODY test_pkg
AS
/******************************************************************************
   NAME:       TEST_PKG
   PURPOSE:

   REVISIONS:
   Ver        Date        Author              Description
   ---------  ----------  ---------------     ---------------------------------
   1.0        05/12/11    Nick Haralabidis    Original
******************************************************************************/
   PROCEDURE test_proc_in (employeeid IN NUMBER)
   IS
   BEGIN
      NULL;
   END;

   PROCEDURE test_proc_in_out (employeeid IN OUT NUMBER)
   IS
   BEGIN
      SELECT manager_id
        INTO employeeid
        FROM employees
       WHERE employee_id = employeeid;
   END;

   FUNCTION test_func_in (managerid IN NUMBER)
      RETURN VARCHAR2
   IS
      managerlastname   employees.last_name%TYPE;
   BEGIN
      SELECT last_name
        INTO managerlastname
        FROM employees
       WHERE employee_id = managerid;

      RETURN managerlastname;
   END;

   FUNCTION test_func_in_out (managerlastname IN OUT VARCHAR2)
      RETURN VARCHAR2
   IS
      email   employees.email%TYPE;
   BEGIN
      SELECT email, first_name
        INTO email, managerlastname
        FROM employees
       WHERE last_name = managerlastname;

      RETURN email;
   END;
END test_pkg;
/


