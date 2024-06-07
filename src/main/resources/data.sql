INSERT INTO url (id, name, url) VALUES
                                    (1,'onewin', 'https://1wmakv.life/casino/list?open=register#5ver'),
                                    (2,'telegram', 'https://t.me/fartfloy'),
                                    (3,'support', 'https://t.me/Forsakenbb'),
                                    (4,'telegrameng', 'https://t.me/thousanddaily1'),
                                    (5,'onewineng', 'https://1wnurc.com/?open=register#fzxb'),
                                    (6,'ip','https://minespredictbot.ru:8080')
    ON CONFLICT (name) DO NOTHING;
INSERT INTO promo (id, name, promo) VALUES
                                        (1,'onewin', 'VIP515'),
                                        (2,'onewineng', 'VIP255')
    ON CONFLICT (name) DO NOTHING;
INSERT INTO token (id, name, token) VALUES
                                        (1,'postback', '7029233296:AAEajxS5NVhXILwa7GjFG1qvL1D9Gkk0aMc'),
                                        (2,'chatid', '741164095')
    ON CONFLICT (name) DO NOTHING;