ALTER TABLE `user_role` DROP FOREIGN KEY `FKhjx9nk20h4mo745tdqj8t8n9d`;
ALTER TABLE `user_role`
    ADD CONSTRAINT `FKhjx9nk20h4mo745tdqj8t8n9d` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_email`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `user_role` DROP FOREIGN KEY `FKka3w3atry4amefp94rblb52n7`;
ALTER TABLE `user_role`
    ADD CONSTRAINT `FKka3w3atry4amefp94rblb52n7` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_name`) ON DELETE CASCADE ON UPDATE CASCADE;
