
create table users
(
    id int auto_increment primary key,
    name varchar(200)
);

create table airlines
(
    id int auto_increment primary key,
    name varchar(200)
);

create table flights
(
    id int auto_increment primary key,
    name varchar(200),
    airline_id int,
    foreign key (airline_id) references airlines(id)
);

drop table seats ;

create table seats
(
    id int primary key,
    name varchar(50),
    flight_id int,
    user_id int,
    foreign key (flight_id) references flights(id),
    foreign key (user_id) references users(id)
);

INSERT INTO users (id, name) VALUES
                                 (1, 'John Doe'),
                                 (2, 'Jane Smith'),
                                 (3, 'Michael Johnson'),
                                 (4, 'Emily Brown'),
                                 (5, 'Christopher Lee'),
                                 (6, 'Jessica Davis'),
                                 (7, 'Matthew Wilson'),
                                 (8, 'Jennifer Taylor'),
                                 (9, 'Daniel Martinez'),
                                 (10, 'Sarah Anderson'),
                                 (11, 'David Thomas'),
                                 (12, 'Olivia Harris'),
                                 (13, 'James Clark'),
                                 (14, 'Emma Lewis'),
                                 (15, 'Robert Walker'),
                                 (16, 'Ava Hall'),
                                 (17, 'William Young'),
                                 (18, 'Sophia White'),
                                 (19, 'Joseph Martin'),
                                 (20, 'Chloe Allen'),
                                 (21, 'Charles Garcia'),
                                 (22, 'Madison King'),
                                 (23, 'Andrew Wright'),
                                 (24, 'Abigail Hill'),
                                 (25, 'Anthony Scott'),
                                 (26, 'Elizabeth Green'),
                                 (27, 'Ryan Baker'),
                                 (28, 'Grace Adams'),
                                 (29, 'Nicholas Nelson'),
                                 (30, 'Victoria Mitchell'),
                                 (31, 'Matthew Perez'),
                                 (32, 'Alyssa Roberts'),
                                 (33, 'Joshua Turner'),
                                 (34, 'Natalie Carter'),
                                 (35, 'Kevin Phillips'),
                                 (36, 'Lauren Evans'),
                                 (37, 'Justin Sanchez'),
                                 (38, 'Hannah Rivera'),
                                 (39, 'Brandon Cooper'),
                                 (40, 'Zoe Reed'),
                                 (41, 'Jason Torres'),
                                 (42, 'Mia Ortiz'),
                                 (43, 'Brian Morales'),
                                 (44, 'Sophie Russell'),
                                 (45, 'Samuel Coleman'),
                                 (46, 'Lily Peterson'),
                                 (47, 'Tyler Gray'),
                                 (48, 'Avery Bell'),
                                 (49, 'Alexander Diaz'),
                                 (50, 'Ella Jenkins'),
                                 (51, 'Benjamin Hughes'),
                                 (52, 'Charlotte Coleman'),
                                 (53, 'Gabriel Washington'),
                                 (54, 'Makayla Long'),
                                 (55, 'Christopher Foster'),
                                 (56, 'Samantha Brooks'),
                                 (57, 'Caleb Ward'),
                                 (58, 'Avery Fisher'),
                                 (59, 'Austin Reed'),
                                 (60, 'Katherine Price'),
                                 (61, 'Logan Butler'),
                                 (62, 'Madeline Ross'),
                                 (63, 'Justin Richardson'),
                                 (64, 'Sydney Patterson'),
                                 (65, 'Dylan Howard'),
                                 (66, 'Brooklyn Cooper'),
                                 (67, 'Jordan Stewart'),
                                 (68, 'Claire Perry'),
                                 (69, 'Aaron Jenkins'),
                                 (70, 'Isabella Ward'),
                                 (71, 'Nathan Rivera'),
                                 (72, 'Haley Campbell'),
                                 (73, 'Christian Bryant'),
                                 (74, 'Morgan Mitchell'),
                                 (75, 'Juan Cox'),
                                 (76, 'Taylor Ward'),
                                 (77, 'Blake Murphy'),
                                 (78, 'Alexandra Gonzales'),
                                 (79, 'Cameron Taylor'),
                                 (80, 'Paige Brooks'),
                                 (81, 'Adrian Powell'),
                                 (82, 'Savannah James'),
                                 (83, 'Zachary Evans'),
                                 (84, 'Julia Coleman'),
                                 (85, 'Kyle Russell'),
                                 (86, 'Leah Barnes'),
                                 (87, 'Connor Wright'),
                                 (88, 'Rebecca Allen'),
                                 (89, 'Lucas Green'),
                                 (90, 'Kennedy Stewart'),
                                 (91, 'Mason Flores'),
                                 (92, 'Isabelle Ramirez'),
                                 (93, 'Bryan King'),
                                 (94, 'Hailey Watson'),
                                 (95, 'Gabriel Adams'),
                                 (96, 'Makayla Lopez'),
                                 (97, 'Evan Sanders'),
                                 (98, 'Destiny Cox'),
                                 (99, 'Jordan Hughes'),
                                 (100, 'Amanda Martinez'),
                                 (101, 'Adam Bailey'),
                                 (102, 'Gabriella Henderson'),
                                 (103, 'Jaden Parker'),
                                 (104, 'Nicole Edwards'),
                                 (105, 'Carter Richardson'),
                                 (106, 'Maria Howard'),
                                 (107, 'Jack Kelly'),
                                 (108, 'Riley Wood'),
                                 (109, 'Dylan Price'),
                                 (110, 'Maya Murphy'),
                                 (111, 'Brayden Nelson'),
                                 (112, 'Vanessa Thomas'),
                                 (113, 'Owen Cooper'),
                                 (114, 'Kylie Jackson'),
                                 (115, 'Tristan Ward'),
                                 (116, 'Faith Morgan'),
                                 (117, 'Dominic Martinez'),
                                 (118, 'Allison Brooks'),
                                 (119, 'Colton Gray'),
                                 (120, 'Leah Foster');


