# Библиотека криптографии на эллиптических кривых и реализация протокола Диффи-Хеллмана

Добро пожаловать в репозиторий проекта, который включает в себя библиотеку криптографии на эллиптических кривых (ECC) и реализации протокола Диффи-Хеллмана (DH) и протокола обмена ключами на эллиптических кривых (ECDH).

## Библиотека DHEC

**Библиотека DHEC (криптография на эллиптических кривых)** - это пакет Java, предназначенный для упрощения работы с эллиптическими кривыми в криптографии. Она включает функциональности, такие как операции с точками, умножение точек, модулярная арифметика и вычисление обратного.

Больше информации о библиотеке DHEC вы можете найти в [DHEC README](./src/DHEC/README.md).

## Реализация протокола DH

Реализация **протокола Диффи-Хеллмана** включает в себя как клиентскую, так и серверную компоненты, демонстрирующие установление общего секретного ключа с использованием протокола DH. Этот проект позволяет двум сущностям (Алисе и Бобу) общаться безопасно, обмениваясь открытыми ключами и генерируя общий секретный ключ с использованием протокола DH.

Больше информации о реализации протокола DH вы можете найти в [DH README](./src/DH/README.md).

## Реализация протокола DHEC

Реализация **протокола обмена ключами на эллиптических кривых (ECDH)** разработана для безопасного обмена ключами с использованием криптографии на эллиптических кривых. Она включает клиентскую и серверную компоненты, которые осуществляют обмен ключами с использованием эллиптических кривых.

### Клиент DHEC

Клиент DHEC генерирует закрытый ключ, вычисляет открытый ключ, отправляет его серверу и получает открытый ключ сервера. Затем он вычисляет общий секретный ключ с использованием операций на эллиптических кривых.

Больше информации о клиенте DHEC вы можете найти в [DHEC Client README](./src/DHEC/Client/README.md).

### Сервер DHEC

Сервер DHEC получает открытый ключ клиента, генерирует собственные закрытый и открытый ключи, и отправляет открытый ключ клиенту. Затем он вычисляет общий секретный ключ с использованием операций на эллиптических кривых.

Больше информации о сервере DHEC вы можете найти в [DHEC Server README](./src/DHEC/Server/README.md).

## Начало работы

Чтобы начать работу с библиотекой DHEC, реализацией протокола DH или реализацией протокола DHEC, пожалуйста, обратитесь к соответствующим файлам README в подкаталогах.

## Вклад

Этот проект открыт для вклада. Если у вас есть идеи для улучшений или исправлений ошибок, не стесняйтесь вносить свой вклад, открывая issue или pull request на [GitHub](https://github.com/your/project-repo).

## Лицензия

Код в этом репозитории распространяется под [лицензией MIT](./LICENSE), что означает, что вы можете использовать, модифицировать и распространять его как для личных, так и для коммерческих целей.

Начните создавать безопасные и эффективные криптографические приложения уже сегодня, используя библиотеку DHEC, реализацию протокола DH и реализацию протокола DHEC!

---
# Алгоритм создания скелета отпечатка пальца

## Алгоритм создания скелета отпечатка пальца

Этот алгоритм позволяет получить скелет отпечатка пальца, что является важным шагом в процессе его анализа и идентификации.

### Описание алгоритма

1. **Предварительная обработка изображения**

   Сначала необходимо провести предварительную обработку изображения отпечатка пальца. Этот шаг включает в себя следующие действия:

   - Преобразование изображения в оттенки серого.
   - Применение адаптивного порогового метода Брэдли для получения двоичного изображения. Этот метод помогает уменьшить влияние шумов и неоднородностей на изображении, делая контуры отпечатка пальца более четкими.

2. **Устранение шумов**

   Следующим шагом является устранение шумов на бинарном изображении отпечатка пальца. Для этого используется метод устранения шумов, основанный на количестве черных пикселей в окрестности каждого пикселя. Пиксели, окруженные меньшим количеством черных пикселей, считаются шумами и заменяются на белые пиксели.

3. **Скелетизация**

   Для получения скелета отпечатка пальца применяется последовательность операций коррозии с использованием различных структурных элементов. Коррозия - это процесс уменьшения объекта на бинарном изображении путем последовательного удаления его краевых пикселей. После каждой итерации коррозии проверяется, удалось ли удалить какие-либо пиксели. Процесс продолжается до тех пор, пока возможно выполнить коррозию. Каждая структура, используемая для коррозии, ориентирована на удаление определенных типов пикселей, чтобы получить желаемую форму скелета отпечатка пальца.

4. **Подсчет специальных точек**

   После получения скелета отпечатка пальца производится подсчет специальных точек - конечных точек и точек ветвления. Конечные точки представляют собой пиксели, у которых только один черный сосед в окрестности 3x3, а точки ветвления - пиксели, у которых три черных соседа в той же окрестности.

### Реализация алгоритма

Алгоритм реализован на языке программирования Java с использованием следующих основных методов:

- **`binarizeImage(String pathToImage)`**: преобразует входное изображение отпечатка пальца в двоичное изображение.
- **`killNoize(int[][] image)`**: устраняет шумы на двоичном изображении.
- **`skeletonize(int[][] image)`**: выполняет скелетизацию отпечатка пальца.
- **`countSpecialPoint(int[][] image)`**: подсчитывает количество конечных и точек ветвления в скелете отпечатка пальца.

### Примечания

Для корректной работы алгоритма необходимо предоставить изображение отпечатка пальца в качестве входных данных.

Этот алгоритм является важной составляющей в области биометрической аутентификации и анализа отпечатков пальцев.
