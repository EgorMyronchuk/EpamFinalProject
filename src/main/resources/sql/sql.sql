
INSERT INTO books (
    name, photo_url, genre, age_group, price, publication_date,
    author, number_of_pages, characteristics, description, sold_amount,
    language, created_at
) VALUES
-- Фентезі та Пригоди
('Гаррі Поттер і філософський камінь', 'https://images.prom.ua/5401990666_w1280_h640_kniga-garri-potter.jpg', 'Фентезі', 'CHILD', 350.00, '1997-06-26', 'Дж.К. Ролінґ', 320, 'Тверда обкладинка', 'Хлопчик, який вижив, дізнається про свій магічний спадок.', 1500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Гаррі Поттер і Таємна кімната', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/4/2/42_1_302.jpg', 'Фентезі', 'CHILD', 360.00, '1998-07-02', 'Дж.К. Ролінґ', 350, 'А-ба-ба-га-ла-ма-га', 'Таємничі напади в школі Гоґвортс.', 1200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Відьмак: Останнє бажання', 'https://upload.wikimedia.org/wikipedia/uk/0/0d/Ostannie_Bazhannia.jpg', 'Фентезі', 'ADULT', 420.00, '1993-01-01', 'Анджей Сапковський', 384, 'Тиснення на палітурці', 'Перша збірка пригод Геральта.', 2100, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Дюна', 'https://kniga.biz.ua/images/goods/big/34033.jpg', 'Фантастика', 'TEEN', 480.00, '1965-08-01', 'Френк Герберт', 600, 'Тверда палітурка', 'Епічне протистояння за найцінніший ресурс у всесвіті.', 1800, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Гобіт', 'https://book-ye.com.ua/media/catalog/product/cache/79524a38d3bc3d0f3b6015a08841400c/e/3/e3686b6d-83d0-11e6-80c0-000c29ae1566_8a9044c4-ccbc-11ee-8192-00505684ea69.jpg', 'Фентезі', 'CHILD', 310.00, '1937-09-21', 'Дж. Р. Р. Толкін', 304, 'З малюнками автора', 'Подорож Більбо Беггінса до Самотньої гори.', 950, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Сучасна українська література
('Інтернат', 'https://book-ye.com.ua/media/catalog/product/cache/79524a38d3bc3d0f3b6015a08841400c/7/e/7e0834e8-832d-11e7-80cf-000c29ae1566_d7e1ebf8-1333-11ed-8173-0050568ef5e6.jpg', 'Драма', 'ADULT', 320.00, '2017-08-23', 'Сергій Жадан', 336, 'Авторський дизайн', 'Жорстока реальність війни очима вчителя.', 1400, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Музей покинутих секретів', 'https://vivat.com.ua/storage/1.d/files/5/b/5b87cf6f_9786177286454-b.jpg', 'Роман', 'ADULT', 550.00, '2009-12-15', 'Оксана Забужко', 800, 'Тверда обкладинка', 'Сага, що охоплює три покоління української родини.', 600, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Фелікс Австрія', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/1/4/145_1_40.jpg', 'Роман', 'ADULT', 290.00, '2014-01-01', 'Софія Андрухович', 312, 'Лауреат нагороди BBC', 'Станіславів на початку ХХ століття.', 850, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Троща', 'https://upload.wikimedia.org/wikipedia/uk/a/ae/Vasyl_Shkliar_-_Troshcha_%28Palityrka_vyd._2017%2C_UKR%29.jpg', 'Історичний роман', 'ADULT', 330.00, '2017-01-01', 'Василь Шкляр', 416, 'Тверда палітурка', 'Роман про УПА та боротьбу за незалежність.', 720, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Чорний ворон', 'https://detector.media/doc/images/news/archive/2016/112617/ArticleImage_112617.jpg', 'Історичний роман', 'ADULT', 310.00, '2009-01-01', 'Василь Шкляр', 380, 'Класика сучасності', 'Боротьба повстанців Холодного Яру.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Психологія та Саморозвиток
('Атомні звички', 'https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcQya1zNIdXxGNi00KKxrtX31X3hDj8BstEZpK8h7JYyD2YV9cO3iKNddU7Xbn8kGUT4w8GXVdA_FrU9Q2MkktIJX3nIICShMyLMtyBBdluYChbqCZqOrGxQXG2MzTxtlD7b9APqYg&usqp=CAc', 'Психологія', 'ADULT', 340.00, '2018-10-16', 'Джеймс Клір', 304, 'Тверда обкладинка', 'Малі зміни, що призводять до вражаючих результатів.', 4500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Мистецтво пофігізму', 'https://ua-read.com.ua/wp-content/uploads/2025/02/Vytonchene-mystetstvo-zabyvaty-na-vse.png', 'Психологія', 'ADULT', 280.00, '2016-09-13', 'Марк Менсон', 192, 'М''яка обкладинка', 'Як жити щасливо, ігноруючи чужі очікування.', 3200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Людина в пошуках справжнього сенсу', 'https://vivat.com.ua/storage/1.d/files/f/2/f24ec788_1.png', 'Психологія', 'ADULT', 220.00, '1946-01-01', 'Віктор Франкл', 160, 'Кишеньковий формат', 'Досвід виживання в концтаборі.', 1900, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Сапієнс', 'https://static.yakaboo.ua/media/catalog/product/3/7/37_1_31.jpg', 'Історія', 'ADULT', 450.00, '2011-01-01', 'Ювал Ной Харарі', 544, 'Тверда обкладинка', 'Коротка історія людства від минулого до майбутнього.', 2500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Чому ми спимо', 'https://static.yakaboo.ua/media/catalog/product/2/0/201_1_5.jpg', 'Наука', 'ADULT', 390.00, '2017-01-01', 'Меттью Вокер', 400, 'Научпоп', 'Наукове дослідження сну та його важливості.', 800, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- English Classics & Modern
('1984', 'https://images-na.ssl-images-amazon.com/images/I/71kxa1-0mfL.jpg', 'Dystopian', 'ADULT', 250.00, '1949-06-08', 'George Orwell', 328, 'Penguin Classics', 'The ultimate dystopian masterpiece.', 5000, 'ENGLISH', CURRENT_TIMESTAMP),
('The Great Gatsby', 'https://m.media-amazon.com/images/M/MV5BMTkxNTk1ODcxNl5BMl5BanBnXkFtZTcwMDI1OTMzOQ@@._V1_.jpg', 'Classic', 'ADULT', 320.00, '1925-04-10', 'F. Scott Fitzgerald', 180, 'Hardcover', 'Decadence and the American Dream.', 1200, 'ENGLISH', CURRENT_TIMESTAMP),
('To Kill a Mockingbird', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/To_Kill_a_Mockingbird_%28first_edition_cover%29.jpg/250px-To_Kill_a_Mockingbird_%28first_edition_cover%29.jpg', 'Drama', 'TEEN', 380.00, '1960-07-11', 'Harper Lee', 281, 'Modern Classic', 'A story of racial injustice in the South.', 1500, 'ENGLISH', CURRENT_TIMESTAMP),
('The Alchemist', 'https://m.media-amazon.com/images/I/71+2-t7M35L._AC_UF1000,1000_QL80_.jpg', 'Philosophy', 'TEEN', 290.00, '1988-01-01', 'Paulo Coelho', 208, 'International bestseller', 'Follow your dreams.', 4000, 'ENGLISH', CURRENT_TIMESTAMP),

-- Дитяча література
('Тореадори з Васюківки', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/r/r/rrcvdnqrqlnylikfe1mdrxgnomnujwzofp8hndql.jpg', 'Пригоди', 'CHILD', 330.00, '1963-01-01', 'Всеволод Нестайко', 544, 'Ілюстрована', 'Найвеселіші пригоди українських школярів.', 2800, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Чарлі і шоколадна фабрика', 'https://4read.org/uploads/posts/2020-02/1582905210_-shokoladna-fabrika.jpg', 'Казка', 'CHILD', 220.00, '1964-01-17', 'Роальд Дал', 192, 'А-ба-ба-га-ла-ма-га', 'Подорож на магічну фабрику Віллі Вонки.', 1500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Аліса в країні див', 'https://4read.org/uploads/posts/2020-05/1590911330_v-krayin-chudes.jpg', 'Казка', 'CHILD', 260.00, '1865-11-26', 'Льюїс Керролл', 192, 'Тверда палітурка', 'Фантастичний світ за кролячою норою.', 900, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Пеппі Довгапанчоха', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFMqs3FzfJf83DixUDaoKvFyn8TBPvUiEFeg&s', 'Пригоди', 'CHILD', 240.00, '1945-01-01', 'Астрід Ліндгрен', 320, 'Веселі картинки', 'Історія найсильнішої дівчинки у світі.', 1300, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Пригоди Тома Соєра', 'https://sv1.office-centre.com.ua/public/8884b57b-2271-11ea-80e3-0cc47aace875/ab6a0e25537188e0e712972cb80a5672/8884b57b-2271-11ea-80e3-0cc47aace875-0.webp', 'Пригоди', 'CHILD', 250.00, '1876-01-01', 'Марк Твен', 280, 'Шкільна серія', 'Бешкетник Том та його друзі.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Детективи
('Вбивство у Східному експресі', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/1/_/1_271_55.jpg', 'Детектив', 'ADULT', 250.00, '1934-01-01', 'Агата Крісті', 288, 'КСД серія', 'Еркюль Пуаро розгадує таємницю потяга.', 2300, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Шерлок Холмс. Етюд у багряних тонах', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqHubP1nLA-Cb6VdIoe0P79yILsW_G5kr6fA&s', 'Детектив', 'TEEN', 290.00, '1887-11-01', 'Артур Конан Дойл', 256, 'Тверда обкладинка', 'Перша зустріч Холмса та Ватсона.', 1700, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Код да Вінчі', 'https://chitaka.com.ua/wp-content/uploads/2022/02/Kod-da-Vinchi-1.jpg', 'Трилер', 'ADULT', 380.00, '2003-01-01', 'Ден Браун', 600, 'Світовий бестселер', 'Таємниці, сховані у картинах Леонардо.', 3500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Дівчина у потягу', 'https://worldinbooks.com.ua/wp-content/uploads/2022/05/divchyna-u-potyagu-22071941-237x364-1-195x300.jpg', 'Трилер', 'ADULT', 280.00, '2015-01-13', 'Пола Гоукінз', 350, 'Психологічний трилер', 'Вона бачила щось жахливе з вікна.', 2100, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Різне
('Норвезький ліс', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/2/0/204441_0.204441', 'Роман', 'ADULT', 320.00, '1987-09-04', 'Харукі Муракамі', 400, 'Японська проза', 'Меланхолійна історія кохання та втрати.', 900, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Тисяча сіяючих сонць', 'https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcQBxb7Jh9Pi-54_Zf6zAMcpzkicK2Zo73cgt8kOKS5T1ruHptcHeGivhsSqVg70H1JUXBgp2NVvbvy1ikqfIk0UOivYIqI03uvCFYOjZ7X92Or1xSa2GNl9yZc-GnH-qStPrvjKuPQ&usqp=CAc', 'Роман', 'ADULT', 315.00, '2007-05-22', 'Халед Госсейні', 432, 'Драматична історія', 'Життя двох жінок у сучасному Афганістані.', 750, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Книжкова злодійка', 'https://knigogo.top/wp-content/uploads/2018/11/kradijka-knyzhok-21112919-237x365.jpg', 'Драма', 'TEEN', 350.00, '2005-01-01', 'Маркус Зузак', 512, 'Тверда обкладинка', 'Смерть розповідає про дівчинку та книги.', 1200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Сім чоловіків Евелін Г''юґо', 'https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcT71-fUhF5iwTKikvSAQQ-hztk3b5NsE6Ggmy2CFd2cKUvutODRvQZVPYI7EiHHPJ4B_BC7cr6xsKaLGQ29r12m8VKX2hmtlPnd1W06ZIGFrlLCLc8cyUsJ3U8XrwWuFqyzlijBGw&usqp=CAc', 'Роман', 'ADULT', 380.00, '2017-06-13', 'Тейлор Дженкінс Рід', 448, 'Арт-Букс', 'Таємниці голлівудської ікони.', 1800, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Гіпотеза кохання', 'https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcRfm0L2SrZPfAXcysBKE8zJ9GhqKXWddrz2jxeIMIhXzx9QiqVhp6FpjRfc1KLHbQNEQ7LgIFsGUQEP4MTngKMFuff9TuKhs4xFhzElgEfqR4x7caT5b7fU', 'Романтика', 'ADULT', 295.00, '2021-09-14', 'Алі Гейзелвуд', 384, 'Ромком', 'Фейковые стосунки у світі академічної науки.', 2500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Квартира на двох', 'https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcSYnfv7sPMxwqwBJLrZqDtJciUG2GxBnph1iz5CctZUrhi8bUM1LoLZewSGlt8jnBc8Bez_DfenOpHzlhXNcnUbMk5ZG_vJ', 'Романтика', 'ADULT', 270.00, '2019-01-01', 'Бет О’Лірі', 416, 'М''яка палітурка', 'Вони ділять одне ліжко, але ніколи не бачились.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Мистецтво війни', 'https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcQOMV80viDXlh2pHEYt-0NupHlXtcPKpcRxRvulspBI68LteDS5aMAC1_ynEyPHy0Ykur4KsjD1znKZV545kri2Vw__Ubf23tsMYV7I7SaEn79zcOiq_5N-zNNKUkWbKJyOhhHnkw&usqp=CAc', 'Філософія', 'ADULT', 180.00, '2015-01-01', 'Сунь-цзи', 128, 'Подарункове видання', 'Давньокитайський трактат про стратегію.', 3000, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Божественна комедія', 'https://knigogo.top/wp-content/uploads/2018/08/bozhestvenna-komediya-19436269-237x364.jpg', 'Класика', 'ADULT', 650.00, '1320-01-01', 'Данте Аліг’єрі', 608, 'Подарункове видання', 'Подорож крізь Пекло, Чистилище та Рай.', 300, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Фауст', 'https://book-ye.com.ua/media/catalog/product/cache/79524a38d3bc3d0f3b6015a08841400c/8/2/823bb26d-cd6c-11e9-811d-000c29ae1566_0d0d8249-e2c5-11ee-8194-00505684ea69.jpg', 'Класика', 'ADULT', 390.00, '1808-01-01', 'Йоганн Гете', 480, 'Тверда обкладинка', 'Трагедія вченого, що продав душу дияволу.', 250, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Перевтілення', 'https://chitaka.com.ua/wp-content/uploads/2021/11/Perevtilennya.jpg', 'Класика', 'ADULT', 190.00, '1915-01-01', 'Франц Кафка', 120, 'Кишенькова книжка', 'Одного ранку Грегор Замза прокинувся комахою.', 600, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Чужаниця', 'https://ksd.ua/storage/products/covers/medium_x2/qrwxkFTTfmG2fbaOqdqW8796QPCQBlTTpcUhl4t2.png?v=1764582025', 'Філософія', 'ADULT', 210.00, '1942-01-01', 'Альбер Камю', 160, 'Екзистенціалізм', 'Роздуми про абсурд людського існування.', 450, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Три товариші', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/1/4/141_5_2.jpg', 'Класика', 'ADULT', 350.00, '1936-01-01', 'Еріх Марія Ремарк', 480, 'Тверда обкладинка', 'Найкраща книга про справжню дружбу та кохання.', 1200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Джури козака Швайки', 'https://s.ababahalamaha.com.ua/images/800x800/dzhury-kozaka-shvaiky.jpg', 'Пригоди', 'CHILD', 290.00, '2007-01-01', 'Володимир Рутківський', 432, 'А-ба-ба-га-ла-ма-га', 'Початок легендарної трилогії про козаків.', 1900, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Дракони, вперед!', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/6/0/609_5.jpg', 'Казка', 'CHILD', 180.00, '2015-01-01', 'Марина та Сергій Дяченки', 160, 'Кольорова серія', 'Добрі історії про маленьких драконів.', 700, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Мелодія кави у тональності сподівання', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/i/m/img095_1_77.jpg', 'Роман', 'ADULT', 240.00, '2014-01-01', 'Наталія Гурницька', 380, 'Український романс', 'Львівська історія кохання XIX століття.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Смачна країна', 'https://static.yakaboo.ua/media/catalog/product/s/m/smachna_krajina_0.jpg', 'Кулінарія', 'ADULT', 390.00, '2015-01-01', 'Костянтин Грубич', 280, 'Подарункове видання', 'Найкращі рецепти з усіх куточків України.', 400, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Ворота Європи', 'https://content1.rozetka.com.ua/goods/images/big/417702222.jpg', 'Історія', 'ADULT', 380.00, '2015-01-01', 'Сергій Плохій', 400, 'Наукове дослідження', 'Історія України від найдавніших часів.', 1500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Справа Василя Стуса', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/0/2/02_122.jpg', 'Документалістика', 'ADULT', 450.00, '2019-05-01', 'Вахтанг Кіпіані', 688, 'Тверда обкладинка', 'Документи з архіву КДБ про долю поета.', 3500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Кров і попіл', 'https://static.yakaboo.ua/media/catalog/product/e/d/ed32eba05ad9ce5cfa3336ee43a5874a.jpg', 'Фентезі', 'ADULT', 360.00, '2020-03-31', 'Дженніфер Л. Арментраут', 600, 'BookChef серія', 'Світовий бестселер у жанрі ромфант.', 2200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Грокаємо алгоритми', 'https://static.yakaboo.ua/media/catalog/product/b/h/bhargava_final_.png', 'IT-література', 'TEEN', 380.00, '2016-01-01', 'Адитья Бхаргава', 288, 'М''яка обкладинка', 'Просто та наочно про складні алгоритми.', 2600, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- IT-література (UKRAINIAN & ENGLISH)
('Чиста архітектура', 'https://static.yakaboo.ua/media/catalog/product/f/b/fb722076u_chystaya-arhytektura_obl_3d.jpg', 'IT-література', 'ADULT', 550.00, '2017-09-10', 'Роберт Мартін', 432, 'Мистецтво розробки ПЗ', 'Посібник зі структури та дизайну програмного забезпечення.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Design Patterns', 'https://www.pdfiles.net/storage/91358586089519.jpg', 'Computer Science', 'ADULT', 890.00, '1994-10-21', 'Erich Gamma', 395, 'Hardcover', 'Elements of Reusable Object-Oriented Software.', 2000, 'ENGLISH', CURRENT_TIMESTAMP),
('Refactoring', 'https://static.yakaboo.ua/media/catalog/product/9/7/9780134757599_0.jpg', 'Computer Science', 'ADULT', 750.00, '2018-11-20', 'Martin Fowler', 448, 'Technical guide', 'Improving the design of existing code.', 850, 'ENGLISH', CURRENT_TIMESTAMP),
('The Pragmatic Programmer', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/i/m/img482_35.jpg', 'IT', 'ADULT', 820.00, '1999-10-30', 'Andrew Hunt', 352, '20th Anniversary Edition', 'Your journey to mastery.', 1500, 'ENGLISH', CURRENT_TIMESTAMP),

-- Світові мови
('Don Quijote de la Mancha', 'https://m.media-amazon.com/images/I/91CIwR3QU1L._UF1000,1000_QL80_.jpg', 'Classic', 'ADULT', 480.00, '1605-01-01', 'Miguel de Cervantes', 864, 'Tapa dura', 'El ingenioso hidalgo de la Mancha.', 300, 'SPANISH', CURRENT_TIMESTAMP),
('Cien años de soledad', 'https://tienda.fundaciongabo.org/cdn/shop/files/21.png?v=1761065413', 'Realismo Magico', 'ADULT', 410.00, '1967-05-30', 'Gabriel Garcia Marquez', 471, 'Edicion de bolsillo', 'La historia de la familia Buendia.', 500, 'SPANISH', CURRENT_TIMESTAMP),
('Le Petit Prince', 'https://content2.rozetka.com.ua/goods/images/big/265962330.jpg', 'Enfants', 'CHILD', 220.00, '1943-04-01', 'Antoine de Saint-Exupery', 96, 'Francais original', 'Dessine-moi un mouton.', 1800, 'FRENCH', CURRENT_TIMESTAMP),
('Les Miserables', 'https://content.rozetka.com.ua/goods/images/big/224230568.jpg', 'Classique', 'ADULT', 590.00, '1862-01-01', 'Victor Hugo', 1232, 'Edition integrale', 'L''histoire de Jean Valjean.', 400, 'FRENCH', CURRENT_TIMESTAMP),
('Die Verwandlung', 'https://www.diogenes.ch/.imaging/diogenesTitleDetailMedia/titel/franz-kafka/die-verwandlung-9783257235456.jpg', 'Klassik', 'ADULT', 195.00, '1915-10-01', 'Franz Kafka', 100, 'Deutsch', 'Gregor Samsa als Ungeziefer.', 600, 'GERMAN', CURRENT_TIMESTAMP),
('Kokoro', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxftiNZyuzSr3GJq0tKYB84HtzUBdqyyt4tA&s', 'Classic', 'ADULT', 450.00, '1914-01-01', 'Natsume Soseki', 250, 'Japanese text', 'A study of modern Japanese isolation.', 150, 'JAPANESE', CURRENT_TIMESTAMP),

-- Бізнес та Економіка
('Багатий тато, бідний тато', 'https://static.yakaboo.ua/media/catalog/product/i/m/img710_45.jpg', 'Бізнес', 'ADULT', 315.00, '1997-04-01', 'Роберт Кіййосакі', 336, 'Світовий бестселер', 'Уроки фінансової грамотності.', 4000, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Атлант розправив плечі', 'https://readukrainianbooks.com/uploads/posts/books/1/7/8/5/atlant-rozpraviv-plechi-chastina-persha-nesuperechnist-ajn-rend.jpg', 'Роман-утопія', 'ADULT', 720.00, '1957-10-10', 'Айн Ренд', 1200, 'Комплект 3 книги', 'Гімн розуму та підприємництву.', 1300, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Мислення швидке й повільне', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5PoMnXswBVcmub2BTCDlfsMTp1CWEHuVpBA&s', 'Психологія', 'ADULT', 450.00, '2011-10-25', 'Даніель Канеман', 480, 'Нобелівський лауреат', 'Як ми приймаємо рішення.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Фантастика та Жахи
('Сяйво', 'https://laboratory.ua/files/products/e6ccb5_w1600.1800x1200.jpg', 'Жахи', 'ADULT', 340.00, '1977-01-28', 'Стівен Кінґ', 480, 'Тверда обкладинка', 'Готель Overlook чекає на вас.', 1900, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Марсіянин', 'https://static.yakaboo.ua/media/catalog/product/1/1/115-1_49.jpg', 'Фантастика', 'TEEN', 290.00, '2011-02-11', 'Енді Вейр', 416, 'Наукова фантастика', 'Як вижити на Марсі наодинці.', 1400, 'UKRAINIAN', CURRENT_TIMESTAMP),
('451 градус за Фаренгейтом', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/i/m/img034_8.jpg', 'Антиутопія', 'TEEN', 240.00, '1953-10-19', 'Рей Бредбері', 272, 'Класика жанру', 'Світ, де спалюють книги.', 2200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Фундація', 'https://bookopt.com.ua/media/catalog/product/cache/image/850x/f/u/fundacija_2.webp', 'Фантастика', 'ADULT', 380.00, '1951-01-01', 'Айзек Азімов', 320, 'Тверда обкладинка', 'Крах імперії та порятунок знань.', 800, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Графічні романи та Комікси
('Вартові', 'https://static.yakaboo.ua/media/catalog/product/a/l/almur.jpg', 'Комікс', 'ADULT', 550.00, '1986-09-01', 'Алан Мур', 416, 'Делюкс видання', 'Хто охороняє охоронців?', 450, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Бетмен: Рік перший', 'https://upload.wikimedia.org/wikipedia/uk/e/ec/%D0%91%D0%B5%D1%82%D0%BC%D0%B5%D0%BD_%D0%A0%D1%96%D0%BA_%D0%9F%D0%B5%D1%80%D1%88%D0%B8%D0%B9.png', 'Комікс', 'TEEN', 280.00, '1987-01-01', 'Френк Міллер', 144, 'Глянцевий папір', 'Становлення Темного лицаря.', 700, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Ліга Справедливості: Книга 1', 'https://static.yakaboo.ua/media/catalog/product/i/m/img408_2_5.jpg', 'Комікс', 'CHILD', 350.00, '2011-01-01', 'Джефф Джонс', 192, 'DC Comics', 'Обєднення найбільших героїв.', 600, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Сучасна проза
('Клавка', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSI0fikZMpq24eWuGqxFlZdbb6U2Ye8rugsIA&s', 'Роман', 'ADULT', 220.00, '2019-01-01', 'Марина Гримич', 336, 'Новинка', 'Київ, 1947 рік, Спілка письменників.', 1200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Радіо Ніч', 'https://static.yakaboo.ua/media/cloudflare/product/webp/352x340/c/o/cover_256_76.jpg', 'Сучасна проза', 'ADULT', 360.00, '2021-12-14', 'Юрій Андрухович', 456, 'Меридіан Черновіц', 'Музичний роман про революцію та ніч.', 800, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Амадока', 'https://static.yakaboo.ua/media/cloudflare/product/webp/352x340/c/o/cover_178_54.jpg', 'Роман', 'ADULT', 680.00, '2020-01-01', 'Софія Андрухович', 832, 'Монументальний твір', 'Пам''ять, що зникає і повертається.', 500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Ворошиловград', 'https://static.yakaboo.ua/media/catalog/product/1/6/161_1_2.jpg', 'Сучасна проза', 'ADULT', 310.00, '2010-09-01', 'Сергій Жадан', 440, 'Книга десятиліття', 'Рейдерство на Донбасі та метафізика.', 1500, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Дитяче та підліткове
('Чудове чудовисько', 'https://static.yakaboo.ua/media/catalog/product/1/8/189_1_3.jpg', 'Казка', 'CHILD', 210.00, '2006-01-01', 'Сашко Дерманський', 280, 'А-ба-ба-га-ла-ма-га', 'Про дружбу дівчинки та монстра.', 2500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Гаррі Поттер і вязень Азкабану', 'https://upload.wikimedia.org/wikipedia/uk/c/c8/HPand_the_Prisoner_of_Azkaban_UKR.jpg', 'Фентезі', 'CHILD', 350.00, '1999-07-08', 'Дж.К. Ролінґ', 384, 'Тверда палітурка', 'Третій рік навчання.', 1900, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Гаррі Поттер і Келих вогню', 'https://upload.wikimedia.org/wikipedia/uk/a/a2/HPand_the_Goblet_of_Fire_UKR.jpg', 'Фентезі', 'CHILD', 480.00, '2000-07-08', 'Дж.К. Ролінґ', 672, 'А-ба-ба-га-ла-ма-га', 'Турнір трьох чарівників.', 1600, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Гаррі Поттер і Орден фенікса', 'https://upload.wikimedia.org/wikipedia/uk/8/80/HPand_the_Order_of_the_Phoenix_UKR.jpg', 'Фентезі', 'CHILD', 550.00, '2003-06-21', 'Дж.К. Ролінґ', 816, 'Найтовстіша частина', 'Повстання проти Амбридж.', 1400, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Відьмак: Меч призначення', 'https://upload.wikimedia.org/wikipedia/uk/1/13/MeczPryznaczennia.jpg', 'Фентезі', 'ADULT', 420.00, '1992-01-01', 'Анджей Сапковський', 400, 'Друга збірка', 'Доля Геральта і Цірі.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Наукова фантастика та Космос
('Проєкт Аве Марія', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS278F3QM8rsFAE_vzDaOziZKrPtKUJ5roiHQ&s', 'Фантастика', 'TEEN', 380.00, '2021-05-04', 'Енді Вейр', 480, 'Тверда обкладинка', 'Остання надія людства на виживання у глибокому космосі.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Гіперіон', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/i/m/img088_104.jpg', 'Фантастика', 'ADULT', 450.00, '1989-05-24', 'Ден Сіммонс', 608, 'Філософська фантастика', 'Шість паломників вирушають до Гробниць Часу.', 750, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Соляріс', 'https://static.yakaboo.ua/media/cloudflare/product/webp/352x340/1/5/15_3_107.jpg', 'Фантастика', 'ADULT', 220.00, '1961-01-01', 'Станіслав Лем', 288, 'Класика жанру', 'Контакт людини з розумним Океаном.', 900, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Біографії та Мемуари
('Стів Джобс', 'https://book-ye.com.ua/media/catalog/product/cache/79524a38d3bc3d0f3b6015a08841400c/3/8/383a64d0-cda6-11e6-80c0-000c29ae1566_589974a1-6bc0-11eb-8148-000c29ae1566.jpg', 'Біографія', 'ADULT', 420.00, '2011-10-24', 'Волтер Айзексон', 600, 'Тверда обкладинка', 'Історія засновника Apple від першої особи.', 3200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Becoming. Моя історія', 'https://content1.rozetka.com.ua/goods/images/big/17459220.jpg', 'Мемуари', 'ADULT', 390.00, '2018-11-13', 'Мішель Обама', 480, 'Бестселер NYT', 'Відверті мемуари колишньої першої леді США.', 1500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Зелене світло', 'https://static.yakaboo.ua/media/catalog/product/8/0/80e1685978d971ca0cb2d34339bd437e.jpg', 'Біографія', 'ADULT', 360.00, '2020-10-20', 'Меттью Макконагі', 320, 'Тверда палітурка', 'Життєві уроки та пригоди голлівудського актора.', 2400, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Японська культура та Манга
('Naruto Vol. 1', 'https://static.yakaboo.ua/media/catalog/product/9/7/9781569319000_0.jpg', 'Manga', 'TEEN', 350.00, '1999-09-21', 'Masashi Kishimoto', 192, 'Softcover', 'The journey of a ninja begins.', 5000, 'JAPANESE', CURRENT_TIMESTAMP),
('Death Note Vol. 1', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/5/f/5f_38.jpg', 'Manga', 'TEEN', 350.00, '2003-12-01', 'Tsugumi Ohba', 200, 'Thriller', 'Whose name will be written next?', 4500, 'JAPANESE', CURRENT_TIMESTAMP),
('Мандрівний замок Хаула', 'https://mybookshelf.com.ua/assets/images/products/11045/big/img-0240-4.webp', 'Фентезі', 'CHILD', 280.00, '1986-01-01', 'Діана Вінн Джонс', 352, 'А-ба-ба-га-ла-ма-га', 'Казкова історія, що надихнула Хаяо Міядзакі.', 1800, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- English - Technical & Modern
('The Clean Coder', 'https://static.yakaboo.ua/media/catalog/product/9/7/9780137081073_0.jpg', 'IT', 'ADULT', 880.00, '2011-05-13', 'Robert Martin', 256, 'Professionalism', 'A Code of Conduct for Professional Programmers.', 1400, 'ENGLISH', CURRENT_TIMESTAMP),
('Normal People', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgyHwvlIdVfj6V1OrmZyS8B1ADXffSqAxpMg&s', 'Modern Prose', 'ADULT', 410.00, '2018-08-28', 'Sally Rooney', 273, 'Paperback', 'A story of mutual fascination and friendship.', 2100, 'ENGLISH', CURRENT_TIMESTAMP),
('Where the Crawdads Sing', 'https://m.media-amazon.com/images/I/71yIb3aiOPL._AC_UF1000,1000_QL80_.jpg', 'Mystery', 'ADULT', 390.00, '2018-08-14', 'Delia Owens', 384, 'International bestseller', 'The story of the Marsh Girl.', 3000, 'ENGLISH', CURRENT_TIMESTAMP),

-- Spanish & French
('La sombra del viento', 'https://cdn.27.ua/sc--media--prod/default/a3/fa/66/a3fa666c-0ae7-493e-88b1-0bed3969891c.jpg', 'Misterio', 'ADULT', 450.00, '2001-04-01', 'Carlos Ruiz Zafon', 569, 'Tapa blanda', 'Un viaje al Cementerio de los Libros Olvidados.', 900, 'SPANISH', CURRENT_TIMESTAMP),
('L''Etranger', 'https://m.media-amazon.com/images/I/81w-+lwQ6ML._AC_UF1000,1000_QL80_.jpg', 'Philosophie', 'ADULT', 230.00, '1942-05-19', 'Albert Camus', 159, 'Poche francaise', 'L''histoire de Meursault.', 1100, 'FRENCH', CURRENT_TIMESTAMP),

-- Українська дитяча та підліткова
('Айседора Мун', 'https://vivat.com.ua/storage/1.d/files/7/5/75cc5353_7a0eea82_z8390_aysedora-mun-potr.png', 'Пригоди', 'CHILD', 195.00, '2016-01-01', 'Гаррієт Мункастер', 128, 'Для дівчаток', 'Наполовину фея, наполовину вампір.', 3500, 'UKRAINIAN', CURRENT_TIMESTAMP),

('Сторожова застава', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/4/2/426_1_7.jpg', 'Історичне фентезі', 'CHILD', 260.00, '1991-01-01', 'Володимир Рутківський', 304, 'А-ба-ба-га-ла-ма-га', 'Подорож сучасного школяра у часи Русі.', 1200, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Сучасні бестселери
('Друзі, коханки і велика халепа', 'https://static.yakaboo.ua/media/catalog/product/5/a/5a994a077b9015cc56026c2c5daa42e8.jpg', 'Біографія', 'ADULT', 440.00, '2022-11-01', 'Меттью Перрі', 320, 'Щира історія', 'Мемуари зірки серіалу Друзі.', 5000, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Поки кава не охолоне', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/5/2/52455_93968_1.jpg', 'Магічний реалізм', 'ADULT', 280.00, '2015-12-01', 'Тосікадзу Кавагуті', 224, 'Японський бестселер', 'Кафе, де можна повернутися в минуле.', 1400, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Завтра, завтра, завтра', 'https://static.yakaboo.ua/media/catalog/product/c/o/cover_95_165.jpg', 'Роман', 'ADULT', 350.00, '2022-07-05', 'Габріель Зевін', 480, 'Про геймдев', 'Історія дружби, кохання та розробки ігор.', 1700, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Там, де співають раки', 'https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/3/2/32a944112133f8a83e296fc1d2e64ca0.jpg', 'Детектив', 'ADULT', 315.00, '2018-08-14', 'Делія Овенс', 400, 'Тверда обкладинка', 'Таємниці дикої природи.', 2800, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Хрещений батько', 'https://bookchef.ua/upload/iblock/dd6/dd6994d3ef32844df8baa62dcbef8c92.jpg', 'Детектив', 'ADULT', 350.00, '1969-03-10', 'Маріо П''юзо', 500, 'Мафія', 'Пропозиція, від якої неможливо відмовитись.', 1800, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Воно (Частина 2)', 'https://static.yakaboo.ua/media/cloudflare/product/webp/352x340/i/m/img564_15.jpg', 'Жахи', 'ADULT', 420.00, '1986-09-15', 'Стівен Кінґ', 600, 'Завершення', 'Фінальна битва Клубу невдах.', 1200, 'UKRAINIAN', CURRENT_TIMESTAMP);