INSERT INTO airlines (id, name) VALUES
                                    (1, 'Delta Airlines'),
                                    (2, 'United Airlines'),
                                    (3, 'American Airlines'),
                                    (4, 'Southwest Airlines'),
                                    (5, 'Lufthansa');

INSERT INTO flights (id, name, airline_id) VALUES
                                               (1, 'DA 001', 1),
                                               (2, 'UA 002', 2),
                                               (3, 'AA 003', 3),
                                               (4, 'SW 004', 4),
                                               (5, 'LU 005', 5),
                                               (6, 'DA 006', 1),
                                               (7, 'UA 007', 2),
                                               (8, 'AA 008', 3),
                                               (9, 'SW 009', 4),
                                               (10, 'LU 010', 5);



INSERT INTO seats (id, name, flight_id) VALUES
                                            ('1', '1A', 1), ('2', '1B', 1), ('3', '1C', 1), ('4', '1D', 1), ('5', '1E', 1), ('6', '1F', 1),
                                            ('7', '2A', 1), ('8', '2B', 1), ('9', '2C', 1), ('10', '2D', 1), ('11', '2E', 1), ('12', '2F', 1),
                                            ('13', '3A', 1), ('14', '3B', 1), ('15', '3C', 1), ('16', '3D', 1), ('17', '3E', 1), ('18', '3F', 1),
                                            ('19', '4A', 1), ('20', '4B', 1), ('21', '4C', 1), ('22', '4D', 1), ('23', '4E', 1), ('24', '4F', 1),
                                            ('25', '5A', 1), ('26', '5B', 1), ('27', '5C', 1), ('28', '5D', 1), ('29', '5E', 1), ('30', '5F', 1),
                                            ('31', '6A', 1), ('32', '6B', 1), ('33', '6C', 1), ('34', '6D', 1), ('35', '6E', 1), ('36', '6F', 1),
                                            ('37', '7A', 1), ('38', '7B', 1), ('39', '7C', 1), ('40', '7D', 1), ('41', '7E', 1), ('42', '7F', 1),
                                            ('43', '8A', 1), ('44', '8B', 1), ('45', '8C', 1), ('46', '8D', 1), ('47', '8E', 1), ('48', '8F', 1),
                                            ('49', '9A', 1), ('50', '9B', 1), ('51', '9C', 1), ('52', '9D', 1), ('53', '9E', 1), ('54', '9F', 1),
                                            ('55', '10A', 1), ('56', '10B', 1), ('57', '10C', 1), ('58', '10D', 1), ('59', '10E', 1), ('60', '10F', 1),
                                            ('61', '11A', 1), ('62', '11B', 1), ('63', '11C', 1), ('64', '11D', 1), ('65', '11E', 1), ('66', '11F', 1),
                                            ('67', '12A', 1), ('68', '12B', 1), ('69', '12C', 1), ('70', '12D', 1), ('71', '12E', 1), ('72', '12F', 1),
                                            ('73', '13A', 1), ('74', '13B', 1), ('75', '13C', 1), ('76', '13D', 1), ('77', '13E', 1), ('78', '13F', 1),
                                            ('79', '14A', 1), ('80', '14B', 1), ('81', '14C', 1), ('82', '14D', 1), ('83', '14E', 1), ('84', '14F', 1),
                                            ('85', '15A', 1), ('86', '15B', 1), ('87', '15C', 1), ('88', '15D', 1), ('89', '15E', 1), ('90', '15F', 1),
                                            ('91', '16A', 1), ('92', '16B', 1), ('93', '16C', 1), ('94', '16D', 1), ('95', '16E', 1), ('96', '16F', 1),
                                            ('97', '17A', 1), ('98', '17B', 1), ('99', '17C', 1), ('100', '17D', 1), ('101', '17E', 1), ('102', '17F', 1),
                                            ('103', '18A', 1), ('104', '18B', 1), ('105', '18C', 1), ('106', '18D', 1), ('107', '18E', 1), ('108', '18F', 1),
                                            ('109', '19A', 1), ('110', '19B', 1), ('111', '19C', 1), ('112', '19D', 1), ('113', '19E', 1), ('114', '19F', 1),
                                            ('115', '20A', 1), ('116', '20B', 1), ('117', '20C', 1), ('118', '20D', 1), ('119', '20E', 1), ('120', '20F', 1);

UPDATE seats set user_id = NULL ;

commit;

SELECT *  from seats s where user_id is null order by id asc limit 1;

SELECT * FROM seats WHERE user_id is null order by id LIMIT 1 FOR UPDATE SKIP LOCKED;

select count(*) from seats where user_id is NULL ;

SELECT s.id,s.name
FROM seats s
WHERE s.user_id IS  NULL;

SELECT s.id,s.name, u.name
FROM seats s
         JOIN users u ON s.user_id = u.id
WHERE s.user_id IS NOT NULL;

show variables where variable_name in ('transaction_isolation','autocommit');

show variables where variable_name like '%autocommit%'
set global transaction_read_only=off;
set global autocommit=OFF;
