create database hostel_dev;

create user 'hostel_dev_user'@localhost identified by 'password';

GRANT SELECT ON hostel_dev.* to 'hostel_dev_user'@localhost;
GRANT INSERT ON hostel_dev.* to 'hostel_dev_user'@localhost;
GRANT UPDATE ON hostel_dev.* to 'hostel_dev_user'@localhost;
GRANT DELETE ON hostel_dev.* to 'hostel_dev_user'@localhost;
