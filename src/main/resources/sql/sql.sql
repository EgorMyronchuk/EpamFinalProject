
INSERT INTO books (
    name, photo_url, genre, age_group, price, publication_date,
    author, number_of_pages, characteristics, description, sold_amount,
    language, created_at
) VALUES
-- Фентезі та Пригоди
('Гаррі Поттер і філософський камінь', 'https://static.yakaboo.ua/media/catalog/product/i/m/img013_1_25.jpg', 'Фентезі', 'CHILD', 350.00, '1997-06-26', 'Дж.К. Ролінґ', 320, 'Тверда обкладинка', 'Хлопчик, який вижив, дізнається про свій магічний спадок.', 1500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Гаррі Поттер і Таємна кімната', 'https://static.yakaboo.ua/media/catalog/product/b/o/book_1_156.jpg', 'Фентезі', 'CHILD', 360.00, '1998-07-02', 'Дж.К. Ролінґ', 350, 'А-ба-ба-га-ла-ма-га', 'Таємничі напади в школі Гоґвортс.', 1200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Відьмак: Останнє бажання', 'https://book-ye.com.ua/upload/iblock/785/19223126_1508960812519126_5469543161686774619_n.jpg', 'Фентезі', 'ADULT', 420.00, '1993-01-01', 'Анджей Сапковський', 384, 'Тиснення на палітурці', 'Перша збірка пригод Геральта.', 2100, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Дюна', 'https://static.yakaboo.ua/media/catalog/product/c/o/cover_1_221.jpg', 'Фантастика', 'TEEN', 480.00, '1965-08-01', 'Френк Герберт', 600, 'Тверда палітурка', 'Епічне протистояння за найцінніший ресурс у всесвіті.', 1800, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Хоббіт', 'https://static.yakaboo.ua/media/catalog/product/i/m/img577_1.jpg', 'Фентезі', 'CHILD', 310.00, '1937-09-21', 'Дж. Р. Р. Толкін', 304, 'З малюнками автора', 'Подорож Більбо Беггінса до Самотньої гори.', 950, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Сучасна українська література
('Інтернат', 'https://static.yakaboo.ua/media/catalog/product/i/m/img017_4.jpg', 'Драма', 'ADULT', 320.00, '2017-08-23', 'Сергій Жадан', 336, 'Авторський дизайн', 'Жорстока реальність війни очима вчителя.', 1400, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Музей покинутих секретів', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786171701311.jpg', 'Роман', 'ADULT', 550.00, '2009-12-15', 'Оксана Забужко', 800, 'Тверда обкладинка', 'Сага, що охоплює три покоління української родини.', 600, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Фелікс Австрія', 'https://static.yakaboo.ua/media/catalog/product/f/e/felix-austria.jpg', 'Роман', 'ADULT', 290.00, '2014-01-01', 'Софія Андрухович', 312, 'Лауреат нагороди BBC', 'Станіславів на початку ХХ століття.', 850, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Троща', 'https://static.yakaboo.ua/media/catalog/product/t/r/troscha_1.jpg', 'Історичний роман', 'ADULT', 330.00, '2017-01-01', 'Василь Шкляр', 416, 'Тверда палітурка', 'Роман про УПА та боротьбу за незалежність.', 720, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Чорний ворон', 'https://static.yakaboo.ua/media/catalog/product/9/7/9789661440264.jpg', 'Історичний роман', 'ADULT', 310.00, '2009-01-01', 'Василь Шкляр', 380, 'Класика сучасності', 'Боротьба повстанців Холодного Яру.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Психологія та Саморозвиток
('Атомні звички', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786171271135.jpg', 'Психологія', 'ADULT', 340.00, '2018-10-16', 'Джеймс Клір', 304, 'Тверда обкладинка', 'Малі зміни, що призводять до вражаючих результатів.', 4500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Мистецтво пофігізму', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786171243163.jpg', 'Психологія', 'ADULT', 280.00, '2016-09-13', 'Марк Менсон', 192, 'М''яка обкладинка', 'Як жити щасливо, ігноруючи чужі очікування.', 3200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Людина в пошуках справжнього сенсу', 'https://static.yakaboo.ua/media/catalog/product/i/m/img889_1_2.jpg', 'Психологія', 'ADULT', 220.00, '1946-01-01', 'Віктор Франкл', 160, 'Кишеньковий формат', 'Досвід виживання в концтаборі.', 1900, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Сапієнс', 'https://static.yakaboo.ua/media/catalog/product/i/m/img749_1_7.jpg', 'Історія', 'ADULT', 450.00, '2011-01-01', 'Ювал Ной Харарі', 544, 'Тверда обкладинка', 'Коротка історія людства від минулого до майбутнього.', 2500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Чому ми спимо', 'https://static.yakaboo.ua/media/catalog/product/i/m/img548_4.jpg', 'Наука', 'ADULT', 390.00, '2017-01-01', 'Меттью Вокер', 400, 'Научпоп', 'Наукове дослідження сну та його важливості.', 800, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- English Classics & Modern
('1984', 'https://images-na.ssl-images-amazon.com/images/I/71kxa1-0mfL.jpg', 'Dystopian', 'ADULT', 250.00, '1949-06-08', 'George Orwell', 328, 'Penguin Classics', 'The ultimate dystopian masterpiece.', 5000, 'ENGLISH', CURRENT_TIMESTAMP),
('The Great Gatsby', 'https://images-na.ssl-images-amazon.com/images/I/81af+MC73RL.jpg', 'Classic', 'ADULT', 320.00, '1925-04-10', 'F. Scott Fitzgerald', 180, 'Hardcover', 'Decadence and the American Dream.', 1200, 'ENGLISH', CURRENT_TIMESTAMP),
('To Kill a Mockingbird', 'https://images-na.ssl-images-amazon.com/images/I/81gepf1eMqL.jpg', 'Drama', 'TEEN', 380.00, '1960-07-11', 'Harper Lee', 281, 'Modern Classic', 'A story of racial injustice in the South.', 1500, 'ENGLISH', CURRENT_TIMESTAMP),
('Clean Code', 'https://images-na.ssl-images-amazon.com/images/I/41xShlnTZTL.jpg', 'IT', 'ADULT', 950.00, '2008-08-01', 'Robert Martin', 464, 'Developer Bible', 'A handbook of agile software craftsmanship.', 3000, 'ENGLISH', CURRENT_TIMESTAMP),
('The Alchemist', 'https://images-na.ssl-images-amazon.com/images/I/51Z9dfSrgYL.jpg', 'Philosophy', 'TEEN', 290.00, '1988-01-01', 'Paulo Coelho', 208, 'International bestseller', 'Follow your dreams.', 4000, 'ENGLISH', CURRENT_TIMESTAMP),

-- Дитяча література
('Тореадори з Васюківки', 'https://static.yakaboo.ua/media/catalog/product/c/o/cover_33_2.jpg', 'Пригоди', 'CHILD', 330.00, '1963-01-01', 'Всеволод Нестайко', 544, 'Ілюстрована', 'Найвеселіші пригоди українських школярів.', 2800, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Чарлі і шоколадна фабрика', 'https://static.yakaboo.ua/media/catalog/product/i/m/img568_1_4.jpg', 'Казка', 'CHILD', 220.00, '1964-01-17', 'Роальд Дал', 192, 'А-ба-ба-га-ла-ма-га', 'Подорож на магічну фабрику Віллі Вонки.', 1500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Аліса в країні див', 'https://static.yakaboo.ua/media/catalog/product/9/7/9789669171733_1.jpg', 'Казка', 'CHILD', 260.00, '1865-11-26', 'Льюїс Керролл', 192, 'Тверда палітурка', 'Фантастичний світ за кролячою норою.', 900, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Пеппі Довгапанчоха', 'https://static.yakaboo.ua/media/catalog/product/9/7/9789663391762.jpg', 'Пригоди', 'CHILD', 240.00, '1945-01-01', 'Астрід Ліндгрен', 320, 'Веселі картинки', 'Історія найсильнішої дівчинки у світі.', 1300, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Пригоди Тома Соєра', 'https://static.yakaboo.ua/media/catalog/product/i/m/img183_1_18.jpg', 'Пригоди', 'CHILD', 250.00, '1876-01-01', 'Марк Твен', 280, 'Шкільна серія', 'Бешкетник Том та його друзі.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Детективи
('Вбивство у Східному експресі', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786171233805.jpg', 'Детектив', 'ADULT', 250.00, '1934-01-01', 'Агата Крісті', 288, 'КСД серія', 'Еркюль Пуаро розгадує таємницю потяга.', 2300, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Шерлок Холмс. Етюд у багряних тонах', 'https://static.yakaboo.ua/media/catalog/product/i/m/img335_1_2.jpg', 'Детектив', 'TEEN', 290.00, '1887-11-01', 'Артур Конан Дойл', 256, 'Тверда обкладинка', 'Перша зустріч Холмса та Ватсона.', 1700, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Код да Вінчі', 'https://static.yakaboo.ua/media/catalog/product/i/m/img884_1_5.jpg', 'Трилер', 'ADULT', 380.00, '2003-01-01', 'Ден Браун', 600, 'Світовий бестселер', 'Таємниці, сховані у картинах Леонардо.', 3500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Дівчина у потягу', 'https://static.yakaboo.ua/media/catalog/product/i/m/img742_1_4.jpg', 'Трилер', 'ADULT', 280.00, '2015-01-13', 'Пола Гоукінз', 350, 'Психологічний трилер', 'Вона бачила щось жахливе з вікна.', 2100, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Різне
('Норвезький ліс', 'https://static.yakaboo.ua/media/catalog/product/i/m/img627_1_10.jpg', 'Роман', 'ADULT', 320.00, '1987-09-04', 'Харукі Муракамі', 400, 'Японська проза', 'Меланхолійна історія кохання та втрати.', 900, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Тисяча сіяючих сонць', 'https://static.yakaboo.ua/media/catalog/product/c/o/cover_1_248.jpg', 'Роман', 'ADULT', 315.00, '2007-05-22', 'Халед Госсейні', 432, 'Драматична історія', 'Життя двох жінок у сучасному Афганістані.', 750, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Книжкова злодійка', 'https://static.yakaboo.ua/media/catalog/product/i/m/img728_1_5.jpg', 'Драма', 'TEEN', 350.00, '2005-01-01', 'Маркус Зузак', 512, 'Тверда обкладинка', 'Смерть розповідає про дівчинку та книги.', 1200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Сім чоловіків Евелін Г''юґо', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786178264017.jpg', 'Роман', 'ADULT', 380.00, '2017-06-13', 'Тейлор Дженкінс Рід', 448, 'Арт-Букс', 'Таємниці голлівудської ікони.', 1800, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Гіпотеза кохання', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786178111007.jpg', 'Романтика', 'ADULT', 295.00, '2021-09-14', 'Алі Гейзелвуд', 384, 'Ромком', 'Фейковые стосунки у світі академічної науки.', 2500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Квартира на двох', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786171285224.jpg', 'Романтика', 'ADULT', 270.00, '2019-01-01', 'Бет О’Лірі', 416, 'М''яка палітурка', 'Вони ділять одне ліжко, але ніколи не бачились.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Мистецтво війни', 'https://static.yakaboo.ua/media/catalog/product/i/m/img575_1.jpg', 'Філософія', 'ADULT', 180.00, '2015-01-01', 'Сунь-цзи', 128, 'Подарункове видання', 'Давньокитайський трактат про стратегію.', 3000, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Маус', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786177579808.jpg', 'Графічний роман', 'ADULT', 420.00, '1980-01-01', 'Арт Шпігельман', 296, 'Пулітцерівська премія', 'Історія Голокосту у форматі коміксу.', 500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Божественна комедія', 'https://static.yakaboo.ua/media/catalog/product/i/m/img267_1_21.jpg', 'Класика', 'ADULT', 650.00, '1320-01-01', 'Данте Аліг’єрі', 608, 'Подарункове видання', 'Подорож крізь Пекло, Чистилище та Рай.', 300, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Фауст', 'https://static.yakaboo.ua/media/catalog/product/i/m/img885_1_4.jpg', 'Класика', 'ADULT', 390.00, '1808-01-01', 'Йоганн Гете', 480, 'Тверда обкладинка', 'Трагедія вченого, що продав душу дияволу.', 250, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Перевтілення', 'https://static.yakaboo.ua/media/catalog/product/9/7/9789660381391.jpg', 'Класика', 'ADULT', 190.00, '1915-01-01', 'Франц Кафка', 120, 'Кишенькова книжка', 'Одного ранку Грегор Замза прокинувся комахою.', 600, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Чужаниця', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786170707758.jpg', 'Філософія', 'ADULT', 210.00, '1942-01-01', 'Альбер Камю', 160, 'Екзистенціалізм', 'Роздуми про абсурд людського існування.', 450, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Три товариші', 'https://static.yakaboo.ua/media/catalog/product/i/m/img744_1_10.jpg', 'Класика', 'ADULT', 350.00, '1936-01-01', 'Еріх Марія Ремарк', 480, 'Тверда обкладинка', 'Найкраща книга про справжню дружбу та кохання.', 1200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Джури козака Швайки', 'https://static.yakaboo.ua/media/catalog/product/9/7/9789667047719.jpg', 'Пригоди', 'CHILD', 290.00, '2007-01-01', 'Володимир Рутківський', 432, 'А-ба-ба-га-ла-ма-га', 'Початок легендарної трилогії про козаків.', 1900, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Дракони, вперед!', 'https://static.yakaboo.ua/media/catalog/product/i/m/img404_3.jpg', 'Казка', 'CHILD', 180.00, '2015-01-01', 'Марина та Сергій Дяченки', 160, 'Кольорова серія', 'Добрі історії про маленьких драконів.', 700, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Мелодія кави у тональності сподівання', 'https://static.yakaboo.ua/media/catalog/product/9/7/9789661463133.jpg', 'Роман', 'ADULT', 240.00, '2014-01-01', 'Наталія Гурницька', 380, 'Український романс', 'Львівська історія кохання XIX століття.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Смачна країна', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786171224810.jpg', 'Кулінарія', 'ADULT', 390.00, '2015-01-01', 'Костянтин Грубич', 280, 'Подарункове видання', 'Найкращі рецепти з усіх куточків України.', 400, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Ворота Європи', 'https://static.yakaboo.ua/media/catalog/product/i/m/img063_1.jpg', 'Історія', 'ADULT', 380.00, '2015-01-01', 'Сергій Плохій', 400, 'Наукове дослідження', 'Історія України від найдавніших часів.', 1500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Справа Василя Стуса', 'https://static.yakaboo.ua/media/catalog/product/i/m/img612_1_1.jpg', 'Документалістика', 'ADULT', 450.00, '2019-05-01', 'Вахтанг Кіпіані', 688, 'Тверда обкладинка', 'Документи з архіву КДБ про долю поета.', 3500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Кров і попіл', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786178044244.jpg', 'Фентезі', 'ADULT', 360.00, '2020-03-31', 'Дженніфер Л. Арментраут', 600, 'BookChef серія', 'Світовий бестселер у жанрі ромфант.', 2200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Грокаємо алгоритми', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786170951168.jpg', 'IT-література', 'TEEN', 380.00, '2016-01-01', 'Адитья Бхаргава', 288, 'М''яка обкладинка', 'Просто та наочно про складні алгоритми.', 2600, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- IT-література (UKRAINIAN & ENGLISH)
('Чиста архітектура', 'https://static.yakaboo.ua/media/catalog/product/p/h/photo_2020-03-31_17-49-41.jpg', 'IT-література', 'ADULT', 550.00, '2017-09-10', 'Роберт Мартін', 432, 'Мистецтво розробки ПЗ', 'Посібник зі структури та дизайну програмного забезпечення.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Design Patterns', 'https://images-na.ssl-images-amazon.com/images/I/81gtS7Zf7ML.jpg', 'Computer Science', 'ADULT', 890.00, '1994-10-21', 'Erich Gamma', 395, 'Hardcover', 'Elements of Reusable Object-Oriented Software.', 2000, 'ENGLISH', CURRENT_TIMESTAMP),
('Refactoring', 'https://images-na.ssl-images-amazon.com/images/I/419z69v6SML.jpg', 'Computer Science', 'ADULT', 750.00, '2018-11-20', 'Martin Fowler', 448, 'Technical guide', 'Improving the design of existing code.', 850, 'ENGLISH', CURRENT_TIMESTAMP),
('The Pragmatic Programmer', 'https://images-na.ssl-images-amazon.com/images/I/41as+49FOKL.jpg', 'IT', 'ADULT', 820.00, '1999-10-30', 'Andrew Hunt', 352, '20th Anniversary Edition', 'Your journey to mastery.', 1500, 'ENGLISH', CURRENT_TIMESTAMP),

-- Світові мови
('Don Quijote de la Mancha', 'https://images-na.ssl-images-amazon.com/images/I/817O-J6tXWL.jpg', 'Classic', 'ADULT', 480.00, '1605-01-01', 'Miguel de Cervantes', 864, 'Tapa dura', 'El ingenioso hidalgo de la Mancha.', 300, 'SPANISH', CURRENT_TIMESTAMP),
('Cien años de soledad', 'https://images-na.ssl-images-amazon.com/images/I/81m69h-28BL.jpg', 'Realismo Magico', 'ADULT', 410.00, '1967-05-30', 'Gabriel Garcia Marquez', 471, 'Edicion de bolsillo', 'La historia de la familia Buendia.', 500, 'SPANISH', CURRENT_TIMESTAMP),
('Le Petit Prince', 'https://images-na.ssl-images-amazon.com/images/I/41pY6I9KqyL.jpg', 'Enfants', 'CHILD', 220.00, '1943-04-01', 'Antoine de Saint-Exupery', 96, 'Francais original', 'Dessine-moi un mouton.', 1800, 'FRENCH', CURRENT_TIMESTAMP),
('Les Miserables', 'https://images-na.ssl-images-amazon.com/images/I/51p0v70OURL.jpg', 'Classique', 'ADULT', 590.00, '1862-01-01', 'Victor Hugo', 1232, 'Edition integrale', 'L''histoire de Jean Valjean.', 400, 'FRENCH', CURRENT_TIMESTAMP),
('Die Verwandlung', 'https://images-na.ssl-images-amazon.com/images/I/41uS2lB3vTL.jpg', 'Klassik', 'ADULT', 195.00, '1915-10-01', 'Franz Kafka', 100, 'Deutsch', 'Gregor Samsa als Ungeziefer.', 600, 'GERMAN', CURRENT_TIMESTAMP),
('Kokoro', 'https://images-na.ssl-images-amazon.com/images/I/51wN7-S9z1L.jpg', 'Classic', 'ADULT', 450.00, '1914-01-01', 'Natsume Soseki', 250, 'Japanese text', 'A study of modern Japanese isolation.', 150, 'JAPANESE', CURRENT_TIMESTAMP),

-- Бізнес та Економіка
('Багатий тато, бідний тато', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786171250758.jpg', 'Бізнес', 'ADULT', 315.00, '1997-04-01', 'Роберт Кіййосакі', 336, 'Світовий бестселер', 'Уроки фінансової грамотності.', 4000, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Атлант розправив плечі', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786175480007.jpg', 'Роман-утопія', 'ADULT', 720.00, '1957-10-10', 'Айн Ренд', 1200, 'Комплект 3 книги', 'Гімн розуму та підприємництву.', 1300, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Мислення швидке й повільне', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786177279180.jpg', 'Психологія', 'ADULT', 450.00, '2011-10-25', 'Даніель Канеман', 480, 'Нобелівський лауреат', 'Як ми приймаємо рішення.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Фантастика та Жахи
('Сяйво', 'https://static.yakaboo.ua/media/catalog/product/i/m/img615_1_3.jpg', 'Жахи', 'ADULT', 340.00, '1977-01-28', 'Стівен Кінґ', 480, 'Тверда обкладинка', 'Готель Overlook чекає на вас.', 1900, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Марсіянин', 'https://static.yakaboo.ua/media/catalog/product/c/o/cover_1_251.jpg', 'Фантастика', 'TEEN', 290.00, '2011-02-11', 'Енді Вейр', 416, 'Наукова фантастика', 'Як вижити на Марсі наодинці.', 1400, 'UKRAINIAN', CURRENT_TIMESTAMP),
('451 градус за Фаренгейтом', 'https://static.yakaboo.ua/media/catalog/product/i/m/img339_1_4.jpg', 'Антиутопія', 'TEEN', 240.00, '1953-10-19', 'Рей Бредбері', 272, 'Класика жанру', 'Світ, де спалюють книги.', 2200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Фундація', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786171239241.jpg', 'Фантастика', 'ADULT', 380.00, '1951-01-01', 'Айзек Азімов', 320, 'Тверда обкладинка', 'Крах імперії та порятунок знань.', 800, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Графічні романи та Комікси
('Вартові', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786177579129.jpg', 'Комікс', 'ADULT', 550.00, '1986-09-01', 'Алан Мур', 416, 'Делюкс видання', 'Хто охороняє охоронців?', 450, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Бетмен: Рік перший', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786177579136.jpg', 'Комікс', 'TEEN', 280.00, '1987-01-01', 'Френк Міллер', 144, 'Глянцевий папір', 'Становлення Темного лицаря.', 700, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Ліга Справедливості: Книга 1', 'https://static.yakaboo.ua/media/catalog/product/c/o/cover_5_27.jpg', 'Комікс', 'CHILD', 350.00, '2011-01-01', 'Джефф Джонс', 192, 'DC Comics', 'Обєднення найбільших героїв.', 600, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Сучасна проза
('Клавка', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786176796954.jpg', 'Роман', 'ADULT', 220.00, '2019-01-01', 'Марина Гримич', 336, 'Новинка', 'Київ, 1947 рік, Спілка письменників.', 1200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Радіо Ніч', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786176798606.jpg', 'Сучасна проза', 'ADULT', 360.00, '2021-12-14', 'Юрій Андрухович', 456, 'Меридіан Черновіц', 'Музичний роман про революцію та ніч.', 800, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Амадока', 'https://static.yakaboo.ua/media/catalog/product/a/m/amadoka.jpg', 'Роман', 'ADULT', 680.00, '2020-01-01', 'Софія Андрухович', 832, 'Монументальний твір', 'Пам''ять, що зникає і повертається.', 500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Ворошиловград', 'https://static.yakaboo.ua/media/catalog/product/v/o/voroshylovhrad.jpg', 'Сучасна проза', 'ADULT', 310.00, '2010-09-01', 'Сергій Жадан', 440, 'Книга десятиліття', 'Рейдерство на Донбасі та метафізика.', 1500, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Дитяче та підліткове
('Чудове чудовисько', 'https://static.yakaboo.ua/media/catalog/product/c/h/chudove-chudovysko.jpg', 'Казка', 'CHILD', 210.00, '2006-01-01', 'Сашко Дерманський', 280, 'А-ба-ба-га-ла-ма-га', 'Про дружбу дівчинки та монстра.', 2500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Гаррі Поттер і вязень Азкабану', 'https://static.yakaboo.ua/media/catalog/product/i/m/img572_1_4.jpg', 'Фентезі', 'CHILD', 350.00, '1999-07-08', 'Дж.К. Ролінґ', 384, 'Тверда палітурка', 'Третій рік навчання.', 1900, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Гаррі Поттер і Келих вогню', 'https://static.yakaboo.ua/media/catalog/product/i/m/img573_1_1.jpg', 'Фентезі', 'CHILD', 480.00, '2000-07-08', 'Дж.К. Ролінґ', 672, 'А-ба-ба-га-ла-ма-га', 'Турнір трьох чарівників.', 1600, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Гаррі Поттер і Орден фенікса', 'https://static.yakaboo.ua/media/catalog/product/i/m/img574_1_1.jpg', 'Фентезі', 'CHILD', 550.00, '2003-06-21', 'Дж.К. Ролінґ', 816, 'Найтовстіша частина', 'Повстання проти Амбридж.', 1400, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Відьмак: Меч призначення', 'https://static.yakaboo.ua/media/catalog/product/v/i/vidmak_mech_pryznachennia.jpg', 'Фентезі', 'ADULT', 420.00, '1992-01-01', 'Анджей Сапковський', 400, 'Друга збірка', 'Доля Геральта і Цірі.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Наукова фантастика та Космос
('Проєкт Аве Марія', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786171292550.jpg', 'Фантастика', 'TEEN', 380.00, '2021-05-04', 'Енді Вейр', 480, 'Тверда обкладинка', 'Остання надія людства на виживання у глибокому космосі.', 1100, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Гіперіон', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786171215238.jpg', 'Фантастика', 'ADULT', 450.00, '1989-05-24', 'Ден Сіммонс', 608, 'Філософська фантастика', 'Шість паломників вирушають до Гробниць Часу.', 750, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Соляріс', 'https://static.yakaboo.ua/media/catalog/product/i/m/img548_1_5.jpg', 'Фантастика', 'ADULT', 220.00, '1961-01-01', 'Станіслав Лем', 288, 'Класика жанру', 'Контакт людини з розумним Океаном.', 900, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Біографії та Мемуари
('Стів Джобс', 'https://static.yakaboo.ua/media/catalog/product/i/m/img143_1_11.jpg', 'Біографія', 'ADULT', 420.00, '2011-10-24', 'Волтер Айзексон', 600, 'Тверда обкладинка', 'Історія засновника Apple від першої особи.', 3200, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Becoming. Моя історія', 'https://static.yakaboo.ua/media/catalog/product/b/e/becoming_1.jpg', 'Мемуари', 'ADULT', 390.00, '2018-11-13', 'Мішель Обама', 480, 'Бестселер NYT', 'Відверті мемуари колишньої першої леді США.', 1500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Зелене світло', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786173456789.jpg', 'Біографія', 'ADULT', 360.00, '2020-10-20', 'Меттью Макконагі', 320, 'Тверда палітурка', 'Життєві уроки та пригоди голлівудського актора.', 2400, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Японська культура та Манга
('Naruto Vol. 1', 'https://images-na.ssl-images-amazon.com/images/I/912VscqhGTL.jpg', 'Manga', 'TEEN', 350.00, '1999-09-21', 'Masashi Kishimoto', 192, 'Softcover', 'The journey of a ninja begins.', 5000, 'JAPANESE', CURRENT_TIMESTAMP),
('Death Note Vol. 1', 'https://images-na.ssl-images-amazon.com/images/I/81SStE7f2dL.jpg', 'Manga', 'TEEN', 350.00, '2003-12-01', 'Tsugumi Ohba', 200, 'Thriller', 'Whose name will be written next?', 4500, 'JAPANESE', CURRENT_TIMESTAMP),
('Мандрівний замок Хаула', 'https://static.yakaboo.ua/media/catalog/product/i/m/img578_1_4.jpg', 'Фентезі', 'CHILD', 280.00, '1986-01-01', 'Діана Вінн Джонс', 352, 'А-ба-ба-га-ла-ма-га', 'Казкова історія, що надихнула Хаяо Міядзакі.', 1800, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- English - Technical & Modern
('The Clean Coder', 'https://images-na.ssl-images-amazon.com/images/I/515u68-S6mL.jpg', 'IT', 'ADULT', 880.00, '2011-05-13', 'Robert Martin', 256, 'Professionalism', 'A Code of Conduct for Professional Programmers.', 1400, 'ENGLISH', CURRENT_TIMESTAMP),
('Normal People', 'https://images-na.ssl-images-amazon.com/images/I/8179u8EfsML.jpg', 'Modern Prose', 'ADULT', 410.00, '2018-08-28', 'Sally Rooney', 273, 'Paperback', 'A story of mutual fascination and friendship.', 2100, 'ENGLISH', CURRENT_TIMESTAMP),
('Where the Crawdads Sing', 'https://images-na.ssl-images-amazon.com/images/I/81HA6QNzhTL.jpg', 'Mystery', 'ADULT', 390.00, '2018-08-14', 'Delia Owens', 384, 'International bestseller', 'The story of the Marsh Girl.', 3000, 'ENGLISH', CURRENT_TIMESTAMP),

-- Spanish & French
('La sombra del viento', 'https://images-na.ssl-images-amazon.com/images/I/8109uO89P-L.jpg', 'Misterio', 'ADULT', 450.00, '2001-04-01', 'Carlos Ruiz Zafon', 569, 'Tapa blanda', 'Un viaje al Cementerio de los Libros Olvidados.', 900, 'SPANISH', CURRENT_TIMESTAMP),
('L''Etranger', 'https://images-na.ssl-images-amazon.com/images/I/71Gv8e5mGSL.jpg', 'Philosophie', 'ADULT', 230.00, '1942-05-19', 'Albert Camus', 159, 'Poche francaise', 'L''histoire de Meursault.', 1100, 'FRENCH', CURRENT_TIMESTAMP),

-- Українська дитяча та підліткова
('Айседора Мун', 'https://static.yakaboo.ua/media/catalog/product/i/m/img011_25.jpg', 'Пригоди', 'CHILD', 195.00, '2016-01-01', 'Гаррієт Мункастер', 128, 'Для дівчаток', 'Наполовину фея, наполовину вампір.', 3500, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Літак без вогнів', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786176790000.jpg', 'Проза', 'TEEN', 220.00, '2015-01-01', 'Гуменюк Надія', 240, 'М''яка обкладинка', 'Детективна історія для підлітків.', 400, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Сторожова застава', 'https://static.yakaboo.ua/media/catalog/product/9/7/9789667047000.jpg', 'Історичне фентезі', 'CHILD', 260.00, '1991-01-01', 'Володимир Рутківський', 304, 'А-ба-ба-га-ла-ма-га', 'Подорож сучасного школяра у часи Русі.', 1200, 'UKRAINIAN', CURRENT_TIMESTAMP),

-- Сучасні бестселери
('Друзі, коханки і велика халепа', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786178264000.jpg', 'Біографія', 'ADULT', 440.00, '2022-11-01', 'Меттью Перрі', 320, 'Щира історія', 'Мемуари зірки серіалу Друзі.', 5000, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Поки кава не охолоне', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786171700001.jpg', 'Магічний реалізм', 'ADULT', 280.00, '2015-12-01', 'Тосікадзу Кавагуті', 224, 'Японський бестселер', 'Кафе, де можна повернутися в минуле.', 1400, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Завтра, завтра, завтра', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786178111001.jpg', 'Роман', 'ADULT', 350.00, '2022-07-05', 'Габріель Зевін', 480, 'Про геймдев', 'Історія дружби, кохання та розробки ігор.', 1700, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Там, де співають раки', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786171268616.jpg', 'Детектив', 'ADULT', 315.00, '2018-08-14', 'Делія Овенс', 400, 'Тверда обкладинка', 'Таємниці дикої природи.', 2800, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Хрещений батько', 'https://static.yakaboo.ua/media/catalog/product/9/7/9786171200006.jpg', 'Детектив', 'ADULT', 350.00, '1969-03-10', 'Маріо П''юзо', 500, 'Мафія', 'Пропозиція, від якої неможливо відмовитись.', 1800, 'UKRAINIAN', CURRENT_TIMESTAMP),
('Воно (Частина 2)', 'https://static.yakaboo.ua/media/catalog/product/i/m/img615_1_2.jpg', 'Жахи', 'ADULT', 420.00, '1986-09-15', 'Стівен Кінґ', 600, 'Завершення', 'Фінальна битва Клубу невдах.', 1200, 'UKRAINIAN', CURRENT_TIMESTAMP);