CREATE TABLE tb_department(
                              department_id NUMBER PRIMARY KEY,
                              name VARCHAR2(20)
);

CREATE SEQUENCE department_seq
    INCREMENT BY 1;

CREATE TRIGGER  insert_department
    BEFORE INSERT
    ON tb_department
    FOR EACH ROW
BEGIN
    SELECT department_seq.NEXTVAL INTO :NEW.department_id FROM DUAL;
END;


CREATE TABLE tb_employee(
                            employee_id NUMBER PRIMARY KEY,
                            name VARCHAR2(100),
                            title VARCHAR2(100),
                            salary NUMBER(8, 2),
                            manager NUMBER REFERENCES  tb_employee,
                            department NUMBER REFERENCES tb_department

);

CREATE SEQUENCE employee_seq
    INCREMENT BY 1;

CREATE TRIGGER  insert_employee
    BEFORE INSERT
    ON tb_employee
    FOR EACH ROW
BEGIN
    SELECT employee_seq.NEXTVAL INTO :NEW.employee_id FROM DUAL;
END;