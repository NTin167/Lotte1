-- INSERT IGNORE INTO roles(name) VALUES('ROLE_USER');
-- INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN');

INSERT  INTO `lotte`.`account` (`id`, `password`, `role`, `status`, `username`) VALUES ('2', '123456', 'ROLEUSER', '1', 'viettin');

INSERT INTO `lotte`.`nguyen_lieu` (`id`, `name`, `price`, `stock`, `unit`) VALUES ('1', 'cá', '121', '0', 'kg');
INSERT INTO `lotte`.`nguyen_lieu` (`id`, `name`, `price`, `stock`, `unit`) VALUES ('2', 'thịt', '323', '0', 'kg');
INSERT INTO `lotte`.`nguyen_lieu` (`id`, `name`, `price`, `stock`, `unit`) VALUES ('3', 'gạo', '424', '0', 'kg');

INSERT INTO `lotte`.`nha_cung_cap` (`id`, `address`, `name`, `phone_number`) VALUES ('1', 'man thiện', 'VIETTEL', '123123');
INSERT INTO `lotte`.`nha_cung_cap` (`id`, `address`, `name`, `phone_number`) VALUES ('2', 'TNP', 'PTUT', '12313');
INSERT INTO `lotte`.`nha_cung_cap` (`id`, `address`, `name`, `phone_number`) VALUES ('3', 'Hiệp phú', 'PTIT', '444444');

INSERT INTO `lotte`.`employees` (`id`, `address`, `gender`, `name`, `phone_number`, `account_id`) VALUES ('1', 'man thiện', 'nam', 'A', '1231232', '1');
INSERT INTO `lotte`.`employees` (`id`, `address`, `gender`, `name`, `phone_number`, `account_id`) VALUES ('2', 'man thiện', 'nữ', 'B', '1412412421', '2');
INSERT INTO `lotte`.`employees` (`id`, `address`, `gender`, `name`, `phone_number`, `account_id`) VALUES ('3', 'TNP', 'nam', 'C', '125412412', '3');


INSERT INTO `lotte`.`khach_hang` (`id`, `address`, `gender`, `name`, `total_point`) VALUES ('1', 'man thieejn', 'nam', 'TTT', '12');
INSERT INTO `lotte`.`khach_hang` (`id`, `address`, `gender`, `name`, `total_point`) VALUES ('2', 'TNP', 'nu', 'abx', '444');

