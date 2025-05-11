--VERSION MODIFICADA (Para que sea compatible con mi base de datos de oracle) 

  -- REGIONS
CREATE TABLE regions (
    region_id NUMBER PRIMARY KEY,
    region_name VARCHAR2(50) NOT NULL
);

CREATE SEQUENCE regions_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_regions_id
BEFORE INSERT ON regions
FOR EACH ROW
BEGIN
    IF :NEW.region_id IS NULL THEN
        :NEW.region_id := regions_seq.NEXTVAL;
    END IF;
END;
/

-- COUNTRIES
CREATE TABLE countries (
    country_id CHAR(2) PRIMARY KEY,
    country_name VARCHAR2(40) NOT NULL,
    region_id NUMBER,
    CONSTRAINT fk_countries_regions FOREIGN KEY (region_id)
        REFERENCES regions(region_id)
        ON DELETE CASCADE
);

-- LOCATIONS
CREATE TABLE locations (
    location_id NUMBER PRIMARY KEY,
    address VARCHAR2(255) NOT NULL,
    postal_code VARCHAR2(20),
    city VARCHAR2(50),
    state VARCHAR2(50),
    country_id CHAR(2),
    CONSTRAINT fk_locations_countries FOREIGN KEY (country_id)
        REFERENCES countries(country_id)
        ON DELETE CASCADE
);

CREATE SEQUENCE locations_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_locations_id
BEFORE INSERT ON locations
FOR EACH ROW
BEGIN
    IF :NEW.location_id IS NULL THEN
        :NEW.location_id := locations_seq.NEXTVAL;
    END IF;
END;
/

-- WAREHOUSES
CREATE TABLE warehouses (
    warehouse_id NUMBER PRIMARY KEY,
    warehouse_name VARCHAR2(255),
    location_id NUMBER,
    CONSTRAINT fk_warehouses_locations FOREIGN KEY (location_id)
        REFERENCES locations(location_id)
        ON DELETE CASCADE
);

CREATE SEQUENCE warehouses_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_warehouses_id
BEFORE INSERT ON warehouses
FOR EACH ROW
BEGIN
    IF :NEW.warehouse_id IS NULL THEN
        :NEW.warehouse_id := warehouses_seq.NEXTVAL;
    END IF;
END;
/

-- EMPLOYEES
CREATE TABLE employees (
    employee_id NUMBER PRIMARY KEY,
    first_name VARCHAR2(255) NOT NULL,
    last_name VARCHAR2(255) NOT NULL,
    email VARCHAR2(255) NOT NULL,
    phone VARCHAR2(50) NOT NULL,
    hire_date DATE NOT NULL,
    manager_id NUMBER,
    job_title VARCHAR2(255) NOT NULL,
    CONSTRAINT fk_employees_manager FOREIGN KEY (manager_id)
        REFERENCES employees(employee_id)
        ON DELETE CASCADE
);

CREATE SEQUENCE employees_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_employees_id
BEFORE INSERT ON employees
FOR EACH ROW
BEGIN
    IF :NEW.employee_id IS NULL THEN
        :NEW.employee_id := employees_seq.NEXTVAL;
    END IF;
END;
/

-- PRODUCT_CATEGORIES
CREATE TABLE product_categories (
    category_id NUMBER PRIMARY KEY,
    category_name VARCHAR2(255) NOT NULL
);

CREATE SEQUENCE product_categories_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_product_categories_id
BEFORE INSERT ON product_categories
FOR EACH ROW
BEGIN
    IF :NEW.category_id IS NULL THEN
        :NEW.category_id := product_categories_seq.NEXTVAL;
    END IF;
END;
/

-- PRODUCTS
CREATE TABLE products (
    product_id NUMBER PRIMARY KEY,
    product_name VARCHAR2(255) NOT NULL,
    description VARCHAR2(2000),
    standard_cost DECIMAL(9, 2),
    list_price DECIMAL(9, 2),
    category_id NUMBER NOT NULL,
    CONSTRAINT fk_products_categories FOREIGN KEY (category_id)
        REFERENCES product_categories(category_id)
        ON DELETE CASCADE
);

CREATE SEQUENCE products_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_products_id
BEFORE INSERT ON products
FOR EACH ROW
BEGIN
    IF :NEW.product_id IS NULL THEN
        :NEW.product_id := products_seq.NEXTVAL;
    END IF;
END;
/

-- CUSTOMERS
CREATE TABLE customers (
    customer_id NUMBER PRIMARY KEY,
    name VARCHAR2(255) NOT NULL,
    address VARCHAR2(255),
    website VARCHAR2(255),
    credit_limit DECIMAL(8, 2)
);

CREATE SEQUENCE customers_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_customers_id
BEFORE INSERT ON customers
FOR EACH ROW
BEGIN
    IF :NEW.customer_id IS NULL THEN
        :NEW.customer_id := customers_seq.NEXTVAL;
    END IF;
END;
/

-- CONTACTS
CREATE TABLE contacts (
    contact_id NUMBER PRIMARY KEY,
    first_name VARCHAR2(255) NOT NULL,
    last_name VARCHAR2(255) NOT NULL,
    email VARCHAR2(255) NOT NULL,
    phone VARCHAR2(20),
    customer_id NUMBER,
    CONSTRAINT fk_contacts_customers FOREIGN KEY (customer_id)
        REFERENCES customers(customer_id)
        ON DELETE CASCADE
);

CREATE SEQUENCE contacts_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_contacts_id
BEFORE INSERT ON contacts
FOR EACH ROW
BEGIN
    IF :NEW.contact_id IS NULL THEN
        :NEW.contact_id := contacts_seq.NEXTVAL;
    END IF;
END;
/

-- ORDERS
CREATE TABLE orders (
    order_id NUMBER PRIMARY KEY,
    customer_id NUMBER NOT NULL,
    status VARCHAR2(20) NOT NULL,
    salesman_id NUMBER,
    order_date DATE NOT NULL,
    CONSTRAINT fk_orders_customers FOREIGN KEY (customer_id)
        REFERENCES customers(customer_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_orders_employees FOREIGN KEY (salesman_id)
        REFERENCES employees(employee_id)
        ON DELETE SET NULL
);

CREATE SEQUENCE orders_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_orders_id
BEFORE INSERT ON orders
FOR EACH ROW
BEGIN
    IF :NEW.order_id IS NULL THEN
        :NEW.order_id := orders_seq.NEXTVAL;
    END IF;
END;
/

-- ORDER_ITEMS
CREATE TABLE order_items (
    order_id NUMBER,
    item_id NUMBER,
    product_id NUMBER NOT NULL,
    quantity DECIMAL(8, 2) NOT NULL,
    unit_price DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY(order_id, item_id),
    CONSTRAINT fk_order_items_products FOREIGN KEY (product_id)
        REFERENCES products(product_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_order_items_orders FOREIGN KEY (order_id)
        REFERENCES orders(order_id)
        ON DELETE CASCADE
);

-- INVENTORIES
CREATE TABLE inventories (
    product_id NUMBER,
    warehouse_id NUMBER,
    quantity NUMBER NOT NULL,
    PRIMARY KEY(product_id, warehouse_id),
    CONSTRAINT fk_inventories_products FOREIGN KEY (product_id)
        REFERENCES products(product_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_inventories_warehouses FOREIGN KEY (warehouse_id)
        REFERENCES warehouses(warehouse_id)
        ON DELETE CASCADE
);
