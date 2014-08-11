CREATE TABLE `Task` (
   `id` bigint(20) not null auto_increment,
   `done` bit(1),
   `due` datetime,
   `text` varchar(255),
   `deleted` bit(1),
   PRIMARY KEY (`id`)
) 